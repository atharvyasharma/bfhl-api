@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM ...
@REM ----------------------------------------------------------------------------
@SET MAVEN_WRAPPER_PROPERTIES=.mvn\wrapper\maven-wrapper.properties
@SET MAVEN_WRAPPER_JAR=.mvn\wrapper\maven-wrapper.jar

@FIND /I "%MAVEN_WRAPPER_PROPERTIES%" > NUL 2>&1
@IF EXIST "%MAVEN_WRAPPER_PROPERTIES%" (
  @FOR /F "usebackq delims== tokens=1,2" %%a IN ("%MAVEN_WRAPPER_PROPERTIES%") DO (
    @IF "%%a"=="distributionUrl" SET DISTRIBUTION_URL=%%b
  )
)

@IF EXIST "%MAVEN_WRAPPER_JAR%" (
  java -jar "%MAVEN_WRAPPER_JAR%" %*
) ELSE (
  @ECHO Maven Wrapper JAR not found. Please run: mvn -N wrapper:wrapper
  @EXIT /B 1
)
