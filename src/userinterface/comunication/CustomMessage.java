package userinterface.comunication;

public class CustomMessage implements Message {

    private final String description;

//----------------------------------------------------------------------------------------------------------------------

    public CustomMessage(String message){
        this.description = message;
    }

//----------------------------------------------------------------------------------------------------------------------

    @Override
    public String getDescription(){
        return description;
    }

//----------------------------------------------------------------------------------------------------------------------

}
