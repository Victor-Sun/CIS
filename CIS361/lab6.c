#include <stdio.h>
#include <string.h>

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
    
    printf("cmd = %p\n", cmd);
    for (i = 0; i < MAXARG; i++) {
        printf("argv[%d] = %p\n", i, argv[i]);
    }
    
    
    // cmd = "ls -l /tmp\n"

    // cmd = "ls\0-l\0/tmp\0"

    // argv[0] -> "ls"  -> cmd + 0
    // argv[1] -> "-l" -> cmd + 3
    // argv[2] -> "/tmp"
    // argv[3] -> NULL
    

    // int execvp(const char *file, char *const argv[]);

    //execvp (argv[0], argv);

}
