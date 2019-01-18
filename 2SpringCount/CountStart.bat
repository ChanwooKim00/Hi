@ECHO ON

SET ROOT_PATH=C:\Users\meta\git\repository\2SpringCount

SET CODE_HOME=%ROOT_PATH%/target/classes

SET LIB_HOME=%ROOT_PATH%/lib

SET CLASSPATH=%CODE_HOME%;%LIB_HOME%/*;

REM spring-core-3.2.3.RELEASE.jar;spring-beans-3.2.3.RELEASE.jar;commons-logging-1.1.1.jar;spring-expression-3.1.3.RELEASE.jar;



ECHO java CountTxt.StartMain %1
java CountTxt.StartMain %1

pause>nul





