package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

import java.util.List;

/**
 * 1. Initiate the assignment for all members
 * 2. Pay for a member’s trip
 * 3. Start all trips for a specific week
 * 4. Finish a member’s trip
 * 5. Cancel a member’s trip
 */
public class AssignmentController {
    
 /**
 * This function initiates the assignment for all members
 * @author Neel Faucher
 * @throws RuntimeException if member does not have assignment, with error message "Assignments could not be completed for all members"
 */
    
    public static void initiateAllAssignments() throws InvalidInputException{
        List<Guide> allGuides = ClimbSafeApplication.getClimbSafe().getGuides();
        List<Member> allMembers = ClimbSafeApplication.getClimbSafe().getMembers();

        for (Guide guide : allGuides) {
            for (Member member : allMembers) {
                if (!member.hasAssignment()) {
                    int startWeek = 1;
                    if (guide.hasAssignments() && member.isGuideRequired()) {
                        int currentStart = 1;
                        for (Assignment a : guide.getAssignments()) {
                            if (a.getEndWeek() > currentStart) {
                                currentStart = a.getEndWeek();
                            }
                        }
                        startWeek = currentStart + 1;
                    }
                    if (member.isGuideRequired()) {
                        if (ClimbSafeApplication.getClimbSafe().getNrWeeks() - startWeek +1 >= member.getNrWeeks()) {
                            int endWeek = startWeek + member.getNrWeeks()-1;
                            Assignment newA = new Assignment(startWeek, endWeek, member, ClimbSafeApplication.getClimbSafe());
                            newA.setGuide(guide);
                        }
                    } else if (!member.isGuideRequired()) {
                        int endWeek = member.getNrWeeks();
                        new Assignment(startWeek, endWeek, member, ClimbSafeApplication.getClimbSafe());
                    }
                }
            }
        }
        for (Member m : ClimbSafeApplication.getClimbSafe().getMembers()){
            if (!m.hasAssignment()){
                String error = "Assignments could not be completed for all members";
                throw new InvalidInputException(error);
            }
        }
        try {
            ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
        }catch (Exception e){
            throw new InvalidInputException(e.getMessage());
        }
    }
    
    /**
     * @author Edward Habelrih
     * @param email this is the email of the member that has finished the trip
     * @throws InvalidInputException if any error happens it will throw this exception in two cases.
     *  1. The member's email does not exist
     *  2. The authcode is invalid
     *  3. Trip has already been paid for
     *  4. Cannot pay for the trip due to a ban
     *  5. Cannot pay for a trip which ahs been cancelled
     *  6. Cannot pay for a trip which has finished
     */
    public static void payMemberTrip(String email, String authCode) throws InvalidInputException{
        Member member = (Member) User.getWithEmail(email);
        if (member == null) {
            String error = "Member with email address " + email + " does not exist";
            throw new InvalidInputException(error);
        }

        if(authCode == null || authCode.equals("")){
                String error = "Invalid authorization code";
                throw new InvalidInputException(error);
        }

        if(member.getAssignment().getAssignmentStatusFullName().equals("Paid")||member.getAssignment().getAssignmentStatusFullName().equals("Started")){
            throw new InvalidInputException("Trip has already been paid for");
        }
        if (member.getBanStatusFullName().equals("Banned")){
            throw new InvalidInputException("Cannot pay for the trip due to a ban");
        }
        if (member.getAssignment().getAssignmentStatusFullName().equals("Cancelled")){
            throw new InvalidInputException("Cannot pay for a trip which has been cancelled");
        }
        if (member.getAssignment().getAssignmentStatusFullName().equals("Finished")){
            throw new InvalidInputException("Cannot pay for a trip which has finished");
        }

        member.getAssignment().setAuthCode(authCode);
        member.getAssignment().pay();
        try {
            ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
        }catch (Exception e){

        }
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
            Boolean canStart = true;
            //Cannot start a trip which has finished
            if(a.getAssignmentStatusFullName().equals("Finished")){//throw new InvalidInputException("Cannot start a trip which has finished");
                canStart= false; }
            //Cannot start a trip which has been cancelled
            if(a.getAssignmentStatusFullName().equals("Cancelled")){//throw new InvalidInputException("Cannot start a trip which has been cancelled");
                canStart= false;}
            //Cannot start the trip due to a ban
            if(a.getMember().getBanStatusFullName().equals("Banned")){//throw new InvalidInputException("Cannot start the trip due to a ban");
                canStart= false;}
            if(canStart){
                a.startWeek(weekNumber);
            }
        }
        for (Assignment a :
                assignments) {
            Boolean canStart = true;
            //Cannot start a trip which has finished
            if(a.getAssignmentStatusFullName().equals("Finished"))throw new InvalidInputException("Cannot start a trip which has finished");
            //Cannot start a trip which has been cancelled
            if(a.getAssignmentStatusFullName().equals("Cancelled"))throw new InvalidInputException("Cannot start a trip which has been cancelled");
            //Cannot start the trip due to a ban
            if(a.getMember().getBanStatusFullName().equals("Banned"))throw new InvalidInputException("Cannot start the trip due to a ban");
        }
        try {
            ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
        }catch (Exception e){
            throw new InvalidInputException(e.getMessage());
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
//            if(memberAssignmentStatus.equals("Finished")){
//                throw new InvalidInputException("Cannot finish a trip which has been cancelled");
//            }
            member.getAssignment().finishTrip();
        }
        try {
            ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
        }catch (Exception e){
            throw new InvalidInputException(e.getMessage());
        }
    }
    /**
     * @author Abhijeet Praveen
     * @param email represents the email of the member for which we wish to cancel the trip
     * @throws InvalidInputException, the method throws a different InvalidInputException based on
     * different types of errors.
     * We throw an error if we are cancelling a trip for a banned member
     * We throw an error if no member with the given email exists
     * We throw an error if the trip has already cancelled
     * Otherwise, no errors are thrown and we just cancel the trip by calling the cancel method
     * from the Assignment class in the model
     */
    public static void cancelMemberTrip(String email) throws InvalidInputException {
        Member member = (Member) User.getWithEmail(email);
        if (member==null) {
            String error = "Member with email address " + email + " does not exist";
            throw new InvalidInputException(error);
        }
        if(member.getBanStatusFullName().equals("Banned")) {
            throw new InvalidInputException("Cannot cancel the trip due to a ban");
        }
        if(member.getAssignment().getAssignmentStatusFullName().equals("Finished")) {
            throw new InvalidInputException("Cannot cancel a trip which has finished");
        }
        member.getAssignment().cancel();
        try {
            ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
        }catch (Exception e){
            throw new InvalidInputException(e.getMessage());
        }
    }
}
