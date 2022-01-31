#include "smallsh.h"
#include <unistd.h>
#include <stdlib.h>

int runcommand(char **cline, int where)
{
	pid_t pid;
	int status;
    int i;
    size_t size = sizeof(*cline) / sizeof(cline[0]); 
	switch (pid = fork()) {
		case 1:
			perror("smallsh");
			return (-1);
		case 0:
            for(i = 0 ; i < 3; i++){
                printf("arg[narg=%d]=%s\n",i,cline[i]);         /*because we are using execvp.. So i know that second parameter of execvp is an array of char that has all the info i need to print what was asked in the lab*/
            }
            printf("arg[narg=%d]=%s\n\n",i,"");                 // last element of char array is always null so im just printing empty string "".
			execvp(cline[0], cline);
			perror(*cline);
			exit(1);
	}
	//code for parents
	if (where == BACKGROUND)
	{
		printf("[process id %d]\n", pid);
		return (0);
	}
	
	if (waitpid(pid, &status, 0) == -1)
		return (-1);
	else
		return (status);
}

