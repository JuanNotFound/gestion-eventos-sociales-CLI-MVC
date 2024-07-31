package userinterface;

import entities.User;
import logic.SocialManager;
import userinterface.comunication.Message;
import java.util.Scanner;

public class CLI {

//----------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        executeSocialManager();
    }

//----------------------------------------------------------------------------------------------------------------------

    private static void executeSocialManager(){
        CLI cli = new CLI();
        Scanner sc = new Scanner(System.in);
        cli.executeMenu(sc);
    }

//----------------------------------------------------------------------------------------------------------------------

    private void executeMenu(Scanner sc){
        SocialManager socialManager = SocialManager.getInstance();

        while (!socialManager.shutdownState()){
            User logedUser = socialManager.getLogedUser();
            String cli = this.printCli(logedUser);
            System.out.print(cli);

            String input = sc.nextLine();

            CommandExecutor commandExecutor = new CommandExecutor(input);
            String []line = input.split(";");
            Message message = commandExecutor.launchCommand(line[0]);

            System.out.println(message.getDescription());
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    private String printCli(User user){

        String cliString = "gps";

        if (user != null) {
            cliString = user.getName();
        }

        return cliString + " > ";
    }

//----------------------------------------------------------------------------------------------------------------------
}
