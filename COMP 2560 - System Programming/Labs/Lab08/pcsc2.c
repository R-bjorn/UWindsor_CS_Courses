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
            pause();			            // waiting for the signal from child class 
			signal(SIGUSR1, action);        // Signal handler installed for SIGUSR1
            kill(pid, SIGUSR1);			    // Sending signal to child class
		}
	}
	else  //child code
		while(1){//child  
			signal(SIGUSR1, action);    // Installing SIGUSR1 signal handler 
            printf("Hello"); 
            pause();			            // Waiting for teh signal 
			printf("Child is running\n");   // Printing Line
            kill(getppid(), SIGUSR1);       // Sending signal to parent process 
	}
}

/* This is Case 2
In this code ... First process goes through parent class and the first line of parent process is sleep(1) so
the process then goes to child process where there is an infinite loop with a signal handler installled for SIGUSR1 which switches the process.. So process goes back to parent process...
in parent process, first it prints the line and then paus for child process to send a signal but child process cannot send a signal as long as we dont switch the processes. 
So it just prints "Parent is running" and then pause the process.. So it never returns anything else. 
Output:
Parent is running 





 */
