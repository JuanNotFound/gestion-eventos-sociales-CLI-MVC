package list;

import entities.Activity;
import entities.User;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;

public class PlanActivityManager extends ActivityList {

//----------------------------------------------------------------------------------------------------------------------

    public double totalCost(User user){
        double totalCost = 0;

        if(!isEmpty()){

            for (Activity current : this) {
                totalCost += current.calculateCost(user);
            }
        }

        return totalCost;
    }

//----------------------------------------------------------------------------------------------------------------------

    public int totalTime() {
        int totalTime = 0;

        if (!this.isEmpty()) {

            for (Activity current : this) {
                totalTime += current.getDuration();

                totalTime += 20;
            }

            totalTime -= 20;
        }

        return totalTime;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message removeActivity(Activity activity){
        assert activity != null;
        Message message;


        if(contains(activity)){
            message = super.removeActivity(activity);
        }else message = ErrorMessage.ACTIVITY_NOT_IN_PLAN;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message addActivity(Activity activity){
        assert activity != null;
        Message message;

        if(!contains(activity)){
            message = super.addActivity(activity);
        }else message = ErrorMessage.ACTIVITY_ALREADY_ON_PLAN;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public int freePlaces() {
        int minFreePlaces = 0;

        if (!isEmpty()) {
            minFreePlaces = Integer.MAX_VALUE;
        }

        for (Activity current : this) {
            int availablePlaces = current.getCapacity() - current.getOccupation();

            if (availablePlaces < minFreePlaces) {
                minFreePlaces = availablePlaces;
            }
        }

        if (minFreePlaces == Integer.MAX_VALUE) {
            return 0;
        }

        return minFreePlaces;
    }

//----------------------------------------------------------------------------------------------------------------------
}
