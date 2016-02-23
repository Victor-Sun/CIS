// lab3.c - measure execution time of C code

#include <stdlib.h>
#include <stdio.h>
#include <time.h>

int main ()
{
	const int MaxSize = 1000;
	int i, j, temp;

	clock_t start = clock();

	// Part one: processing a statically allocated array

	int staticArray[MaxSize];		// declare an array 

	for (i=0; i<MaxSize; i++)		// initialize the array with a
		staticArray[i] = MaxSize - i;	// descending sequence of values

	for (i = 0; i < MaxSize - 1; i++)		// bubble sort data in the array
		for (j = MaxSize - 1; j > i; j--)
			if (staticArray[j - 1] > staticArray[j]){
				temp = staticArray[j - 1];
				staticArray[j - 1] = staticArray[j];
				staticArray[j] = temp;
			}

	clock_t ending = clock();
	// Part two: processing a dynamically allocated array

	clock_t begin = clock();

	int *dynamicArray;
	dynamicArray = (int*) malloc(MaxSize *sizeof (int));



	for (i = 0; i < MaxSize; i++){
		(*(dynamicArray + i)) = MaxSize - i;
	}

	for (i = 0; i < MaxSize - 1; i++){
		for (j = MaxSize-1; j > i; j--){
			if ((*(dynamicArray + (j - 1))) > (*(dynamicArray + j))){

				temp = (*(dynamicArray + (j - 1)));
				(*(dynamicArray + (j - 1))) = (*(dynamicArray + j));
				(*(dynamicArray + j)) = temp;
			}
		}
	}

	clock_t end = clock();


	// Display the amount of time used for each part above

	double staticTotal = (double)(ending - start) / CLOCKS_PER_SEC;
	double dynamicTotal = (double)(end - begin) / CLOCKS_PER_SEC;

	printf( "Time used for Static Array: %lf%s\n", staticTotal, " seconds");
	printf( "Time used for Dynamic Array: %lf%s\n", dynamicTotal, " seconds");

	if(staticTotal > dynamicTotal){
		printf( "Static Array was faster by: ", staticTotal - dynamicTotal, " seconds");
	}
	else{
		
	}

	return 0;
}
