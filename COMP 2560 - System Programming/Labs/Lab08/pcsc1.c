#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>

void action(int dummy){  
  	 sleep(1); 
	 printf("Switching\n");
}

int main(int argc, char *argv[]){  
	pid_t pid;

	if((pid=fork())>0){//parent  
		sleep(1);   // Goes to Child Process
		while(1){
			printf("Parent is running\n");  // Printing line 
            kill(pid, SIGUSR1);			    // Sending signal to child class 
			signal(SIGUSR1, action);        // Signal handler installed for SIGUSR1
            pause();			            // waiting for the signal from child class
		}
	}
	else  //child code
		while(1){//child  
			signal(SIGUSR1, action);    // Installing SIGUSR1 signal handler     
            kill(getppid(), SIGUSR1);       // Sending signal to parent process 
			printf("Child is running\n");   // Printing Line
			pause();			            // Waiting for teh signal
	}
}

/* This is Case 1 
In this code ... First process goes through parent class and the first line of parent process is sleep(1) so
the process then goes to child process where there is an infinite loop with a signal handler installled for SIGUSR1 which switches the process.. 
The second line of code is when child sends a SIGUSR1 signal to parent process. 
Third line is just printing the line 
in the forth line it waits for parent process to send any kind of signal but process never returns to parent process and always stuck at foruth line of code in child process.. Thats why the output is only 
OUTPUT:
Child is running
User defined signal 1
*/
