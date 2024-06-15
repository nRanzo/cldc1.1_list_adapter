@echo off

rem Funzione per ottenere la versione da un file JAR
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

rem Mostra le versioni delle librerie JUnit e Hamcrest-Core
echo Mostrando le versioni delle librerie JUnit e Hamcrest-Core:
call :GET_VERSION "JUnit\junit-4.13.2.jar"
call :GET_VERSION "JUnit\hamcrest-core-1.3.jar"

rem Compila i file Java
"C:\Program Files\Java\jdk-22\bin\javac.exe" -cp .;JUnit\junit-4.13.2.jar;JUnit\hamcrest-core-1.3.jar myAdapter\*.java myTest\*.java

rem Verifica se la compilazione è andata a buon fine
if %ERRORLEVEL% neq 0 (
    echo Compilazione fallita.
    exit /b 1
)

rem Esegui i test
"C:\Program Files\Java\jdk-22\bin\java.exe" -cp .;JUnit\junit-4.13.2.jar;JUnit\hamcrest-core-1.3.jar myTest.TestRunner

rem Verifica se i test sono andati a buon fine
if %ERRORLEVEL% neq 0 (
    echo Alcuni test sono falliti. Controlla l'output sopra per i dettagli.
    exit /b 1
) else (
    echo Tutti i test sono passati con successo.
)
