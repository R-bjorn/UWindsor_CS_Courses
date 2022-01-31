#include<string.h>
#include<netdb.h>
#include<netinet/in.h>
#include <arpa/inet.h>
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

int main(int argc, char *argv[]){  
	char buffer[255]="ls";
	int csd;  socklen_t len;
	
	char output[10240];
	
	struct sockaddr_in servAdd; //server socket address

	//creating socket properly
	csd = socket(AF_INET, SOCK_STREAM, 0);

	//preparing struct for calling connect
	servAdd.sin_family = AF_INET;  
	servAdd.sin_addr.s_addr = inet_addr(argv[1]);  
	servAdd.sin_port = 50000;

	//connecting to server
	int ret=connect(csd, (struct sockaddr *) &servAdd,  sizeof(servAdd));

	//error checking for calling socket functions is optional, no marks awarded
	if(ret==-1) {
		fprintf(stderr, "connection error, quiting.\n");
		perror("the problem is:");
		exit(10);

	}

	fprintf(stderr, "please help me execute a simple command %s.\n", buffer);  
	//sending the command to the server
 	write(csd, buffer, strlen(buffer)+1);

	fprintf(stderr, "\n here is what I received from the server\n");
	fprintf(stderr, "\n ----------------------------------------\n\n");

	//obtain output from the command via socket
	//getting output from the server
	int cnt=read(csd, output, 40960);

	//for printing out the output
	fprintf(stderr, output);
}	


