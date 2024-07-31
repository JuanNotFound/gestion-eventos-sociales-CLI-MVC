package userinterface.commands;

import userinterface.InputHandler;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;

public class AddActivityToPlan extends Command{

    public AddActivityToPlan(InputHandler fields, String id, int maxFields) {
        super(fields, id, maxFields);
    }
    public Message execute() {
        Message message;

        if(this.checkMatchesFieldNumber()){
            message =  getSocialManager().addActivityToPlan(getField(1),getField(2));
        }else message = ErrorMessage.NUM_OF_PARAM_NOT_VALID;

        return message;
    }

}