#!/bin/bash

# Funzione per ottenere la versione da un file JAR
get_version() {
    jar_file=$1
    version=$(unzip -p $jar_file META-INF/MANIFEST.MF | grep 'Implementation-Version' | cut -d' ' -f2)
    echo "Implementation-Version: $version"
}

# Mostro le versioni delle librerie JUnit
echo "Mostrando le versioni delle librerie JUnit e Hamcrest-Core:"
get_version "JUnit/junit-4.13.2.jar"
get_version "JUnit/hamcrest-core-1.3.jar"

# Compilo i file Java
javac -cp .:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar myAdapter/*.java myTest/*.java

# Eseguo i test
java -cp .:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar myTest.TestRunner

# Messaggi di errore o successo stampati da TestRunner.java