#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>

void readFreq(float given[], char fname[]){
    FILE *fin;
    char letter;
    float freq;
    int i;

    //fin = fopen("LetFreq.txt","r");
    fin = fopen(fname,"r");
    if (fin == NULL) {
        printf("File could not be opened. \n");
        exit(1);
    }

    for (i = 0; i < 26; i++) {
        fscanf(fin, " %c%f", &letter, &freq);
        given[i] = freq;
    }
    fclose(fin);
}

int main() {
    int i;
    float freq[26];

    readFreq(freq, "LetFreq.txt");
    for (i = 0; i < 26; i++) {
        printf("%2d: %f\n", i, freq[i]);
    }

    return 0;
}