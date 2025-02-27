import manager.*;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Создание временного файла
        File tempFile = File.createTempFile("tasks", ".csv");

        // Сохранение и загрузка пустого файла
        FileBackedTaskManager emptyManager = new FileBackedTaskManager(tempFile);
        emptyManager.save();
        FileBackedTaskManager loadedEmptyManager = FileBackedTaskManager.loadFromFile(tempFile);
        System.out.println("Empty Manager Tasks: " + loadedEmptyManager.getAllTasks());

        // Сохранение нескольких задач
        FileBackedTaskManager manager = new FileBackedTaskManager(tempFile);
        manager.addTask(new Task(1, "Task1", "NEW", "Description task1"));
        manager.addEpic(new Epic(3, "Epic1", "NEW", "Description epic1"));
        manager.addSubTask(new SubTask(2, "Subtask1", "NEW", "Description subtask1", 1));

        // Сохраняем задачи
        manager.save();

        // Загрузка нескольких задач
        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(tempFile);
        System.out.println("Loaded Manager Tasks: " + loadedManager.getListOfTask());
        System.out.println("Loaded Manager Epics: " + loadedManager.getListOfEpic());
        System.out.println("Loaded Manager Subtasks: " + loadedManager.getListOfSubTask());
    }
}