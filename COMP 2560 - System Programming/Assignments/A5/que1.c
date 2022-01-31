#include <stdio.h>      // for printf and everything else
#include <signal.h>     // for signal()
#include <unistd.h>     // for fork()
#include <stdlib.h>     // for exit()

void alarmSIG()
{
    printf("Ding!\n");
    printf("done\n");
    exit(0);
}

int main( int argc, char *argv[])
{
    pid_t pid;
    int slt = atoi(argv[1]);        // (the number of seconds is an command line argument)
    printf("alarm application starting\n");
    
    pid = fork();       // using fork to split process in two.

    if(pid == 0){                         // Child Process
        printf("waiting for alarm to go off\n");
        printf("<%d second pause>\n", slt);
        sleep(slt);                         // sleep for given time
        kill(getppid(), SIGALRM);           // send signal(SIGALRM) to parent process..     
    }
    else{                                // parent process.
        signal(SIGALRM, alarmSIG);
        pause();
    }
}








