package logic;

import entities.Activity;
import entities.Plan;
import entities.User;
import list.PlanList;
import userinterface.comunication.CustomMessage;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PlanManager {

    private static PlanManager instance = null;

//----------------------------------------------------------------------------------------------------------------------

    private PlanManager(){
    }

//----------------------------------------------------------------------------------------------------------------------

    public static PlanManager getInstance(){
        if (instance == null) {
            instance = new PlanManager();
        }
        return instance;
    }

//----------------------------------------------------------------------------------------------------------------------
    private final PlanList list = new PlanList();

//----------------------------------------------------------------------------------------------------------------------

    public Message addUserToPlan(User user, String plan){
        assert user != null;
        Message message;

        Plan selectedPlan = this.list.searchByName(plan);

        if(this.list.contains(selectedPlan)) {

            message = selectedPlan.addUser(user);

        }else message = ErrorMessage.PLAN_NOT_FOUND;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message removeUserFromPlan(User user, String plan){
        assert user != null;
        Message message;

        Plan selectedPlan = this.list.searchByName(plan);

        if(!this.list.contains(selectedPlan)) {
            if(selectedPlan.checkUserIsSubscribed(user)){

                message = selectedPlan.removeUser(user);

            }else message = ErrorMessage.USER_NOT_SUBSCRIBED_IN_PLAN;
        }else message = ErrorMessage.PLAN_NOT_FOUND;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message addActivityToPlan(User user, Activity activity, String plan){
        assert activity != null;
        Message message;

        Plan selectedPlan = this.list.searchByName(plan);

        if(this.list.contains(selectedPlan)){

            message = selectedPlan.addActivity(user, activity);

        }else message = ErrorMessage.PLAN_NOT_FOUND;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message removeActivityFromPlan(User user, Activity activity, String plan){
        assert activity != null;
        Message message;

        Plan selectedPlan = this.list.searchByName(plan);

        if(this.list.contains(selectedPlan)) {

                message = selectedPlan.removeActivity(user, activity);
        }else message = ErrorMessage.PLAN_NOT_FOUND;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message createPlan(String name, String dateTime, String location, User owner){
        assert owner != null;
        Message message;

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        if(this.list.searchByName(name) == null){
            if (!dateTime.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
                message = ErrorMessage.DATE_NOT_VALID;
            } else {

                LocalDateTime dateTimeObj = LocalDateTime.parse(dateTime, formatter);

                if (name == null) {
                    message = ErrorMessage.NAME_NULL;
                } else if (location == null) {
                    message = ErrorMessage.LOCATION_NULL;
                } else {
                    Plan newPlan = new Plan(name, dateTimeObj, location, owner);
                    message = this.list.addPlan(newPlan);
                }

            }
        }else message = ErrorMessage.PLAN_ALREADY_IN_LIST;


        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message deletePlan(User user, String plan){

        Message message;

        Plan selectedPlan = this.list.searchByName(plan);

        if (selectedPlan != null) {
            if (selectedPlan.checkUserIsOwner(user)) {

                message = this.list.removePlan(selectedPlan);

            } else message = ErrorMessage.USER_NOT_OWNER;
        } else message = ErrorMessage.PLAN_NOT_FOUND;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message closePlan(User user, String name){
        assert user != null && name != null;
        Message message;

        Plan selectedPlan = this.list.searchByName(name);

        if(selectedPlan != null){
            if(selectedPlan.checkUserIsOwner(user)){

                message = selectedPlan.closePlan();

            }else message = ErrorMessage.USER_NOT_OWNER;
        }else message = ErrorMessage.PLAN_NOT_FOUND;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message listByDate(){
        Message message;
        if(!this.list.isEmpty()){
            StringBuilder sb = new StringBuilder();

            for (Plan current : this.list.orderByDate()) {
                sb.append(current.toString()).append("\n");
            }

            message = new CustomMessage(sb.toString());

        }else message = ErrorMessage.NO_PLANS_ON_LIST;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message listSubscribedPlans(User user){
        Message message;

        if(!this.list.isEmpty()) {
            ArrayList<Plan> subscribedPlansList = this.list.subscribedPlans(user);
            StringBuilder sb = new StringBuilder();

            if(!subscribedPlansList.isEmpty()){

                for (Plan current :this.list.subscribedPlans(user)) {
                    sb.append(current.toString()).append("\n");
                }

                message = new CustomMessage(sb.toString());

            }else message = ErrorMessage.NO_PLANS_ON_LIST;
        }else message = ErrorMessage.NO_PLANS_ON_LIST;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message listAvailablePlans(){
        Message message;

        if(!this.list.isEmpty()) {
            StringBuilder sb = new StringBuilder();

            for (Plan current :this.list.availablePlans()) {
                sb.append(current.toString()).append("\n");
            }

            message = new CustomMessage(sb.toString());

        }else message = ErrorMessage.NO_PLANS_ON_LIST;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message listByLocation(String location){
        Message message;
        PlanList result = new PlanList();

        result.addAll(this.list.filterLocation(location));
        if(!result.isEmpty()) {

            StringBuilder sb = new StringBuilder();

            for (Plan current :result.filterLocation(location)) {
                sb.append(current.toString()).append("\n");
            }

            message = new CustomMessage(sb.toString());

        }else message = ErrorMessage.NO_PLANS_ON_LIST;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message calculatePlanTotalCost(String name, User user){
        assert name != null && user != null;
        Message message;

        Plan selectedPlan = this.list.searchByName(name);

        if(selectedPlan != null){
            double cost = selectedPlan.totalCost(user);
            message = new CustomMessage("The price for current logged user: " + selectedPlan.getName() + " is " + cost + " € ");
        }else message = ErrorMessage.PLAN_NOT_FOUND;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message calculatePlanTotalTime(String name, User user){
        assert name != null && user != null;
        Message message;

        Plan selectedPlan = this.list.searchByName(name);

        if(selectedPlan != null){
            int time = selectedPlan.totalTime();
            message = new CustomMessage("The total time for: " + selectedPlan.getName() + " is " + time + " min ");
        }else message = ErrorMessage.PLAN_NOT_FOUND;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------
}
