package entities;

import list.UserList;

public class Activity extends Entity{

    private final String name;
    private final String description;
    private final int duration;
    private final double cost;
    private final ActivityType activityType;
    private final int capacity;
    private final UserList list = new UserList();

//----------------------------------------------------------------------------------------------------------------------

    public Activity(String name, String description, int duration, double cost, int capacity, ActivityType activityType) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.cost = cost;
        this.activityType = activityType;
        this.capacity = capacity;
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean addUser(User user){
        assert user != null;

        if(this.list.size() < this.capacity){
            return this.list.add(user);
        }else{
            return false;
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean removeUser(User user){
        assert user != null;
        return this.list.remove(user);
    }

//----------------------------------------------------------------------------------------------------------------------

    public double calculateCost(User user){
        assert user != null;
        int discount = this.getActivityType().getDiscountPercentage(user);

        return this.getCost() * ((double) discount/100);
    }


//----------------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
    return "Activity: " + name + " Description: " + description + " Duration: " + duration + " min Cost:" + cost +
    " € T " + activityType.toString();
    }

//----------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean checkNameMatches(String name) {
        return getName().equalsIgnoreCase(name);
    }
//----------------------------------------------------------------------------------------------------------------------

    public String getName(){
        return this.name;
    }

    public double getCost() {
        return this.cost;
    }

    public int getDuration() {
        return duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupation(){
        return list.size();
    }

    public ActivityType getActivityType() {
        return activityType;
    }

//----------------------------------------------------------------------------------------------------------------------
}



