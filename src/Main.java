import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        System.out.println("=== Process Scheduling Simulator ===");
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nProcess P" + (i + 1));
            System.out.print("Arrival Time: ");
            int arrival = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burst = scanner.nextInt();
            processes.add(new Process(i + 1, arrival, burst));
        }

        System.out.println("\nChoose Scheduling Algorithm:");
        System.out.println("1. First-Come-First-Serve (FCFS)");
        System.out.println("2. Shortest Job First (SJF)");
        System.out.println("3. Round Robin (RR)");
    System.out.print("Enter choice (1, 2 or 3): ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                Scheduler.runFCFS(processes);
                break;
            case 2:
                Scheduler.runSJF(processes);
                break;
            case 3:
                System.out.print("Enter Time Quantum: ");
                int tq = scanner.nextInt();
                Scheduler.runRoundRobin(processes, tq);
        break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
