package entities;

public class User extends Entity{

    private final String name;
    private final int age;
    private final String phone;
    private final String password;

//----------------------------------------------------------------------------------------------------------------------

    public User(String username, String phoneNumber, String password, int age){
        this.age = age;
        this.name = username;
        this.password = password;
        this.phone = phoneNumber;
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean checkPasswordMatch(String password){
        assert password != null && password.length() >= 3;
        return this.password.equals(password);
    }


//----------------------------------------------------------------------------------------------------------------------

    public boolean checkPhoneMatches(String phone){return this.phone.equals(phone);}

//----------------------------------------------------------------------------------------------------------------------

    public String toString() {
        return " name: " + name + " phone: " + phone + " age: " + age;
    }

//----------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean checkNameMatches(String name) {
        return getName().equalsIgnoreCase(name);

    }
//----------------------------------------------------------------------------------------------------------------------

    public int getAge() {
        return age;
    }

    public String getName(){
            return name;
    }

//----------------------------------------------------------------------------------------------------------------------
}
