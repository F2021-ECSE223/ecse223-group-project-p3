package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 33 "climbSafe.ump"
public class Member extends Account
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Member> membersByEmail = new HashMap<String, Member>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private int climbStartWeek;
  private int climbEndWeek;
  private String email;
  private String name;
  private int emergencyContact;
  private boolean stayBeforeClimb;
  private boolean stayAfterClimb;
  private int lengthOfStay;
  private int totalBundleCost;
  private int totalCost;
  private String username;

  //Member Associations
  private List<ClimbingWeek> climbingWeek;
  private Guide guide;
  private List<Equipment> piecesOfEquipment;
  private List<Bundle> bundles;
  private List<Booking> bookings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aPassword, int aClimbStartWeek, int aClimbEndWeek, String aEmail, String aName, int aEmergencyContact, boolean aStayBeforeClimb, boolean aStayAfterClimb, int aLengthOfStay, int aTotalBundleCost, int aTotalCost)
  {
    super(aPassword);
    climbStartWeek = aClimbStartWeek;
    climbEndWeek = aClimbEndWeek;
    name = aName;
    emergencyContact = aEmergencyContact;
    stayBeforeClimb = aStayBeforeClimb;
    stayAfterClimb = aStayAfterClimb;
    lengthOfStay = aLengthOfStay;
    totalBundleCost = aTotalBundleCost;
    totalCost = aTotalCost;
    username = email;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    climbingWeek = new ArrayList<ClimbingWeek>();
    piecesOfEquipment = new ArrayList<Equipment>();
    bundles = new ArrayList<Bundle>();
    bookings = new ArrayList<Booking>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setClimbStartWeek(int aClimbStartWeek)
  {
    boolean wasSet = false;
    climbStartWeek = aClimbStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setClimbEndWeek(int aClimbEndWeek)
  {
    boolean wasSet = false;
    climbEndWeek = aClimbEndWeek;
    wasSet = true;
    return wasSet;
  }

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
      membersByEmail.remove(anOldEmail);
    }
    membersByEmail.put(aEmail, this);
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

  public boolean setStayBeforeClimb(boolean aStayBeforeClimb)
  {
    boolean wasSet = false;
    stayBeforeClimb = aStayBeforeClimb;
    wasSet = true;
    return wasSet;
  }

  public boolean setStayAfterClimb(boolean aStayAfterClimb)
  {
    boolean wasSet = false;
    stayAfterClimb = aStayAfterClimb;
    wasSet = true;
    return wasSet;
  }

  public boolean setLengthOfStay(int aLengthOfStay)
  {
    boolean wasSet = false;
    lengthOfStay = aLengthOfStay;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalBundleCost(int aTotalBundleCost)
  {
    boolean wasSet = false;
    totalBundleCost = aTotalBundleCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalCost(int aTotalCost)
  {
    boolean wasSet = false;
    totalCost = aTotalCost;
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

  public int getClimbStartWeek()
  {
    return climbStartWeek;
  }

  public int getClimbEndWeek()
  {
    return climbEndWeek;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static Member getWithEmail(String aEmail)
  {
    return membersByEmail.get(aEmail);
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

  public boolean getStayBeforeClimb()
  {
    return stayBeforeClimb;
  }

  public boolean getStayAfterClimb()
  {
    return stayAfterClimb;
  }

  public int getLengthOfStay()
  {
    return lengthOfStay;
  }

  /**
   * these two are derived attributes
   */
  public int getTotalBundleCost()
  {
    return totalBundleCost;
  }

  public int getTotalCost()
  {
    return totalCost;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isStayBeforeClimb()
  {
    return stayBeforeClimb;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isStayAfterClimb()
  {
    return stayAfterClimb;
  }
  /* Code from template association_GetMany */
  public ClimbingWeek getClimbingWeek(int index)
  {
    ClimbingWeek aClimbingWeek = climbingWeek.get(index);
    return aClimbingWeek;
  }

  public List<ClimbingWeek> getClimbingWeek()
  {
    List<ClimbingWeek> newClimbingWeek = Collections.unmodifiableList(climbingWeek);
    return newClimbingWeek;
  }

  public int numberOfClimbingWeek()
  {
    int number = climbingWeek.size();
    return number;
  }

  public boolean hasClimbingWeek()
  {
    boolean has = climbingWeek.size() > 0;
    return has;
  }

  public int indexOfClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    int index = climbingWeek.indexOf(aClimbingWeek);
    return index;
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
  /* Code from template association_GetMany */
  public Equipment getPiecesOfEquipment(int index)
  {
    Equipment aPiecesOfEquipment = piecesOfEquipment.get(index);
    return aPiecesOfEquipment;
  }

  public List<Equipment> getPiecesOfEquipment()
  {
    List<Equipment> newPiecesOfEquipment = Collections.unmodifiableList(piecesOfEquipment);
    return newPiecesOfEquipment;
  }

  public int numberOfPiecesOfEquipment()
  {
    int number = piecesOfEquipment.size();
    return number;
  }

  public boolean hasPiecesOfEquipment()
  {
    boolean has = piecesOfEquipment.size() > 0;
    return has;
  }

  public int indexOfPiecesOfEquipment(Equipment aPiecesOfEquipment)
  {
    int index = piecesOfEquipment.indexOf(aPiecesOfEquipment);
    return index;
  }
  /* Code from template association_GetMany */
  public Bundle getBundle(int index)
  {
    Bundle aBundle = bundles.get(index);
    return aBundle;
  }

  public List<Bundle> getBundles()
  {
    List<Bundle> newBundles = Collections.unmodifiableList(bundles);
    return newBundles;
  }

  public int numberOfBundles()
  {
    int number = bundles.size();
    return number;
  }

  public boolean hasBundles()
  {
    boolean has = bundles.size() > 0;
    return has;
  }

  public int indexOfBundle(Bundle aBundle)
  {
    int index = bundles.indexOf(aBundle);
    return index;
  }
  /* Code from template association_GetMany */
  public Booking getBooking(int index)
  {
    Booking aBooking = bookings.get(index);
    return aBooking;
  }

  public List<Booking> getBookings()
  {
    List<Booking> newBookings = Collections.unmodifiableList(bookings);
    return newBookings;
  }

  public int numberOfBookings()
  {
    int number = bookings.size();
    return number;
  }

  public boolean hasBookings()
  {
    boolean has = bookings.size() > 0;
    return has;
  }

  public int indexOfBooking(Booking aBooking)
  {
    int index = bookings.indexOf(aBooking);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbingWeek()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeek.contains(aClimbingWeek)) { return false; }
    climbingWeek.add(aClimbingWeek);
    if (aClimbingWeek.indexOfMember(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aClimbingWeek.addMember(this);
      if (!wasAdded)
      {
        climbingWeek.remove(aClimbingWeek);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasRemoved = false;
    if (!climbingWeek.contains(aClimbingWeek))
    {
      return wasRemoved;
    }

    int oldIndex = climbingWeek.indexOf(aClimbingWeek);
    climbingWeek.remove(oldIndex);
    if (aClimbingWeek.indexOfMember(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aClimbingWeek.removeMember(this);
      if (!wasRemoved)
      {
        climbingWeek.add(oldIndex,aClimbingWeek);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {  
    boolean wasAdded = false;
    if(addClimbingWeek(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeek()) { index = numberOfClimbingWeek() - 1; }
      climbingWeek.remove(aClimbingWeek);
      climbingWeek.add(index, aClimbingWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {
    boolean wasAdded = false;
    if(climbingWeek.contains(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeek()) { index = numberOfClimbingWeek() - 1; }
      climbingWeek.remove(aClimbingWeek);
      climbingWeek.add(index, aClimbingWeek);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClimbingWeekAt(aClimbingWeek, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setGuide(Guide aNewGuide)
  {
    boolean wasSet = false;
    if (aNewGuide == null)
    {
      Guide existingGuide = guide;
      guide = null;
      
      if (existingGuide != null && existingGuide.getMember() != null)
      {
        existingGuide.setMember(null);
      }
      wasSet = true;
      return wasSet;
    }

    Guide currentGuide = getGuide();
    if (currentGuide != null && !currentGuide.equals(aNewGuide))
    {
      currentGuide.setMember(null);
    }

    guide = aNewGuide;
    Member existingMember = aNewGuide.getMember();

    if (!equals(existingMember))
    {
      aNewGuide.setMember(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPiecesOfEquipment()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addPiecesOfEquipment(String aName, float aWeight, int aPrice, NMC aNmc)
  {
    return new Equipment(aName, aWeight, aPrice, this, aNmc);
  }

  public boolean addPiecesOfEquipment(Equipment aPiecesOfEquipment)
  {
    boolean wasAdded = false;
    if (piecesOfEquipment.contains(aPiecesOfEquipment)) { return false; }
    Member existingMember = aPiecesOfEquipment.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);
    if (isNewMember)
    {
      aPiecesOfEquipment.setMember(this);
    }
    else
    {
      piecesOfEquipment.add(aPiecesOfEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePiecesOfEquipment(Equipment aPiecesOfEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aPiecesOfEquipment, as it must always have a member
    if (!this.equals(aPiecesOfEquipment.getMember()))
    {
      piecesOfEquipment.remove(aPiecesOfEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPiecesOfEquipmentAt(Equipment aPiecesOfEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addPiecesOfEquipment(aPiecesOfEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPiecesOfEquipment()) { index = numberOfPiecesOfEquipment() - 1; }
      piecesOfEquipment.remove(aPiecesOfEquipment);
      piecesOfEquipment.add(index, aPiecesOfEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePiecesOfEquipmentAt(Equipment aPiecesOfEquipment, int index)
  {
    boolean wasAdded = false;
    if(piecesOfEquipment.contains(aPiecesOfEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPiecesOfEquipment()) { index = numberOfPiecesOfEquipment() - 1; }
      piecesOfEquipment.remove(aPiecesOfEquipment);
      piecesOfEquipment.add(index, aPiecesOfEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPiecesOfEquipmentAt(aPiecesOfEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBundle(Bundle aBundle)
  {
    boolean wasAdded = false;
    if (bundles.contains(aBundle)) { return false; }
    bundles.add(aBundle);
    if (aBundle.indexOfMember(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBundle.addMember(this);
      if (!wasAdded)
      {
        bundles.remove(aBundle);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBundle(Bundle aBundle)
  {
    boolean wasRemoved = false;
    if (!bundles.contains(aBundle))
    {
      return wasRemoved;
    }

    int oldIndex = bundles.indexOf(aBundle);
    bundles.remove(oldIndex);
    if (aBundle.indexOfMember(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBundle.removeMember(this);
      if (!wasRemoved)
      {
        bundles.add(oldIndex,aBundle);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleAt(Bundle aBundle, int index)
  {  
    boolean wasAdded = false;
    if(addBundle(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleAt(Bundle aBundle, int index)
  {
    boolean wasAdded = false;
    if(bundles.contains(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleAt(aBundle, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Booking addBooking(Date aStartDate, Date aEndDate, Hotel aHo)
  {
    return new Booking(aStartDate, aEndDate, this, aHo);
  }

  public boolean addBooking(Booking aBooking)
  {
    boolean wasAdded = false;
    if (bookings.contains(aBooking)) { return false; }
    Member existingMember = aBooking.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);
    if (isNewMember)
    {
      aBooking.setMember(this);
    }
    else
    {
      bookings.add(aBooking);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBooking(Booking aBooking)
  {
    boolean wasRemoved = false;
    //Unable to remove aBooking, as it must always have a member
    if (!this.equals(aBooking.getMember()))
    {
      bookings.remove(aBooking);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookingAt(Booking aBooking, int index)
  {  
    boolean wasAdded = false;
    if(addBooking(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookingAt(Booking aBooking, int index)
  {
    boolean wasAdded = false;
    if(bookings.contains(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookingAt(aBooking, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    membersByEmail.remove(getEmail());
    ArrayList<ClimbingWeek> copyOfClimbingWeek = new ArrayList<ClimbingWeek>(climbingWeek);
    climbingWeek.clear();
    for(ClimbingWeek aClimbingWeek : copyOfClimbingWeek)
    {
      aClimbingWeek.removeMember(this);
    }
    if (guide != null)
    {
      guide.setMember(null);
    }
    for(int i=piecesOfEquipment.size(); i > 0; i--)
    {
      Equipment aPiecesOfEquipment = piecesOfEquipment.get(i - 1);
      aPiecesOfEquipment.delete();
    }
    ArrayList<Bundle> copyOfBundles = new ArrayList<Bundle>(bundles);
    bundles.clear();
    for(Bundle aBundle : copyOfBundles)
    {
      aBundle.removeMember(this);
    }
    for(int i=bookings.size(); i > 0; i--)
    {
      Booking aBooking = bookings.get(i - 1);
      aBooking.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "climbStartWeek" + ":" + getClimbStartWeek()+ "," +
            "climbEndWeek" + ":" + getClimbEndWeek()+ "," +
            "email" + ":" + getEmail()+ "," +
            "name" + ":" + getName()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "," +
            "stayBeforeClimb" + ":" + getStayBeforeClimb()+ "," +
            "stayAfterClimb" + ":" + getStayAfterClimb()+ "," +
            "lengthOfStay" + ":" + getLengthOfStay()+ "," +
            "totalBundleCost" + ":" + getTotalBundleCost()+ "," +
            "totalCost" + ":" + getTotalCost()+ "," +
            "username" + ":" + getUsername()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null");
  }
}