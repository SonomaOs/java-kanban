package data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Task;
import tasks.TaskStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {
    private Epic epic;

    @BeforeEach
    void createEpic() {
        epic = new Epic("Epic #1", "Epic #1 description");
    }

    @Test
    void getSubtasksIDs() {
        Assertions.assertTrue(epic.getSubtasksIDs().isEmpty());
    }

    @Test
    void getSubtasksIDs_ShouldReturnCorrectList() {
        epic.getSubtasksIDs().add(1);
        epic.getSubtasksIDs().add(2);
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        Assertions.assertEquals(ids, epic.getSubtasksIDs());
    }

    @Test
    void EpicInstancesEqualIfTheirIDEqualTest() {
        Epic epic = new Epic("Epic #1", "Epic #1 description");
        epic.setId(1);
        Epic epic2 = new Epic("Epic #1", "Epic #1 description");
        epic2.setId(1);
        assertEquals(epic, epic2);
        assertEquals(epic.getId(), epic2.getId());
    }
}