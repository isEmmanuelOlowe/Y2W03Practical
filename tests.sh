#!/bin/bash

#since adding files to src breaks stacscheck this is a quick fix to allow for junit tests aswell as maintain all functionality
cp junit-tests/* src
cd src
javac -cp "junit-4.12.jar:hamcrest-core-1.3.jar:." *.java
java -cp "junit-4.12.jar:hamcrest-core-1.3.jar:." org.junit.runner.JUnitCore FSMTests
rm junit-4.12.jar hamcrest-core-1.3.jar FSMTests.java
