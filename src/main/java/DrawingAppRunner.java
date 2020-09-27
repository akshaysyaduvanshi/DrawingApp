import app.DrawingApp;
import factory.Command;
import factory.CommandFactory;
import factory.InputValidatorFactory;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import renderer.ConsoleRenderer;

public class DrawingAppRunner {

  public static void main(String[] args) {
    DrawingApp drawingApp = new DrawingApp(new CommandFactory(), new InputValidatorFactory(), new ConsoleRenderer());

    Scanner scanner = new Scanner(System.in);

    while (true) {
      printEnterInputMessage();

      char commandId;
      int[] arguments;

      try {
        commandId =  readCommand(scanner);
        arguments = readArguments(scanner);
      } catch (Exception e) {
        System.out.println("That was an invalid input, please enter valid input");
        continue;
      }

      try {
        drawingApp.executeCommand(commandId, arguments);
      } catch (IllegalArgumentException exception) {
        exception.printStackTrace();
        System.out.println("That was an invalid input, please enter valid input");
        continue;
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("There was an unexpected error in the application, please try again");
        continue;
      }

      if (isCommandToQuit(commandId)) {
        break;
      }
    }
  }

  private static void printEnterInputMessage() {
    System.out.println();
    System.out.println("Enter Command:");
  }

  private static boolean isCommandToQuit(char commandId) {
    Optional<Command> commandOp = CommandFactory.convertCommandIdToCommand(commandId);
    return commandOp.isPresent() && commandOp.get() == Command.QUIT;
  }

  private static int[] readArguments(Scanner scanner) {
    String[] arguments = scanner.nextLine().split(" ");
    return Arrays
        .stream(arguments)
        .filter(s -> s != null && !s.isEmpty())
        .map(s -> {
          if(s.length() == 1 && (int)s.charAt(0) < (int)'0' || (int)s.charAt(0) > (int)'9') {
            return "" + (int)s.charAt(0);
          }
          return s;
        })
        .mapToInt(Integer::parseInt)
        .toArray();
  }

  private static char readCommand(Scanner scanner) {
    return scanner.next().toCharArray()[0];
  }
}

