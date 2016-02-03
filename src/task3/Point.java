package task3;

public class Point {
	@Override
	public String toString() {
		return Integer.toString(location);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + location;
		result = prime * result + maxLocation;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (location != other.location)
			return false;
		if (maxLocation != other.maxLocation)
			return false;
		return true;
	}

	private int location;

	public void setLocation(int value) {
		location = value;
	}

	public int getLocation() {
		return location;
	}

	private int maxLocation;

	public void setMaxLocation(int value) {
		maxLocation = value;
	}

	public int getMaxLocation() {
		return maxLocation;
	}

	public Point(int locationValue, int maxLocationValue) {
		setLocation(locationValue);
		setMaxLocation(maxLocationValue);
	}
	
	public void add(int value) {
		location+=value;
		shrink();
	}
	
	public void subtract(int value) {
		location-=value;
		shrink();
	}
	
	private void shrink() {
		while (location > maxLocation) {
			location -= ( maxLocation + 1);
		}
		while (location < 0) {
			location += ( maxLocation + 1);
		}
	}
}