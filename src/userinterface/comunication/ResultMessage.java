package userinterface.comunication;

public enum ResultMessage implements Message {

    REGISTERED("Message: User registered."),
    LOGGED("Message: logged."),
    LOGEDOUT("Message: logged out."),
    ACTIVITY_ADDED("Message: Activity added."),
    PLAN_CLOSED("Message: Plan closed"),
    ACTIVITY_CREATED("Message: Activity created."),
    ACTIVITY_DELETED("Message: Activity deleted."),
    USER_ADDED("Message: User added."),
    USER_REMOVED("Message: User removed."),
    PLAN_DELETED("Message: Plan deleted."),
    PLAN_ADDED("Message: plan added"),
    SHUTDOWN("Message: System shutdown.");

    private final String description;

//----------------------------------------------------------------------------------------------------------------------

    ResultMessage(String description) {
        this.description = description;
    }

//----------------------------------------------------------------------------------------------------------------------

    @Override
    public String getDescription(){
        return description;
    }

//----------------------------------------------------------------------------------------------------------------------


}
