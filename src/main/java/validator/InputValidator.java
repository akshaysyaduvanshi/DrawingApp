package validator;

import models.Canvas;

public interface InputValidator {
  boolean validate(Canvas canvas, int... args);
}
