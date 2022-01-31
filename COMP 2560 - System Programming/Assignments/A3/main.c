#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

void childFunction(char *expr)
{
    int a=0, b=0;
    char op;
    int result;
    char buffer[100];
    int sc_status;

    puts("I am a child working for my parent");
    sc_status = sscanf(expr, "%d %c %d", &a, &op, &b);
    if(sc_status != 3) {
        exit(50);           // Wrong Statement
    }

    if(op == '+') {
        result = a+b;
    } else if(op == '-') {
        result = a-b;
    } else if(op == '*') {
        result = a*b;
    } else if(op == '/') {
        if(b == 0) {
            exit(100);          // divided by zero
        } else {
            result = a/b;
        }
    } else {
        exit(200);              // Wrong Operator
    }

    sprintf(buffer, "%d %c %d = %d\n", a,op,b,result);      // Using sprintf to store result string in buffer 
    write(1, buffer, strlen(buffer));                       // Using write instead of printf
    exit(0);
}

int main()
{
    pid_t n, wpid;
    int status = 0;
    int exit_status;

    while(1) {      // Infinite Loop as asked in the Asng

        char expr[100];
        char enter_msg[] = {"Enter an arithmetic statement, e.g., 34 + 132 > "};
        char ws_msg[] = {"Wrong statement\n"};
        char db0_msg[] = {"Division by 0\n"};
        char wop_msg[] = {"Wrong operator\n"};
        write(1,enter_msg,strlen(enter_msg));
        fgets(expr, 100, stdin);

        n = fork();

        if(n == 0) {        // Child Process
            childFunction(expr);
        } else {            // Parent Process
            puts("Created a child to make your operation, waiting");
            wait(&status);          // Waiting for the child's response

            exit_status = WEXITSTATUS(status);

            if(exit_status == 50) {
                write(1,ws_msg,strlen(ws_msg));
            } else if (exit_status == 100) {
                write(1,db0_msg,strlen(db0_msg));
            } else if(exit_status == 200) {
                write(1,wop_msg,strlen(wop_msg));
            }
        }
    }

    return 0;
}
