package generators;

public class AlwaysFailureGenerator implements ConnectionSuccessGenerator {

	@Override
	public boolean getNextResult() {
		return false;
	}

}
