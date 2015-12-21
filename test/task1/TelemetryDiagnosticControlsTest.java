package task1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;

import generators.ConnectionSuccessGenerator;

public class TelemetryDiagnosticControlsTest {
	@Test(expected=Exception.class)
	public void testErrorConnection() throws Exception {
		ConnectionSuccessGenerator mock = mock(ConnectionSuccessGenerator.class);
		when(mock.getNextResult()).thenReturn(false);
		TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(new TelemetryClient(mock));
		telemetryDiagnosticControls.checkTransmission();
	}
	
	@Test
	public void testDataSendingAtThirdSuccess() throws Exception {
		ConnectionSuccessGenerator mockGenerator = mock(ConnectionSuccessGenerator.class);
		when(mockGenerator.getNextResult()).thenReturn(false, false, true);
		TelemetryClient realClient = new TelemetryClient(mockGenerator);
		TelemetryClient spy = spy(realClient);
		TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(spy);
		telemetryDiagnosticControls.checkTransmission();
		verify(spy).send(anyString());
	}
	
	@Test
	public void testDataSendingAtSecondSuccess() throws Exception {
		ConnectionSuccessGenerator mockGenerator = mock(ConnectionSuccessGenerator.class);
		when(mockGenerator.getNextResult()).thenReturn(false, true);
		TelemetryClient realClient = new TelemetryClient(mockGenerator);
		TelemetryClient spy = spy(realClient);
		TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(spy);
		telemetryDiagnosticControls.checkTransmission();
		verify(spy).send(anyString());
	}
	
	@Test
	public void testDataSendingAtFirstSuccess() throws Exception {
		ConnectionSuccessGenerator mockGenerator = mock(ConnectionSuccessGenerator.class);
		when(mockGenerator.getNextResult()).thenReturn(true);
		TelemetryClient realClient = new TelemetryClient(mockGenerator);
		TelemetryClient spy = spy(realClient);
		TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(spy);
		telemetryDiagnosticControls.checkTransmission();
		verify(spy).send(anyString());
	}
	
	@Test
	public void testDisconnectCalled() throws Exception {
		ConnectionSuccessGenerator mockGenerator = mock(ConnectionSuccessGenerator.class);
		when(mockGenerator.getNextResult()).thenReturn(true);
		TelemetryClient realClient = new TelemetryClient(mockGenerator);
		TelemetryClient spy = spy(realClient);
		TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(spy);
		telemetryDiagnosticControls.checkTransmission();
		verify(spy).disconnect();
	}
	
	@Test
	public void testProperResponseIfSucceeded() throws Exception {
		ConnectionSuccessGenerator mockGenerator = mock(ConnectionSuccessGenerator.class);
		when(mockGenerator.getNextResult()).thenReturn(true);
		TelemetryClient realClient = new TelemetryClient(mockGenerator);
		TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(realClient);
		telemetryDiagnosticControls.checkTransmission();
		assertEquals(TelemetryClient.TEST_DIAGNOSTIC_MESSAGE_RESULT,telemetryDiagnosticControls.getDiagnosticInfo());
	}
}
