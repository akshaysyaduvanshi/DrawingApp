package validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DefaultInputValidatorTest {
  @Test
  public void shouldReturnTrueForAnyInput() {
    assertTrue(new DefaultInputValidator().validate(null, 1));
    assertTrue(new DefaultInputValidator().validate(null, 1,2,3));
  }
}