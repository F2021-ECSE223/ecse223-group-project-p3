package ca.mcgill.ecse.climbsafe.persistence;
import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.model.*;

public class ClimbSafePersistence {
    private static String filename = "data.climbsafe";

    public static void setFilename(String filename) {
        ClimbSafePersistence.filename = filename;
    }

    public static void save() {
        PersistenceObjectStream.setFilename(filename);
        save(ClimbSafeApplication.getClimbSafe());
    }

    public static void save(ClimbSafe climbSafe) {
        PersistenceObjectStream.setFilename(filename);
        PersistenceObjectStream.serialize(climbSafe);
    }

    public static ClimbSafe load() {
        PersistenceObjectStream.setFilename(filename);
        ClimbSafe climbsafe = (ClimbSafe) PersistenceObjectStream.deserialize();
        // model cannot be loaded - create empty ClimbSafe
        if (climbsafe == null) {
            climbsafe = new ClimbSafe();
        }
        return climbsafe;
    }
}
