package list;

import entities.Activity;
import entities.User;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;
import userinterface.comunication.ResultMessage;


public class ActivityList extends EntityList<Activity> {

//----------------------------------------------------------------------------------------------------------------------

    public Message addUserToAll(User user){
        assert user != null;
        Message message = ResultMessage.USER_ADDED;
        boolean added = false;

        for (Activity current : this){
             added = current.addUser(user);
        }

        if(!added){
            message = ErrorMessage.USER_NOT_ADDED;
        }

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message removeUserFromAll(User user){
        assert user != null;
        Message message = ResultMessage.USER_REMOVED;
        boolean removed = false;

        for (Activity current : this){
            removed = current.removeUser(user);
        }

        if(!removed){
            message = ErrorMessage.USER_NOT_REMOVED;
        }

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean checkNameUsed(String name){
        return searchByName(name) != null;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message removeActivity(Activity activity){
        assert activity != null;
        Message message;

            if(this.remove(activity)){
                message = ResultMessage.ACTIVITY_DELETED;
            }else message = ErrorMessage.ACTIVITY_NOT_REMOVED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message addActivity(Activity activity){
        assert activity != null;
        Message message;

            if(this.add(activity)){

                message = ResultMessage.ACTIVITY_ADDED;

            }else message = ErrorMessage.ACTIVITY_NOT_ADDED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------
}

