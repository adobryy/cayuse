Cayuse Challenge Micro service
===============================

Cayuse Challenge Micro service API Project
## Created by
Aleksey Dobryy, Portland OR on April 21 2019


## API Instructions
Make application run  and after that make call at browser or "Postment" (GET) with URL like
 **http://localhost:8080/api?zip=97214**
 substituting given value at example with any valid ZIP code.
 Returned message will look like:
 **At the location Portland, the temperature is 12.68 C, the timezone is Pacific Daylight Time, and the elevation is 3 meter(s).**
  
        
        
## Run Instructions
You can start the application from your 'IntelliJ IDEA' IDE by running the main method in **com.cayuse.challenge.ChallengeApplication**
This applicatoin is using annotation library LOMBOK. For successful compilation you wil need to  enable annotatons in compiler:
CTRL-ALTR-S  -> "Build, Execution ..." -> Compiler -> "Annotation Processor" -> check "enable annotatoin Processing", "Apply", "OK"



## Logging

By default, you will see log levels `INFO` or higher, if you want to see all the debug logs, uncomment property in application.properties

        logging.level.com.cayuse.challenge=DEBUG  

You can set whichever packages you want to debug, that example will give you all of the `DEBUG` logging for `com.cayuse.challeng.*` classes.


