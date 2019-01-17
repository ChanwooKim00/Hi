@ECHO ON

SET ROOT_PATH=C:\Users\meta\git\repository\2SpringCount

SET BIN_HOME=%ROOT_PATH%/target/classes

SET LIB_HOME=%ROOT_PATH%/lib

SET CLASSPATH=%BIN_HOME%;%LIB_HOME%/spring-context-3.2.3.RELEASE.jar;%LIB_HOME%/spring-core-3.2.3.RELEASE.jar;%LIB_HOME%/spring-beans-3.2.3.RELEASE.jar;%LIB_HOME%/commons-logging-1.1.1.jar;%LIB_HOME%/spring-expression-3.1.3.RELEASE.jar;



ECHO java CountTxt.StartMain %1
java CountTxt.StartMain %1

pause>nul