package commands;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import models.Canvas;
import models.Point;

public class BucketFill implements CanvasCommand {
  private final Point point;
  private final char fillColor;

  public BucketFill(Point point, char fillColor) {
    this.point = point;
    this.fillColor = fillColor;
  }

  public Canvas execute(Canvas canvas) {
    if(!canvas.isInBounds(point)) {
      throw new IllegalArgumentException("Given point is out of canvas bounds " + point.toString());
    }
    Queue<Point> queue = new LinkedList<>();
    Set<Point> visitedNodes = new HashSet<>();
    queue.add(point);
    visitedNodes.add(point);
    while (!queue.isEmpty()) {
      Point currentPoint = queue.poll();
      int currentPointX = currentPoint.getX();
      int currentPointY = currentPoint.getY();
      canvas.setPixelValue(currentPointX, currentPointY, fillColor);

      Point leftPoint = new Point(currentPointX-1, currentPointY);
      Point rightPoint = new Point(currentPointX+1, currentPointY);
      Point topPoint = new Point(currentPointX, currentPointY-1);
      Point downPoint = new Point(currentPointX, currentPointY+1);

      Arrays.asList(leftPoint,rightPoint,topPoint,downPoint).forEach(point -> {
        if (canvas.isInBounds(point)  && canvas.isEmpty(point) && !visitedNodes.contains(point)) {
          queue.add(point);
          visitedNodes.add(point);
        }
      });
    }
    return canvas;
  }
}
