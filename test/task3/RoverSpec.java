package task3;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RoverSpec {
  private Rover rover;
  private Coordinates roverCoordinates;
  private final Direction direction = Direction.NORTH;
  private Point x;
  private Point y;
  private List<Obstacle> obstacles = new ArrayList<>();

  @Before
  public void beforeRoverTest() {
    x = new Point(1, 9);
    y = new Point(2, 9);
    roverCoordinates = new Coordinates(x, y, direction, obstacles);
    rover = new Rover(roverCoordinates);
  }

  @Test
  public void testInitialCoordinatesAreSet() {
    assertEquals(roverCoordinates, rover.getCoordinates());
  }

  @Test
  public void testMoveForward() throws Exception {
    int expected = y.getLocation() - 1;
    rover.receiveSingleCommand('F');
    assertEquals(expected, rover.getCoordinates().getY().getLocation());
  }

  @Test
  public void testMoveBackwards() throws Exception {
    int expected = y.getLocation() + 1;
    rover.receiveSingleCommand('B');
    assertEquals(expected, rover.getCoordinates().getY().getLocation());
  }

  @Test
  public void testTurnLeft() throws Exception {
    rover.receiveSingleCommand('L');
    assertEquals(Direction.WEST, rover.getCoordinates().getDirection());
  }

  @Test
  public void testTurnRight() throws Exception {
    rover.receiveSingleCommand('R');
    assertEquals(Direction.EAST, rover.getCoordinates().getDirection());
  }

  @Test
  public void testCommandIgnoreCase() throws Exception {
    rover.receiveSingleCommand('r');
    assertEquals(Direction.EAST, rover.getCoordinates().getDirection());
  }

  @Test(expected = Exception.class)
  public void testUnknownCommand() throws Exception {
    rover.receiveSingleCommand('X');
  }

  @Test
  public void testMultipleCommands() throws Exception {
    int expected = x.getLocation() + 1;
    rover.receiveCommands("RFR");
    assertEquals(expected, rover.getCoordinates().getX().getLocation());
    assertEquals(Direction.SOUTH, rover.getCoordinates().getDirection());
  }

  @Test
  public void testMapIsEdgeClosed() throws Exception {
    int expected = x.getMaxLocation() + x.getLocation() - 2;
    rover.receiveCommands("LFFF");
    assertEquals(expected, rover.getCoordinates().getX().getLocation());
  }

  @Test
  public void testHaltOnObstacle() throws Exception {
    int expected = x.getLocation() + 1;
    rover.getCoordinates().setObstacles(Arrays.asList(new Obstacle(expected, y.getLocation())));
    rover.getCoordinates().setDirection(Direction.EAST);
    rover.receiveCommands("FFFRF");
    assertEquals(expected, rover.getCoordinates().getX().getLocation());
    assertEquals(Direction.EAST, rover.getCoordinates().getDirection());
  }

  @Test
  public void testPositionFormat() throws Exception {
    rover.receiveCommands("LFFFRFF");
    assertEquals("8 X 0 N", rover.getPosition());
  }

  @Test
  public void testNOKOnObstacle() throws Exception {
    rover.getCoordinates().setObstacles(Arrays.asList(new Obstacle(x.getLocation() + 1, y.getLocation())));
    rover.getCoordinates().setDirection(Direction.EAST);
    rover.receiveCommands("F");
    assertEquals("NOK", rover.getPosition());
  }
}