#! /bin/tcsh

#TODO: check if input is a number

if ( $#argv == 0 || $#argv > 7) then
	echo "Wrong number of inputs."
	exit 1
endif

set sum = 0
set count = 0
set avg = 0
foreach i ($argv[*])
	@ sum += $argv[i]
	@ count++
	echo $i
end
@ avg = $sum / $count

echo "Average of inputs: $avg"
echo "Sum of inputs: $sum"
