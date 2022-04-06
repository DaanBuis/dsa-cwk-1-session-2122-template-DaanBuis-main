package daos;

import datastructures.ADTSingleLinkedList;
import model.ADTDate;
import model.Task;
import model.TeamMember;

/**
 * An abstract Data Access Object class
 * which specifies the functionality required
 * to interact with a data store and implemented
 * in concrete subclasses
 */
public abstract class DAO {

    public abstract void loadFromFile(String filename);
    public abstract void writeToFile(ADTSingleLinkedList<Task> listItems, String filename);
    public abstract void add(Task aTask);
    public abstract Task getTask();
    public abstract Task removeTask(int pos);
    public abstract TeamMember getTeamMember();
    public abstract ADTDate parseDateInput(String aDate);
}