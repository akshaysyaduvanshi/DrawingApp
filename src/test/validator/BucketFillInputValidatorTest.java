package validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import models.Canvas;
import models.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BucketFillInputValidatorTest {
  private Canvas canvas;

  @BeforeEach
  public void setUp() {
    canvas = mock(Canvas.class);

    doAnswer(invocation -> {
      Point point = (Point) invocation.getArguments()[0];
      return point.getX() > 0 && point.getX() <= 3
          && point.getY() > 0 && point.getY() <= 3;
    }).when(canvas).isInBounds(any(Point.class));
  }

  @Test
  public void shouldThrowInvalidArgumentsException() {
    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> {
      new BucketFillInputValidator().validate(canvas, 1);
    });

    assertEquals(exception1.getMessage(), "Invalid input to bucket fill");

    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {
      new BucketFillInputValidator().validate(canvas, 100,10,12);
    });

    assertEquals(exception2.getMessage(), "Invalid input, Point ouf of bounds of canvas");
  }

  @Test
  public void shouldReturnTrueForValidInputs() {
    assertTrue(new BucketFillInputValidator().validate(canvas,1,2,22));
  }
}