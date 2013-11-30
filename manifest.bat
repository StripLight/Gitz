@echo off

rem
rem Change the path used to the location of your Gitz! install
rem

set GITZ_LIB_PATH=C:/temp
set CP=%GITZ_LIB_PATH%/gitz-core-1.0.0.jar

if (%1)==() goto show

if NOT EXIST %1 goto badfile
SET GITZ_MANIFEST=%1
goto done

:badfile
echo File does not exist!
goto done

:show
java -cp %CP% org.gitz.cli.ReadManifest

:done
