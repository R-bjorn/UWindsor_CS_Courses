#include "smallsh.h"
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

int runcommand(char **cline, int where)
{
    // I created an char * [] var for a copy of cline and also adding '\0' at the end of every tok... and also printing all the args to show user what args are used..
    int i = 0;
    char* temp[MAXARG+1];
    do{
        printf("arg[%d]>%s\n",i, cline[i]);
        temp[i] = cline[i];
        strcat(temp[i],"\0");   // Adding '\0' at the end
    }while(cline[i++] != NULL);


    pid_t pid;
    int status;
    switch (pid = fork()) {
        case 1:
            perror("smallsh");
            return (-1);
        case 0:
            execvp(temp[0], temp);
            perror(*temp);
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

