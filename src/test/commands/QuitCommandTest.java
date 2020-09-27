package commands;

import static org.mockito.Mockito.verify;

import models.Canvas;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class QuitCommandTest {
  @Test
  public void shouldClearCanvas() {
    Canvas canvas = Mockito.mock(Canvas.class);

    new QuitCommand().execute(canvas);

    verify(canvas).clear();
  }
}