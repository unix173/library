#!/bin/bash

mvn compile && java -jar target/ZLibrary-1.0-SNAPSHOT.jar server ZLibrary.yml
