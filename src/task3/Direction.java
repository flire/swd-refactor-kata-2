package task3;

public enum Direction {
	NORTH, EAST, SOUTH, WEST;

	public Direction turnLeft() {
		switch (this) {
		case NORTH:
			return Direction.WEST;
		case SOUTH:
			return Direction.EAST;
		case EAST:
			return Direction.NORTH;
		case WEST:
			return Direction.SOUTH;
		default:
			throw new RuntimeException("Unknown direction");
		}
	}

	protected Direction turnRight() {
		switch (this) {
		case NORTH:
			return Direction.EAST;
		case SOUTH:
			return Direction.WEST;
		case EAST:
			return Direction.SOUTH;
		case WEST:
			return Direction.NORTH;
		default:
			throw new RuntimeException("Unknown direction");
		}
	}
}
