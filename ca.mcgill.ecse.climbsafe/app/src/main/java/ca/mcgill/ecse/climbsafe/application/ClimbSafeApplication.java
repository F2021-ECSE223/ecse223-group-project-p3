/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ca.mcgill.ecse.climbsafe.application;

import java.sql.Date;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import ca.mcgill.ecse.climbsafe.view.ClimbSafePage;

public class ClimbSafeApplication {
  private static ClimbSafe climbSafe;

  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    System.out.println(new ClimbSafeApplication().getGreeting());
    start();
  }

  public static ClimbSafe getClimbSafe() {
    if (climbSafe == null) {
      // these attributes are default, you should set them later with the setters
      climbSafe = ClimbSafePersistence.load();
    }
    return climbSafe;
  }
}
