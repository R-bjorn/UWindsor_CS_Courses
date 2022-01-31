#include <stdio.h>
#include <stdlib.h>

//This code runs in every situation wheather the input file has special character
//, space, new line, numbers, strings as well as if the file is empty, this code returns 0

int main(int argc, char *argv[]){  
	FILE *fd;
	
	int ch;

	int fileSize=-1; 

	fd = fopen(argv[1], "r+"); 
    
    // we didn't check if the input file exist or not 
    // so i add this block of code 
    if(fd == NULL){
        printf("Error : File Can't Open\n");
        exit(1);
    }

	do{
		ch=getc(fd);  //printf("ch=%c ", ch);
		printf("fileSize=%d\n", ++fileSize);    // ++filesize adds 1 to filesize first
	} while( ch != EOF);
	
	printf("Size of %s is %d\n", "a.txt", fileSize);
}
