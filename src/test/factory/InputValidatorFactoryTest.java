package factory;

import static org.junit.jupiter.api.Assertions.*;

import commands.BucketFill;
import commands.CreateCanvas;
import commands.DrawLine;
import commands.DrawRectangle;
import commands.QuitCommand;
import org.junit.jupiter.api.Test;
import validator.BucketFillInputValidator;
import validator.CreateCanvasInputValidator;
import validator.DefaultInputValidator;
import validator.LineInputValidator;
import validator.RectangleInputValidator;

class InputValidatorFactoryTest {
  @Test
  public void shouldCreateCommands() {
    InputValidatorFactory inputValidatorFactory = new InputValidatorFactory();

    assertTrue(inputValidatorFactory.create('C') instanceof CreateCanvasInputValidator);
    assertTrue(inputValidatorFactory.create('L') instanceof LineInputValidator);
    assertTrue(inputValidatorFactory.create('R') instanceof RectangleInputValidator);
    assertTrue(inputValidatorFactory.create('Q') instanceof DefaultInputValidator);
    assertTrue(inputValidatorFactory.create('B') instanceof BucketFillInputValidator);
  }

  @Test()
  public void shouldThrowExceptionForInvalidCommand() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new InputValidatorFactory().create('X');
    });
  }
}