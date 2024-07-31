package entities;

import list.PlanActivityManager;
import list.UserList;
import userinterface.comunication.Message;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.ResultMessage;


import java.time.*;

public class Plan extends Entity {

    private final String name;
    private final User owner;
    private final LocalDateTime dateTime;
    private final String location;
    private boolean openToChange = true;
    private final PlanActivityManager activityList = new PlanActivityManager();
    private final UserList userList = new UserList();

//----------------------------------------------------------------------------------------------------------------------

    public Plan(String name, LocalDateTime dateTime, String location, User owner) {
        this.name = name;
        this.dateTime = dateTime;
        this.location = location;
        this.owner = owner;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message removeActivity(User user, Activity activity) {
        assert  activity != null;
        Message message;

        if(this.checkUserIsOwner(user)){
            if(this.openToChange){

                message = this.activityList.removeActivity(activity);

            }else message = ErrorMessage.PLAN_NOT_OPEN;
        }else  message = ErrorMessage.USER_NOT_OWNER;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------
    public Message addActivity(User user, Activity activity) {
        assert  activity != null;
        Message message;

        if(this.checkUserIsOwner(user)){
            if(this.openToChange){

                    message = this.activityList.addActivity(activity);

            }else message = ErrorMessage.PLAN_NOT_OPEN;
        }else  message = ErrorMessage.USER_NOT_OWNER;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    private boolean planDateTimeAfterThisMoment(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        return this.getDateTime().isAfter(currentDateTime);
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message addUser(User user){
        assert user != null;
        Message message;

        if(this.planDateTimeAfterThisMoment()){
            if(!this.openToChange){
                if(this.checkCanAddUser()){
                    if(!this.checkUserIsSubscribed(user)){

                        message = this.activityList.addUserToAll(user);
                        this.userList.add(user);


                    }else message = ErrorMessage.USER_ALREADY_SUBSCRIBED_IN_PLAN;
                }else message = ErrorMessage.CANT_ADD_USER;
            }else message = ErrorMessage.PLAN_NOT_CLOSED;
        }else message = ErrorMessage.PLAN_EXPIRED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message removeUser(User user){
        assert  user != null;
        Message message;

        if(this.planDateTimeAfterThisMoment()){
            if(this.checkUserIsSubscribed(user)){

                message = this.activityList.removeUserFromAll(user);

               if(message == null){
                   this.userList.remove(user);
               }

            }else message = ErrorMessage.USER_NOT_SUBSCRIBED_IN_PLAN;
        }else message = ErrorMessage.PLAN_EXPIRED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public double totalCost(User user){
        assert user != null;
        return this.activityList.totalCost(user);
    }

//----------------------------------------------------------------------------------------------------------------------

    public int totalTime(){
        return this.activityList.totalTime();
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message closePlan(){
        Message message;

        if(this.openToChange){

            this.openToChange = false;
            message = ResultMessage.PLAN_CLOSED;

        }else message = ErrorMessage.PLAN_ALREADY_NOT_OPEN;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean checkLocationMatches(String location){
        return this.location.equalsIgnoreCase(location);
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean checkUserIsSubscribed(User user){
        return this.userList.contains(user);
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean checkUserIsOwner(User user){
        assert user != null;
        return this.owner == user;
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean checkPlanIsAfter(Plan plan){
            return this.dateTime.isAfter(plan.getDateTime());
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean checkCanAddUser(){
        return this.activityList.freePlaces() > 0;
    }


//----------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean checkNameMatches(String name) {
        return getName().equalsIgnoreCase(name);
    }

//----------------------------------------------------------------------------------------------------------------------

    public String toString(){
        return "Owner: "+ owner.getName() +" Name: " + name + " Date: " + dateTime.toString() + " Location: " + location  + " Spaces_left: " +
        this.activityList.freePlaces();
    }

//----------------------------------------------------------------------------------------------------------------------
    public LocalDateTime getDateTime(){
        return this.dateTime;
    }

//----------------------------------------------------------------------------------------------------------------------

    public String getName() {
        return this.name;
    }

//----------------------------------------------------------------------------------------------------------------------
}

