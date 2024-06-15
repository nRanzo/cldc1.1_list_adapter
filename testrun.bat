@echo off

rem Function to obtain the version from a JAR file
:GET_VERSION
setlocal
set "jar_file=%1"
for /f "delims=" %%A in ('"C:\Program Files\Java\jdk-22\bin\jar.exe" -xvf %jar_file% META-INF/MANIFEST.MF') do (
    for /f "delims=" %%B in ('type META-INF\MANIFEST.MF ^| findstr /B /C:"Implementation-Version"') do (
        echo %%B
    )
)
rd /s /q META-INF
endlocal
goto :eof

rem Schowing the version of the HUnit and Hamcrest_Core libraries
echo Schowing the version of the HUnit and Hamcrest_Core libraries:
call :GET_VERSION "JUnit\junit-4.13.2.jar"
call :GET_VERSION "JUnit\hamcrest-core-1.3.jar"

rem Compiling Java files
"C:\Program Files\Java\jdk-22\bin\javac.exe" -cp .;JUnit\junit-4.13.2.jar;JUnit\hamcrest-core-1.3.jar myAdapter\*.java myTest\*.java

rem Verifying if compiling was successful
if %ERRORLEVEL% neq 0 (
    echo Compiling failed.
    exit /b 1
)

rem Running tests
"C:\Program Files\Java\jdk-22\bin\java.exe" -cp .;JUnit\junit-4.13.2.jar;JUnit\hamcrest-core-1.3.jar myTest.TestRunner

rem Verifying if tests were successful
if %ERRORLEVEL% neq 0 (
    echo Some tests failed. Please check the output up here for details.
    exit /b 1
) else (
    echo All tests passed with success.
)
