package logic;

import entities.Activity;
import entities.ActivityType;
import list.ActivityList;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;
import userinterface.comunication.ResultMessage;

public class ActivityManager {

    private static ActivityManager instance = null;

//----------------------------------------------------------------------------------------------------------------------

    private ActivityManager(){}

//----------------------------------------------------------------------------------------------------------------------

    public static ActivityManager getInstance(){
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

//----------------------------------------------------------------------------------------------------------------------

    private final ActivityList list = new ActivityList();

//----------------------------------------------------------------------------------------------------------------------

    public Activity searchActivity(String name){
        return this.list.searchByName(name);
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message createActivity(String name, String description, int duration, double cost, int capacity, ActivityType activityType){
        Message message;

        if (this.list.checkNameUsed(name)) {
            message = ErrorMessage.ACTIVITY_NAME_ALREADY_USED;
        } else if (description == null) {
            message = ErrorMessage.DESCRIPTION_NULL;
        } else if (duration <= 0) {
            message = ErrorMessage.NULL_VALUE;
        } else if (cost < 0) {
            message = ErrorMessage.COST_NEGATIVE;
        } else if (capacity < 0) {
            message = ErrorMessage.CAPACITY_NEGATIVE;
        } else if (activityType == null) {
            message = ErrorMessage.ACTIVITY_TYPE_NOT_VALID;
        } else {
            Activity newActivity = new Activity(name, description, duration, cost, capacity, activityType);
            this.list.addActivity(newActivity);
            message = ResultMessage.ACTIVITY_CREATED;

            if (!this.list.add(newActivity)) {
                message = ErrorMessage.ERROR_ADDING_ACTIVITY;
            }
        }

     return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message deleteActivity(String name){
        Message message;
        Activity selectedActivity = searchActivity(name);

        if(this.list.contains(selectedActivity)){
            message = this.list.removeActivity(selectedActivity);
        }else message = ErrorMessage.ACTIVITY_NOT_FOUND;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------
}
