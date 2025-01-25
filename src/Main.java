import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        //  Создание задач
        Task task1 = new Task("Task #1", "Task description #1", TaskStatus.NEW);
        task1 = manager.addTask(task1);
        Task task2 = new Task("Task #2", "Task description #2", TaskStatus.NEW);
        task2 = manager.addTask(task2);

        //  Создание эпиков
        Epic epic1 = new Epic("Epic #1", "Epic description #1", TaskStatus.NEW);
        epic1 = manager.addEpic(epic1);
        Epic epic2 = new Epic("Epic #2", "Epic description #2", TaskStatus.NEW);
        epic2 = manager.addEpic(epic2);

        // Создание подзадач
        SubTask subTask1 = new SubTask("SubTask #1", "SubTask description #1", TaskStatus.NEW, epic1.getId());
        subTask1 = manager.addSubTask(subTask1);
        SubTask subTask2 = new SubTask("SubTask #2", "SubTask description #2", TaskStatus.NEW, epic1.getId());
        subTask2 = manager.addSubTask(subTask2);
        SubTask subTask3 = new SubTask("SubTask #3", "SubTask description #3", TaskStatus.NEW, epic2.getId());
        subTask3 = manager.addSubTask(subTask3);

        //  Получение всех видов созданных задач
        ArrayList<Task> tasks1 = manager.getListOfTask();
        System.out.println(tasks1);

        ArrayList<Epic> epics1 = manager.getListOfEpic();
        System.out.println(epics1);

        ArrayList<SubTask> subTasks1 = manager.getListOfSubTask();
        System.out.println(subTasks1);

        //  Изменение статуса задач
        task1.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateTask(task1);
        task2.setStatus(TaskStatus.DONE);
        manager.updateTask(task2);

        //  Изменение статуса подзадач
        subTask1.setStatus(TaskStatus.DONE);
        manager.updateSubTask(subTask1);
        subTask2.setStatus(TaskStatus.DONE);
        manager.updateSubTask(subTask2);
        subTask3.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubTask(subTask3);

        //  Получение задач с изменённым статусом
        tasks1 = manager.getListOfTask();
        System.out.println(tasks1);

        //  Получение подзадач с изменённым статусом
        subTasks1 = manager.getListOfSubTask();
        System.out.println(subTasks1);

        //  Получение статуса эпика после изменения статусов подзадач
        manager.updateStatusEpic(epic1);
        manager.updateStatusEpic(epic2);

        //  Проверка изменения статуса эпиков
        epics1 = manager.getListOfEpic();
        System.out.println(epics1);

        //  Удаление задач с использованием идентификатора
        manager.deleteTaskById(1);
        manager.deleteEpicById(4);
        manager.deleteSubTaskById(5);

        //  Проверка на правильность удаления
        tasks1 = manager.getListOfTask();
        System.out.println(tasks1);
        epics1 = manager.getListOfEpic();
        System.out.println(epics1);
        subTasks1 = manager.getListOfSubTask();
        System.out.println(subTasks1);

    }
}