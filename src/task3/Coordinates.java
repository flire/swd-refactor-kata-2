package task3;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {
	private static final String POINT_FORMAT = "%d x %d %c";

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
	
	protected void moveAlong(int axis, int direction) {
		if (hasObstacleAtCurrentPoint()) return;
		Point axisPoint;
		if (axis == 0) {
			axisPoint = x;
		} else {
			axisPoint = y;
		}
		
		axisPoint.setLocation(axisPoint.getLocation() + direction);
		if (axisPoint.getLocation() < 0) {
			axisPoint.setLocation(axisPoint.getMaxLocation());
		}
		if (axisPoint.getLocation() > axisPoint.getMaxLocation()) {
			axisPoint.setLocation(0);
		}
	}
	
	protected void turnLeft() {
		switch(direction) {
		case NORTH:
			direction = Direction.WEST;
			break;
		case SOUTH:
			direction = Direction.EAST;
			break;
		case EAST:
			direction = Direction.NORTH;
			break;
		case WEST:
			direction = Direction.SOUTH;
			break;
		}
	}
	
	protected void turnRight() {
		switch(direction) {
		case NORTH:
			direction = Direction.EAST;
			break;
		case SOUTH:
			direction = Direction.WEST;
			break;
		case EAST:
			direction = Direction.SOUTH;
			break;
		case WEST:
			direction = Direction.NORTH;
			break;
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