package HeapDanBST;

import java.util.*;

class Task implements Comparable<Task> {
    String name;
    int priority;
    int burstTime;
    int arrivalTime;
    int remainingTime;

    public Task(String name, int priority, int burstTime, int arrivalTime) {
        this.name = name;
        this.priority = priority;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.remainingTime = burstTime;
    }

    @Override
    public int compareTo(Task otherTask) {
        if (this.priority != otherTask.priority) {
            return Integer.compare(this.priority, otherTask.priority);
        }
        return Integer.compare(this.arrivalTime, otherTask.arrivalTime);
    }
}

public class PreemptiveScheduler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalTasks = scanner.nextInt();
        scanner.nextLine();

        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < totalTasks; i++) {
            String[] details = scanner.nextLine().trim().split(" ");
            String taskName = details[0];
            int taskPriority = Integer.parseInt(details[1]);
            int burstDuration = Integer.parseInt(details[2]);
            int arrivalMoment = Integer.parseInt(details[3]);

            taskList.add(new Task(taskName, taskPriority, burstDuration, arrivalMoment));
        }

        taskList.sort(Comparator.comparingInt((Task t) -> t.arrivalTime)
                                 .thenComparingInt(t -> t.priority));

        PriorityQueue<Task> readyQueue = new PriorityQueue<>();
        int currentTime = 0;
        Task currentTask = null;

        while (!taskList.isEmpty() || !readyQueue.isEmpty() || currentTask != null) {
            Iterator<Task> iterator = taskList.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.arrivalTime == currentTime) {
                    System.out.printf("%03d add %s\n", currentTime, task.name);
                    readyQueue.offer(task);
                    iterator.remove();
                }
            }

            if (currentTask == null && !readyQueue.isEmpty() && currentTime > 0) {
                currentTask = readyQueue.poll();
                System.out.printf("%03d executing %s\n", currentTime, currentTask.name);
            }

            if (currentTask != null && !readyQueue.isEmpty()) {
                Task higherPriorityTask = readyQueue.peek();
                if (higherPriorityTask.priority < currentTask.priority) {
                    System.out.printf("%03d %s preempted\n", currentTime, currentTask.name);
                    readyQueue.add(currentTask);
                    currentTask = readyQueue.poll();
                    System.out.printf("%03d executing %s\n", currentTime, currentTask.name);
                }
            }

            if (currentTask != null) {
                currentTask.remainingTime--;
                if (currentTask.remainingTime == 0) {
                    System.out.printf("%03d %s done\n", currentTime, currentTask.name);
                    currentTask = null;
                }
            }

            currentTime++;
        }

        scanner.close();
    }
}
