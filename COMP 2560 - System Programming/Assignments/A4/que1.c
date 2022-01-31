#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <sys/types.h> 
#include <unistd.h> 

#define BUF 20


int main()
{
    //Opening a.txt file in reading mode.. a.txt file contains 2 lines. First is "Hello".. Second is "Parent"
    FILE *fp = fopen("a.txt","r");       
    char pstr1[BUF], pstr2[BUF];      // Two char arrays (String) to read from file..
    
    pid_t pid;
    if((pid = fork()) == -1){       // Creating and checking if the fork works 
        perror("Fork");
        exit(-1);
    }
    
    if(pid == 0){  // Child Process
        fclose(fp); // Closing the file.. To see of if file remains open in parent process 
    } 
    else{              // Parent Process
        fscanf(fp,"%s",pstr1);                  // Reading from file
        printf("Parent Process: %s\n",pstr1);   // Printing the data from file
        sleep(3);
        wait(NULL);                 // Waiting for the child process to read and then close the file.
        
        printf("My Child must have closed the file... Lets try reading from file\n");
        fscanf(fp,"%s",pstr2);                  // Reading from file
        printf("Parent Process: %s\n",pstr2);   // Printing the data from file
    }

    return 0;
}

