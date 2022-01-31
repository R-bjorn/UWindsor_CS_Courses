#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>


//p ------->c1------>c3
//     |----->c2------>c4

int main(int argc, char *argv[]) {

	pid_t pid, cpid;

	printf(" I am the parent process, my pid = % d \n.", getpid());


	for(int i=0; i<2; i++) {
		cpid=fork();

		if(cpid==0) {   // Child process 
			printf(" I am a child process, parent pid = %d, my pid = %d \n.", 
				getppid(), getpid());
			//exit(10);
			pid_t ccpid;
			ccpid=fork();
			if(ccpid==0) {  // child child process
			     printf(" I am a child process, my parent pid = %d, my pid = %d \n.", 
				getppid(), getpid());
			     exit(10);
			}
			else{           // child parent process
                //exit(11); //delete it?
            }
		      
		}
		else continue;  //parent process 
	}
}
