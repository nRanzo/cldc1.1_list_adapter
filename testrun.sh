#!/bin/bash

# Function to obtain the version from a JAR file
get_version() {
    jar_file=$1
    version=$(unzip -p $jar_file META-INF/MANIFEST.MF | grep 'Implementation-Version' | cut -d' ' -f2)
    echo "Implementation-Version: $version"
}

# Schowing versions of the JUnit libraries
echo "Mostrando le versioni delle librerie JUnit e Hamcrest-Core:"
get_version "JUnit/junit-4.13.2.jar"
get_version "JUnit/hamcrest-core-1.3.jar"

# Compiling Java files
javac -cp .:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar myAdapter/*.java myTest/*.java

# Running Tests
java -cp .:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar myTest.TestRunner
