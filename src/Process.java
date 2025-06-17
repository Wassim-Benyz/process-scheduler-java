public class Process {
    public int pid;
    public int arrivalTime;
    public int burstTime;
    public int remainingTime;
    public int waitingTime;
    public int turnaroundTime;
    public int completionTime;

    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}
