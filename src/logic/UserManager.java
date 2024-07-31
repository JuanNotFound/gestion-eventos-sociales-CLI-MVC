package logic;

import entities.User;
import list.UserList;
import userinterface.comunication.ErrorMessage;
import userinterface.comunication.Message;
import userinterface.comunication.ResultMessage;

public class UserManager {

    private static UserManager instance;

//----------------------------------------------------------------------------------------------------------------------

    private final UserList list = new UserList();

//----------------------------------------------------------------------------------------------------------------------

    public static UserManager getInstance(){
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

//----------------------------------------------------------------------------------------------------------------------

    private UserManager(){
    }

//----------------------------------------------------------------------------------------------------------------------

    private boolean checkPhoneFormat(String phone){
        String regex = "^(\\d{1,2}\\s?)?\\(?(\\d{3})\\)?[-\\s]?\\d{3}[-\\s]?\\d{4}$";
        return phone.matches(regex);
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message register(String name, String phone, String password, int age){
        assert name != null && phone != null && password != null;
        Message message;

        if (list.checkNameUsed(name)) {
            message = ErrorMessage.USER_ALREADY_EXIST;
        } else if (list.checkPhoneUsed(phone)) {
            message = ErrorMessage.PHONE_ALREADY_USED;
        }else if(!this.checkPhoneFormat(phone)){
            message = ErrorMessage.PHONE_FORMAT_NOT_VALID;
        } else if (password.length() < 3) {
            message = ErrorMessage.PASSWORD_NOT_SAFE;
        } else if (age < 14 || age > 100) {
            message = ErrorMessage.AGE_NOT_VALID;
        } else {
            User newUser = new User(name, phone, password, age);
            this.list.add(newUser);
            message = ResultMessage.REGISTERED;
        }

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message login(String name, String password){
        assert name != null && password != null;
        SocialManager socialManager = SocialManager.getInstance();
        Message message;

        if (this.list.checkNameUsed(name)) {
            User tempUser = this.list.searchByName(name);

            if (tempUser != null && tempUser.checkPasswordMatch(password)) {
                socialManager.setLoggedUser(tempUser);
                message = ResultMessage.LOGGED;
            } else {
                message = ErrorMessage.USER_PASSWORD_DONT_MATCH;
            }

        } else {
            message = ErrorMessage.USER_NOT_FOUND;
        }

        return message;
    }

//----------------------------------------------------------------------------------------------------------------------
}
