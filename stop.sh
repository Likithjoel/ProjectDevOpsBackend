#!/bin/bash
cd target
kill $(cat ./pid.file)
