package list;

import entities.Plan;
import entities.User;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;
import userinterface.comunication.ResultMessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class PlanList extends EntityList<Plan> {

//----------------------------------------------------------------------------------------------------------------------

    public Message removePlan(Plan plan){
        assert plan != null;
        Message message;

        if(this.contains(plan)){
            if(this.remove(plan)){

                message = ResultMessage.PLAN_DELETED;

            }else message = ErrorMessage.PLAN_NOT_DELETED;
        }else message = ErrorMessage.PLAN_NOT_FOUND;


        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message addPlan(Plan plan){
        assert plan != null;
        Message message;

        if(!this.contains(plan)){
            if(this.add(plan)){

                message = ResultMessage.PLAN_ADDED;

            }else message = ErrorMessage.PLAN_NOT_ADDED;
        }else message = ErrorMessage.PLAN_ALREADY_IN_LIST;


        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public ArrayList<Plan> availablePlans(){
        ArrayList<Plan> result = new PlanList();
        Iterator <Plan> iterator = iterator();

        LocalDateTime currentDate = LocalDateTime.now();

        if (!isEmpty()) {
            while (iterator.hasNext()) {
                Plan current = iterator.next();
                if (current.getDateTime().isAfter(currentDate)) {
                    result.add(current);
                }
            }
        }

        return result;
    }

//----------------------------------------------------------------------------------------------------------------------
    public ArrayList<Plan> orderByDate() {

        ArrayList<Plan> result = new ArrayList<>();
        ArrayList<Plan> list = this;

        Plan olderPlan;

        for(int i = 0; i < this.size();i++){
            Iterator<Plan> iterator = list.iterator();
            olderPlan = iterator.next();

            while (iterator.hasNext()) {
                Plan currentPlan = iterator.next();

                assert olderPlan != null;
                if (olderPlan.checkPlanIsAfter(currentPlan)) {
                    olderPlan = currentPlan;
                }
            }

            result.add(olderPlan);
            list.remove(olderPlan);
        }

        return result;
    }

//----------------------------------------------------------------------------------------------------------------------

    public ArrayList<Plan> subscribedPlans(User user){

        ArrayList<Plan> result = new PlanList();
        Iterator <Plan> iterator = iterator();

        if (!isEmpty()) {
            while (iterator.hasNext()) {
                Plan current = iterator.next();
                if (current.checkUserIsSubscribed(user)) {
                    result.add(current);
                }
            }
        }

        return result;
    }

//----------------------------------------------------------------------------------------------------------------------

    public ArrayList<Plan> filterLocation(String location){

        ArrayList<Plan> result = new PlanList();
        Iterator <Plan> iterator = iterator();

        if (!isEmpty()) {
            while (iterator.hasNext()) {
                Plan current = iterator.next();
                if (current.checkLocationMatches(location)) {
                    result.add(current);
                }
            }
        }

        return result;
    }

//----------------------------------------------------------------------------------------------------------------------
}





