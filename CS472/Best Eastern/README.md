# Best Eastern
To run on linux
```
./run.sh
```
Or on windows
```
mvnw clean package cargo:run
```

Once the runtime starts, you can access the project at <br>
http://localhost:8080/best-eastern/pages<br>
OR go to<br>
https://best-eastern.enmucs.com<br>
For the live hosted version<br><br>

Report 
To run on linux
```
./report.sh
```
Or on windows
```
mvnw clean test jacoco:report
```
When running you will most likely get the error named CHECK_FILE_PATH <br>
to resolve this go into     
```
./src/main/java/controllers/Controller.java
```
and change 
```
private static final String filePath = "YOUR_FILE_PATH_TO_DATABASE_FOLDER";
```
After this has been changed you should be able to go back up to the beginining and run it with no issues.
