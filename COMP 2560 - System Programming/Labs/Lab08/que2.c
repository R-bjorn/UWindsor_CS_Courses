#include <stdio.h>
#include <unistd.h>
#include <signal.h>

int main()
{
    pid_t pid; 
    
    pid = fork();                                       // a) In the main function, fork a child process. 
    
    if(pid == 0){   // Child Process 
        int i = 0;
        while(1){
            printf("%d\n",++i);                         // b) In the child process, using an infinite loop to print out something. 
            sleep(1);
        }    
    }
    else {          // Parent Process 
        sleep(2);
        kill(pid,SIGSTOP);                            // c) In the parent process, send the SIGSTOP signal to the child process.
        printf("Send SIGSTOP to child\n");
        sleep(2);
        kill(pid,SIGCONT);                             // d) In the parent process, send the SIGONT signal to the child process.
        printf("Send SIGCONT to child\n");
        sleep(10);
        kill(pid,SIGTERM);                            // e) In the parent process, send the SIGTERM signal to the child process to terminate it. 
        printf("Send SIGTERM to child\n");
    }

    return 0;
}

