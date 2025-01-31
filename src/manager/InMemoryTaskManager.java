package manager;

import tasks.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private int currentID = 0;

    private final HistoryManager historyManager;

    public InMemoryTaskManager() {
        this.historyManager = Managers.getDefaultHistory();
    }

    private int generateID() {
        return ++currentID;
    }

    @Override
    public Task addTask(Task task) {
        int newID = generateID();
        task.setId(newID);
        tasks.put(newID, task);
        return task;
    }

    @Override
    public SubTask addSubTask(SubTask subTask) {
        int newId = generateID();
        subTask.setId(newId);
        subTasks.put(newId, subTask);

        Epic epic = epics.get(subTask.getEpicID());
        if (epic != null) {
            epic.addSubtaskId(newId);
            updateStatusEpic(epic);
        }
        return subTask;
    }

    @Override
    public Epic addEpic(Epic epic) {
        int newID = generateID();
        epic.setId(newID);
        epics.put(newID, epic);
        return epic;
    }

    @Override
    public ArrayList<Task> getListOfTask() {
        return new ArrayList<>(this.tasks.values());
    }

    @Override
    public ArrayList<SubTask> getListOfSubTask() {
        return new ArrayList<>(this.subTasks.values());
    }

    @Override
    public ArrayList<Epic> getListOfEpic() {
        return new ArrayList<>(this.epics.values());
    }

    @Override
    public void deleteAllTask() {
        tasks.clear();
    }

    @Override
    public void deleteAllSubTasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasksIDs().clear();
            updateStatusEpic(epic);
        }
        subTasks.clear();
    }

    @Override
    public void deleteAllEpic() {
        subTasks.clear();
        epics.clear();
    }

    @Override
    public Task getTask(int id) {
        Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }

    @Override
    public SubTask getSubTask(int id) {
        SubTask subTask = subTasks.get(id);
        historyManager.add(subTask);
        return subTask;
    }

    @Override
    public Epic getEpic(int id) {
        Epic epic = epics.get(id);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public ArrayList<SubTask> getSubTaskByEpic(Epic epic) {
        ArrayList<SubTask> subTasksByEpic = new ArrayList<>();
        for (int subTaskId : epic.getSubtasksIDs()) {
            SubTask subTask = subTasks.get(subTaskId);
            if (subTask != null) {
                subTasksByEpic.add(subTask);
            }
        }
        return subTasksByEpic;
    }

    @Override
    public void updateTask(Task task, Task newTask) {
        newTask.setId(task.getId());
        tasks.put(task.getId(), newTask);
    }

    @Override
    public void updateSubTask(SubTask subTask, SubTask newSubTask) {
        newSubTask.setId(subTask.getId());
        subTasks.put(subTask.getId(), newSubTask);
        Epic epic = epics.get(newSubTask.getEpicID());
        if (epic != null) {
            deleteSubTaskById(subTask.getId());
            epic.addSubtaskId(newSubTask.getId());
            epics.put(epic.getId(), epic);
            updateStatusEpic(epic);
        }
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

    @Override
    public void updateEpic(Epic epic) {
        epic = epics.get(epic.getId());
        if (epic == null) {
            return;
        }
        epic.setName(epic.getName());
        epic.setDescription(epic.getDescription());
        updateStatusEpic(epic);
    }

    @Override
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    @Override
    public void deleteSubTaskById(Integer id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = getEpic(subTask.getEpicID());
        subTasks.remove(id);

        List<Integer> epicSubTasks = epic.getSubtasksIDs();
        epicSubTasks.remove(id);
        updateStatusEpic(epic);
    }

    @Override
    public void deleteEpicById(Integer id) {
        Epic epic = getEpic(id);
        List<Integer> subTaskByEpicId = epic.getSubtasksIDs();
        for (Integer subTasksId : subTaskByEpicId) {
            subTasks.remove(subTasksId);
        }
        epic.getSubtasksIDs().clear();
        epics.remove(id);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return (ArrayList<Task>) historyManager.getHistory();
    }
}
