import java.util.*;

public class Scheduler {

    public static void runFCFS(List<Process> processes) {
        // Sort processes by arrival time
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println("\n=== FCFS Scheduling ===");

        for (Process p : processes) {
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }

            p.waitingTime = currentTime - p.arrivalTime;
            p.completionTime = currentTime + p.burstTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.remainingTime = 0;  // ✅ mark as done
            currentTime += p.burstTime;


            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
        }

        printResults(processes, totalWaitingTime, totalTurnaroundTime);
    }


    public static void runSJF(List<Process> originalProcesses) {
    List<Process> processes = new ArrayList<>();
    for (Process p : originalProcesses) {
        processes.add(new Process(p.pid, p.arrivalTime, p.burstTime));
    }

    System.out.println("\n=== SJF Scheduling ===");

    int currentTime = 0;
    int completed = 0;
    int n = processes.size();

    List<Process> readyQueue = new ArrayList<>();

    while (completed < n) {
        for (Process p : processes) {
            if (p.arrivalTime <= currentTime && p.remainingTime > 0 && !readyQueue.contains(p)) {
                readyQueue.add(p);
            }
        }

        if (readyQueue.isEmpty()) {
            currentTime++;
            continue;
        }

        readyQueue.sort(Comparator.comparingInt(p -> p.burstTime));
        Process current = readyQueue.get(0);
        readyQueue.remove(0);

        current.waitingTime = currentTime - current.arrivalTime;
        currentTime += current.burstTime;
        current.completionTime = currentTime;
        current.turnaroundTime = current.completionTime - current.arrivalTime;
        current.remainingTime = 0;
        completed++;
    }

    int totalWaitingTime = 0;
    int totalTurnaroundTime = 0;
    for (Process p : processes) {
        totalWaitingTime += p.waitingTime;
        totalTurnaroundTime += p.turnaroundTime;
    }
    printResults(processes, totalWaitingTime, totalTurnaroundTime);
}

public static void runRoundRobin(List<Process> originalProcesses, int timeQuantum) {
    List<Process> processes = new ArrayList<>();
    for (Process p : originalProcesses) {
        processes.add(new Process(p.pid, p.arrivalTime, p.burstTime));
    }

    System.out.println("\n=== Round Robin Scheduling (Time Quantum: " + timeQuantum + ") ===");

    Queue<Process> queue = new LinkedList<>();
    int currentTime = 0;
    int completed = 0;
    int n = processes.size();

    // Sort processes by arrival time
    processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
    int index = 0;

    while (completed < n) {
        // Add all processes that have arrived by currentTime
        while (index < n && processes.get(index).arrivalTime <= currentTime) {
            queue.add(processes.get(index));
            index++;
        }

        if (queue.isEmpty()) {
            currentTime++;
            continue;
        }

        Process current = queue.poll();

        int execTime = Math.min(timeQuantum, current.remainingTime);
        current.remainingTime -= execTime;
        currentTime += execTime;

        // Add any new arrivals during this time slice
        while (index < n && processes.get(index).arrivalTime <= currentTime) {
            queue.add(processes.get(index));
            index++;
        }

        if (current.remainingTime > 0) {
            queue.add(current); // not finished, requeue
        } else {
            current.completionTime = currentTime;
            current.turnaroundTime = current.completionTime - current.arrivalTime;
            current.waitingTime = current.turnaroundTime - current.burstTime;
            completed++;
        }
    }

    int totalWaitingTime = 0;
    int totalTurnaroundTime = 0;
    for (Process p : processes) {
        totalWaitingTime += p.waitingTime;
        totalTurnaroundTime += p.turnaroundTime;
    }

    printResults(processes, totalWaitingTime, totalTurnaroundTime);
}


    private static void printResults(List<Process> processes, int totalWaiting, int totalTurnaround) {
        System.out.printf("%-5s %-12s %-10s %-10s %-15s %-15s %-15s\n", 
            "PID", "ArrivalTime", "BurstTime", "CompTime", "WaitingTime", "Turnaround", "Remaining");

        for (Process p : processes) {
            System.out.printf("%-5d %-12d %-10d %-10d %-15d %-15d %-15d\n",
                p.pid, p.arrivalTime, p.burstTime, p.completionTime, p.waitingTime, p.turnaroundTime, p.remainingTime);
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", (double) totalWaiting / processes.size());
        System.out.printf("Average Turnaround Time: %.2f\n", (double) totalTurnaround / processes.size());
    }
}
