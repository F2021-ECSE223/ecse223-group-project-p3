package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;

// line 26 "climbSafe.ump"
public class Administrator extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Administrator Attributes
  private String password;
  private String username;

  //Administrator Associations
  private NMC nmc;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Administrator(String aPassword, NMC aNmc)
  {
    super(aPassword);
    resetPassword();
    resetUsername();
    if (aNmc == null || aNmc.getAdministrator() != null)
    {
      throw new RuntimeException("Unable to create Administrator due to aNmc. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    nmc = aNmc;
  }

  public Administrator(String aPassword, int aWeekCostGuideForNmc, Date aSeasonStartDateForNmc, Date aSeasonEndDateForNmc)
  {
    super(aPassword);
    resetPassword();
    resetUsername();
    nmc = new NMC(aWeekCostGuideForNmc, aSeasonStartDateForNmc, aSeasonEndDateForNmc, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetDefaulted */
  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean resetPassword()
  {
    boolean wasReset = false;
    password = getDefaultPassword();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean resetUsername()
  {
    boolean wasReset = false;
    username = getDefaultUsername();
    wasReset = true;
    return wasReset;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template attribute_GetDefaulted */
  public String getDefaultPassword()
  {
    return "admin";
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetDefaulted */
  public String getDefaultUsername()
  {
    return "admin@nmc.nt";
  }
  /* Code from template association_GetOne */
  public NMC getNmc()
  {
    return nmc;
  }

  public void delete()
  {
    NMC existingNmc = nmc;
    nmc = null;
    if (existingNmc != null)
    {
      existingNmc.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "password" + ":" + getPassword()+ "," +
            "username" + ":" + getUsername()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nmc = "+(getNmc()!=null?Integer.toHexString(System.identityHashCode(getNmc())):"null");
  }
}