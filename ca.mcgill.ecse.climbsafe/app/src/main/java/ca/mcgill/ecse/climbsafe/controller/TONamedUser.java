/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 28 "../../../../../../model.ump"
// line 66 "../../../../../../model.ump"
public class TONamedUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TONamedUser Attributes
  private String email;
  private String password;
  private String name;
  private String emergencyContact;
  private boolean hotelRequired;
  private boolean guideRequired;
  private int nrWeeks;
  private String guideOrMember;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TONamedUser(String aEmail, String aPassword, String aName, String aEmergencyContact, boolean aHotelRequired, boolean aGuideRequired, int aNrWeeks, String aGuideOrMember)
  {
    email = aEmail;
    password = aPassword;
    name = aName;
    emergencyContact = aEmergencyContact;
    hotelRequired = aHotelRequired;
    guideRequired = aGuideRequired;
    nrWeeks = aNrWeeks;
    guideOrMember = aGuideOrMember;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergencyContact(String aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public boolean setHotelRequired(boolean aHotelRequired)
  {
    boolean wasSet = false;
    hotelRequired = aHotelRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideRequired(boolean aGuideRequired)
  {
    boolean wasSet = false;
    guideRequired = aGuideRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setNrWeeks(int aNrWeeks)
  {
    boolean wasSet = false;
    nrWeeks = aNrWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideOrMember(String aGuideOrMember)
  {
    boolean wasSet = false;
    guideOrMember = aGuideOrMember;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  public String getName()
  {
    return name;
  }

  public String getEmergencyContact()
  {
    return emergencyContact;
  }

  public boolean getHotelRequired()
  {
    return hotelRequired;
  }

  public boolean getGuideRequired()
  {
    return guideRequired;
  }

  public int getNrWeeks()
  {
    return nrWeeks;
  }

  public String getGuideOrMember()
  {
    return guideOrMember;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "," +
            "name" + ":" + getName()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "," +
            "hotelRequired" + ":" + getHotelRequired()+ "," +
            "guideRequired" + ":" + getGuideRequired()+ "," +
            "nrWeeks" + ":" + getNrWeeks()+ "," +
            "guideOrMember" + ":" + getGuideOrMember()+ "]";
  }
}