package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.model.*;

import java.util.List;

/**
 * 1. Initiate the assignment for all members
 * 2. Pay for a member’s trip
 * 3. Start all trips for a specific week
 * 4. Finish a member’s trip
 * 5. Cancel a member’s trip
 */
public class AssignmentController {
    public boolean initiateAllAssignments(List<Member> allMembers){
        return true;
    }
    public boolean payMemberTrip(Member payer, String authCode){
        return true;
    }
    public static void startAllTrips(int weekNumber) throws InvalidInputException {}
    public static void finishMemberTrip(String email) throws InvalidInputException {}
    public boolean cancelMemberTrip(Member member){
        return true;
    }
}
