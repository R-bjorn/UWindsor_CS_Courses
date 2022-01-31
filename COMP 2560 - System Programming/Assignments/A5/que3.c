#include <stdio.h>      
#include <stdlib.h>
#include <unistd.h>     
#include <string.h>
#include <sys/stat.h>   
#include <sys/types.h>
#include <sys/wait.h>   
#define MAXBUF 10005

int main(int argc, char *argv[]){
    
    int pip[2];                  // to store pipe file ends
    pipe(pip);                  
    pid_t pid = fork();           // Using fork to split process in two.
    
    if(pid == 0){               // Child Process 
    
    close(pip[0]); // closing unusable pipe end
       
    FILE *fp;
    fp = popen(argv[1], "r");        // Creating a pipe with given file name
    if(fp == NULL){                  // Checking if file opens      
        printf("FILE : Can't open\n");
        perror("File : ");
        exit(-1);
    }
       
    char buffer[MAXBUF];                // array to hold command result

    while(fgets(buffer, sizeof(buffer), fp) != NULL){
        write(pip[1], buffer, strlen(buffer) + 1);              // writing to pipe
        sleep(1); // sleep child process until ast line is read from pipe by parent process and write to result.txt
    }
    
    pclose(fp); // closing command output
    close(pip[1]); // closing pipe another end
    
    } 
    else{
        close(pip[1]);                  // closing unusable pipe end in parent
        char arr[10005];                // array to store data reading from pipe
        int n1, n2 = 0;;
    
        // opening file result.y=txt
        FILE *fptr = fopen("result.txt", "w");
        if(fptr == NULL){
            printf("Failed to open result.txt\n");
            exit(1);
        }
    
        // while there is data in pipe
        while((n1 = read(pip[0], arr, 10000)) > 0){
            n2 += n1;
            fprintf(fptr, "%s", arr);       // writing to file result.txt
        }
        printf("The result of %s is written into result.txt with total %d bytes.\n", argv[1], n2);
        fclose(fptr); // closing result.txt
        close(pip[0]); // closing pipe other end
   }
   return 0;
}
