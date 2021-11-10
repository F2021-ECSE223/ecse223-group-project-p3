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
    /**
     * @author Romen Poirier Taksev
     * @param weekNumber the week for which the assignments are to be started
     * @throws InvalidInputException
     */
    public static void startAllTrips(int weekNumber) throws InvalidInputException{
        ClimbSafe climbsafe = ClimbSafeApplication.getClimbSafe();
        List<Assignment> assignments = climbsafe.getAssignments();
        for (Assignment a :
                assignments) {
            //Cannot start a trip which has finished
            if(a.getAssignmentStatusFullName().equals("Finished")){throw new InvalidInputException("Cannot start a trip which has finished");}
            //Cannot start a trip which has been cancelled
            if(a.getAssignmentStatusFullName().equals("Cancelled")){throw new InvalidInputException("Cannot start a trip which has been cancelled");}
            //Cannot start the trip due to a ban
            if(a.getMember().getBanStatusFullName().equals("Banned")){throw new InvalidInputException("Cannot start the trip due to a ban");}
            a.startWeek(weekNumber);
        }
    }
    /**
     * @author Rooshnie Velautham
     * 
     * This function finishes the trip of that specific member by checking all the edge cases in order to 
     * do the finish trip properly.
     * 
     * @param email this is the email of the member that has finished the trip
     * @throws InvalidInputException if any error happens it will throw this exception
     */
    public static void finishMemberTrip(String email) throws InvalidInputException {
        Member member= null;
        User user =  User.getWithEmail(email);
        if(user==null) throw new InvalidInputException("Member with email address "+email+" does not exist");
        if(user instanceof Member){
            member = (Member) user;

        String memberAssignmentStatus = member.getAssignment().getAssignmentStatusFullName();
        if(member.getBanStatusFullName().equals("Banned")){
            throw new InvalidInputException("Cannot finish the trip due to a ban");
        }
        if(memberAssignmentStatus.equals("Paid") || memberAssignmentStatus.equals("Assigned")){
            throw new InvalidInputException("Cannot finish a trip which has not started");
        }
        if(memberAssignmentStatus.equals("Cancelled")){
            throw new InvalidInputException("Cannot finish a trip which has been cancelled");
        }
        member.getAssignment().finishTrip();
         }
    }
    public boolean cancelMemberTrip(Member member){
        return true;
    }
}
