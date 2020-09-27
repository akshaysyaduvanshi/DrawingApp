package commands;

import models.Canvas;

public interface CanvasCommand {
  Canvas execute(Canvas canvas);
}
