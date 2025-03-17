import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    private Task head = null;
    private Task tail = null;
    private Task currentTask = null;

    public void addTaskAtEnd(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            tail = newTask;
            newTask.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }
    }

    public void addTaskAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }

    public void removeTask(int taskId) {
        if (head == null) return;
        
        Task temp = head, prev = null;
        if (temp.taskId == taskId) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            return;
        }
        
        do {
            prev = temp;
            temp = temp.next;
            if (temp.taskId == taskId) {
                prev.next = temp.next;
                if (temp == tail) tail = prev;
                break;
            }
        } while (temp != head);
    }

    public void viewCurrentTask() {
        if (currentTask == null) currentTask = head;
        if (currentTask != null) {
            System.out.println("Current Task: " + currentTask.taskName);
            currentTask = currentTask.next;
        } else {
            System.out.println("No tasks available.");
        }
    }

    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task temp = head;
        do {
            System.out.println("Task ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority + ", Due Date: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTaskByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("Task ID: " + temp.taskId + ", Name: " + temp.taskName + ", Due Date: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        
        if (!found) {
            System.out.println("No tasks found with priority " + priority);
        }
    }
}

public class TaskSchedulers{
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        Scanner scanner = new Scanner(System.in);
        
        scheduler.addTaskAtEnd(1, "Complete Assignment", 2, "2025-03-20");
        scheduler.addTaskAtEnd(2, "Prepare Presentation", 1, "2025-03-22");
        scheduler.addTaskAtEnd(3, "Attend Meeting", 3, "2025-03-25");
        
        System.out.println("All Tasks:");
        scheduler.displayTasks();
        
        System.out.println("\nViewing Tasks:");
        scheduler.viewCurrentTask();
        scheduler.viewCurrentTask();
        scheduler.viewCurrentTask();
        
        System.out.println("\nSearching Task by Priority (1):");
        scheduler.searchTaskByPriority(1);
        
        System.out.println("\nRemoving Task with ID 2 and Displaying Remaining Tasks:");
        scheduler.removeTask(2);
        scheduler.displayTasks();
        
        scanner.close();
    }
}