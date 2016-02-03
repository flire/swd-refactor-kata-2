package task3;

public class Rover {
	private static final char FORWARD = 'f';
	private static final char BACKWARD = 'b';
	private static final char LEFT = 'l';
	private static final char RIGHT = 'r';
	private static final String NOK_STATUS = "NOK";
	private final Coordinates coordinates;

	public Rover(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void receiveSingleCommand(char command) {
		if (coordinates.hasObstacleAtCurrentPoint()) {
			return;
		}
		switch (Character.toLowerCase(command)) {
		case FORWARD:
			coordinates.moveForward();
			break;
		case BACKWARD:
			coordinates.moveBackwards();
			break;
		case LEFT:
			coordinates.turnLeft();
			break;
		case RIGHT:
			coordinates.turnRight();
			break;
		default:
			throw new IllegalArgumentException("Unknown command: " + Character.toString(command));
		}
	}

	public void receiveCommands(String commands) {
		for (char command : commands.toCharArray()) {
			receiveSingleCommand(command);
		}
	}

	public String getPosition() {
		if (coordinates.hasObstacleAtCurrentPoint()) {
			return NOK_STATUS;
		}
		return coordinates.toString();
	}
}
