#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>

void handler(int);

void handler(int hd){
    signal(SIGINT,handler);
    printf("Ctrl-c pressed\n");
}

int main(){
    
    signal(SIGTSTP,SIG_IGN);// ctrl Z ignored 
    signal(SIGINT,handler);// Installing Handler for Ctrl-c
    
    pid_t pid;
    if((pid=fork()) == 0){ // child process
        printf("I am Child\n");
        execlp("./donothing","donothing",NULL);
    }
    else{                  // Parent process
    
        for(int i = 0 ; i < 15 ; i++){
            printf("I am in parent process.\n");
            kill(pid,SIGTSTP);      // ingore ctrlZ
            kill(pid,SIGINT);       //first iteration will be handle by my handler() which prints "Ctrl-C pressed", after first iteration, It will set back to default handler and in the second iteration, the execlp running the do nothing file will execute because of the ctrl C.. 
            sleep(1);
        }
    
    }

    return 0;
}
