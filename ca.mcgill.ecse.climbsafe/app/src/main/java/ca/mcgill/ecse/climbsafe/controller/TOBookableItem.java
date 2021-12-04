/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;
import java.util.*;

// line 19 "../../../../../../model.ump"
// line 56 "../../../../../../model.ump"
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

  //TOBookableItem Associations
  private List<TOBundleItem> tOBundleItems;

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
    tOBundleItems = new ArrayList<TOBundleItem>();
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
  /* Code from template association_GetMany */
  public TOBundleItem getTOBundleItem(int index)
  {
    TOBundleItem aTOBundleItem = tOBundleItems.get(index);
    return aTOBundleItem;
  }

  public List<TOBundleItem> getTOBundleItems()
  {
    List<TOBundleItem> newTOBundleItems = Collections.unmodifiableList(tOBundleItems);
    return newTOBundleItems;
  }

  public int numberOfTOBundleItems()
  {
    int number = tOBundleItems.size();
    return number;
  }

  public boolean hasTOBundleItems()
  {
    boolean has = tOBundleItems.size() > 0;
    return has;
  }

  public int indexOfTOBundleItem(TOBundleItem aTOBundleItem)
  {
    int index = tOBundleItems.indexOf(aTOBundleItem);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTOBundleItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addTOBundleItem(TOBundleItem aTOBundleItem)
  {
    boolean wasAdded = false;
    if (tOBundleItems.contains(aTOBundleItem)) { return false; }
    TOBookableItem existingTOBookableItem = aTOBundleItem.getTOBookableItem();
    if (existingTOBookableItem == null)
    {
      aTOBundleItem.setTOBookableItem(this);
    }
    else if (!this.equals(existingTOBookableItem))
    {
      existingTOBookableItem.removeTOBundleItem(aTOBundleItem);
      addTOBundleItem(aTOBundleItem);
    }
    else
    {
      tOBundleItems.add(aTOBundleItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTOBundleItem(TOBundleItem aTOBundleItem)
  {
    boolean wasRemoved = false;
    if (tOBundleItems.contains(aTOBundleItem))
    {
      tOBundleItems.remove(aTOBundleItem);
      aTOBundleItem.setTOBookableItem(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTOBundleItemAt(TOBundleItem aTOBundleItem, int index)
  {  
    boolean wasAdded = false;
    if(addTOBundleItem(aTOBundleItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTOBundleItems()) { index = numberOfTOBundleItems() - 1; }
      tOBundleItems.remove(aTOBundleItem);
      tOBundleItems.add(index, aTOBundleItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTOBundleItemAt(TOBundleItem aTOBundleItem, int index)
  {
    boolean wasAdded = false;
    if(tOBundleItems.contains(aTOBundleItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTOBundleItems()) { index = numberOfTOBundleItems() - 1; }
      tOBundleItems.remove(aTOBundleItem);
      tOBundleItems.add(index, aTOBundleItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTOBundleItemAt(aTOBundleItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    tobookableitemsByName.remove(getName());
    while( !tOBundleItems.isEmpty() )
    {
      tOBundleItems.get(0).setTOBookableItem(null);
    }
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