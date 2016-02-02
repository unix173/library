#!/bin/bash

mvn clean && mvn package && java -jar target/ZLibrary-1.0-SNAPSHOT.jar server ZLibrary.yml
