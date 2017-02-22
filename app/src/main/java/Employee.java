import java.util.Date;

/**
 * Created by GuptaRa on 2/22/2017.
 */

public class Employee {
    String fName;   //first name
    String lName;   //last name
    String email;   //email address
    public boolean IsAdmin;
    public boolean AccountActive;
    public Date DateCreated;
    public Date DateModified;
    public String ModifiedBy;

    public String getEmail(){return email;}
    public String getlName(){return lName;}
    public String getfName(){return fName;}
    public boolean getIsAdmin(){return IsAdmin;}
    public boolean getAccountActive(){return AccountActive;};
    public Date getDateCreated(){return DateCreated;};
    public Date getDateModified(){return DateModified;}
    public String getModifiedBy(){return ModifiedBy;}

    //default constuctor
    public void Employee(){};

}
