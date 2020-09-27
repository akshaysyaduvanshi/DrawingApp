package validator;

import models.Canvas;
import models.Point;

public class LineInputValidator implements InputValidator {
  @Override public boolean validate(Canvas canvas, int... args) {
    if (args == null || args.length != 4) {
      throw new IllegalArgumentException("Invalid input to draw a line");
    }
    if (!canvas.isInBounds(new Point(args[0], args[1])) || !canvas.isInBounds(new Point(args[2], args[3]))) {
      throw new IllegalArgumentException("Invalid input, Point ouf of bounds of canvas");
    }
    return true;
  }
}
