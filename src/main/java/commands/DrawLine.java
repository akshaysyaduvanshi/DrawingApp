package commands;

import models.Canvas;
import models.Point;

public class DrawLine implements CanvasCommand {
  private static final char DRAWING_CHAR = 'x';

  private final Point start;
  private final Point end;

  public DrawLine(Point start, Point end) {
    this.start = start;
    this.end = end;
  }

  @Override public Canvas execute(Canvas canvas) {
    drawLine(canvas, start, end);
    drawLine(canvas, end, start);
    return canvas;
  }

  private void drawLine(Canvas canvas, Point start, Point end) {
    int x1 = start.getX();
    int y1 = start.getY();

    int x2 = end.getX();
    int y2 = end.getY();

    while (x1 <= x2 || y1 <= y2) {
      canvas.setPixelValue(Math.min(x1, x2), Math.min(y1, y2), DRAWING_CHAR);
      if (x1 <= x2) {
        x1++;
      }
      if (y1 <= y2) {
        y1++;
      }
    }
  }
}
