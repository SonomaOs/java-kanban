package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;

import java.util.ArrayList;

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
}