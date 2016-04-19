#!/bin/bash

die(){
    echo "$@"
    exit 1
}

if [ $# != 1 ]
    then
        echo 'Incorrect usage, please use:'
        echo './Proj3c.sh <file>'
        exit 1
fi

zipFile="$1"
dataDir="data"

if [[ "$zipFile" != *.zip ]]
    then
        echo "Expected a zip file, received: $zipFile"
        exit 1
fi

mkdir -p "$dataDir" || die "Cannot create data directory: $dataDir"
unzip -d "$dataDir" "$zipFile" || die "Cannot unzip $zipFile"

# Moving and renaming the files.
main() {

for filename in "$dataDir"/*_attempt_*; do
    username="$(echo "$filename" | sed 's/^.*_\([a-z0-9]\+\)_attempt_.*$/\1/')"
    shortname="$(echo "$filename" | sed 's/^.*_attempt_[-0-9]\+_\?\(.*\)$/\1/')"
        if [ "$username" = "$filename" ]; then
            echo "Could not extract the username from: $filename"
            continue
        fi
        if [ "$shortname" = "$filename" ]; then
            echo "Could not extract the original filename from: $filename"
            continue
        fi

        if [ "$shortname" = ".txt" ]; then
            shortname="memo.txt"
        fi
        userdir="${dataDir}/${username}"
        mkdir -p "$userdir" || die "Could not create directory: $userdir"
        mv "$filename" "${userdir}/${shortname}" || die "Could not move file: $filename"
done

for userdir in "$dataDir"/*/; do
    make -f ../../makefile -C "$userdir" \
    && make test -f ../../makefile -C "$userdir"
done

}

main | tee report.txt