#include <stdio.h>
#include <ctype.h>

int main(){
	char ch, tmpch;
	char word[100];	
	int	wordCount = 0, characterCount = 0;
	float averageCharacter;

	printf("Enter text(Ctrl-D to quit).\n");
	while (ch = getchar(), ch != EOF){
			//Checks if ch is a letter/num
			if(isalnum(ch)){
				characterCount++;
			}

			//checks if ch is a space
			if(isspace(ch)){
				if(!isspace(tmpch)){
					wordCount++;
				}
			}
			tmpch = ch;
		}
	averageCharacter = (float) characterCount / wordCount;
	printf("Number of words: %d\n", wordCount); 
	printf("Average length of word: %d\n", characterCount);
}
