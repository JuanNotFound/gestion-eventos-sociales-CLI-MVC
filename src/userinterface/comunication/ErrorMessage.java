package userinterface.comunication;

public enum ErrorMessage implements Message {

    PASSWORD_NOT_SAFE("Error: Password must be at least 3 characters long."),
    AGE_NOT_VALID("Error: User must be older than 14 and younger than 100."),
    USER_PASSWORD_DONT_MATCH("Error: User and password don't match."),
    PLAN_NOT_OPEN("Error: Plan not open to change."),
    CANT_ADD_USER("Error: Can't subscribe to the selected plan."),
    PHONE_ALREADY_USED("Error: Phone already on use."),
    NULL_VALUE("Error: Null value introduced."),
    USER_NOT_SUBSCRIBED_IN_PLAN("Error: User not subscribed to plan."),
    USER_NOT_FOUND("Error: User not found."),
    USER_NOT_OWNER("Error: User not owner of the plan."),
    PLAN_EXPIRED("Error: Plan already happened."),
    USER_ALREADY_SUBSCRIBED_IN_PLAN("Error: User already in the plan."),
    LOCATION_NULL("Error: Location is empty."),
    NAME_NULL("Error: Name is empty."),
    ACTIVITY_TYPE_NOT_VALID("Error: Activity type not valid."),
    DESCRIPTION_NULL("Error: Description is empty."),
    ACTIVITY_NOT_FOUND("Error: Activity not found."),
    NOT_LOGGED("Error: There is no user logged."),
    COMMAND_NOT_FOUND("Error: The input command wasn't found."),

    NUM_OF_PARAM_NOT_VALID("Error: The number of parameters is not valid."),
    USER_ALREADY_EXIST("Error: Username already on use."),
    NO_PLANS_ON_LIST("Error: There are no plans to show."),
    PLAN_ALREADY_NOT_OPEN("Error: Plan was already closed before."),
    ERROR_ADDING_ACTIVITY("Error: Error adding activity to main activity list."),
    PLAN_NOT_FOUND("Error: Plan not found."),
    USER_LOGGED("Error: You are already logged."),
    ACTIVITY_NOT_IN_PLAN("Error: Activity not found on the selected plan."),
    DATE_NOT_VALID("Error: Date format not valid (YYYY-MM-DDTHH:mm:ss)."),
    PLAN_NOT_CLOSED("Error: Plan is not closed yet."),
    ACTIVITY_ALREADY_ON_PLAN("Error: Activity already on plan."),
    USER_NOT_ADDED("Error: Couldn't add user to the plan."),
    USER_NOT_REMOVED("Error: user not removed."),
    ACTIVITY_NAME_ALREADY_USED("Error: Activity name already on use."),
    COST_NEGATIVE("Error: cost, cant be negative."),
    CAPACITY_NEGATIVE("Error: capacity, cant be negative."),
    ACTIVITY_NOT_REMOVED("Error: cant remove activity."),
    ACTIVITY_NOT_ADDED("Error: cant add activity."),
    PLAN_NOT_DELETED("Error: cant remove plan."),
    PLAN_ALREADY_IN_LIST("Error: plan was already on the list"),
    PLAN_NOT_ADDED("Error: cant add plan"),
    NUMERIC_INPUT_NOT_VALID("Error: numeric input has strange value"),
    PHONE_FORMAT_NOT_VALID("Error: phone input not proper format +34000000000");

    private final String description;

//----------------------------------------------------------------------------------------------------------------------

    ErrorMessage(String description) {
        this.description = description;
    }

//----------------------------------------------------------------------------------------------------------------------

    public String getDescription(){
        return description;
    }

//----------------------------------------------------------------------------------------------------------------------

}