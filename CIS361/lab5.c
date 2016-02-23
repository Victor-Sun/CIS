#include <stdio.h>
#include <stdlib.h>

struct Mark {
	int x;
	int y;
};

typedef struct Mark Mark;

/* load the structure that p points to with the data from f */
void getInfo (FILE * f, Mark * p);

/* write the data stored in structure item into output file f */
void printInfo (FILE * f, Mark item);

/* compare what pointers a and b point to; to be used by qsort() */
int compare (const void * a, const void * b);

int main()
{
	Mark list[100];
	Mark mark;
	int size = 0, i, col = 0;
	FILE * fin;

	fin = fopen ("lab5.dat", "r");
		if ( fin == NULL ){
		printf ("Cannot open file.\n");
		exit(1);
	}

	while (!feof(fin)){
		getInfo (fin, &mark);
		list[size++] = mark;
	}

	// use qsort() to sort data in list
	qsort(list, size, sizeof(list[0]), compare);
	
	for (i = 0; i < size; i++){
		printInfo (stdout, list[i]);
		if ( ++col % 5 == 0 )
			printf("\n");
	}

	fclose (fin);

	return 0;
}

/* complete the following helper functions */

void getInfo (FILE * f, Mark * p){
	fscanf(f, "%i %i ", &p->x, &p->y);
}

void printInfo (FILE * f, Mark item){
	// display each mark in format of (x, y) 
	// and five marks per line 
	printf("(%d %d) ", item.x, item.y);
}

int compare (const void * a, const void * b){
	
	int ax = (((Mark*)a)->x);
	int ay = (((Mark*)a)->y);
	int bx = (((Mark*)b)->x);
	int by = (((Mark*)b)->y);

	if (ay > by)
		return -1;
	else if (ay < by)
		return 1;
	else if (ax < bx)
		return -1;
	else if (ax > bx)
		return 1;
	return 0;
}
