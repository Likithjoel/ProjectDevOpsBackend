#!/bin/bash
cd target
java -jar projectDevOps-0.0.1-SNAPSHOT.war & echo $! > ./pid.file &
