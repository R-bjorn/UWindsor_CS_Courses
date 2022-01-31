#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>

void handler(int);

pid_t pid;

void handler(int hd){
    signal(SIGCHLD,handler);
    
    
    int status,exit_status;
    int temp = wait(&status);   // Use wait for child process
    
    if (WIFEXITED(status))      // retrive exit status from child process
	{
		exit_status = WEXITSTATUS(status);
		printf("Exit status from %d was %d\n", pid, exit_status);
	}
}

int main(){
    signal(SIGCHLD,handler);    // Installing signal handler for SIGCHLD

    if((pid = fork()) == 0){          // Child Process
        printf("I am Child : PID = %d\n",getpid());
        sleep(5);       // sleep for 5 sec 
        exit(45);        // Exit with 45...
    }
    
    printf("I am parent: PID = %d\n",getpid());
    
    while(1){   //Infitine loop.....
    }

    return 0;
}
