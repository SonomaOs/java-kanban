import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subTasks = new ArrayList<>();

    public Epic(String name, String description, TaskStatus status) {
        super(name, description, status);
    }

    public ArrayList<Integer> getSubtasksIDs() {
        return subTasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                ", subtasks=" + subTasks +
                '}';
    }
}
