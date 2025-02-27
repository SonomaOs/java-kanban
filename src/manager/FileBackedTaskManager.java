package manager;

import tasks.*;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private final File file;

    public FileBackedTaskManager(File file) {
        this.file = file;
    }

    @Override
    public Task addTask(Task task) {
        super.addTask(task);
        save();
        return task;
    }

    @Override
    public SubTask addSubTask(SubTask subTask) {
        super.addSubTask(subTask);
        save();
        return subTask;
    }

    @Override
    public Epic addEpic(Epic epic) {
        super.addEpic(epic);
        save();
        return epic;
    }

    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();

        // Добавляем все задачи
        allTasks.addAll(tasks.values());

        // Добавляем все подзадачи
        allTasks.addAll(subTasks.values());

        // Добавляем все эпики
        allTasks.addAll(epics.values());

        return allTasks;
    }

    public void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
            writer.write("id,type,name,status,description,epic\n");

            for (Task task : tasks.values()) {
                writer.write(toString(task) + "\n");
            }
            for (Epic epic : epics.values()) {
                writer.write(toString(epic) + "\n");
            }
            for (SubTask subTask : subTasks.values()) {
                writer.write(toString(subTask) + "\n");
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при сохранении данных", e);
        }
    }

    private String toString(Task task) {
        int epicId = (task instanceof SubTask) ? ((SubTask) task).getEpicID() : 0; // 0, если не подзадача
        return String.join(",",
                String.valueOf(task.getId()),
                task.getType().toString(),
                task.getName(),
                task.getStatus().toString(),
                task.getDescription(),
                String.valueOf(epicId)
        );
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        FileBackedTaskManager manager = new FileBackedTaskManager(file);
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            for (String line : lines) {
                if (line.startsWith("id")) continue;
                Task task = fromString(line);
                if (task != null) {
                    if (task instanceof SubTask) {
                        manager.addSubTask((SubTask) task);
                    } else if (task instanceof Epic) {
                        manager.addEpic((Epic) task);
                    } else {
                        manager.addTask(task);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return manager;
    }

    private static Task fromString(String value) {
        String[] fields = value.split(",");
        if ((fields[0].equals("id")) && (fields.length < 6)) return null;
        try {
            int id = Integer.parseInt(fields[0].trim());
            TaskType type = TaskType.valueOf(fields[1].trim());
            String name = fields[2].trim();
            String status = fields[3].trim();
            String description = fields[4].trim();
            int epicId = fields.length > 5 && !fields[5].trim().isEmpty() ? Integer.parseInt(fields[5].trim()) : -1;
            switch (type) {
                case TASK:
                    return new Task(id, name, status, description);
                case EPIC:
                    return new Epic(id, name, status, description);
                case SUBTASK:
                    return new SubTask(id, name, status, description, epicId);
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
