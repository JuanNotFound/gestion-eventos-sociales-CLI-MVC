package userinterface.commands;

import userinterface.InputHandler;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;

public class Register extends Command {

    public Register(InputHandler fields, String id, int maxFields) {
        super(fields, id, maxFields);
    }
    public Message execute() {
        Message message;

        if(this.getParseError()){
            if(this.checkMatchesFieldNumber()){
                message = this.getSocialManager().register(this.getField(1),this.getField(2),this.getField(3), this.getFieldToInteger(4));
            }else message = ErrorMessage.NUM_OF_PARAM_NOT_VALID;

        }else message = ErrorMessage.NUMERIC_INPUT_NOT_VALID;

        return message;
    }

}
