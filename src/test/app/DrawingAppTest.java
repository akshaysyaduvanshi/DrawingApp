package app;


import static org.junit.jupiter.api.Assertions.assertEquals;

import factory.CommandFactory;
import factory.InputValidatorFactory;
import org.junit.jupiter.api.Test;
import renderer.Renderer;

class DrawingAppTest {
  private final TestRenderer renderer = new TestRenderer();
  DrawingApp drawingApp = new DrawingApp(new CommandFactory(), new InputValidatorFactory(), renderer);

  @Test
  public void endToEndTest() {
    testCanvasCreation();
    testDrawLine();
    testDrawRectangle();
    testBucketFill();
  }

  private void testBucketFill() {
    char[][] gridWithBucketFill = new char[][] {
        {'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'x', 'x', 'x', 'x', 'x', 'M', 'M'},
        {'x', 'x', 'x', 'x', 'x', 'x', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'x', ' ', ' ', ' ', 'x', 'M', 'M'},
        {' ', ' ', ' ', ' ', ' ', 'x', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'x', 'x', 'x', 'x', 'x', 'M', 'M'},
        {' ', ' ', ' ', ' ', ' ', 'x', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M', 'M'}
    };

    drawingApp.executeCommand('B', 10, 3, 'M');

    assertTwoPixelGridsEqual(renderer.getLastRenderedGrid(), gridWithBucketFill);
  }

  private void testDrawRectangle() {
    char[][] gridWithRectangle = new char[][] {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x', 'x', 'x', 'x', 'x', ' ', ' '},
        {'x', 'x', 'x', 'x', 'x', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', 'x', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x', 'x', 'x', 'x', 'x', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };

    drawingApp.executeCommand('R', 14, 1, 18, 3);

    assertTwoPixelGridsEqual(renderer.getLastRenderedGrid(), gridWithRectangle);
  }

  private void testDrawLine() {
    char[][] gridWithLine = new char[][] {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'x', 'x', 'x', 'x', 'x', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };

    drawingApp.executeCommand('L', 1, 2, 6, 2);
    drawingApp.executeCommand('L', 6, 3, 6, 4);

    assertTwoPixelGridsEqual(renderer.getLastRenderedGrid(), gridWithLine);
  }

  private void testCanvasCreation() {
    char[][] emptyGrid = new char[][] {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };

    drawingApp.executeCommand('C', 20, 4);

    assertTwoPixelGridsEqual(renderer.getLastRenderedGrid(), emptyGrid);
  }

  public void assertTwoPixelGridsEqual(char[][] a, char[][] b) {
    assertEquals(a.length, b.length);
    assertEquals(a[0].length, b[0].length);

    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[0].length; j++) {
        assertEquals(a[i][j], b[i][j]);
      }
    }
  }

  private static class TestRenderer implements Renderer {
    private char[][] grid;

    @Override public void render(char[][] grid) {
      this.grid = grid;
    }

    public char[][] getLastRenderedGrid() {
      return grid;
    }
  }
}