# swd-refactor-kata-2

1.Telemetry diagnostics client (0..1 point)
-------------------------------------------
Write tests for TelemetryDiagnosticControls.java. TelemetryDiagnosticControls uses TelemetryClient to establish
connection to telemetry server (in this example it is fake).
You can probably meet some restrictions of implementations to write tests - so feel free to
improve design to achieve your goal.

2. Video store (0..2 points)
----------------------------
In this kata you refactor video store domain model using existing tests.
Make code as simple and readable as it is just possible.

3. Mars Rover (0..2 points)
---------------------------
Create mars rover implementation based on existing RoverSpec.

Refactor hints
--------------

Bad smells to avoid:
* Method bodies too long
* Methods have big nesting (if, try/catch, ...)
* Class has too much responsibilities
* Duplicate code blocks
* Train crushes ( getScene().getFigure(figureId).getSkeleton().removeComponent("head")
* Methods with too many arguments
* Uncliear exception handling
* JDK utils underused