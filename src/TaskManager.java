import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, SubTask> subTasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    static int currentID = 0;

    public Task addTask(Task task) {
        int newID = generateID();
        task.setId(newID);
        tasks.put(task.getId(), task);
        return task;
    }

    public SubTask addSubTask(SubTask subTask) {
        if (subTask.getEpicID() == 0) {
            return subTask;
        }

        if (!epics.containsKey(subTask.getEpicID())) {
            return subTask;
        }

        int newID = generateID();
        subTask.setId(newID);
        subTasks.put(subTask.getId(), subTask);

        Epic epic = epics.get(subTask.getEpicID());
        epic.getSubtasksIDs().add(newID);
        return subTask;
    }

    public Epic addEpic(Epic epic) {
        int newID = generateID();
        epic.setId(newID);
        epics.put(epic.getId(), epic);
        return epic;
    }

    public ArrayList<Task> getListOfTask() {
        return new ArrayList<>(this.tasks.values());
    }

    public ArrayList<SubTask> getListOfSubTask() {
        return new ArrayList<>(this.subTasks.values());
    }

    public ArrayList<Epic> getListOfEpic() {
        return new ArrayList<>(this.epics.values());
    }

    public void deletingAllTask() {
        tasks.clear();
    }

    public void deletingAllSubTasks() {
        subTasks.clear();
    }

    public void deletingAllEpic() {
        epics.clear();
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public SubTask getSubTask(int id) {
        return subTasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public ArrayList<SubTask> getSubTaskByEpic(Epic epic) {
        ArrayList<SubTask> subTasksByEpic = new ArrayList<>();
        ArrayList<Integer> ids = epic.getSubtasksIDs();
        for (int subTask : ids) {
            subTasksByEpic.add(getSubTask(subTask));
        }
        return subTasksByEpic;
    }

    public void updateTask(Task task) {
        int id = task.getId();
        tasks.put(id, task);
    }

    public void updateSubTask(SubTask subTask) {
        int id = subTask.getId();
        subTasks.put(id, subTask);
    }

    public boolean isStatus(ArrayList<SubTask> subTasks, TaskStatus status) {
        for (SubTask subTask : subTasks) {
            if (!subTask.getStatus().equals(status)) {
                return false;
            }
        }
        return true;
    }

    public void updateStatusEpic(Epic epic) {
        TaskStatus status;
        ArrayList<SubTask> allSubTask = getSubTaskByEpic(epic);
        if (allSubTask.isEmpty() || isStatus(allSubTask, TaskStatus.NEW)) {
            status = TaskStatus.NEW;
        } else if (isStatus(allSubTask, TaskStatus.DONE)) {
            status = TaskStatus.DONE;
        } else {
            status = TaskStatus.IN_PROGRESS;
        }
        epic.setStatus(status);
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteSubTaskById(int id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = getEpic(subTask.getEpicID());
        subTasks.remove(id);

        ArrayList<Integer> epicSubTasks = epic.getSubtasksIDs();
        for (int i = 0; i < epicSubTasks.size(); i++) {
            if (epicSubTasks.get(i) == id) {
                epicSubTasks.remove(i);
                break;
            }
        }
    }





    public void deleteEpicById(int id) {
        epics.remove(id);
    }

    public int generateID() {
        return ++currentID;
    }
}
