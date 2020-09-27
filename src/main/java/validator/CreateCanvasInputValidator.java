package validator;

import models.Canvas;

public class CreateCanvasInputValidator implements InputValidator {
  @Override public boolean validate(Canvas canvas, int... args) {
    if (args == null || args.length != 2 || args[0] <= 0 || args[1] <= 0)
      throw new IllegalArgumentException("Invalid input to create a canvas");
    return true;
  }
}
