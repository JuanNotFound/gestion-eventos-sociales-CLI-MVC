package userinterface.commands;

import userinterface.InputHandler;
import userinterface.comunication.CustomMessage;
import userinterface.comunication.Message;

public class Help extends Command {
    public Help(InputHandler inputHandler, String id, int maxFields) {
        super(inputHandler, id, maxFields);
    }

    @Override
    public Message execute() {
        return new CustomMessage("""

                Login;Username;passsword\s
                Logout
                Register;Username;phone;password;age
                CreatePlan;name;dateTime;location
                CreateActivity;name;description;duration;cost;[capacity];activityType
                AddActivityToPlan;ActivityName;PlanName
                RemoveActivityFromPlan;ActivityName;PlanName
                DeleteActivity;activityName
                DeletePlan;planName
                LookAvailablePlans
                LookSubscribedPlans
                OrderByDate;date
                CalculateTotalCost;planName
                CalculateTotalTime;planName
                FilterByLocation;location
                ClosePlan;planName
                Suscribe;planName
                unsuscribe;planName
                Shutdown""");
    }
}
