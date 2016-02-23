// Project: Project 1 Automatic Caesar Cipher Breaker
// Name: Victor Sun

#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
/**
 * Store the Letter and the frequencies from the text document
 * @param given: Letter frequency
 * @param fname: File name
 */
void readFreq(float given[], char fname[]){
	FILE *f;
	char letter;
	float freq;
	int i;

	//fin = fopen("LetFreq.txt","r");
	f = fopen(fname,"r");
	if (f == NULL) {
		printf("File could not be opened. \n");
		exit(1);
	}

	for (i = 0; i < 26; i++) {
		if (fscanf(f, "%c %f\n", &letter, &freq) != 2) {
			printf("Error while reading the file.\n");
			exit(1);
		}
		if (letter != 'A' + i) {
			printf("Error: out of order letter: %c\n", letter);
			exit(1);
		}
		given[i] = freq;
		//printf("%lf\n", given[i]);
	}
	fclose(f);
}

//found: Store frequency data for accumulated letter frequency data
void calcFreq(float found[], char fname[]) {
	FILE *f;
	char letter;
	int i,k;
	int total = 0;
	int count[26];

	f = fopen(fname, "r");
	if (f == NULL) {
		printf("File could not be opened. 2\n");
		exit(1);
	}

	for (i = 0; i < 26; i++) {
		count[i] = 0;
		//printf("%d\n", count[i]);
	}

	while (fscanf(f, " %c", &letter) == 1) {
		if (isupper(letter)) {
			total++;
			count[letter - 'A']++;
		} else if (islower(letter)) {
			total++;
			count[letter - 'a']++;
		}
		//printf("%i\n", total);
	}

	for(k = 0; k < 26; k++){
		found[k] = (float) count[k] / (float) total;
		//printf("k=%2d  count[k]=%d  total=%d  found[k]=%lf\n", k, count[k], total, found[k]);
	}
	fclose(f);
}

char rotate(char ch, int num){
	if(num < 0) {
		num = num + 26;
	}

	if(isupper(ch)){
		return (char) ((ch - 'A' + num) % 26 + 'A');
	} else if(islower(ch)){
		return (char) ((ch - 'a' + num) % 26 + 'a');
	}
	return ch;
}
void rotatearrr(float arr[])
{
	// STore the first array element as it would be overridden during the subsequent rotation
	float temp = arr[0];
	int i;
	// Rotate each element of the array left by one position
	for(i = 0; i < 25; arr[i] = arr[i + 1], i++);
	arr[25] = temp;
}
float finddiff(float given[], float found[])
{
	float sum = 0;
	int i;
	//Find the absolute difference between the given and the found frequency
	for(i = 0; i < 26; sum += fabs(given[i] - found[i]), i++);
	return sum;
}
// given: Frequency Given
// found: Frequency Found
int findKey(float given[], float found[]){
	int n, m;
	int key = -1;
	float difference = -1;
	float temp;
	float least = 100000;
	float min = INT_MAX, diff = 0;
	int min_rotate = 0;
	for(n = 0; n < 26; n ++){
		 diff = finddiff(given, found);
		 rotatearrr(found);
		 if (diff < min) 
		 {
			 min = diff;
			 min_rotate = n;
		 }
	}
	return min_rotate;
}

void decrypt(int key, char fname[]){
	FILE *f;
	char c;
	f = fopen(fname,"r");

	printf("Decrypted Text: ");
	while (fscanf(f,"%c", &c) == 1){
		if(isspace(c)){
			printf(" ");
		}
		else if(isalpha(c)){
			c = rotate(c, key);
			printf("%c", c);
		}
		else{
			printf("%c", c);
		}
	}
	fclose(f);
}

int main() {
	int key;
	float given[26],found[26];
	readFreq(given,"LetFreq.txt");
	calcFreq(found,"data.out");
	key = - findKey(given,found);
	printf("Key: %d\n", -key); 
	decrypt(key, "data.out");

	return 0;
}