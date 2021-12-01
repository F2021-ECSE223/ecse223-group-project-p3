/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 27 "../../../../../../model.ump"
// line 56 "../../../../../../model.ump"
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
  private String hotelRequired;
  private String guideRequired;
  private String nrWeeks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TONamedUser(String aEmail, String aPassword, String aName, String aEmergencyContact, String aHotelRequired, String aGuideRequired, String aNrWeeks)
  {
    email = aEmail;
    password = aPassword;
    name = aName;
    emergencyContact = aEmergencyContact;
    hotelRequired = aHotelRequired;
    guideRequired = aGuideRequired;
    nrWeeks = aNrWeeks;
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

  public boolean setHotelRequired(String aHotelRequired)
  {
    boolean wasSet = false;
    hotelRequired = aHotelRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideRequired(String aGuideRequired)
  {
    boolean wasSet = false;
    guideRequired = aGuideRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setNrWeeks(String aNrWeeks)
  {
    boolean wasSet = false;
    nrWeeks = aNrWeeks;
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

  public String getHotelRequired()
  {
    return hotelRequired;
  }

  public String getGuideRequired()
  {
    return guideRequired;
  }

  public String getNrWeeks()
  {
    return nrWeeks;
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
            "nrWeeks" + ":" + getNrWeeks()+ "]";
  }
}