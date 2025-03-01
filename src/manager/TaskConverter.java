package manager;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TaskType;

public class TaskConverter {
    public static String taskToString(Task task) {
        int epicId = (task.getType() == TaskType.SUBTASK) ? ((SubTask) task).getEpicID() : -1; // -1, если не подзадача
        return String.join(",",
                String.valueOf(task.getId()),
                task.getType().toString(),
                task.getName(),
                task.getStatus().toString(),
                task.getDescription(),
                epicId == -1 ? "" : String.valueOf(epicId)
        );
    }

    public static Task stringToTask(String value) {
        String[] fields = value.split(",");
        if (fields.length < 5) return null;
        try {
            int id = Integer.parseInt(fields[0].trim());
            TaskType type = TaskType.valueOf(fields[1].trim());
            String name = fields[2].trim();
            String status = fields[3].trim();
            String description = fields[4].trim();
            int epicId = fields.length > 5 && !fields[5].trim().isEmpty() ? Integer.parseInt(fields[5].trim()) : -1;
            return switch (type) {
                case EPIC -> new Epic(id, name, status, description);
                case SUBTASK -> new SubTask(id, name, status, description, epicId);
                default -> new Task(id, name, status, description);
            };
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка преобразования числа в строке: " + value, e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Некорректные данные в строке: " + value, e);
        }
    }
}
