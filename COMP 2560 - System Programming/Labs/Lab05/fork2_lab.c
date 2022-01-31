//fork2_lab.c
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int glb = 100; // global variable  

int main(){
	int pid;
	int var = 88;  
	
	printf("Before fork\n");  
	pid = fork();

	if( pid < 0 ){  
		perror("fork");  
		exit(1);
	}
	
	if(pid == 0){ //child  
		
		//glb++; var++;
	//	sleep(10);
		sleep(60);
	        glb++; var++;
	}
	else{ // parent
		sleep(2);
	}
	
	printf("pid = %d, glob = %d, var = %d\n", getpid(), glb, var);
	return 0;
}

