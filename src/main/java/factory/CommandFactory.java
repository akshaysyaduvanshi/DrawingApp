package factory;

import commands.BucketFill;
import commands.CanvasCommand;
import commands.CreateCanvas;
import commands.DrawLine;
import commands.DrawRectangle;
import commands.QuitCommand;
import java.util.Arrays;
import java.util.Optional;
import models.Point;
import renderer.ConsoleRenderer;
import renderer.Renderer;
import validator.BucketFillInputValidator;
import validator.CreateCanvasInputValidator;
import validator.LineInputValidator;
import validator.RectangleInputValidator;

public class CommandFactory {

  public CanvasCommand create(char commandId, Renderer renderer, int... args) {
    Optional<Command> commandOp =
        convertCommandIdToCommand(commandId);
    if (!commandOp.isPresent()) {
      throw new IllegalArgumentException("Invalid command given to application");
    }
    switch (commandOp.get()) {
      case CREATE_CANVAS:
        return new CreateCanvas(args[0], args[1], renderer);
      case DRAW_RECTANGLE:
        return new DrawRectangle(new Point(args[0], args[1]), new Point(args[2], args[3]));
      case DRAW_LINE:
        return new DrawLine(new Point(args[0], args[1]), new Point(args[2], args[3]));
      case BUCKET_FILL:
        return new BucketFill(new Point(args[0], args[1]), (char)args[2]);
      case QUIT:
        return new QuitCommand();
      default:
        throw new IllegalArgumentException("Invalid command given to application");
    }
  }

  public static Optional<Command> convertCommandIdToCommand(char commandId) {
    return Arrays.stream(Command.values()).filter((c) -> c.getId() == commandId).findFirst();
  }
}
