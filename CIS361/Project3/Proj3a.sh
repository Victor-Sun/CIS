#!/bin/bash

currDir="$(pwd)"
junkDir="${HOME}/.junk"

show_help(){
	cat <<EOF
NAME
	Proj3a.sh - move files to junk folder

SYNOPSIS
	Proj3a.sh [OPTION] ... [FILE] ...

DESCRIPTION
	Move FILE(S) to the .junk folder
	Multiple optiosn can be used, and options and the file can be used

OPTIONS
	Move the file to the .junk folder

	-l
		Lists the files in the .junk folder
	-n
		Display the number of files inside the junk folder

Author
	Written by 

COPYRIGHT
	NO COPYRIGHT
EOF
}

if [ $# -eq 0 ]
	then
		echo "Not enough arguments, use --help to see usage."
		exit 1
fi

#for cmdArg in $*
for cmdArg in "$@"
do
	case "$cmdArg" in
		"-l") optionL=1;;
		"-n") optionN=1;;
		"--help") optionH=1;;
		*) list="$list $cmdArg";;
	esac
done

if [ "$optionH" = "1" ]
	then
		show_help
		exit
fi

if [ ! -d "$junkDir" ]
	then
		mkdir -p "$junkDir"
fi

if [ "$optionL" = "1" ]
	then
		echo "Files inside junk directory: "
		ls -al "$junkDir"
fi

if [ "$optionN" = "1" ]
	then
		N="$(find "$junkDir" -type f | wc -l)"
		echo "Number of files in junk directory: $N"
fi

if [ -n "$list" ]
	then 
		for file in $list
		do
			if [ -d "$file" ]
				then
				echo "Directories are now allowed to be moved!"
				echo "Use --help for usage information."
			else
				mv "$file" "$junkDir"
			fi
		done
	exit 0
fi
