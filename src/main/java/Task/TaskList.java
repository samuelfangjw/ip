package Task;

import Utils.Command;

import java.util.ArrayList;

import static Utils.Print.printWithIndentation;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void printTasks() {
        if (taskList.size() == 0) {
            printWithIndentation("You have not added any tasks.");
        } else {
            String[] tasksArr = new String[taskList.size()];

            for (int i = 0; i < taskList.size(); i++) {
                tasksArr[i] = (i + 1) + "." + taskList.get(i).toString();
            }

            printWithIndentation(tasksArr);
        }
    }

    public void addTask(Command command, String input) {
        String[] tokens;
        Task task;

        switch (command) {
            case TODO:
                task = new Todo(input);
                break;
            case DEADLINE:
                tokens = input.split(" /by ", 2);
                input = tokens[0];
                task = new Deadline(input, tokens[1]);
                break;
            case EVENT:
                tokens = input.split(" /at ", 2);
                input = tokens[0];
                task = new Event(input, tokens[1]);
                break;
            default:
                printWithIndentation("I do not understand.");
                return;
        }

        taskList.add(task);
        int numTasks =  taskList.size();
        String formattedTasksCount = numTasks > 1 ? String.format("%d tasks", numTasks) : "1 task";
        printWithIndentation("Got it! I've added this task:",
                "  " + task.toString(),
                "Now you have " + formattedTasksCount + " in the list.");
    }

    public void markAsDone(int idx) {
        Task task = taskList.get(idx);
        task.markAsDone();
        printWithIndentation("Good Job! I've marked this task as done!", task.toString());
    }
}
