package tasks;

import java.util.Objects;

public class SubTask extends Task {

    private final int epicID;
    private int subTaskId;

    public SubTask(String name, String description, int epicID) {
        super(name, description);
        this.epicID = epicID;
    }

    public SubTask(String name, String description, TaskStatus status, int epicID) {
        super(name, description, status);
        this.epicID = epicID;
    }

    public int getEpicID() {
        return epicID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubTask subTask = (SubTask) o;
        return subTaskId == subTask.subTaskId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subTaskId);
    }

    @Override
    public String toString() {
        return "tasks.SubTask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                ", epicID=" + epicID +
                '}';
    }
}
