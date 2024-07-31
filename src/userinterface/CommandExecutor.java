package userinterface;

import userinterface.commands.*;
import userinterface.commands.OrderByDate;
import userinterface.comunication.*;
import java.util.ArrayList;


public class CommandExecutor {

//----------------------------------------------------------------------------------------------------------------------

    private final ArrayList<Command> commands;

//----------------------------------------------------------------------------------------------------------------------

    public CommandExecutor(String input) {
        commands = initializeCommands(input);
    }

//----------------------------------------------------------------------------------------------------------------------

    public Message launchCommand(String input){
        Command foundCommand = this.searchCommand(input);

        if (foundCommand != null) {
            return foundCommand.execute();
        } else {
            return ErrorMessage.COMMAND_NOT_FOUND;
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    private ArrayList<Command> initializeCommands(String input) {
        ArrayList<Command> commandList = new ArrayList<>();
        InputHandler inputHandler = new InputHandler(input);

        commandList.add(new ClosePlan(inputHandler, "closePlan", 1));
        commandList.add(new DeleteActivity(inputHandler, "deleteActivity", 1));
        commandList.add(new DeletePlan(inputHandler, "deletePlan", 1));
        commandList.add(new LogIn(inputHandler, "login", 2));
        commandList.add(new LogOut(inputHandler, "logout", 0));
        commandList.add(new Shutdown(inputHandler, "shutdown", 0));
        commandList.add(new SubscribePlan(inputHandler, "subscribe", 1));
        commandList.add(new UnsubscribePlan(inputHandler, "unsubscribe", 1));
        commandList.add(new Register(inputHandler, "register", 4));
        commandList.add(new CalculateTotalCost(inputHandler, "calculateTotalCost", 1));
        commandList.add(new CalculateTotalTime(inputHandler, "calculateTotalTime", 1));
        commandList.add(new LookAvailablePlans(inputHandler, "lookAvailablePlans", 0));
        commandList.add(new LookSubscribedPlans(inputHandler, "lookSubscribedPlans", 0));
        commandList.add(new CreateActivity(inputHandler, "createActivity", 6, 5));
        commandList.add(new CreatePlan(inputHandler, "createPlan", 3));
        commandList.add(new AddActivityToPlan(inputHandler, "addActivityToPlan", 2));
        commandList.add(new RemoveActivityFromPlan(inputHandler, "removeActivityFromPlan", 2));
        commandList.add(new FilterByLocation(inputHandler, "filterByLocation", 1));
        commandList.add(new Help(inputHandler,"help",0));
        commandList.add(new OrderByDate(inputHandler,"orderByDate",0));

        return commandList;
    }

//----------------------------------------------------------------------------------------------------------------------

    private Command searchCommand(String commandName) {
        Command foundCommand = null;
        for (Command command : commands) {
            if (command.getId().equalsIgnoreCase(commandName)) {
                foundCommand = command;
            }
        }
        return foundCommand;
    }

//----------------------------------------------------------------------------------------------------------------------
}