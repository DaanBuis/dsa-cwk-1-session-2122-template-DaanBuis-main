package model;

public class TeamMember {
    private String firstname;
    private String lastname;
    private Role tmRole;

    public TeamMember() {
        this.firstname = "";
        this.lastname = "";
        this.tmRole = Role.ANALYST;

    }

    public TeamMember(String theFirstname, String theLastname, Role theRole) {
        this.firstname = theFirstname;
        this.lastname = theLastname;
        this.tmRole = theRole;

    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Role getTmRole() {
        return this.tmRole;
    }

    public void setTmRole(Role tmRole) {
        this.tmRole = tmRole;
    }

    public String getRoleAsString(){
        return this.tmRole.toString();
    }

    public String getAllocatedTo(){
        return this.firstname + " " + this.lastname + ", " + this.tmRole.toString();
    }

    public String CSVFormat(){

        String csvStr = this.firstname + "," + this.lastname + "," + this.tmRole.toString();
        return csvStr;
    }

    @Override
    public String toString() {
        return "Team Member(Firstname ='" + this.firstname + "\'" + ", Lastname ='" + this.lastname + "\'" + ", Role ='" + this.getRoleAsString() + "')";
    }
}
