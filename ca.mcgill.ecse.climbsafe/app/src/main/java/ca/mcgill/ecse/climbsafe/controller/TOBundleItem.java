/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 43 "../../../../../../model.ump"
// line 76 "../../../../../../model.ump"
public class TOBundleItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBundleItem Attributes
  private int quantity;

  //TOBundleItem Associations
  private TOBookableItem tOBookableItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBundleItem(int aQuantity)
  {
    quantity = aQuantity;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public int getQuantity()
  {
    return quantity;
  }
  /* Code from template association_GetOne */
  public TOBookableItem getTOBookableItem()
  {
    return tOBookableItem;
  }

  public boolean hasTOBookableItem()
  {
    boolean has = tOBookableItem != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setTOBookableItem(TOBookableItem aTOBookableItem)
  {
    boolean wasSet = false;
    TOBookableItem existingTOBookableItem = tOBookableItem;
    tOBookableItem = aTOBookableItem;
    if (existingTOBookableItem != null && !existingTOBookableItem.equals(aTOBookableItem))
    {
      existingTOBookableItem.removeTOBundleItem(this);
    }
    if (aTOBookableItem != null)
    {
      aTOBookableItem.addTOBundleItem(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (tOBookableItem != null)
    {
      TOBookableItem placeholderTOBookableItem = tOBookableItem;
      this.tOBookableItem = null;
      placeholderTOBookableItem.removeTOBundleItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tOBookableItem = "+(getTOBookableItem()!=null?Integer.toHexString(System.identityHashCode(getTOBookableItem())):"null");
  }
}