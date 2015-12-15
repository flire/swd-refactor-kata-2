package generators;

public class AlwaysSuccessGenerator implements ConnectionSuccessGenerator {

	@Override
	public boolean getNextResult() {
		return true;
	}

}
