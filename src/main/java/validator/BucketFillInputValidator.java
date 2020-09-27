package validator;

import models.Canvas;
import models.Point;

public class BucketFillInputValidator implements InputValidator {
  @Override public boolean validate(Canvas canvas, int... args) {
    if (args == null || args.length != 3) {
      throw new IllegalArgumentException("Invalid input to bucket fill");
    }
    if (!canvas.isInBounds(new Point(args[0], args[1]))) {
      throw new IllegalArgumentException("Invalid input, Point ouf of bounds of canvas");
    }
    return true;
  }
}
