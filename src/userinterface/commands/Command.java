package userinterface.commands;

import entities.ActivityType;
import logic.SocialManager;
import userinterface.InputHandler;
import userinterface.comunication.Message;

public abstract class Command {

    private final String id;
    private final int maxFields;
    private final InputHandler inputHandler;
    private final SocialManager socialManager = SocialManager.getInstance();

//---------------------------------------------------------------------------------------------------------------------

    public Command(InputHandler inputHandler, String id, int maxFields){
        this.id = id;
        this.maxFields = maxFields;
        this.inputHandler = inputHandler;
    }

//---------------------------------------------------------------------------------------------------------------------

    public boolean checkMatchesFieldNumber(){
        return this.inputHandler.countFields() == this.maxFields;
    }

//---------------------------------------------------------------------------------------------------------------------

    public boolean checkMatchesFieldNumber(int fields){
        return this.inputHandler.countFields() == fields;
    }

//---------------------------------------------------------------------------------------------------------------------

    public abstract Message execute();

//---------------------------------------------------------------------------------------------------------------------
    protected String getField(int i){
        return this.inputHandler.get(i);
    }

//---------------------------------------------------------------------------------------------------------------------

    protected int getFieldToInteger(int i){
        return this.inputHandler.fieldToInteger(i);
    }

//---------------------------------------------------------------------------------------------------------------------

    protected ActivityType getFieldType(int i){
        return ActivityType.getType(getField(i));
    }

//---------------------------------------------------------------------------------------------------------------------

    @SuppressWarnings("SameParameterValue")
    protected double getFieldToDouble(int i){
        return this.inputHandler.fieldToDouble(i);
    }

//---------------------------------------------------------------------------------------------------------------------

    protected boolean getParseError(){
        return this.inputHandler.getError();
    }

//---------------------------------------------------------------------------------------------------------------------

    protected SocialManager getSocialManager(){
        return this.socialManager;
    }

//---------------------------------------------------------------------------------------------------------------------

    public String getId(){
        return this.id;
    }

//----------------------------------------------------------------------------------------------------------------------
}
