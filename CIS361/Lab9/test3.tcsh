#! /bin/tcsh

set num = 0
echo -n "Enter number of elements in a list: "
set num = "$<"
echo "Limit is $num"
echo -n "Enter a few numbers into an array: "
set list = ($<)
echo "Input is $list"

if ( $num != $#list) then
	echo "Number of elements does not match length of array!"
	exit 1
endif

set max = 0
set sum = 0
set count = 0
set avg = 0
set index = 1
while ( $index <= $num )
	if ( $list[$index] > $max ) then
		@ max = $list[$index]
	endif 	
	@ index++
end

echo "Largest input: $max"
