#!/bin/bash

currDir=$(pwd)
junkDir="~/.junk"

show_help(){
	cat <<EOF
TODO: Help message + Command options + Usage
EOF
}

if [ $# -eq 0 ]
	then
		echo "Not enough arguments, use --help to see usage."
		exit s
fi

for cmdArg in $*
do
	case $cmdArg in
		"-l") optionL=1;;
		"-n") optionN=1;;
		"--help") optionH=1;;
		*) list="$list $cmdArg";;
	esac
done

if [ ! -d $junkDir ]
	then
		mkdir -p $junkDir
fi

if [ "$optionL" = "1" ]
	then
		F=$(ls -al $junkDir)
		echo "Files inside junk directory: $F"
fi

if [ "$optionN" = "1" ]
	then
		N=$(find $junkDir -type f | wc -l)
		echo "Number of files in junk directory: $N"
fi

if [ "$optionH" = "1" ]
	then
		show_help
fi

if [ -z $list ]
	then 
		for file in $list
		do
			if [ -d $file ]
				then
				echo "Directories are now allowed to be moved!"
				echo "Use --help for usage information."
			else
				mv $file $junkDir
			fi
		done
	exit 0
fi
