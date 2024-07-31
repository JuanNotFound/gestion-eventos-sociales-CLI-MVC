package userinterface.commands;

import userinterface.InputHandler;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;

public class SubscribePlan extends Command {

    public SubscribePlan(InputHandler fields, String id, int maxFields) {
        super(fields, id, maxFields);
    }
    public Message execute() {
        Message message;

        if(this.checkMatchesFieldNumber()){
            message = this.getSocialManager().subscribePlan(this.getField(1));
        }else message = ErrorMessage.NUM_OF_PARAM_NOT_VALID;

        return message;
    }

}
