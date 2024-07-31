package userinterface.commands;

import userinterface.InputHandler;
import userinterface.comunication.Message;
import userinterface.comunication.ResultMessage;

public class Shutdown extends Command {

    public Shutdown(InputHandler fields, String id, int maxFields) {
        super(fields, id, maxFields);
    }
    public Message execute() {
            this.getSocialManager().shutdown();
            return ResultMessage.SHUTDOWN;
    }

}
