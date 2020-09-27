package factory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import commands.BucketFill;
import commands.CreateCanvas;
import commands.DrawLine;
import commands.DrawRectangle;
import commands.QuitCommand;
import org.junit.jupiter.api.Test;
import renderer.Renderer;

class CommandFactoryTest {
  @Test
  public void shouldCreateCommands() {
    CommandFactory commandFactory = new CommandFactory();
    Renderer renderer = mock(Renderer.class);

    assertTrue(commandFactory.create('C',renderer, 1,2) instanceof CreateCanvas);
    assertTrue(commandFactory.create('L',renderer, 1,2,3,4) instanceof DrawLine);
    assertTrue(commandFactory.create('R',renderer, 1,2,3,4) instanceof DrawRectangle);
    assertTrue(commandFactory.create('Q', renderer) instanceof QuitCommand);
    assertTrue(commandFactory.create('B',renderer, 1, 2, 22) instanceof BucketFill);
  }

  @Test()
  public void shouldThrowExceptionForInvalidCommand() {
    Renderer renderer = mock(Renderer.class);
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new CommandFactory().create('X', renderer);
    });
  }
}