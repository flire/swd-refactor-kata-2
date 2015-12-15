package task1;

import generators.ConnectionSuccessGenerator;

public class TelemetryDiagnosticControls
{
  private final String DiagnosticChannelConnectionString = "*111#";

  private final TelemetryClient telemetryClient;
  private String diagnosticInfo = "";

  public TelemetryDiagnosticControls(ConnectionSuccessGenerator generator)
  {
    telemetryClient = new TelemetryClient(generator);
  }

  public String getDiagnosticInfo(){
    return diagnosticInfo;
  }

  protected void setDiagnosticInfo(String diagnosticInfo){
    this.diagnosticInfo = diagnosticInfo;
  }

  public boolean checkTransmission()
  {
    diagnosticInfo = "";

    telemetryClient.disconnect();

    int retryLeft = 3;
    while (telemetryClient.getOnlineStatus() == false && retryLeft > 0)
    {
      telemetryClient.connect(DiagnosticChannelConnectionString);
      retryLeft -= 1;
    }

    if(telemetryClient.getOnlineStatus() == false)
    {
      return false;
    }
    try {
	    telemetryClient.send(TelemetryClient.DIAGNOSTIC_MESSAGE);
	    diagnosticInfo = telemetryClient.receive();
    } catch (IllegalArgumentException e) {
    	return false;
    }
    return true;
  }
}