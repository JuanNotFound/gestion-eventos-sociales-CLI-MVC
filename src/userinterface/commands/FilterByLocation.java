package userinterface.commands;

import userinterface.InputHandler;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;

public class FilterByLocation extends Command{

    public FilterByLocation(InputHandler fields, String id, int maxFields) {
        super(fields, id, maxFields);
    }
    public Message execute() {
        Message message;

        if(this.checkMatchesFieldNumber()){
            message = this.getSocialManager().filterByLocation(this.getField(1));
        }else message = ErrorMessage.NUM_OF_PARAM_NOT_VALID;

        return message;
    }

}
