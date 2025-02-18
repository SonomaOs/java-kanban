package manager;

import tasks.Task;
import java.util.List;

public interface HistoryManager {

    void add(Task task);

    // Получение истории
    List<Task> getHistory();
}
