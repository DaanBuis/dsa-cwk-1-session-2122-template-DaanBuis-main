package model;

public class Task {

    private ListType theList;
    private ADTDate aDueDate;
    private String aTask;
    private TeamMember allocatedToTM;
    private String theTaskComments;
    private String taskTags;

    public Task() {
        this.theList = ListType.TODO;
        this.aDueDate = new ADTDate();
        this.allocatedToTM = new TeamMember();
        this.aTask = "";
        this.theTaskComments = "";
        this.taskTags = "";
    }

    public ListType getTheListType() {
        return this.theList;
    }

    public void setTheListType(ListType theListType) {
        this.theList = theListType;
    }

    public ADTDate getTheDueDate() {
        return this.aDueDate;
    }

    public void setTheDueDate(ADTDate aDueDate) {
        this.aDueDate = aDueDate;
    }

    public String getTheTask() {
        return this.aTask;
    }

    public void setTheTask(String aTask) {
        this.aTask = aTask;
    }

    public TeamMember getAllocatedTeamMember() {
        return this.allocatedToTM;
    }

    public void setAllocatedTeamMember(TeamMember allocatedToTM) {
        this.allocatedToTM = allocatedToTM;

    }

    public String getTaskComments() {
        return this.theTaskComments;
    }

    public void setTaskComments(String theTaskComments) {
        this.theTaskComments = theTaskComments;
    }

    public String getTheTags() {
        return this.taskTags;
    }

    public void setTheTags(String taskTags) {
        this.taskTags = taskTags;
    }


    public String CSVFormat(){
        String csvStr = ListType.valueOf(this.theList.toString()) + "," + this.aDueDate.toString() + "," + this.aTask + "," + this.allocatedToTM.CSVFormat() + "," + this.theTaskComments + "," + this.taskTags;
        return csvStr;
    }

    @Override
    public String toString() {
        return "The Task(The List=" + this.theList + ", Due Date=" + this.aDueDate + ", The Task= " + this.aTask + ", Allocated To=" + this.allocatedToTM.getAllocatedTo() + ", Task Comments=" + this.theTaskComments + ", Tags =" + this.taskTags;
    }
}
