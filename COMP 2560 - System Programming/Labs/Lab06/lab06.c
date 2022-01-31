/*
Name : Ravi Trivedi 
Lab Section : 5
*/

#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h> 
#include <unistd.h>
#include <stdlib.h>


//p ------->c1------>c3
//     |----->c2------>c4

int main(int argc, char *argv[]) {

	pid_t pid, cpid;

	printf(" I am the parent process, my pid = % d \n.", getpid());


	for(int i=0; i<2; i++) {
		cpid=fork();

		if(cpid==0) {
			//printf(" Welcome I'm Child C%d \n",(i+1));
			//exit(10);
			pid_t ccpid;
			ccpid=fork();
			if(ccpid==0) {
			     printf(" I am a child process, my parent pid = %d, my pid = %d \n.", 
				getppid(), getpid());
			     exit(10);
			}
			else{
                printf(" I am a child process, parent pid = %d, my pid = %d \n.",getppid(), getpid());
                wait(NULL);         // C1 and C2 both are parents here .. They wait for their child to terminates first
                sleep(1);
                exit(11); //delete it?
            }
		      
		}
		else {
            wait(NULL);         // Main Parent waits for c1 and c2 to terminates
            sleep(1);
            //printf("My child will be terminated by now\n.");
            continue;
        }
	}

    printf("\n\n\n");
}
