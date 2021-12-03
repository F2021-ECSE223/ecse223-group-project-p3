/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;
import ca.mcgill.ecse.climbsafe.model.Assignment;

// line 107 "../../../../../../ClimbSafePersistence.ump"
// line 167 "../../../../../../ClimbSafePersistence.ump"
// line 1 "../../../../../../ClimbSafeStates.ump"
// line 60 "../../../../../../ClimbSafeStates.ump"
// line 70 "../../../../../../ClimbSafeStates.ump"
// line 95 "../../../../../../model.ump"
// line 176 "../../../../../../model.ump"
public class Assignment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private String authCode;
  private int startWeek;
  private int endWeek;

  //Assignment State Machines
  public enum AssignmentStatus { Assigned, Paid, Started, Finished, Cancelled }
  private AssignmentStatus assignmentStatus;

  //Assignment Associations
  private Member member;
  private Guide guide;
  private Hotel hotel;
  private ClimbSafe climbSafe;

  //Helper Variables
  private boolean canSetAuthCode;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(int aStartWeek, int aEndWeek, Member aMember, ClimbSafe aClimbSafe)
  {
    canSetAuthCode = true;
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create assignment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create assignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setAssignmentStatus(AssignmentStatus.Assigned);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetImmutable */
  public boolean setAuthCode(String aAuthCode)
  {
    boolean wasSet = false;
    if (!canSetAuthCode) { return false; }
    canSetAuthCode = false;
    authCode = aAuthCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public String getAuthCode()
  {
    return authCode;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }

  public String getAssignmentStatusFullName()
  {
    String answer = assignmentStatus.toString();
    return answer;
  }

  public AssignmentStatus getAssignmentStatus()
  {
    return assignmentStatus;
  }

  public boolean pay()
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Assigned:
        if (verifyPay(getAuthCode()))
        {
          setAssignmentStatus(AssignmentStatus.Paid);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Assigned:
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 14 "../../../../../../ClimbSafeStates.ump"
        refund(50);
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        // line 17 "../../../../../../ClimbSafeStates.ump"
        refund(10);
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startWeek(int week)
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Assigned:
        if (correctWeek(week))
        {
        // line 10 "../../../../../../ClimbSafeStates.ump"
          banMember();
          setAssignmentStatus(AssignmentStatus.Cancelled);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        if (correctWeek(week))
        {
          setAssignmentStatus(AssignmentStatus.Started);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finishTrip()
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Started:
        // line 18 "../../../../../../ClimbSafeStates.ump"
        refund(0);
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setAssignmentStatus(AssignmentStatus aAssignmentStatus)
  {
    assignmentStatus = aAssignmentStatus;

    // entry actions and do activities
    switch(assignmentStatus)
    {
      case Assigned:
        // line 7 "../../../../../../ClimbSafeStates.ump"
        assign(member);
        break;
    }
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }

  public boolean hasGuide()
  {
    boolean has = guide != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      //Unable to setMember to null, as assignment must always be associated to a member
      return wasSet;
    }
    
    Assignment existingAssignment = aNewMember.getAssignment();
    if (existingAssignment != null && !equals(existingAssignment))
    {
      //Unable to setMember, the current member already has a assignment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setAssignment(this);

    if (anOldMember != null)
    {
      anOldMember.setAssignment(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    Guide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeAssignment(this);
    }
    if (aGuide != null)
    {
      aGuide.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeAssignment(this);
    }
    if (aHotel != null)
    {
      aHotel.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      existingClimbSafe.removeAssignment(this);
    }
    climbSafe.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setAssignment(null);
    }
    if (guide != null)
    {
      Guide placeholderGuide = guide;
      this.guide = null;
      placeholderGuide.removeAssignment(this);
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeAssignment(this);
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeAssignment(this);
    }
  }

  // line 27 "../../../../../../ClimbSafeStates.ump"
   private boolean verifyPay(String authCode){
    if (authCode.isEmpty() || authCode == null ) return false;
        setAuthCode(authCode);
        return true;
  }

  // line 32 "../../../../../../ClimbSafeStates.ump"
   private boolean correctWeek(int week){
    return week == this.getStartWeek();
  }

  // line 35 "../../../../../../ClimbSafeStates.ump"
   private boolean banMember(){
    return this.getMember().banMember();
  }

  // line 38 "../../../../../../ClimbSafeStates.ump"
   private boolean refund(int percentage){
    return this.getMember().setRefund(percentage);
  }

  // line 41 "../../../../../../ClimbSafeStates.ump"
   private boolean assign(Member member){
    return this.setMember(member);
  }


  public String toString()
  {
    return super.toString() + "["+
            "authCode" + ":" + getAuthCode()+ "," +
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 110 "../../../../../../ClimbSafePersistence.ump"
  private static final long serialVersionUID = 11L ;

  
}