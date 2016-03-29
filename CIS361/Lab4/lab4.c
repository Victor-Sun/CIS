#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define LEN 4

char* strnsub (char *p, int n);

int main(){
	char line[] = "His textbook was bought from that bookstore";  
	char *p1, *p2;

	//set p1 to the beginning of string line;
	p1 = line;

	while(strlen(p1) >= LEN){
		//set p2 to the position immediately after p1
		p2 = p1;
		p2++;

		while(strlen(p2) >= LEN){
			if(strcmp(strnsub(p1,LEN), strnsub(p2,LEN)) == 0) {
				goto done;	
			}
			p2++;
		}
		p1++;
	}

done:	printf ("the first substring: %s\n", strnsub(p1, LEN));
	printf ("the second substring: %s\n", strnsub(p2, LEN));

	return 0;
}


// returns a string with the first n characters of string p

char* strnsub (char *p, int n)
{
	// write function definition here
	char* t;
	t =	(char*) malloc((n + 1) *sizeof (char));

	strncpy(t, p, n);
//	for(i = 0; i < n; i++){
//		*x = *p;
//		x++;
//		p++;
//	}
	return t;
}
