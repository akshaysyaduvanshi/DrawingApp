package commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import models.Canvas;
import models.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

class DrawRectangleTest {
  private Canvas canvas;

  @BeforeEach
  public void setUp() {
    canvas = mock(Canvas.class);

    doAnswer(invocation -> {
      Point point = (Point) invocation.getArguments()[0];
      return point.getX() > 0 && point.getX() <= 10
          && point.getY() > 0 && point.getY() <= 10;
    }).when(canvas).isInBounds(any(Point.class));
  }

  @Test
  public void shouldDrawRectangle() {
    new DrawRectangle(new Point(1,1), new Point(5,5)).execute(canvas);

    InOrder inOrder = Mockito.inOrder(canvas);
    inOrder.verify(canvas).setPixelValue(1,1, 'x');
    inOrder.verify(canvas).setPixelValue(1,2, 'x');
    inOrder.verify(canvas).setPixelValue(1,3, 'x');
    inOrder.verify(canvas).setPixelValue(1,4, 'x');
    inOrder.verify(canvas).setPixelValue(1,5, 'x');

    inOrder.verify(canvas).setPixelValue(2,5, 'x');
    inOrder.verify(canvas).setPixelValue(3,5, 'x');
    inOrder.verify(canvas).setPixelValue(4,5, 'x');
    inOrder.verify(canvas).setPixelValue(5,5, 'x');

    inOrder.verify(canvas).setPixelValue(5,2, 'x');
    inOrder.verify(canvas).setPixelValue(5,3, 'x');
    inOrder.verify(canvas).setPixelValue(5,4, 'x');
    inOrder.verify(canvas).setPixelValue(5,5, 'x');

    inOrder.verify(canvas).setPixelValue(2,1, 'x');
    inOrder.verify(canvas).setPixelValue(3,1, 'x');
    inOrder.verify(canvas).setPixelValue(4,1, 'x');
    inOrder.verify(canvas).setPixelValue(5,1, 'x');

  }
}