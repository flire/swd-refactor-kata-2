package task3;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {
	private static final int STEP_BACKWARDS = -1;
	private static final int STEP_FORWARD = 1;
	private static final String POINT_FORMAT = "%d X %d %c";

	public Point getX() {
		return x;
	}

	public Point getY() {
		return y;
	}

	public void setDirection(Direction value) {
		direction = value;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setObstacles(List<Obstacle> value) {
		obstacles = new ArrayList<>(value);
	}

	public void turnLeft() {
		direction = direction.turnLeft();
	}

	protected void turnRight() {
		direction = direction.turnRight();
	}

	public void moveForward() {
		move(STEP_FORWARD);
	}

	public void moveBackwards() {
		move(STEP_BACKWARDS);
	}

	protected void move(int delta) {
		switch (direction) {
		case NORTH:
			y.subtract(delta);
			break;
		case SOUTH:
			y.add(delta);
			break;
		case EAST:
			x.add(delta);
			break;
		case WEST:
			x.subtract(delta);
			break;
		default:
			throw new RuntimeException("Unknown direction");
		}
	}

	public boolean hasObstacleAtCurrentPoint() {
		for (Obstacle obstacle : obstacles) {
			if (obstacle.x == x.getLocation() && obstacle.y == y.getLocation()) {
				return true;
			}
		}
		return false;
	}

	public Coordinates(Point xValue, Point yValue, Direction directionValue, List<Obstacle> obstaclesValue) {
		x = new Point(xValue.getLocation(), xValue.getMaxLocation());
		y = new Point(yValue.getLocation(), yValue.getMaxLocation());
		direction = directionValue;
		obstacles = new ArrayList<>(obstaclesValue);
	}

	@Override
	public String toString() {
		return String.format(POINT_FORMAT, x.getLocation(), y.getLocation(), direction.name().charAt(0));
	}

	private Point x;
	private Point y;
	private Direction direction;
	private ArrayList<Obstacle> obstacles;

}