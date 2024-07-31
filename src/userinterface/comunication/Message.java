package userinterface.comunication;

public interface Message {
    String description = null;

//----------------------------------------------------------------------------------------------------------------------

     default String getDescription(){
        return description;
    }

//----------------------------------------------------------------------------------------------------------------------


}
