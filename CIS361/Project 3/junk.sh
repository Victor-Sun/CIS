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
		exit s1
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

mkdir -p $junkDir

if [ $optionL -eq 1 ]
	then
		F=$(ls -al $junkDir)
		echo "Files inside junk directory: $F"
fi

if [ $optionN -eq 1 ]
	then
		N=$(find $junkDir -type f | wc -l)
		echo "Number of files in junk directory: $N"
fi

if [ $optionH -eq 1]
	then
		show_help
fi

if [ -z $list ]
	then 
		for file in $list
		do
			if [ -d $file ]
				echo "Directories are now allowed to be moved!"
				echo "Use --help for usage information."
			else 
				if [ -f $file ]
					then
						mv $file $junkDir
				fi
			fi
		done
	exit 0
fi
