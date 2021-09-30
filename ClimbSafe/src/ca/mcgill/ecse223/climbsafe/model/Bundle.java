package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 78 "climbSafe.ump"
public class Bundle
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final float DISCOUNT = (float) 0.75;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bundle Attributes
  private int price;
  private float weight;
  private String name;

  //Bundle Associations
  private List<Equipment> equipment;
  private List<Member> member;
  private NMC nmc;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bundle(int aPrice, float aWeight, String aName, NMC aNmc, Equipment... allEquipment)
  {
    price = aPrice;
    weight = aWeight;
    name = aName;
    equipment = new ArrayList<Equipment>();
    boolean didAddEquipment = setEquipment(allEquipment);
    if (!didAddEquipment)
    {
      throw new RuntimeException("Unable to create Bundle, must have at least 2 equipment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    member = new ArrayList<Member>();
    boolean didAddNmc = setNmc(aNmc);
    if (!didAddNmc)
    {
      throw new RuntimeException("Unable to create bundle due to nmc. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeight(float aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
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

  public int getPrice()
  {
    return price;
  }

  public float getWeight()
  {
    return weight;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
  }
  /* Code from template association_GetMany */
  public Member getMember(int index)
  {
    Member aMember = member.get(index);
    return aMember;
  }

  public List<Member> getMember()
  {
    List<Member> newMember = Collections.unmodifiableList(member);
    return newMember;
  }

  public int numberOfMember()
  {
    int number = member.size();
    return number;
  }

  public boolean hasMember()
  {
    boolean has = member.size() > 0;
    return has;
  }

  public int indexOfMember(Member aMember)
  {
    int index = member.indexOf(aMember);
    return index;
  }
  /* Code from template association_GetOne */
  public NMC getNmc()
  {
    return nmc;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfEquipmentValid()
  {
    boolean isValid = numberOfEquipment() >= minimumNumberOfEquipment();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    equipment.add(aEquipment);
    if (aEquipment.indexOfBundle(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEquipment.addBundle(this);
      if (!wasAdded)
      {
        equipment.remove(aEquipment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (!equipment.contains(aEquipment))
    {
      return wasRemoved;
    }

    if (numberOfEquipment() <= minimumNumberOfEquipment())
    {
      return wasRemoved;
    }

    int oldIndex = equipment.indexOf(aEquipment);
    equipment.remove(oldIndex);
    if (aEquipment.indexOfBundle(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEquipment.removeBundle(this);
      if (!wasRemoved)
      {
        equipment.add(oldIndex,aEquipment);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setEquipment(Equipment... newEquipment)
  {
    boolean wasSet = false;
    ArrayList<Equipment> verifiedEquipment = new ArrayList<Equipment>();
    for (Equipment aEquipment : newEquipment)
    {
      if (verifiedEquipment.contains(aEquipment))
      {
        continue;
      }
      verifiedEquipment.add(aEquipment);
    }

    if (verifiedEquipment.size() != newEquipment.length || verifiedEquipment.size() < minimumNumberOfEquipment())
    {
      return wasSet;
    }

    ArrayList<Equipment> oldEquipment = new ArrayList<Equipment>(equipment);
    equipment.clear();
    for (Equipment aNewEquipment : verifiedEquipment)
    {
      equipment.add(aNewEquipment);
      if (oldEquipment.contains(aNewEquipment))
      {
        oldEquipment.remove(aNewEquipment);
      }
      else
      {
        aNewEquipment.addBundle(this);
      }
    }

    for (Equipment anOldEquipment : oldEquipment)
    {
      anOldEquipment.removeBundle(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMember()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addMember(Member aMember)
  {
    boolean wasAdded = false;
    if (member.contains(aMember)) { return false; }
    member.add(aMember);
    if (aMember.indexOfBundle(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMember.addBundle(this);
      if (!wasAdded)
      {
        member.remove(aMember);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeMember(Member aMember)
  {
    boolean wasRemoved = false;
    if (!member.contains(aMember))
    {
      return wasRemoved;
    }

    int oldIndex = member.indexOf(aMember);
    member.remove(oldIndex);
    if (aMember.indexOfBundle(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMember.removeBundle(this);
      if (!wasRemoved)
      {
        member.add(oldIndex,aMember);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMemberAt(Member aMember, int index)
  {  
    boolean wasAdded = false;
    if(addMember(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMember()) { index = numberOfMember() - 1; }
      member.remove(aMember);
      member.add(index, aMember);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMemberAt(Member aMember, int index)
  {
    boolean wasAdded = false;
    if(member.contains(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMember()) { index = numberOfMember() - 1; }
      member.remove(aMember);
      member.add(index, aMember);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMemberAt(aMember, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setNmc(NMC aNmc)
  {
    boolean wasSet = false;
    if (aNmc == null)
    {
      return wasSet;
    }

    NMC existingNmc = nmc;
    nmc = aNmc;
    if (existingNmc != null && !existingNmc.equals(aNmc))
    {
      existingNmc.removeBundle(this);
    }
    nmc.addBundle(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Equipment> copyOfEquipment = new ArrayList<Equipment>(equipment);
    equipment.clear();
    for(Equipment aEquipment : copyOfEquipment)
    {
      aEquipment.removeBundle(this);
    }
    ArrayList<Member> copyOfMember = new ArrayList<Member>(member);
    member.clear();
    for(Member aMember : copyOfMember)
    {
      aMember.removeBundle(this);
    }
    NMC placeholderNmc = nmc;
    this.nmc = null;
    if(placeholderNmc != null)
    {
      placeholderNmc.removeBundle(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "," +
            "weight" + ":" + getWeight()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nmc = "+(getNmc()!=null?Integer.toHexString(System.identityHashCode(getNmc())):"null");
  }
}