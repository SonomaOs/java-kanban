package data;
import org.junit.jupiter.api.Test;
import tasks.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    @Test
    void TaskInstancesEqualIfTheirIDEqualTest() {
        Task task = new Task("Task #1", "Task #1 description", TaskStatus.NEW);
        task.setId(1);
        Task task2 = new Task("Task #1", "Task #1 description", TaskStatus.NEW);
        task2.setId(1);
        assertEquals(task, task2);
        assertEquals(task.getId(), task2.getId());
    }

    @Test
    void setId() {
        Task task = new Task("fist task", "paint green button", TaskStatus.NEW);
        task.setId(148);
        assertEquals(148, task.getId(), "установка ID таксков работает не корректно");
    }
}