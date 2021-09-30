package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 51 "climbSafe.ump"
public class Guide extends Account
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Guide> guidesByEmail = new HashMap<String, Guide>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Guide Attributes
  private String email;
  private String name;
  private int emergencyContact;
  private String username;

  //Guide Associations
  private Member member;
  private List<ClimbingWeek> assignedWeeks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Guide(String aPassword, String aEmail, String aName, int aEmergencyContact)
  {
    super(aPassword);
    name = aName;
    emergencyContact = aEmergencyContact;
    username = email;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    assignedWeeks = new ArrayList<ClimbingWeek>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      guidesByEmail.remove(anOldEmail);
    }
    guidesByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergencyContact(int aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static Guide getWithEmail(String aEmail)
  {
    return guidesByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getName()
  {
    return name;
  }

  public int getEmergencyContact()
  {
    return emergencyContact;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }

  public boolean hasMember()
  {
    boolean has = member != null;
    return has;
  }
  /* Code from template association_GetMany */
  public ClimbingWeek getAssignedWeek(int index)
  {
    ClimbingWeek aAssignedWeek = assignedWeeks.get(index);
    return aAssignedWeek;
  }

  public List<ClimbingWeek> getAssignedWeeks()
  {
    List<ClimbingWeek> newAssignedWeeks = Collections.unmodifiableList(assignedWeeks);
    return newAssignedWeeks;
  }

  public int numberOfAssignedWeeks()
  {
    int number = assignedWeeks.size();
    return number;
  }

  public boolean hasAssignedWeeks()
  {
    boolean has = assignedWeeks.size() > 0;
    return has;
  }

  public int indexOfAssignedWeek(ClimbingWeek aAssignedWeek)
  {
    int index = assignedWeeks.indexOf(aAssignedWeek);
    return index;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      Member existingMember = member;
      member = null;
      
      if (existingMember != null && existingMember.getGuide() != null)
      {
        existingMember.setGuide(null);
      }
      wasSet = true;
      return wasSet;
    }

    Member currentMember = getMember();
    if (currentMember != null && !currentMember.equals(aNewMember))
    {
      currentMember.setGuide(null);
    }

    member = aNewMember;
    Guide existingGuide = aNewMember.getGuide();

    if (!equals(existingGuide))
    {
      aNewMember.setGuide(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignedWeeks()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAssignedWeek(ClimbingWeek aAssignedWeek)
  {
    boolean wasAdded = false;
    if (assignedWeeks.contains(aAssignedWeek)) { return false; }
    assignedWeeks.add(aAssignedWeek);
    if (aAssignedWeek.indexOfGuide(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAssignedWeek.addGuide(this);
      if (!wasAdded)
      {
        assignedWeeks.remove(aAssignedWeek);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAssignedWeek(ClimbingWeek aAssignedWeek)
  {
    boolean wasRemoved = false;
    if (!assignedWeeks.contains(aAssignedWeek))
    {
      return wasRemoved;
    }

    int oldIndex = assignedWeeks.indexOf(aAssignedWeek);
    assignedWeeks.remove(oldIndex);
    if (aAssignedWeek.indexOfGuide(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAssignedWeek.removeGuide(this);
      if (!wasRemoved)
      {
        assignedWeeks.add(oldIndex,aAssignedWeek);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignedWeekAt(ClimbingWeek aAssignedWeek, int index)
  {  
    boolean wasAdded = false;
    if(addAssignedWeek(aAssignedWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedWeeks()) { index = numberOfAssignedWeeks() - 1; }
      assignedWeeks.remove(aAssignedWeek);
      assignedWeeks.add(index, aAssignedWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignedWeekAt(ClimbingWeek aAssignedWeek, int index)
  {
    boolean wasAdded = false;
    if(assignedWeeks.contains(aAssignedWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedWeeks()) { index = numberOfAssignedWeeks() - 1; }
      assignedWeeks.remove(aAssignedWeek);
      assignedWeeks.add(index, aAssignedWeek);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignedWeekAt(aAssignedWeek, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    guidesByEmail.remove(getEmail());
    if (member != null)
    {
      member.setGuide(null);
    }
    ArrayList<ClimbingWeek> copyOfAssignedWeeks = new ArrayList<ClimbingWeek>(assignedWeeks);
    assignedWeeks.clear();
    for(ClimbingWeek aAssignedWeek : copyOfAssignedWeeks)
    {
      aAssignedWeek.removeGuide(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "name" + ":" + getName()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "," +
            "username" + ":" + getUsername()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null");
  }
}