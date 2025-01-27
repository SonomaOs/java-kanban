package manager;

import tasks.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    private int currentID = 0;

    public Task addTask(Task task) {
        int newID = generateID();
        task.setId(newID);
        tasks.put(task.getId(), task);
        return task;
    }

    public SubTask addSubTask(SubTask subTask) {
        Epic epic = epics.get(subTask.getEpicID());
        if (epic == null) {
            return subTask;
        }

        int newID = generateID();
        subTask.setId(newID);
        subTasks.put(subTask.getId(), subTask);
        epic.getSubtasksIDs().add(newID);
        updateStatusEpic(epic);
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

    public void deleteAllTask() {
        tasks.clear();
    }

    public void deleteAllSubTasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasksIDs().clear();
            updateStatusEpic(epic);
        }
        subTasks.clear();
    }

    public void deleteAllEpic() {
        subTasks.clear();
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
        int epicId = subTask.getEpicID();
        updateStatusEpic(epics.get(epicId));
        subTasks.put(id, subTask);
    }

    private boolean isStatus(ArrayList<SubTask> subTasks, TaskStatus status) {
        for (SubTask subTask : subTasks) {
            if (!subTask.getStatus().equals(status)) {
                return false;
            }
        }
        return true;
    }

    private void updateStatusEpic(Epic epic) {
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

    public void updateEpic(Epic epic) {
        epic = epics.get(epic.getId());
        if (epic == null) {
            return;
        }
        epic.setName(epic.getName());
        epic.setDescription(epic.getDescription());
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteSubTaskById(Integer id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = getEpic(subTask.getEpicID());
        subTasks.remove(id);

        ArrayList<Integer> epicSubTasks = epic.getSubtasksIDs();
        epicSubTasks.remove(id);
        updateStatusEpic(epic);
    }

    public void deleteEpicById(Integer id) {
        Epic epic = getEpic(id);
        ArrayList<Integer> subTaskByEpicId = epic.getSubtasksIDs();
        for (Integer subTasksId : subTaskByEpicId) {
            subTasks.remove(subTasksId);
        }
        epic.getSubtasksIDs().clear();
        epics.remove(id);
    }

    private int generateID() {
        return ++currentID;
    }
}
