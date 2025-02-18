package manager;
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
    void addTask() {
        Task task = new Task("Task #1", "Task #1 description");
        Task addTask = manager.addTask(task);
        assertNotNull(addTask);
        assertEquals(1, manager.getListOfTask().size());
    }

    @Test
    void addTEpic() {
        Epic epic = new Epic("Epic #1", "Epic #1 description");
        Epic addEpic = manager.addEpic(epic);
        assertNotNull(addEpic);
        assertEquals(1, manager.getListOfEpic().size());
    }

    @Test
    void addSubTask() {
        Epic epic = new Epic("Epic #1", "Epic #1 description");
        SubTask subTask = new SubTask("Subtask #1", "Subtask #1 description", epic.getId());
        SubTask addSubTask = manager.addSubTask(subTask);
        assertNotNull(addSubTask);
        assertEquals(1, manager.getListOfSubTask().size());
    }

    @Test
    void getTask() {
        Task task = manager.addTask(new Task("Task #1", "Task #1 description"));
        assertEquals(task, manager.getTask(task.getId()));
    }

    @Test
    void getEpic() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        assertEquals(epic, manager.getEpic(epic.getId()));
    }

    @Test
    void getSubTask() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        SubTask subTask = manager.addSubTask(new SubTask("Subtask #1", "Subtask #1 description", epic.getId()));
        assertEquals(subTask, manager.getSubTask(subTask.getId()));
    }

    @Test
    void deleteTask() {
        Task task = manager.addTask(new Task("Task #1", "Task #1 description"));
        manager.deleteTaskById(task.getId());
        assertNull(manager.getTask(task.getId()));
    }

    @Test
    void deleteEpic() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        manager.deleteEpicById(epic.getId());
        assertNull(manager.getEpic(epic.getId()));
    }

    @Test
    void deleteSubTask() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        SubTask subTask = manager.addSubTask(new SubTask("Subtask #1", "Subtask #1 description", epic.getId()));
        manager.deleteSubTaskById(subTask.getId());
        assertNull(manager.getSubTask(subTask.getId()));
    }

    @Test
    void updateTask() {
        Task task = manager.addTask(new Task("Task #1", "Task #1 description"));
        Task taskUpdate = new Task("Task #1 update", "Task #1 update description");
        manager.updateTask(task, taskUpdate);
        assertEquals("Task #1 update", manager.getTask(task.getId()).getName());
        assertEquals("Task #1 update description", manager.getTask(task.getId()).getDescription());
    }

    @Test
    void updateEpic() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        epic.setName("Epic #1 update");
        manager.updateEpic(epic);
        epic.setDescription("Epic #1 update description");
        assertEquals("Epic #1 update", manager.getEpic(epic.getId()).getName());
        assertEquals("Epic #1 update description", manager.getEpic(epic.getId()).getDescription());
    }

    @Test
    void updateSubTask() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        SubTask subTask = manager.addSubTask(new SubTask("Old SubTask", "Old Description", epic.getId()));
        subTask.setName("New SubTask");
        subTask.setDescription("New Description");
        manager.updateSubTask(subTask);
        assertEquals("New SubTask", subTask.getName());
        assertEquals("New Description", subTask.getDescription());
    }

    @Test
    void deleteAllTasks() {
        Task task = manager.addTask(new Task("Task #1", "Task #1 description"));
        Task task2 = manager.addTask(new Task("Task #2", "Task #2 description"));
        manager.deleteAllTask();
        assertTrue(manager.getListOfTask().isEmpty());
    }
    @Test
    void deleteAllEpics() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        Epic epic2 = manager.addEpic(new Epic("Epic #2", "Epic #2 description"));
        manager.deleteAllEpic();
        assertTrue(manager.getListOfEpic().isEmpty());
    }

    @Test
    void deleteAllSubTask() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        SubTask subTask = manager.addSubTask(new SubTask("Subtask #1", "Subtask #1 description", epic.getId()));
        SubTask subTask2 = manager.addSubTask(new SubTask("Subtask #2", "Subtask #2 description", epic.getId()));
        manager.deleteAllSubTasks();
        assertTrue(manager.getListOfSubTask().isEmpty());
    }

    @Test
    void deleteTaskById() {
        Task task = manager.addTask(new Task("Task #1", "Task #1 description"));
        manager.deleteTaskById(task.getId());
        assertTrue(manager.getListOfTask().isEmpty());
    }

    @Test
    void deleteEpicById() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        manager.deleteEpicById(epic.getId());
        assertTrue(manager.getListOfEpic().isEmpty());
    }

    @Test
    void deleteSubTaskById() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        SubTask subTask = manager.addSubTask(new SubTask("SubTask #1", "SubTask #1 description", epic.getId()));
        manager.deleteSubTaskById(subTask.getId());
        assertTrue(manager.getListOfSubTask().isEmpty());
    }

    @Test
    void getListOfTask() {
        Task task = manager.addTask(new Task("Task #1", "Task #1 description"));
        ArrayList<Task> tasks = manager.getListOfTask();
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertTrue(tasks.contains(task));
    }

    @Test
    void getListOfEpic() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        ArrayList<Epic> epics = manager.getListOfEpic();
        assertNotNull(epics);
        assertEquals(1, epics.size());
        assertTrue(epics.contains(epic));
    }

    @Test
    void getListOfSubTask() {
        Epic epic = manager.addEpic(new Epic("Epic #1", "Epic #1 description"));
        SubTask subTask = manager.addSubTask(new SubTask("SubTask #1", "SubTask #1 description", epic.getId()));
        ArrayList<SubTask> subTasks = manager.getListOfSubTask();
        assertNotNull(subTasks);
        assertEquals(1, subTasks.size());
        assertTrue(subTasks.contains(subTask));
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
        Epic epic = new Epic("Epic #1", "Epic #1 description");
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
        assertEquals("Task #1", history.get(0).getName());
        assertEquals("Task #1 description", history.get(0).getDescription());
    }
}