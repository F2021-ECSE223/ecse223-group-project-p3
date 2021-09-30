package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 7 "climbSafe.ump"
public class NMC extends ClimbSafe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //NMC Attributes
  private int weekCostGuide;
  private Date seasonStartDate;
  private Date seasonEndDate;

  //NMC Associations
  private List<Hotel> hotels;
  private List<Bundle> bundles;
  private List<Equipment> equipments;
  private Administrator administrator;
  private List<ClimbingWeek> weeks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NMC(int aWeekCostGuide, Date aSeasonStartDate, Date aSeasonEndDate, Administrator aAdministrator)
  {
    super();
    weekCostGuide = aWeekCostGuide;
    seasonStartDate = aSeasonStartDate;
    seasonEndDate = aSeasonEndDate;
    hotels = new ArrayList<Hotel>();
    bundles = new ArrayList<Bundle>();
    equipments = new ArrayList<Equipment>();
    if (aAdministrator == null || aAdministrator.getNmc() != null)
    {
      throw new RuntimeException("Unable to create NMC due to aAdministrator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    administrator = aAdministrator;
    weeks = new ArrayList<ClimbingWeek>();
  }

  public NMC(int aWeekCostGuide, Date aSeasonStartDate, Date aSeasonEndDate, String aPasswordForAdministrator)
  {
    super();
    weekCostGuide = aWeekCostGuide;
    seasonStartDate = aSeasonStartDate;
    seasonEndDate = aSeasonEndDate;
    hotels = new ArrayList<Hotel>();
    bundles = new ArrayList<Bundle>();
    equipments = new ArrayList<Equipment>();
    administrator = new Administrator(aPasswordForAdministrator, this);
    weeks = new ArrayList<ClimbingWeek>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeekCostGuide(int aWeekCostGuide)
  {
    boolean wasSet = false;
    weekCostGuide = aWeekCostGuide;
    wasSet = true;
    return wasSet;
  }

  public boolean setSeasonStartDate(Date aSeasonStartDate)
  {
    boolean wasSet = false;
    seasonStartDate = aSeasonStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setSeasonEndDate(Date aSeasonEndDate)
  {
    boolean wasSet = false;
    seasonEndDate = aSeasonEndDate;
    wasSet = true;
    return wasSet;
  }

  public int getWeekCostGuide()
  {
    return weekCostGuide;
  }

  public Date getSeasonStartDate()
  {
    return seasonStartDate;
  }

  public Date getSeasonEndDate()
  {
    return seasonEndDate;
  }
  /* Code from template association_GetMany */
  public Hotel getHotel(int index)
  {
    Hotel aHotel = hotels.get(index);
    return aHotel;
  }

  public List<Hotel> getHotels()
  {
    List<Hotel> newHotels = Collections.unmodifiableList(hotels);
    return newHotels;
  }

  public int numberOfHotels()
  {
    int number = hotels.size();
    return number;
  }

  public boolean hasHotels()
  {
    boolean has = hotels.size() > 0;
    return has;
  }

  public int indexOfHotel(Hotel aHotel)
  {
    int index = hotels.indexOf(aHotel);
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
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipments.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipments()
  {
    List<Equipment> newEquipments = Collections.unmodifiableList(equipments);
    return newEquipments;
  }

  public int numberOfEquipments()
  {
    int number = equipments.size();
    return number;
  }

  public boolean hasEquipments()
  {
    boolean has = equipments.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipments.indexOf(aEquipment);
    return index;
  }
  /* Code from template association_GetOne */
  public Administrator getAdministrator()
  {
    return administrator;
  }
  /* Code from template association_GetMany */
  public ClimbingWeek getWeek(int index)
  {
    ClimbingWeek aWeek = weeks.get(index);
    return aWeek;
  }

  public List<ClimbingWeek> getWeeks()
  {
    List<ClimbingWeek> newWeeks = Collections.unmodifiableList(weeks);
    return newWeeks;
  }

  public int numberOfWeeks()
  {
    int number = weeks.size();
    return number;
  }

  public boolean hasWeeks()
  {
    boolean has = weeks.size() > 0;
    return has;
  }

  public int indexOfWeek(ClimbingWeek aWeek)
  {
    int index = weeks.indexOf(aWeek);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Hotel addHotel(String aName, String aAddress, int aRating)
  {
    return new Hotel(aName, aAddress, aRating, this);
  }

  public boolean addHotel(Hotel aHotel)
  {
    boolean wasAdded = false;
    if (hotels.contains(aHotel)) { return false; }
    NMC existingNmc = aHotel.getNmc();
    boolean isNewNmc = existingNmc != null && !this.equals(existingNmc);
    if (isNewNmc)
    {
      aHotel.setNmc(this);
    }
    else
    {
      hotels.add(aHotel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotel(Hotel aHotel)
  {
    boolean wasRemoved = false;
    //Unable to remove aHotel, as it must always have a nmc
    if (!this.equals(aHotel.getNmc()))
    {
      hotels.remove(aHotel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelAt(Hotel aHotel, int index)
  {  
    boolean wasAdded = false;
    if(addHotel(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelAt(Hotel aHotel, int index)
  {
    boolean wasAdded = false;
    if(hotels.contains(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelAt(aHotel, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Bundle addBundle(int aPrice, float aWeight, String aName, Equipment... allEquipment)
  {
    return new Bundle(aPrice, aWeight, aName, this, allEquipment);
  }

  public boolean addBundle(Bundle aBundle)
  {
    boolean wasAdded = false;
    if (bundles.contains(aBundle)) { return false; }
    NMC existingNmc = aBundle.getNmc();
    boolean isNewNmc = existingNmc != null && !this.equals(existingNmc);
    if (isNewNmc)
    {
      aBundle.setNmc(this);
    }
    else
    {
      bundles.add(aBundle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBundle(Bundle aBundle)
  {
    boolean wasRemoved = false;
    //Unable to remove aBundle, as it must always have a nmc
    if (!this.equals(aBundle.getNmc()))
    {
      bundles.remove(aBundle);
      wasRemoved = true;
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
  public static int minimumNumberOfEquipments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addEquipment(String aName, float aWeight, int aPrice, Member aMember)
  {
    return new Equipment(aName, aWeight, aPrice, aMember, this);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipments.contains(aEquipment)) { return false; }
    NMC existingNmc = aEquipment.getNmc();
    boolean isNewNmc = existingNmc != null && !this.equals(existingNmc);
    if (isNewNmc)
    {
      aEquipment.setNmc(this);
    }
    else
    {
      equipments.add(aEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a nmc
    if (!this.equals(aEquipment.getNmc()))
    {
      equipments.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipments.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeeks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ClimbingWeek addWeek(boolean aSafeOrNot, int aNumber)
  {
    return new ClimbingWeek(aSafeOrNot, aNumber, this);
  }

  public boolean addWeek(ClimbingWeek aWeek)
  {
    boolean wasAdded = false;
    if (weeks.contains(aWeek)) { return false; }
    NMC existingNmc = aWeek.getNmc();
    boolean isNewNmc = existingNmc != null && !this.equals(existingNmc);
    if (isNewNmc)
    {
      aWeek.setNmc(this);
    }
    else
    {
      weeks.add(aWeek);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWeek(ClimbingWeek aWeek)
  {
    boolean wasRemoved = false;
    //Unable to remove aWeek, as it must always have a nmc
    if (!this.equals(aWeek.getNmc()))
    {
      weeks.remove(aWeek);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWeekAt(ClimbingWeek aWeek, int index)
  {  
    boolean wasAdded = false;
    if(addWeek(aWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeks()) { index = numberOfWeeks() - 1; }
      weeks.remove(aWeek);
      weeks.add(index, aWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWeekAt(ClimbingWeek aWeek, int index)
  {
    boolean wasAdded = false;
    if(weeks.contains(aWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeks()) { index = numberOfWeeks() - 1; }
      weeks.remove(aWeek);
      weeks.add(index, aWeek);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWeekAt(aWeek, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=hotels.size(); i > 0; i--)
    {
      Hotel aHotel = hotels.get(i - 1);
      aHotel.delete();
    }
    for(int i=bundles.size(); i > 0; i--)
    {
      Bundle aBundle = bundles.get(i - 1);
      aBundle.delete();
    }
    for(int i=equipments.size(); i > 0; i--)
    {
      Equipment aEquipment = equipments.get(i - 1);
      aEquipment.delete();
    }
    Administrator existingAdministrator = administrator;
    administrator = null;
    if (existingAdministrator != null)
    {
      existingAdministrator.delete();
    }
    for(int i=weeks.size(); i > 0; i--)
    {
      ClimbingWeek aWeek = weeks.get(i - 1);
      aWeek.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "weekCostGuide" + ":" + getWeekCostGuide()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "seasonStartDate" + "=" + (getSeasonStartDate() != null ? !getSeasonStartDate().equals(this)  ? getSeasonStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "seasonEndDate" + "=" + (getSeasonEndDate() != null ? !getSeasonEndDate().equals(this)  ? getSeasonEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "administrator = "+(getAdministrator()!=null?Integer.toHexString(System.identityHashCode(getAdministrator())):"null");
  }
}