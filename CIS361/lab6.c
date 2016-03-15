#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

#define MAXLINE 80
#define MAXARG 20


void background (char * cmd);


int main(){
	char cmd[MAXLINE];

	for (; ;) {
		printf("mysh$ ");
		fgets(cmd, MAXLINE, stdin); 
		// read a command from the user
		// if the command in cmd is “exit\n”, then terminate this program;
		// otherwise, create a child process to handle that command.

		if (strcmp(cmd, "exit\n") == 0) {
			break;
		} else {
			background(cmd);
		}
	}

	return 0;
}

void background (char * cmd){
	int i;
	pid_t pid;
	char *argv[MAXARG];
	const char DELIMITERS[] = "\t \n\r";

	argv[0] = strtok(cmd, DELIMITERS);

	for (i = 1; i < MAXARG - 1; i++) {
		argv[i] = strtok(NULL, DELIMITERS);
		if (argv[i] == NULL) {
			break;
		}
	}
	argv[i] = NULL;

	/*
	printf("cmd = %p \"%s\"\n", cmd, cmd);
	for (i = 0; i < MAXARG; i++) {
		if (argv[i] == NULL) {
			printf("argv[%d] = %p \n", i, argv[i]);
			break;
		} else {
			printf("argv[%d] = %p \"%s\"\n", i, argv[i], argv[i]);
		}
	}
	*/

	pid = fork();

	if (pid < 0) {
		perror("Error while forking");
		return;
	} else if (pid == 0) {
		execvp(argv[0], argv);
		perror("Error when executing the command.");
	} else {
		int status;
		waitpid(pid, &status, 0);
	}
}
