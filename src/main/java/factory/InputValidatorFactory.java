package factory;

import java.util.Arrays;
import java.util.Optional;
import validator.BucketFillInputValidator;
import validator.CreateCanvasInputValidator;
import validator.DefaultInputValidator;
import validator.InputValidator;
import validator.LineInputValidator;
import validator.RectangleInputValidator;

public class InputValidatorFactory {
  public InputValidator create(char commandId) {
    Optional<Command> commandOp =
        convertCommandIdToCommand(commandId);
    if (!commandOp.isPresent()) {
      throw new IllegalArgumentException("Invalid command given to application");
    }
    switch (commandOp.get()) {
      case CREATE_CANVAS:
        return new CreateCanvasInputValidator();
      case DRAW_RECTANGLE:
        return new RectangleInputValidator();
      case DRAW_LINE:
        return new LineInputValidator();
      case BUCKET_FILL:
        return new BucketFillInputValidator();
      case QUIT:
        return new DefaultInputValidator();
      default:
        throw new IllegalArgumentException("Invalid command given to application");
    }
  }

  public static Optional<Command> convertCommandIdToCommand(char commandId) {
    return Arrays.stream(Command.values()).filter((c) -> c.getId() == commandId).findFirst();
  }
}
