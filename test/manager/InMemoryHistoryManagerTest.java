package manager;

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
        // Внесены изменения
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        for (int i = 0; i < 12; i++) {
            historyManager.add(new Task("History", "Description", TaskStatus.NEW));
        }

        final List<Task> history = historyManager.getHistory();
        final List<Task> expectedHistory = new ArrayList<>(history);

        assertNotNull(history);
        assertArrayEquals(expectedHistory.toArray(), history.toArray());
    }
}