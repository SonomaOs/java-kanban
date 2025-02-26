import manager.*;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TaskStatus;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();
        Task task1 = new Task("Task #1", "Task #1 description", TaskStatus.NEW);
        Task task2 = new Task("Task #2", "Task #2 description", TaskStatus.NEW);
        manager.addTask(task1);
        manager.addTask(task2);

        Epic epic1 = new Epic("Epic #1", "Epic #1 description");
        manager.addEpic(epic1);


        SubTask subtask1 = new SubTask("SubTask #1_1", "SubTask #1_1 description", TaskStatus.NEW, epic1.getId());
        SubTask subtask2 = new SubTask("SubTask #1_2", "SubTask #1_2 description", TaskStatus.NEW, epic1.getId());
        manager.addSubTask(subtask1);
        manager.addSubTask(subtask1);
        subtask1.setStatus(TaskStatus.DONE);
        subtask2.setStatus(TaskStatus.DONE);

        manager.updateSubTask(subtask1);
        manager.updateSubTask(subtask2);
        System.out.println();
        System.out.println("***** ВЫВОД ВСЕХ ЗАДАЧ *****");
        System.out.println(manager.getListOfTask());
        System.out.println(manager.getListOfEpic());
        System.out.println(manager.getListOfSubTask());

        manager.getTask(1);
        manager.getTask(2);
        manager.getTask(1);
        manager.getTask(2);
        manager.getEpic(3);
        manager.getSubTask(4);
        manager.getSubTask(5);
        manager.getSubTask(4);
        manager.getSubTask(5);
        System.out.println();
        System.out.println("***** ВЫВОД ИСТОРИИ *****");
        System.out.println(manager.getHistory());
        System.out.println();
    }
}