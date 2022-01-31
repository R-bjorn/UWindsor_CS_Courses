#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define BUF 20
#define MAX 512
char *promt = ">>>>>miniminishell ";        // Welcome String for shell

int main()
{
    char input[MAX];    // Store all the input from user
    char *inp;          // Pointer to input[] array which contains user input data
    
    printf("%s",promt);
    scanf("%[^\n]",input);      // Parallal Input Stored in input array
    int len = strlen(input);    // get length of input array
    input[len] = '\0';          // Adding EOF to the end of array
    inp = input;
    
    int num = 0;
    while(*inp != '\0'){        // till EOF
        
        char argv[BUF] = {}, cmdv[BUF] = {};    // one for command and other is argument 
        char *arg[MAX+1];                       // for concating the command and its argument 
        
        int count = 0;
        int i = 0 , j = 0;
        
        while(1 && *inp!= '\0'){        // till EOF
            if(*inp != ' ' && *inp != '\0'){    // If space skip or if EOF skip
                if(count == 0){         
                    argv[i++] = *inp++; 
                }else{
                    cmdv[j++] = *inp++;
                }
            }else{
                *inp++;
                if(++count == 2){
                    break;
                }
                sleep(1);
            }
        }
        
        pid_t pid = fork(); 
        if(pid == 0){       // Child Process
        
            alarm(3);       // alarm for 3 sec..
            arg[num++] = argv;
            printf("\narg[0] : '%s'\n",arg[0]);
            if(j != 0){         // if command doesn't have any argument 
                arg[num++] = cmdv;       
                printf("arg[1] : '%s'\n\n",arg[1]);
            }
            arg[num] = NULL;        // last is NULL for execvp() function.
            execvp(arg[0],arg);     // Using execvp...
            
        }
        else{          // Parent process 
            wait(NULL); // waiting for child
        }
        
    }
}
