package manager;
import tasks.*;
import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    // Добавление задачи
    Task addTask(Task task);

    // Добавление подзадачи
    SubTask addSubTask(SubTask subTask);

    // Добавление эпика
    Epic addEpic(Epic epic);

    // Получение списка задач
    ArrayList<Task> getListOfTask();

    // Получение списка подзадач
    ArrayList<SubTask> getListOfSubTask();

    // Получение списка эпиков
    ArrayList<Epic> getListOfEpic();

    // Получение задачи по идентификатору
    Task getTask(int id);

    // Получение подзадачи по идентификатору
    SubTask getSubTask(int id);

    // Получение эпика по идентификатору
    Epic getEpic(int id);

    // Получение подзадач по эпику
    ArrayList<SubTask> getSubTaskByEpic(Epic epic);

    // Обновление задачи
    void updateTask(Task task, Task newTask);

    // Обновление подзадачи
    void updateSubTask(SubTask subTask);

    // Обновление эпика
    void updateEpic(Epic epic);

    // Удаление задачи по идентификатору
    void deleteTaskById(int id);

    // Удаление подзадачи по идентификатору
    void deleteSubTaskById(Integer id);

    // Удаление эпика по идентификатору
    void deleteEpicById(Integer id);

    // Удаление всех задач
    void deleteAllTask();

    // Удаление всех подзадач
    void deleteAllSubTasks();

    // Удаление всех эпиков
    void deleteAllEpic();

    // Получение истории
    List<Task> getHistory();
}
