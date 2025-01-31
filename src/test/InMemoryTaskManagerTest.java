package test;
import manager.TaskManager;
import manager.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    public void create() {
        manager = new InMemoryTaskManager();
    }

    @Test
    public void shouldAddAndRetrieveTaskById() {
        Task task = new Task("Task #1", "Task #1 description");
        manager.addTask(task);

        Task retrievedTask = manager.getTask(task.getId());

        assertNotNull(retrievedTask);
        assertEquals(task, retrievedTask);
    }

    @Test
    public void shouldAddAndRetrieveEpicById() {
        Epic epic = new Epic("Epic 1", "Epic Description");
        manager.addEpic(epic);

        Epic retrievedEpic = manager.getEpic(epic.getId());

        assertNotNull(retrievedEpic);
        assertEquals(epic, retrievedEpic);
    }

    @Test
    public void shouldAddAndRetrieveSubTaskById() {
        Epic epic = new Epic("Epic #1", "Epic #1 description");
        manager.addEpic(epic);

        SubTask subTask = new SubTask("Subtask #1", "Subtask #1 description", epic.getId());
        manager.addSubTask(subTask);

        SubTask retrievedSubTask = manager.getSubTask(subTask.getId());

        assertNotNull(retrievedSubTask);
        assertEquals(subTask, retrievedSubTask);
    }

    @Test
    public void shouldAddSubTaskToEpicAndUpdateEpicStatus() {
        Epic epic = new Epic("Epic #1", "Epic #1 description");
        manager.addEpic(epic);

        SubTask subTask = new SubTask("Subtask #1", "Subtask #1 description", epic.getId());
        manager.addSubTask(subTask);

        ArrayList<SubTask> subTasks = manager.getSubTaskByEpic(epic);

        assertNotNull(subTasks);
        assertEquals(1, subTasks.size());
        assertEquals(subTask, subTasks.get(0));
    }

    @Test
    public void testEpicEqualityBasedOnId() {
        Epic epic1 = new Epic("Epic #1", "Epic #1 description");
        Epic epic2 = new Epic("Epic #2", "Epic #2 description");

        epic1.setId(1);
        epic2.setId(1);

        assertEquals(epic1, epic2);
    }

    @Test
    public void shouldNotConflictBetweenGivenIdAndGeneratedId() {
        Task taskWithGivenId = new Task("Task #1", "Task #1 description", 1);
        manager.addTask(taskWithGivenId);

        Task generatedTask = new Task("Task #2", "Task #2 description");
        manager.addTask(generatedTask);

        Task retrievedByGivenId = manager.getTask(taskWithGivenId.getId());
        Task retrievedByGeneratedId = manager.getTask(generatedTask.getId());

        assertNotNull(retrievedByGivenId);
        assertNotNull(retrievedByGeneratedId);
        assertNotEquals(retrievedByGivenId, retrievedByGeneratedId);
    }

    @Test
    public void shouldNotChangeTaskDataWhenAddedToManager() {
        Task task = new Task("Task #1", "Task #1 description", 1);

        manager.addTask(task);
        Task retrievedTask = manager.getTask(task.getId());

        assertEquals(task.getName(), retrievedTask.getName());
        assertEquals(task.getDescription(), retrievedTask.getDescription());
        assertEquals(task.getStatus(), retrievedTask.getStatus());
        assertEquals(task.getId(), retrievedTask.getId());
    }

    @Test
    public void shouldPreserveTaskDataInHistoryManager() {
        Task task = new Task("Task #1", "Task #1 description", 1);

        manager.addTask(task);

        manager.getTask(task.getId());
        List<Task> history = manager.getHistory();

        assertNotNull(history);
        assertEquals(1, history.size());
        assertEquals(task, history.get(0));
        assertEquals("Task 1", history.get(0).getName());
        assertEquals("Description 1", history.get(0).getDescription());
    }
}