package manager;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    private final ArrayList<Task> history = new ArrayList<>();
    private final int MAX_HISTORY_SIZE = 10; // Вынесла размер списка в константу

    @Override
    public void add(Task task) {
        if (task != null) {
            if (history.size() >= MAX_HISTORY_SIZE) {
                history.removeFirst();
            }
            history.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return List.copyOf(history);  // Возвращаем копию списка
    }
}
