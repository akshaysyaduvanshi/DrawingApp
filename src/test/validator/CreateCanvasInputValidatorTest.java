package validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CreateCanvasInputValidatorTest {

  @Test
  public void shouldThrowInvalidArgumentsException() {
    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> {
      new CreateCanvasInputValidator().validate(null, 1);
    });

    assertEquals(exception1.getMessage(), "Invalid input to create a canvas");

    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {
      new CreateCanvasInputValidator().validate(null, -100,10,12);
    });

    assertEquals(exception2.getMessage(), "Invalid input to create a canvas");
  }

  @Test
  public void shouldReturnTrueForValidInputs() {
    assertTrue(new CreateCanvasInputValidator().validate(null,1,2));
  }
}