/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;
import java.util.*;

// line 4 "../../../../../../model.ump"
// line 13 "../../../../../../model.ump"
public class TOBookableItem
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, TOBookableItem> tobookableitemsByName = new HashMap<String, TOBookableItem>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBookableItem Attributes
  private String name;
  private int discount;
  private int weight;
  private int pricePerWeek;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBookableItem(String aName, int aDiscount, int aWeight, int aPricePerWeek)
  {
    discount = aDiscount;
    weight = aWeight;
    pricePerWeek = aPricePerWeek;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      tobookableitemsByName.remove(anOldName);
    }
    tobookableitemsByName.put(aName, this);
    return wasSet;
  }

  public boolean setDiscount(int aDiscount)
  {
    boolean wasSet = false;
    discount = aDiscount;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeight(int aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setPricePerWeek(int aPricePerWeek)
  {
    boolean wasSet = false;
    pricePerWeek = aPricePerWeek;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static TOBookableItem getWithName(String aName)
  {
    return tobookableitemsByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public int getDiscount()
  {
    return discount;
  }

  public int getWeight()
  {
    return weight;
  }

  public int getPricePerWeek()
  {
    return pricePerWeek;
  }

  public void delete()
  {
    tobookableitemsByName.remove(getName());
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "discount" + ":" + getDiscount()+ "," +
            "weight" + ":" + getWeight()+ "," +
            "pricePerWeek" + ":" + getPricePerWeek()+ "]";
  }
}