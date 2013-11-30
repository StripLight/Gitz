@echo off

rem
rem Change the path used to the location of your Gitz! install.
rem


set %GITZ_LIB_PATH%=C:/temp
set LOG_OPTS=-Dlog4j.configuration=file:%GITZ_LIB_PATH%/log4j.properties
set CP=%GITZ_LIB_PATH%/gitz-core-1.0.0.jar;%GITZ_LIB_PATH%/log4j-1.2.16.jar

java %LOG_OPTS% -cp %CP% org.gitz.cli.Gitz %*
