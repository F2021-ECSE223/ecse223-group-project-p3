/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 25 "../../../../../../model.ump"
// line 61 "../../../../../../model.ump"
public class TOClimbSafe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOClimbSafe Attributes
  private int nrWeeks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOClimbSafe(int aNrWeeks)
  {
    nrWeeks = aNrWeeks;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNrWeeks(int aNrWeeks)
  {
    boolean wasSet = false;
    nrWeeks = aNrWeeks;
    wasSet = true;
    return wasSet;
  }

  public int getNrWeeks()
  {
    return nrWeeks;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "nrWeeks" + ":" + getNrWeeks()+ "]";
  }
}