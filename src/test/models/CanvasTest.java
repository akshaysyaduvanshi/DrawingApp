package models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import renderer.Renderer;

class CanvasTest {
  private Renderer renderer;
  private Canvas canvas;

  @BeforeEach
  public void setUp() {
    renderer = mock(Renderer.class);
    canvas = new Canvas(10, 10, renderer);
  }

  @Test
  public void shouldReturnPixelAsEmpty() {
    assertTrue(canvas.isEmpty(new Point(5,5)));
  }

  @Test
  public void shouldReturnPixelAsNotEmpty() {
    canvas.setPixelValue(5,5,'x');
    assertFalse(canvas.isEmpty(new Point(5,5)));
  }

  @Test
  public void shouldReturnPixelIsInBounds() {
    assertTrue(canvas.isInBounds(new Point(5,5)));
  }

  @Test
  public void shouldReturnPixelIsOutOfBounds() {
    assertFalse(canvas.isInBounds(new Point(0,0)));
    assertFalse(canvas.isInBounds(new Point(11,11)));
  }

  @Test
  public void shouldRenderCurrentState() {
    canvas.renderCurrentState();

    verify(renderer).render(any());
  }

  @Test
  public void shouldClearCanvas() {
    canvas.setPixelValue(5,5,'x');

    canvas.clear();

    assertTrue(canvas.isEmpty(new Point(5,5)));
  }
}