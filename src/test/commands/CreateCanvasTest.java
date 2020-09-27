package commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import models.Canvas;
import models.Point;
import org.junit.jupiter.api.Test;
import renderer.Renderer;


class CreateCanvasTest {

  @Test
  public void shouldCreateCanvasWithGivenArguments() {
    Renderer renderer = mock(Renderer.class);

    Canvas canvas = new CreateCanvas(10, 10, renderer).execute(null);

    assertNotNull(canvas);
    assertTrue(canvas.isInBounds(new Point(10,10)));
    assertTrue(canvas.isInBounds(new Point(1,1)));

    assertFalse(canvas.isInBounds(new Point(11,1)));
    assertFalse(canvas.isInBounds(new Point(1,11)));

    assertFalse(canvas.isInBounds(new Point(0,1)));
    assertFalse(canvas.isInBounds(new Point(1,0)));
  }
}