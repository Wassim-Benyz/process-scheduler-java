# Process Scheduling Simulator

This is a command line utility written in Java for B206 Operating Systems final project Gisma University of Applied Sciences.

# Author
Name: Ouassim Benyezzar
Student ID: GH1026327

# Description
This program simulates three CPU scheduling algorithms:
- First-Come-First-Serve (FCFS)
- Shortest Job First (SJF)
- Round Robin (RR)

Users can input process arrival and burst times, select the desired scheduling algorithm, and view results including waiting time, turnaround time, and completion time for each process.

# Features
- Accurate simulation of FCFS, SJF, and Round Robin algorithms
- Command-line interface for process input and algorithm selection
- Calculates and displays:
  - Completion time
  - Waiting time
  - Turnaround time
  - Averages for waiting and turnaround time

# How to Run
1. Compile the program:
   javac src/*.java

2. Run the simulator:
   java -cp src Main

# Project Structure
- src/Process.java – Process representation
- src/Scheduler.java – Scheduling algorithm logic
- src/Main.java – CLI for user interaction

# Demo
- Video Demonstration: In repository
