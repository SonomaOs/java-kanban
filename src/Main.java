import manager.*;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TaskStatus;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        TaskManager manager = new InMemoryTaskManager();
        HistoryManager history = new InMemoryHistoryManager();

        //  Создание задач
        Task task1 = new Task("Task #1", "Task description #1");
        task1 =manager.addTask(task1);
        Task task2 = new Task("Task #2", "Task description #2");
        task2 =manager.addTask(task2);

        //  Создание эпиков
        Epic epic1 = new Epic("Epic #1", "Epic description #1");
        epic1 =manager.addEpic(epic1);
        Epic epic2 = new Epic("Epic #2", "Epic description #2");
        epic2 =manager.addEpic(epic2);

        // Создание подзадач
        SubTask subTask1 = new SubTask("SubTask #1", "SubTask description #1", epic1.getId());
        subTask1 =manager.addSubTask(subTask1);
        SubTask subTask2 = new SubTask("SubTask #2", "SubTask description #2", epic1.getId());
        subTask2 =manager.addSubTask(subTask2);
        SubTask subTask3 = new SubTask("SubTask #3", "SubTask description #3", epic2.getId());
        subTask3 =manager.addSubTask(subTask3);

        //  Получение всех видов созданных задач
        ArrayList<Task> tasks1 = manager.getListOfTask();
        System.out.println(tasks1);
        System.out.println();

        ArrayList<Epic> epics1 = manager.getListOfEpic();
        System.out.println(epics1);
        System.out.println();

        ArrayList<SubTask> subTasks1 = manager.getListOfSubTask();
        System.out.println(subTasks1);
        System.out.println();

        //  Изменение статуса задач
        Task task1Update = new Task("Обновление задачи 1", "Дополнили задачу 1",
                TaskStatus.DONE);
        SubTask subTask1Update = new SubTask("Обновление подзадачи 1", "Дополнили подзадачу 1",
                TaskStatus.DONE, epic1.getId());
        SubTask subTask3Update = new SubTask("Обновление подзадачи 3", "Дополнили задачу 3",
                TaskStatus.DONE, epic2.getId());

//        manager.updateTask(task1, task1Update);
//        manager.updateSubTask(subTask1, subTask1Update);
//        manager.updateSubTask(subTask3, subTask3Update);

        //  Получение задач с изменённым статусом
        tasks1 = manager.getListOfTask();
        System.out.println(tasks1);
        System.out.println();

        //  Получение подзадач с изменённым статусом
        subTasks1 = manager.getListOfSubTask();
        System.out.println(subTasks1);
        System.out.println();

        //  Проверка изменения статуса эпиков
        epics1 = manager.getListOfEpic();
        System.out.println(epics1);
        System.out.println();

        //  Удаление задач с использованием идентификатора
        manager.deleteTaskById(1);
        manager.deleteEpicById(4);
        //manager.deleteSubTaskById(5);

        //  Проверка на правильность удаления
        tasks1 = manager.getListOfTask();
        System.out.println(tasks1);
        System.out.println();
        epics1 = manager.getListOfEpic();
        System.out.println(epics1);
        System.out.println();
        subTasks1 = manager.getListOfSubTask();
        System.out.println(subTasks1);
        System.out.println();
    }
}