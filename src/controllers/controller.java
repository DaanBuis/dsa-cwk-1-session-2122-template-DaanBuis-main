package controllers;

import daos.listDAOImpl;
import helpers.InputHelper;
import model.ListType;
import model.Role;
import model.Task;
import model.TeamMember;
import view.taskListView;

public class controller {
    private final listDAOImpl toDoListDAO;
    private final listDAOImpl doingListDAO;
    private final listDAOImpl doneListDAO;
    private final taskListView tlView;
    private final InputHelper inputHelper;

    public controller() {
        toDoListDAO = new listDAOImpl();
        doingListDAO = new listDAOImpl();
        doneListDAO = new listDAOImpl();
        tlView = new taskListView();
        inputHelper = new InputHelper();
    }

    public void run(){
        this.setup();
        boolean finished = false;

        int iChoice = 0;

        do {
            this.theMenu();
            iChoice = inputHelper.readInt("Enter choice", 8,1);
            switch (iChoice) {
                case 1:
                    this.viewToDoList();
                    System.out.println();
                    break;
                case 2:
                    this.viewDoingList();
                    System.out.println();
                    break;
                case 3:
                    this.viewDoneList();
                    System.out.println();
                    break;
                case 4:
                    this.viewAllLists();
                    System.out.println();
                    break;
                case 5:
                    this.addToDoList();
                    System.out.println();
                    break;
                case 6:
                    this.moveTaskToDoingList();
                    System.out.println();
                    break;
                case 7:
                    this.moveTaskToDoneList();
                    System.out.println();
                    break;
                case 8:
                    System.out.println("Exiting Program.");
                    finished = true;
                    break;

                default: // invalid option
                    System.out.println("Oops! Something has went wrong!");
                    break;
            }
        }while(!finished);
    }

    private void setup(){
        this.toDoListDAO.loadFromFile("toDoList.txt");
        this.doingListDAO.loadFromFile("doingList.txt");
        this.doneListDAO.loadFromFile("doneList.txt");
    }

    private void theMenu() {
        System.out.println("Check Tasks");
        System.out.println("-------------------");
        System.out.println("1. View To Do List");
        System.out.println("2. View Doing List");
        System.out.println("3. View done list");
        System.out.println("4. View all tasks");
        System.out.println("5. Add a task to the To Do List");
        System.out.println("6. Move a task from the To Do List to the Doing List");
        System.out.println("7. Move a task from the Doing List to the Done List");
        System.out.println("8. Exit");
        System.out.println();
    }


    private void viewToDoList() {
        System.out.println("Viewing the To Do List");
        System.out.println("----------------------");
        this.tlView.displayListTasks(this.toDoListDAO.getTheTaskLL());
    }

    private void viewDoingList() {
        System.out.println("Viewing the Doing List");
        System.out.println("----------------------");
        this.tlView.displayListTasks(this.doingListDAO.getTheTaskLL());
    }

    private void viewDoneList() {
        System.out.println("Viewing the Done List");
        System.out.println("---------------------");
        this.tlView.displayListTasks(this.doneListDAO.getTheTaskLL());
    }

    private void viewAllLists() {
        System.out.println("All the Tasks");
        System.out.println("-------------");
        this.viewToDoList();
        this.viewDoingList();
        this.viewDoneList();
    }

    private void addToDoList() {
        System.out.println("Add a Task to the To Do List");
        System.out.println("----------------------------");
        System.out.println("5.");
        String task = this.inputHelper.readString("Enter a Task: ");
        String dueDate = this.inputHelper.readString("Enter a due date in the format - dd/mm/yyyy: ");
        String firstName = this.inputHelper.readString("Enter the Team Members First Name: ");
        String lastName = this.inputHelper.readString("Enter the Team Members Last Name: ");
        String tmRole = this.inputHelper.readString("Enter the Team Members Role, either DESIGNER, ANALYST or DEVELOPER: ");
        String taskComments = this.inputHelper.readString("Enter any Task Comments: ");
        String taskTags = this.inputHelper.readString("Enter any Task Tags in the format - 'word:word': ");
        Task newTask = new Task();
        newTask.setTheTask(task);
        newTask.setAllocatedTeamMember(new TeamMember(firstName, lastName, Role.valueOf(tmRole)));
        newTask.setTaskComments(taskComments);
        newTask.setTheTags(taskTags);
        newTask.setTheDueDate(this.toDoListDAO.parseDateInput(dueDate));
        this.toDoListDAO.add(newTask);
    }

    private void moveTaskToDoingList() {
        System.out.println("Move a task from the To Do List to the Doing List");
        System.out.println("-------------------------------------------------");
        this.tlView.displayListTasks(this.toDoListDAO.getTheTaskLL());
        int pos = this.inputHelper.readInt("Enter the index value of the task you wish to move: ");
        Task nodeSwitch = this.toDoListDAO.removeTask(pos);
        nodeSwitch.setTheListType(ListType.DOING);
        this.tlView.displayATask(nodeSwitch);
        System.out.println("Moving task to the Doing List.");
        this.doingListDAO.add(nodeSwitch);
        this.tlView.displayListTasks(this.doingListDAO.getTheTaskLL());
    }

    private void moveTaskToDoneList() {
        System.out.println("Move a task from the Doing List to the Done List");
        System.out.println("------------------------------------------------");
        this.tlView.displayListTasks(this.doingListDAO.getTheTaskLL());
        int pos = this.inputHelper.readInt("Enter the index value of the task you wish to move: ");
        Task nodeSwitch = this.doingListDAO.removeTask(pos);
        nodeSwitch.setTheListType(ListType.DONE);
        this.tlView.displayATask(nodeSwitch);
        System.out.println("Moving task to the Done List.");
        this.doneListDAO.add(nodeSwitch);
        this.tlView.displayListTasks(this.doneListDAO.getTheTaskLL());
    }

}

