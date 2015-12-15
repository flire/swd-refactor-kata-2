package task3;

public class Rover {
	private static final char FORWARD = 'F';
	private static final char BACKWARD = 'B';
	private static final char LEFT = 'L';
	private static final char RIGHT = 'R';
	private static final String NOK_STATUS = "NOK";
	private final Coordinates coordinates;

	public Rover(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void receiveSingleCommand(char command) {
    switch (command) {
	    case FORWARD:
	    	coordinates.moveAlong(0, 1);
	    	break;
	    case BACKWARD:
	    	coordinates.moveAlong(0, -1);
	    	break;
	    case LEFT:
	    	coordinates.turnLeft();
	    	break;
	    case RIGHT:
	    	coordinates.turnRight();
	    	break;
	    default:
	    	throw new IllegalArgumentException("Unknown command: "+ Character.toString(command));
    }
  }

	public void receiveCommands(String commands) {
		for (char command: commands.toCharArray()) {
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
