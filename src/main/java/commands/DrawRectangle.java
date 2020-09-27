package commands;

import models.Canvas;
import models.Point;

public class DrawRectangle implements CanvasCommand {
  private final Point upperLeft;
  private final Point lowerRight;

  public DrawRectangle(Point upperLeft, Point lowerRight) {
    this.upperLeft = upperLeft;
    this.lowerRight = lowerRight;
  }

  @Override public Canvas execute(Canvas canvas) {
    new DrawLine(upperLeft, new Point(upperLeft.getX(), lowerRight.getY())).execute(canvas);
    new DrawLine(new Point(upperLeft.getX(), lowerRight.getY()), lowerRight).execute(canvas);
    new DrawLine(lowerRight, new Point(lowerRight.getX(), upperLeft.getY())).execute(canvas);
    new DrawLine(new Point(lowerRight.getX(), upperLeft.getY()), upperLeft).execute(canvas);
    return canvas;
  }
}
