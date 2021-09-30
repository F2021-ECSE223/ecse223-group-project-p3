package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 96 "climbSafe.ump"
public class ClimbingWeek
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingWeek Attributes
  private boolean safeOrNot;
  private int number;

  //ClimbingWeek Associations
  private NMC nmc;
  private List<Member> members;
  private List<Guide> guides;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingWeek(boolean aSafeOrNot, int aNumber, NMC aNmc)
  {
    safeOrNot = aSafeOrNot;
    number = aNumber;
    boolean didAddNmc = setNmc(aNmc);
    if (!didAddNmc)
    {
      throw new RuntimeException("Unable to create week due to nmc. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    members = new ArrayList<Member>();
    guides = new ArrayList<Guide>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSafeOrNot(boolean aSafeOrNot)
  {
    boolean wasSet = false;
    safeOrNot = aSafeOrNot;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean getSafeOrNot()
  {
    return safeOrNot;
  }

  public int getNumber()
  {
    return number;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isSafeOrNot()
  {
    return safeOrNot;
  }
  /* Code from template association_GetOne */
  public NMC getNmc()
  {
    return nmc;
  }
  /* Code from template association_GetMany */
  public Member getMember(int index)
  {
    Member aMember = members.get(index);
    return aMember;
  }

  public List<Member> getMembers()
  {
    List<Member> newMembers = Collections.unmodifiableList(members);
    return newMembers;
  }

  public int numberOfMembers()
  {
    int number = members.size();
    return number;
  }

  public boolean hasMembers()
  {
    boolean has = members.size() > 0;
    return has;
  }

  public int indexOfMember(Member aMember)
  {
    int index = members.indexOf(aMember);
    return index;
  }
  /* Code from template association_GetMany */
  public Guide getGuide(int index)
  {
    Guide aGuide = guides.get(index);
    return aGuide;
  }

  public List<Guide> getGuides()
  {
    List<Guide> newGuides = Collections.unmodifiableList(guides);
    return newGuides;
  }

  public int numberOfGuides()
  {
    int number = guides.size();
    return number;
  }

  public boolean hasGuides()
  {
    boolean has = guides.size() > 0;
    return has;
  }

  public int indexOfGuide(Guide aGuide)
  {
    int index = guides.indexOf(aGuide);
    return index;
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
      existingNmc.removeWeek(this);
    }
    nmc.addWeek(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMembers()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addMember(Member aMember)
  {
    boolean wasAdded = false;
    if (members.contains(aMember)) { return false; }
    members.add(aMember);
    if (aMember.indexOfClimbingWeek(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMember.addClimbingWeek(this);
      if (!wasAdded)
      {
        members.remove(aMember);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeMember(Member aMember)
  {
    boolean wasRemoved = false;
    if (!members.contains(aMember))
    {
      return wasRemoved;
    }

    int oldIndex = members.indexOf(aMember);
    members.remove(oldIndex);
    if (aMember.indexOfClimbingWeek(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMember.removeClimbingWeek(this);
      if (!wasRemoved)
      {
        members.add(oldIndex,aMember);
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
      if(index > numberOfMembers()) { index = numberOfMembers() - 1; }
      members.remove(aMember);
      members.add(index, aMember);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMemberAt(Member aMember, int index)
  {
    boolean wasAdded = false;
    if(members.contains(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMembers()) { index = numberOfMembers() - 1; }
      members.remove(aMember);
      members.add(index, aMember);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMemberAt(aMember, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGuides()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addGuide(Guide aGuide)
  {
    boolean wasAdded = false;
    if (guides.contains(aGuide)) { return false; }
    guides.add(aGuide);
    if (aGuide.indexOfAssignedWeek(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aGuide.addAssignedWeek(this);
      if (!wasAdded)
      {
        guides.remove(aGuide);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeGuide(Guide aGuide)
  {
    boolean wasRemoved = false;
    if (!guides.contains(aGuide))
    {
      return wasRemoved;
    }

    int oldIndex = guides.indexOf(aGuide);
    guides.remove(oldIndex);
    if (aGuide.indexOfAssignedWeek(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aGuide.removeAssignedWeek(this);
      if (!wasRemoved)
      {
        guides.add(oldIndex,aGuide);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGuideAt(Guide aGuide, int index)
  {  
    boolean wasAdded = false;
    if(addGuide(aGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGuides()) { index = numberOfGuides() - 1; }
      guides.remove(aGuide);
      guides.add(index, aGuide);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGuideAt(Guide aGuide, int index)
  {
    boolean wasAdded = false;
    if(guides.contains(aGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGuides()) { index = numberOfGuides() - 1; }
      guides.remove(aGuide);
      guides.add(index, aGuide);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGuideAt(aGuide, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    NMC placeholderNmc = nmc;
    this.nmc = null;
    if(placeholderNmc != null)
    {
      placeholderNmc.removeWeek(this);
    }
    ArrayList<Member> copyOfMembers = new ArrayList<Member>(members);
    members.clear();
    for(Member aMember : copyOfMembers)
    {
      aMember.removeClimbingWeek(this);
    }
    ArrayList<Guide> copyOfGuides = new ArrayList<Guide>(guides);
    guides.clear();
    for(Guide aGuide : copyOfGuides)
    {
      aGuide.removeAssignedWeek(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "safeOrNot" + ":" + getSafeOrNot()+ "," +
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nmc = "+(getNmc()!=null?Integer.toHexString(System.identityHashCode(getNmc())):"null");
  }
}