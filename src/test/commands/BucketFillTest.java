package commands;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import models.Canvas;
import models.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

class BucketFillTest {
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
  public void shouldFillEntireCanvas() {
    when(canvas.isEmpty(any(Point.class))).thenReturn(true);
    BucketFill bucketFill = new BucketFill(new Point(2,2), 'a');
    bucketFill.execute(canvas);

    InOrder inOrder = Mockito.inOrder(canvas);
    inOrder.verify(canvas).setPixelValue(2,2,'a');
    inOrder.verify(canvas).setPixelValue(1,2,'a');
    inOrder.verify(canvas).setPixelValue(3,2,'a');
    inOrder.verify(canvas).setPixelValue(2,1,'a');
    inOrder.verify(canvas).setPixelValue(2,3,'a');
    inOrder.verify(canvas).setPixelValue(1,1,'a');
    inOrder.verify(canvas).setPixelValue(1,3,'a');
    inOrder.verify(canvas).setPixelValue(3,1,'a');
    inOrder.verify(canvas).setPixelValue(3,3,'a');
  }

  @Test
  public void shouldNotFillPaintedPixels() {
    doAnswer(invocation -> {
      Point point = (Point) invocation.getArguments()[0];
      return (point.getX() != 1 || point.getY() != 2)
          && (point.getX() != 3 || point.getY() != 2);
    }).when(canvas).isEmpty(any(Point.class));

    BucketFill bucketFill = new BucketFill(new Point(2,2), 'a');
    bucketFill.execute(canvas);

    InOrder inOrder = Mockito.inOrder(canvas);
    inOrder.verify(canvas).setPixelValue(2,2,'a');
    inOrder.verify(canvas).setPixelValue(2,1,'a');
    inOrder.verify(canvas).setPixelValue(2,3,'a');
    inOrder.verify(canvas).setPixelValue(1,1,'a');
    inOrder.verify(canvas).setPixelValue(3,1,'a');
    inOrder.verify(canvas).setPixelValue(1,3,'a');
    inOrder.verify(canvas).setPixelValue(3,3,'a');
  }

  @Test
  public void shouldPaintOnlyBoundedArea() {
    when(canvas.isEmpty(any(Point.class))).thenReturn(false);
    BucketFill bucketFill = new BucketFill(new Point(2,2), 'a');
    bucketFill.execute(canvas);

    verify(canvas, times(1)).setPixelValue(anyInt(),anyInt(),anyChar());
  }
}