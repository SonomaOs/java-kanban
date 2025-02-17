package manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.TaskStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {

    InMemoryHistoryManager manager;
    protected Task task1;
    protected Task task2;
    protected Task task3;

    @BeforeEach
    public void beforeEach() {
        manager = new InMemoryHistoryManager();
        task1 = new Task("Задача 1", "Описание задачи 1", TaskStatus.NEW);
        task2 = new Task("Задача 2", "Описание задачи 2", TaskStatus.DONE);
        task3 = new Task("Задача 3", "Описание задачи 3", TaskStatus.IN_PROGRESS);

        task1.setId(0);
        task2.setId(1);
        task3.setId(2);
    }

    @Test
    public void shouldLastVersionTaskSaveInHistoryManager() {
        manager.add(task1);
        manager.add(task2);
        manager.add(task3);
        assertEquals(3, manager.getHistory().size());
    }

    @Test
    public void linkLastTest() {
        manager.add(task1);
        List<InMemoryHistoryManager.Node> history = manager.getHistoryMap();
        InMemoryHistoryManager.Node task1Node = history.get(task1.getId());
        Assertions.assertNull(task1Node.prev);
        Assertions.assertNull(task1Node.next);
    }

    @Test
    void testRemoveFromMiddle() {
        manager.add(task1);  // [task1]
        manager.add(task2);  // [task1, task2]
        manager.add(task3);  // [task1, task2, task3]

        List<Task> history = manager.getHistory();

        manager.remove(task2.getId());

        history = manager.getHistory();

        assertEquals(2, history.size());  // Должно быть 2 задачи после удаления
        assertTrue(history.contains(task1));  // task1 должен остаться
        assertTrue(history.contains(task3));  // task3 должен остаться
        assertFalse(history.contains(task2));  // task2 должен быть удален
    }

    @Test
    void testRemoveFirstTask() {
        manager.add(task1);
        manager.add(task2);
        manager.add(task3);

        List<Task> historyBeforeRemove = manager.getHistory();
        assertEquals(3, historyBeforeRemove.size(), "Перед удалением истории должно быть 3 задачи");
        assertTrue(historyBeforeRemove.contains(task1), "История должна содержать Task 1");
        assertTrue(historyBeforeRemove.contains(task2), "История должна содержать Task 2");
        assertTrue(historyBeforeRemove.contains(task3), "История должна содержать Task 3");

        manager.remove(task1.getId());

        List<Task> historyAfterRemove = manager.getHistory();
        assertEquals(2, historyAfterRemove.size(), "После удаления первой задачи в истории должно остаться 2 задачи");
        assertFalse(historyAfterRemove.contains(task1), "Task 1 должна быть удалена");
        assertTrue(historyAfterRemove.contains(task2), "История должна содержать Task 2");
        assertTrue(historyAfterRemove.contains(task3), "История должна содержать Task 3");
    }

    @Test
    public void testRemoveLastTask() {
        manager.add(task1);
        manager.add(task2);
        manager.add(task3);

        List<Task> historyBeforeRemove = manager.getHistory();
        System.out.println("History before remove: " + historyBeforeRemove.size()); // Диагностика
        assertEquals(3, historyBeforeRemove.size(), "Перед удалением истории должно быть 3 задачи");
        assertTrue(historyBeforeRemove.contains(task1), "История должна содержать Task 1");
        assertTrue(historyBeforeRemove.contains(task2), "История должна содержать Task 2");
        assertTrue(historyBeforeRemove.contains(task3), "История должна содержать Task 3");

        manager.remove(task3.getId());

        List<Task> historyAfterRemove = manager.getHistory();
        System.out.println("History after remove: " + historyAfterRemove.size()); // Диагностика
        assertEquals(2, historyAfterRemove.size(), "После удаления последней задачи в истории должно остаться 2 задачи");
        assertFalse(historyAfterRemove.contains(task3), "Task 3 должна быть удалена");
        assertTrue(historyAfterRemove.contains(task1), "История должна содержать Task 1");
        assertTrue(historyAfterRemove.contains(task2), "История должна содержать Task 2");
    }

    @Test
    public void testRemoveTaskFromMiddle() {
        manager.add(task1);
        manager.add(task2);
        manager.add(task3);

        List<Task> historyBeforeRemove = manager.getHistory();
        assertEquals(3, historyBeforeRemove.size(), "Перед удалением истории должно быть 3 задачи");

        manager.remove(task2.getId());

        List<Task> historyAfterRemove = manager.getHistory();
        assertEquals(2, historyAfterRemove.size(), "После удаления задачи из середины в истории должно остаться 2 задачи");
        assertTrue(historyAfterRemove.contains(task1), "История должна содержать Task 1");
        assertTrue(historyAfterRemove.contains(task3), "История должна содержать Task 3");
        assertEquals(task1, historyAfterRemove.get(0), "Task 1 должна быть первой в истории");
        assertEquals(task3, historyAfterRemove.get(1), "Task 3 должна быть второй в истории");
    }

    @Test
    public void testRemoveSingleTask() {
        manager.add(task1);
        List<Task> historyBeforeRemove = manager.getHistory();
        assertEquals(1, historyBeforeRemove.size(), "Перед удалением в истории должна быть 1 задача");
        manager.remove(task1.getId());
        List<Task> historyAfterRemove = manager.getHistory();
        assertEquals(0, historyAfterRemove.size(), "После удаления единственной задачи история должна быть пуста");
    }

    @Test
    public void testRemoveFromEmptyHistory() {
        List<Task> historyBeforeRemove = manager.getHistory();
        assertEquals(0, historyBeforeRemove.size(), "История должна быть пуста");
        manager.remove(1);
        List<Task> historyAfterRemove = manager.getHistory();
        assertEquals(0, historyAfterRemove.size(), "После попытки удалить задачу из пустой истории история должна быть пуста");
    }

    @Test
    public void addTaskTest() {
        manager.add(task1);
        manager.add(task2);
        manager.add(task3);
        List<Task> history = manager.getHistory();
        assertEquals(history.size(), 3);
        assertEquals(task1, history.get(0));
        assertEquals(task2, history.get(1));
        assertEquals(task3, history.get(2));
    }
}