package commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import models.Canvas;
import models.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

class DrawLineTest {
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
  public void shouldDrawVerticalLine() {
    new DrawLine(new Point(1,1), new Point(1,5)).execute(canvas);

    InOrder inOrder = Mockito.inOrder(canvas);
    inOrder.verify(canvas).setPixelValue(1,1, 'x');
    inOrder.verify(canvas).setPixelValue(1,2, 'x');
    inOrder.verify(canvas).setPixelValue(1,3, 'x');
    inOrder.verify(canvas).setPixelValue(1,4, 'x');
    inOrder.verify(canvas).setPixelValue(1,5, 'x');
  }

  @Test
  public void shouldDrawHorizontalLine() {
    new DrawLine(new Point(1,1), new Point(5,1)).execute(canvas);

    InOrder inOrder = Mockito.inOrder(canvas);
    inOrder.verify(canvas).setPixelValue(1,1, 'x');
    inOrder.verify(canvas).setPixelValue(2,1, 'x');
    inOrder.verify(canvas).setPixelValue(3,1, 'x');
    inOrder.verify(canvas).setPixelValue(4,1, 'x');
    inOrder.verify(canvas).setPixelValue(5,1, 'x');
  }

  @Test
  public void shouldDrawDiagonalLine() {
    new DrawLine(new Point(1,1), new Point(5,5)).execute(canvas);

    InOrder inOrder = Mockito.inOrder(canvas);
    inOrder.verify(canvas).setPixelValue(1,1, 'x');
    inOrder.verify(canvas).setPixelValue(2,2, 'x');
    inOrder.verify(canvas).setPixelValue(3,3, 'x');
    inOrder.verify(canvas).setPixelValue(4,4, 'x');
    inOrder.verify(canvas).setPixelValue(5,5, 'x');
  }
}