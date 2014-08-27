#!/bin/bash

CLASSPATH=""

for jarFileName in `ls ../lib`
do
    CLASSPATH+=:../lib/$jarFileName
done

CLASSPATH+=:../samples-jdk.jar

export CLASSPATH

java org.junit.runner.JUnitCore $@
