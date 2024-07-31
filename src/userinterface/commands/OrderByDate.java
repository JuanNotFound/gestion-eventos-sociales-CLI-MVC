package userinterface.commands;

import userinterface.InputHandler;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;

public class OrderByDate extends Command {

    public OrderByDate(InputHandler fields, String id, int maxFields) {
        super(fields, id, maxFields);
    }

    public Message execute() {
        Message message;

        if(this.checkMatchesFieldNumber()){
            message = this.getSocialManager().orderByDate();
        }else message = ErrorMessage.NUM_OF_PARAM_NOT_VALID;

        return message;
    }

}
