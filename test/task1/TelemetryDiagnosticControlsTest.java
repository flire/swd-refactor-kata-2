package task1;

import static org.junit.Assert.*;

import org.junit.Test;

import generators.AlwaysFailureGenerator;
import generators.AlwaysSuccessGenerator;

public class TelemetryDiagnosticControlsTest {
	// your test go here

	@Test
	public void checkErrorConnection() {
		TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(new AlwaysFailureGenerator());
		assertFalse(telemetryDiagnosticControls.checkTransmission());
		assertTrue(telemetryDiagnosticControls.getDiagnosticInfo().isEmpty());
	}
	
	@Test
	public void checkSuccessConnection() {
		TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(new AlwaysSuccessGenerator());
		assertTrue(telemetryDiagnosticControls.checkTransmission());
		assertFalse(telemetryDiagnosticControls.getDiagnosticInfo().isEmpty());
	}
}
