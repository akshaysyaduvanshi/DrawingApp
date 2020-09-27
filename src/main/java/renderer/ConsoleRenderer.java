package renderer;

public class ConsoleRenderer implements Renderer {
  @Override public void render(char[][] grid) {
    for (int i = -1; i <= grid.length; i++) {
      System.out.println();
      for (int j = -1; j <= grid[0].length; j++) {
        if (i == -1 || i == grid.length) {
          System.out.print("-");
        } else if (j == -1 || j == grid[0].length) {
          System.out.print("|");
        } else {
          System.out.print(grid[i][j]);
        }
      }
    }
  }
}
