package models;

import renderer.Renderer;

public class Canvas {
  private final static char EMPTY_CHAR = ' ';

  private final char[][] pixelsGrid;
  private final Renderer renderer;

  public Canvas(int width, int height, Renderer renderer) {
    this.renderer = renderer;
    pixelsGrid = new char[height][width];
    initializePixelsWithEmptyChars();
  }

  public void clear() {
    initializePixelsWithEmptyChars();
  }

  public void renderCurrentState() {
    renderer.render(pixelsGrid);
  }

  private void initializePixelsWithEmptyChars() {
    for (int i = 0; i < pixelsGrid.length; i++) {
      for (int j = 0; j < pixelsGrid[0].length; j++) {
        pixelsGrid[i][j] = EMPTY_CHAR;
      }
    }
  }

  public void setPixelValue(int x, int y, char c) {
    pixelsGrid[y-1][x-1] = c;
  }

  public boolean isInBounds(Point point) {
    return point.getX() > 0 && point.getX() <= pixelsGrid[0].length
        && point.getY() > 0 && point.getY() <= pixelsGrid.length;
  }

  public boolean isEmpty(Point point) {
    return pixelsGrid[point.getY()-1][point.getX()-1] == EMPTY_CHAR;
  }
}
