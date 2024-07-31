package list;


import entities.User;
import java.util.Iterator;

public class UserList extends EntityList<User> {

//----------------------------------------------------------------------------------------------------------------------

    private User searchByPhoneNumber(String phone) {
        assert phone != null;

        Iterator<User> iterator = this.iterator();
        User result = null;

        if (!isEmpty()) {
            while (iterator.hasNext()) {
                User current = iterator.next();

                if (current.checkPhoneMatches(phone)){
                    result = current;
                }
            }
        }

        return result;
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean checkPhoneUsed(String phone){
        return searchByPhoneNumber(phone) != null;
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean checkNameUsed(String name){
        return searchByName(name) != null;
    }

//----------------------------------------------------------------------------------------------------------------------
}



