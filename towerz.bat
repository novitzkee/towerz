@echo off

call ./mvnw.cmd package
start /b java -jar target/towerz-1.0-SNAPSHOT.jar