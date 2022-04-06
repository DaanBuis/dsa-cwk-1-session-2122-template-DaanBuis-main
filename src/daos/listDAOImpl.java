package daos;

import app.ToDoApp;
import datastructures.ADTSingleLinkedList;
import datastructures.listNode;
import model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class listDAOImpl extends DAO{
    private ADTSingleLinkedList<Task> theTaskLL;

    public static final char DELIMITER = ',';
    public static final char EOLN='\n';
    public static final String QUOTE="\"";
    public static final String USERDIRECTORY = System.getProperty("user.dir");

    private String stripQuotes(String str) {
        return str.substring(1, str.length()-1);
    }

    public listDAOImpl(){
        this.theTaskLL = new ADTSingleLinkedList();
    }

    public listDAOImpl(ADTSingleLinkedList<Task> theTaskLL) {
        this.theTaskLL = theTaskLL;
    }

    public ADTSingleLinkedList<Task> getTheTaskLL() {
        return this.theTaskLL;
    }

    public void setTheTaskLL(ADTSingleLinkedList<Task> theTaskLL) {
        this.theTaskLL = theTaskLL;
    }

    @Override
    public void loadFromFile(String filename) {
        String transactionFile = USERDIRECTORY +"\\" + filename;

        try (BufferedReader br = new BufferedReader(new FileReader(transactionFile))) {
           String theList;
           String aDueDate;
           String aTask;
           String allocatedFirstname;
           String allocatedLastname;
           String allocatedRole;
           String theTaskComments;
           String taskTags;

            String[] temp;
            String line = br.readLine();
            while(line!=null){
                temp=line.split(Character.toString(DELIMITER));
                theList = temp[0];
                aDueDate = temp[1];
                aTask = temp[2];
                allocatedFirstname = temp[3];
                allocatedLastname = temp[4];
                allocatedRole = temp[5];
                theTaskComments = temp[6];
                taskTags = temp[7];



                Task theTask = new Task();
                theTask.setTheListType(ListType.valueOf(theList));
                theTask.setTheDueDate(this.parseDateInput(aDueDate));
                theTask.setTheTask(aTask);
                theTask.setAllocatedTeamMember(new TeamMember(allocatedFirstname, allocatedLastname, Role.valueOf(allocatedRole)));
                theTask.setTaskComments(theTaskComments);
                theTask.setTheTags(taskTags);
                this.theTaskLL.insert(theTask);
                line = br.readLine();

            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(ToDoApp.class.getName()).log(Level.INFO, null, ex);
        }
    }

    @Override
    public void writeToFile(ADTSingleLinkedList<Task> listItems, String filename) {
        File myFile;
        try {
            myFile = new File(USERDIRECTORY + "\\" + filename);
            if (myFile.exists()) {
                myFile.delete();
                System.out.println("File " + myFile.getName() + " has been deleted");
            } else if (myFile.createNewFile()) {
                System.out.println("File " + myFile.getName() + " has been created");
            }
        } catch (IOException var) {
            System.out.println("There was an error.");
            var.printStackTrace();
        }

        try {
            myFile = new File(USERDIRECTORY + "\\" + filename);
            FileWriter myWriter = new FileWriter(myFile.getName());

            for(listNode tmp = this.theTaskLL.front(); tmp != null; tmp = tmp.getNextNode()) {
                if (tmp.getNextNode() == null) {
                    myWriter.write(((Task)tmp.getNodeData()).CSVFormat());
                } else {
                    myWriter.write(((Task)tmp.getNodeData()).CSVFormat());
                }
            }
            myWriter.close();;
            System.out.println("The file has been wrote to successfully");
        } catch (IOException var2) {
            System.out.println("There was an error");
            var2.printStackTrace();
        }
    }

    @Override
    public void add(Task aTask) {
        this.theTaskLL.insert(aTask);
    }

    @Override
    public Task getTask() {

        return null;
    }

    @Override
    public Task removeTask(int pos) {
        listNode<Task> aTask = this.theTaskLL.remove(pos);
        Task newTask = new Task();
        newTask.setTheTask(((Task)aTask.getNodeData()).getTheTask());
        newTask.setTheDueDate((this).parseDateInput(((Task)aTask.getNodeData()).getTheDueDate().toString()));
        newTask.setAllocatedTeamMember(new TeamMember(((Task)aTask.getNodeData()).getAllocatedTeamMember().getFirstname(), ((Task)aTask.getNodeData()).getAllocatedTeamMember().getLastname(), Role.valueOf(((Task)aTask.getNodeData()).getAllocatedTeamMember().getRoleAsString())));
        newTask.setTaskComments(((Task)aTask.getNodeData()).getTaskComments());
        newTask.setTheTags(((Task)aTask.getNodeData()).getTheTags());
        return newTask;
    }

    @Override
    public TeamMember getTeamMember() {

        return null;
    }

    @Override
    public ADTDate parseDateInput(String aDate) {
        String delimiter = "/";
        String[] tokens = aDate.split(delimiter);
        ADTDate theDate = new ADTDate(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
        LocalDate currentDate = LocalDate.now();
        theDate.setElapsedDays(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());
        return theDate;
    }
}
