#!/bin/bash

#./Proj3c.sh <file> <directory>

# Unzip downloaded file into a data directory that is named by the user under the current working directory
# 

# Create a sub-directory under the data directory for each student and name it by the student's username in the prefix
# 

if [ $# -lt 2 ]
	then
		echo "Not enough arguments, see --help to see usage."
		exit 1
fi

if [ ${2}: -4 !=  .zip ]
	then
		echo "2nd parameter must be a .zip file"
		exit 1
fi

mkdir -p "$3"

unzip -d "$3" "$2"