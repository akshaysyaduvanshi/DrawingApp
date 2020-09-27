package commands;

import models.Canvas;

public class QuitCommand implements CanvasCommand {
  @Override public Canvas execute(Canvas canvas) {
    canvas.clear();
    return canvas;
  }
}
