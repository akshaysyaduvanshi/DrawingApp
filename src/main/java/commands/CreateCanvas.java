package commands;

import models.Canvas;
import renderer.Renderer;

public class CreateCanvas implements CanvasCommand {
  private final int width;
  private final int height;
  private final Renderer renderer;

  public CreateCanvas(int width, int height, Renderer renderer) {
    this.width = width;
    this.height = height;
    this.renderer = renderer;
  }

  @Override public Canvas execute(Canvas canvas) {
    return new Canvas(width, height, renderer);
  }
}
