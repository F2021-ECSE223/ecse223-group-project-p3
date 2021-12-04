/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 38 "../../../../../../model.ump"
// line 71 "../../../../../../model.ump"
public class TOBookedItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBookedItem Attributes
  private int quantity;
  private String memberEmail;
  private TOBookableItem item;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBookedItem(int aQuantity, String aMemberEmail, TOBookableItem aItem)
  {
    quantity = aQuantity;
    memberEmail = aMemberEmail;
    item = aItem;
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

  public boolean setMemberEmail(String aMemberEmail)
  {
    boolean wasSet = false;
    memberEmail = aMemberEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setItem(TOBookableItem aItem)
  {
    boolean wasSet = false;
    item = aItem;
    wasSet = true;
    return wasSet;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public String getMemberEmail()
  {
    return memberEmail;
  }

  public TOBookableItem getItem()
  {
    return item;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "," +
            "memberEmail" + ":" + getMemberEmail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "item" + "=" + (getItem() != null ? !getItem().equals(this)  ? getItem().toString().replaceAll("  ","    ") : "this" : "null");
  }
}