#include<unistd.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<string.h>
#include<netdb.h>
#include<stdlib.h>
#include<stdio.h>
#include<sys/wait.h>


void child(int sd);

int main(int argc, char *argv[]){ 
	char buffer[100];
	int sd, cd;  
	socklen_t len;
	
	struct sockaddr_in servAdd, cliAdd;

	//creating socket properly
	sd = socket(AF_INET, SOCK_STREAM, 0);  

	//preparing struct for calling bind
	servAdd.sin_family = AF_INET;  
	servAdd.sin_addr.s_addr = INADDR_ANY;  
	servAdd.sin_port = 50000;

/* optional
	int yes=1;
	if (setsockopt(sd, SOL_SOCKET, SO_REUSEADDR, &yes,
				sizeof(int)) == -1) {
			perror("setsockopt");
			exit(1);
	}
*/
	//calling bind properly	
	bind(sd,(struct sockaddr*)&servAdd,sizeof(servAdd));  

	//calling listen
	listen(sd, 2);

	//while(1){
		len = sizeof(cliAdd);
		fprintf(stderr, "waiting for connection: ");

		//calling accept
		cd = accept(sd, (struct sockaddr *) &cliAdd, &len);  
		if(cd==-1) {
			printf("connection error, quiting...\n");
			exit(3);
		}
		fprintf(stderr, " CONNECTED!\n");		
		//forking the child to
		if(fork()==0) {
			child(cd);
		}  
		else { //parent wait for the child to terminate
			int status;

			//wait call
			wait(&status);

			//getting exit staus value
			if(WIFEXITED(status))
				printf(" normal termination, exit staus=%d\n", WEXITSTATUS(status));
			else
				printf(" abnormal termination, exit staus=%d\n");
		
		}
		close(cd);
	//}
}


void child(int sd){  

	char line[255];

	//while(1){

		//reading from the socket (read the command name)
		int rt= read(sd, line, 255);

		if (rt==0) {
		   fprintf(stderr, "client probably exited, I am leaving too.\n");
		   exit(3);
		}
		printf("The command received is %s \n", line);	

		//redircting output from running the command to the socket
		dup2(sd, 1);  //redirect standard output to the socket

		//calling execlp properly

		execlp(line, line, (char *)0);
		//error checking here if execlp failed
		fprintf(stderr, "execlp failed....\n");
		exit(5);
	//}
}
