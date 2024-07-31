package logic;

import entities.Activity;
import entities.ActivityType;
import entities.User;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;
import userinterface.comunication.ResultMessage;

public class SocialManager {

    private User logedUser = null;
    private boolean shutdown = false;
    private final ActivityManager activityManager = ActivityManager.getInstance();
    private final UserManager userManager = UserManager.getInstance();
    private final PlanManager planManager = PlanManager.getInstance();

//----------------------------------------------------------------------------------------------------------------------

    private static SocialManager instance = null;

//----------------------------------------------------------------------------------------------------------------------

    private SocialManager(){
    }

//----------------------------------------------------------------------------------------------------------------------

    public static SocialManager getInstance() {
        if (instance == null) {
            instance = new SocialManager();
        }
        return instance;
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean shutdownState() {
        return shutdown;
    }

//----------------------------------------------------------------------------------------------------------------------

    public void shutdown(){
        this.shutdown = true;
    }

//----------------------------------------------------------------------------------------------------------------------

    private boolean userLogged(){
        return logedUser != null;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message login(String username, String password){
        assert username != null && password != null;
        Message message;

        if(!userLogged()) {
            message = this.userManager.login(username,password);
        }else message = ErrorMessage.USER_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message logout(){
        Message message;

        if(userLogged()){
            this.logedUser = null;
            message = ResultMessage.LOGEDOUT;
        }else message = ErrorMessage.NOT_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message deletePlan(String name){
        assert name != null;
        Message message;

        if(userLogged()){
            message = this.planManager.deletePlan(logedUser, name);
        }else message = ErrorMessage.NOT_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message deleteActivity(String name){
        assert name != null;
        Message message;

        if (userLogged()) {
            message = this.activityManager.deleteActivity(name);
        } else message = ErrorMessage.NOT_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message closePlan(String name){
        assert name != null;
        Message message;

        if (userLogged()) {
            message = this.planManager.closePlan(this.logedUser,name);
        } else {
            message = ErrorMessage.NOT_LOGGED;
        }

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message subscribePlan(String name){
        assert name != null;
        Message message;

        if (userLogged()) {
            message = this.planManager.addUserToPlan(this.logedUser, name);
        } else {
            message = ErrorMessage.NOT_LOGGED;
        }

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message unsubscribePlan(String name){
        assert name != null;
        Message message;

        if (userLogged()) {

        message = this.planManager.removeUserFromPlan(this.logedUser, name);

        } else {
            message = ErrorMessage.NOT_LOGGED;
        }
        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public void setLoggedUser(User user){
        assert user != null;
        this.logedUser = user;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message calculateTotalCost(String name){
        assert name != null;
        Message message;

        if (userLogged()) {
            message = this.planManager.calculatePlanTotalCost(name, this.logedUser);
        } else {
            message = ErrorMessage.NOT_LOGGED;
        }

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message calculateTotalTime(String name){
        assert name != null;
        Message message;

        if (userLogged()) {
                message = this.planManager.calculatePlanTotalTime(name, this.logedUser);
        } else {
            message = ErrorMessage.NOT_LOGGED;
        }

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message lookAvailablePlans(){
        Message message;

        if(userLogged()){
            message = this.planManager.listAvailablePlans();
        }else message = ErrorMessage.NOT_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message lookSubscribedPlans(){
        Message message;

        if(userLogged()){
            message = this.planManager.listSubscribedPlans(this.logedUser);
        }else message = ErrorMessage.NOT_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message orderByDate(){
        Message message;

        if(userLogged()){
            message = this.planManager.listByDate();
        }else message = ErrorMessage.NOT_LOGGED;

        return message;

    }

//----------------------------------------------------------------------------------------------------------------------

    public Message createPlan(String name, String dateTime, String location){
        assert name != null && dateTime != null && location != null;
        Message message;

        if(userLogged()){
            message = this.planManager.createPlan(name,dateTime,location,this.logedUser);
        }else message = ErrorMessage.NOT_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message createActivity(String name, String description, int duration, double cost, int capacity, ActivityType activityType){
        assert name != null && description != null;
        Message message;

        if(userLogged()){
            message = this.activityManager.createActivity(name,description,duration,cost,capacity,activityType);
        }else message = ErrorMessage.NOT_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message register(String name, String phone, String password, int age){
        assert name != null && phone != null && password != null;
        Message message;

        if(!userLogged()){
            message = this.userManager.register(name,phone,password,age);
        }else message = ErrorMessage.USER_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message removeActivityFromPlan(String activityName, String planName){
        assert activityName != null && planName != null;
        Message message;

        if (userLogged()) {
            Activity selectedActivity = this.activityManager.searchActivity(activityName);

            if (selectedActivity != null) {
                message = this.planManager.removeActivityFromPlan(this.logedUser, selectedActivity, planName);
            } else message = ErrorMessage.ACTIVITY_NOT_FOUND;

        } else message = ErrorMessage.NOT_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message addActivityToPlan(String activityName, String planName){
        assert activityName != null && planName != null;
        Message message;

        if (userLogged()) {
            Activity selectedActivity = this.activityManager.searchActivity(activityName);

            if (selectedActivity != null) {
                message =  this.planManager.addActivityToPlan(this.logedUser, selectedActivity, planName);
            } else message = ErrorMessage.ACTIVITY_NOT_FOUND;

        } else message = ErrorMessage.NOT_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message filterByLocation(String location){
        assert location != null;
        Message message;

        if(userLogged()){
            message = this.planManager.listByLocation(location);
        }else message = ErrorMessage.NOT_LOGGED;

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public User getLogedUser() {
        return logedUser;
    }

//----------------------------------------------------------------------------------------------------------------------
}