package userinterface.commands;

import userinterface.InputHandler;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;

public class CreateActivity extends Command {

    private final int minFields;

//----------------------------------------------------------------------------------------------------------------------

    public CreateActivity(InputHandler fields, String id, int maxFields, int minFields) {
        super(fields, id, maxFields);
        this.minFields = minFields;
    }
    public Message execute() {
        Message message = ErrorMessage.NUM_OF_PARAM_NOT_VALID;

        if(this.checkMatchesFieldNumber()){

            if(this.getParseError()){

                if(this.checkMatchesFieldNumber()){
                    message = this.getSocialManager().createActivity(getField(1),getField(2), this.getFieldToInteger(3), this.getFieldToDouble(4), this.getFieldToInteger(5) ,this.getFieldType(6) );
                }else if(this.checkMatchesFieldNumber(minFields)){
                    message = this.getSocialManager().createActivity(this.getField(1),getField(2),this.getFieldToInteger(3) , this.getFieldToDouble(4),Integer.MAX_VALUE, this.getFieldType(5));
                }

            }else message = ErrorMessage.NUMERIC_INPUT_NOT_VALID;

        }

        return message;
    }

}
