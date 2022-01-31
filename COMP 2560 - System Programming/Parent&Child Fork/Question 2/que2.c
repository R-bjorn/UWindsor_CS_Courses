#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main()
{
    int c1pid;    
    
    c1pid = fork();               // This creates a child... c1 
    
    
    if(c1pid == 0){               // for child c1...
        
        printf(".I am a child process, my parent id = %d, my pid = %d\n",getppid(),getpid());            // for c1
        
    }else{                      // for parent 
        
        int c2pid = fork();
        int c3pid;
    
        if(c2pid == 0){               // another child which is c2 ...
            int c4pid = fork();
        
            if(c4pid == 0){         // for c4
                printf("....I am a child process, my parent id = %d, my pid = %d\n",getppid(),getpid());        // for c4
                exit(0);
            }else{
                printf("..I am a child process, my parent id = %d, my pid = %d\n",getppid(),getpid());        // for c2
            }
        }else if( (c3pid = fork()) == 0 ){  // another child which is c3 ...
            printf("...I am a child process, my parent id = %d, my pid = %d\n",getppid(),getpid());        // for c3    
        }else{      // for parent 
            printf("I am the parent process, my pid = %d\n",getpid());                                      // for parent 
        
        }
            
    }
    
    return 0;
}

