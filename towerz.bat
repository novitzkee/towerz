@echo off

call ./mvnw.cmd package -Dmaven.test.skip
start /b java -jar target/towerz-1.0-SNAPSHOT.jar