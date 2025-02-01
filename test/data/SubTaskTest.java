package data;

import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.SubTask;
import tasks.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubTaskTest {

    @Test
    public void testSubTaskEqualityBasedOnId() {
        Epic epic = new Epic("Epic #1", "Epic #1 description");
        epic.setId(1);

        SubTask subTask1 = new SubTask("SubTask #1", "SubTask #1 description", epic.getId());
        SubTask subTask2 = new SubTask("SubTask #2", "SubTask #2 description", epic.getId());

        subTask1.setId(1);
        subTask2.setId(1);

        assertEquals(subTask1, subTask2);
    }

    @Test
    void newSubTask() {
        SubTask subtask = new SubTask("Subtask #1", "Description 1", TaskStatus.NEW, 1);
        assertEquals("Subtask #1", subtask.getName());
        assertEquals("Description 1", subtask.getDescription());
        assertEquals(TaskStatus.NEW, subtask.getStatus());
    }
}