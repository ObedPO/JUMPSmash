// Obed and Aldenis

public class Game
{
  private Grid grid;
  private int userRow;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  
  public Game()
  {
    grid = new Grid(5, 10);
    userRow = 0;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, 0), "user.gif");
  }
  
  public void play()
  {
    while (!isGameOver())
    {
      grid.pause(100);
      handleKeyPress();
      if (msElapsed % 300 == 0)
      {
        scrollLeft();
        populateRightEdge();
      }
      updateTitle();
      msElapsed += 100;
    }
  }
  
  public void handleKeyPress()
  {
    int key = grid.checkLastKeyPressed();
    if(key == 38 && userRow > 0){
      grid.setImage(new Location(userRow, 0), null);
      userRow--;
      grid.setImage(new Location(userRow, 0), "user.gif");

    }else if(key == 40 && userRow < grid.getNumRows() - 1){
      grid.setImage(new Location(userRow, 0), null);
      userRow++;
      grid.setImage(new Location(userRow, 0), "user.gif");

    }
  }
  
  public void populateRightEdge()
  {
    int row = (int) (Math.random()*5);
    String file = "";
    int pop = (int) (Math.random()*2);
    if(pop == 0)
      file = "get.gif";
    else
      file = "avoid.gif";
    grid.setImage(new Location(row,grid.getNumCols()-1),file);
  }
  
  public void scrollLeft()
  {
  }
  
  public void handleCollision(Location loc)
  {
  }
  
  public int getScore()
  {
    return 0;
  }
  
  public void updateTitle()
  {
    grid.setTitle("Game:  " + getScore());
  }
  
  public boolean isGameOver()
  {
    return false;
  }
  
  public static void test()
  {
    Game game = new Game();
    //game.play();
    game.populateRightEdge();
  }
  
  public static void main(String[] args)
  {
    test();
  }
}