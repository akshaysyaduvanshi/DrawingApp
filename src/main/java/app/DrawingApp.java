package app;

import commands.CanvasCommand;
import factory.CommandFactory;
import factory.InputValidatorFactory;
import models.Canvas;
import renderer.Renderer;
import validator.InputValidator;

public class DrawingApp {
  private Canvas canvas;
  private final CommandFactory commandFactory;
  private final InputValidatorFactory inputValidatorFactory;
  private final Renderer renderer;

  public DrawingApp(CommandFactory commandFactory, InputValidatorFactory inputValidatorFactory, Renderer renderer) {
    this.commandFactory = commandFactory;
    this.inputValidatorFactory = inputValidatorFactory;
    this.renderer = renderer;
  }

  public void executeCommand(char command, int... arguments) {
    InputValidator inputValidator = inputValidatorFactory.create(command);
    inputValidator.validate(canvas, arguments);

    CanvasCommand canvasCommand = commandFactory.create(command,renderer, arguments);
    canvas = canvasCommand.execute(canvas);
    canvas.renderCurrentState();
  }
}

