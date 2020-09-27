package factory;

public enum Command {
  CREATE_CANVAS('C'), DRAW_LINE('L'), DRAW_RECTANGLE('R'), BUCKET_FILL('B'), QUIT('Q');
  private final char id;

  Command(char id) {
    this.id = id;
  }

  public char getId() {
    return id;
  }
}
