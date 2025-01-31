package test;

import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    public void shouldReturnNonNullTaskManager() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager);
    }

    @Test
    public void shouldReturnNonNullHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager);
    }

    @Test
    public void shouldReturnNewTaskManagerInstanceEachTime() {
        TaskManager firstInstance = Managers.getDefault();
        TaskManager secondInstance = Managers.getDefault();
        assertNotSame(firstInstance, secondInstance);
    }

    @Test
    public void shouldReturnNewHistoryManagerInstanceEachTime() {
        HistoryManager firstInstance = Managers.getDefaultHistory();
        HistoryManager secondInstance = Managers.getDefaultHistory();
        assertNotSame(firstInstance, secondInstance);
    }

    @Test
    public void taskManagerInstanceShouldBeFunctional() {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task("Task #1", "Task #1 description");
        taskManager.addTask(task);
        assertEquals(1, taskManager.getListOfTask().size());
        assertEquals(task, taskManager.getListOfTask().get(0));
    }

    @Test
    public void historyManagerInstanceShouldBeFunctional() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task = new Task("Task #1", "Task #1 description");
        task.setId(1);
        historyManager.add(task);
        var history = historyManager.getHistory();
        assertNotNull(history);
        assertEquals(1, history.size());
        assertEquals(task, history.get(0));
    }
}