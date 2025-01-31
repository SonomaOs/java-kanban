package test;

import manager.InMemoryHistoryManager;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.TaskStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryHistoryManagerTest {

    @Test
    void getHistory() {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        Task task = new Task("History 1", "Description 1", TaskStatus.NEW);
        Task task2 = new Task("History 2", "Description 2", TaskStatus.NEW);
        Task task3 = new Task("History 3", "Description 3", TaskStatus.NEW);
        Task task4 = new Task("History 4", "Description 4", TaskStatus.NEW);
        Task task5 = new Task("History 5", "Description 5", TaskStatus.NEW);
        Task task6 = new Task("History 6", "Description 6", TaskStatus.NEW);
        Task task7 = new Task("History 7", "Description 7", TaskStatus.NEW);
        Task task8 = new Task("History 8", "Description 8", TaskStatus.NEW);
        Task task9 = new Task("History 9", "Description 9", TaskStatus.NEW);
        Task task10 = new Task("History 10", "Description 10", TaskStatus.NEW);
        Task task11 = new Task("History 11", "Description 11", TaskStatus.NEW);

        historyManager.add(task);
        historyManager.add(task2);
        historyManager.add(task3);
        historyManager.add(task4);
        historyManager.add(task5);
        historyManager.add(task6);
        historyManager.add(task7);
        historyManager.add(task8);
        historyManager.add(task9);
        historyManager.add(task10);
        historyManager.add(task11);

        final List<Task> history = historyManager.getHistory();
        final List<Task> expectedHistory = new ArrayList<>();
        expectedHistory.add(task2);
        expectedHistory.add(task3);
        expectedHistory.add(task4);
        expectedHistory.add(task5);
        expectedHistory.add(task6);
        expectedHistory.add(task7);
        expectedHistory.add(task8);
        expectedHistory.add(task9);
        expectedHistory.add(task10);
        expectedHistory.add(task11);
        assertNotNull(history);
        assertArrayEquals(expectedHistory.toArray(), history.toArray());
    }
}