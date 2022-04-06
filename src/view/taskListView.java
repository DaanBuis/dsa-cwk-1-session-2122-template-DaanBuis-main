package view;

import datastructures.ADTSingleLinkedList;
import datastructures.listNode;
import model.Task;

public class taskListView {
    public void displayATask(Task aTask){

        System.out.println(String.format("Task: %s",aTask.getTheTask()));
        System.out.println(String.format("Allocated To: %s", aTask.getAllocatedTeamMember()));
        System.out.println(String.format("Due Date: %02d/%02d/%02d",aTask.getTheDueDate().getDay(),aTask.getTheDueDate().getMonth(),aTask.getTheDueDate().getYear()));
    }

    public void displayATask(listNode<Task> aTask){

        System.out.println(String.format("Task: %s",aTask.getNodeData().getTheTask()));
        System.out.println(String.format("Allocated To: %s", aTask.getNodeData().getAllocatedTeamMember()));
        System.out.println(String.format("Due Date: %02d/%02d/%02d",aTask.getNodeData().getTheDueDate().getDay(),aTask.getNodeData().getTheDueDate().getMonth(),aTask.getNodeData().getTheDueDate().getYear()));
    }


    public void displayListTasks(ADTSingleLinkedList<Task> listTasks){

        System.out.println(String.format("------------------------------------------------------------------------------------------------"));
        System.out.println(String.format("| %3s | %-45s | %-25s | %-10s |","No.","Task","Allocated To","Due Date"));
        System.out.println(String.format("------------------------------------------------------------------------------------------------"));
        int count = 0;
        listNode<Task> tmp = listTasks.front();
        while (tmp != null) {
            count++;
            System.out.println(String.format("| %3s | %-45s | %-25s | %-10s |",count,tmp.getNodeData().getTheTask(),tmp.getNodeData().getAllocatedTeamMember().getAllocatedTo(),tmp.getNodeData().getTheDueDate().toString()));
            tmp = tmp.getNextNode();
        }
        System.out.println("------------------------------------------------------------------------------------------------");
        }

    }


