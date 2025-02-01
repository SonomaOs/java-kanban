package manager;

public class Managers {

    // Добавила отступ и изменила тип возвращаемого значения
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
