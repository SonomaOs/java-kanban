package manager;

import tasks.Task;
import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final Map<Integer, Node> historyMap = new HashMap<>();
    private Node head;
    private Node tail;

    @Override
    public void add(Task task) {
        if (task != null) {
            removeNode(historyMap.get(task.getId()));
            linkLast(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    @Override
    public void remove(int id) {
        Node node = historyMap.get(id);
        removeNode(node);  // Удаляем ноду, если она существует
    }

    public List<Node> getHistoryMap() {
        return new ArrayList<>(historyMap.values());
    }

    private List<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Node node = head;
        while (node != null) {
            tasks.add(node.task);
            node = node.next;
        }
        return tasks;
    }

    private void removeNode(Node node) {
        if (node == null) {
            return;
        }

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
            if (head != null) {
                head.prev = null;
            }
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        historyMap.remove(node.task.getId());
        node.prev = null;
        node.next = null;
    }

    private void linkLast(Task task) {
        final Node oldTail = tail;
        final Node newNode = new Node(tail, task, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        historyMap.put(task.getId(), newNode);
    }

    static class Node {
        public Task task;
        public Node prev;
        public Node next;

        public Node(Node prev, Task task, Node next) {
            this.task = task;
            this.prev = prev;
            this.next = next;
        }
    }
}
