## Drawing Application  <br />
Maven based project which runs on Java 11.

To start the application, run main method present in DrawingAppRunner class. Default input/output is from console<br/>
This app supports following commands

* C 20 4 - Creates a canvas of size 20*4
* L 1 2 6 2 - Draw line from point(1,2) to point(6,2)
* R 14 1 18 3 - Draw rectangle from upper left point(14,1) to bottom right point (18,3)
* B 10 3 o - Bucket fill at point (10,3) with color 'o'
* Q - Quit the application

DrawingAppTest - This class contains end to end test which uses TestRenderer rather than Console Renderer to test the end to end functionality. Unit tests are also present to test low level details.<br/>

Unit Tests are written using below frameworks
* Junit  - Test runner
* Mockito - Used for mocking the dependencies