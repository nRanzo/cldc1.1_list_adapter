@echo off

rem Function to obtain the version from a JAR file
:GET_VERSION
setlocal
set "jar_file=%1"
for /f "tokens=2 delims= " %%a in ('"unzip -p %jar_file% META-INF/MANIFEST.MF | findstr Implementation-Version"') do set version=%%a
echo Implementation-Version: %version%
goto :eof

echo Schowing the version of the HUnit and Hamcrest_Core libraries:
call :GET_VERSION "JUnit\junit-4.13.2.jar"
call :GET_VERSION "JUnit\hamcrest-core-1.3.jar"

rem Compiling Java files
javac -cp .;JUnit\junit-4.13.2.jar;JUnit\hamcrest-core-1.3.jar myAdapter\*.java myTest\*.java

rem Running tests
java -cp .;JUnit\junit-4.13.2.jar;JUnit\hamcrest-core-1.3.jar myTest.TestRunner
