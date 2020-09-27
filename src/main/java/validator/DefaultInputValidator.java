package validator;

import models.Canvas;

public class DefaultInputValidator implements InputValidator {
  @Override public boolean validate(Canvas canvas, int... args) {
    return true;
  }
}
