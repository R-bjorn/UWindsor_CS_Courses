#include <stdio.h>
#include "logic_tools.h"
#include "comp_tools.h"
#include "convert_tools.h"
#define MAX 8

void welcome_msg(){
    printf("\n\nEnter the command number:\n");
    printf("0) Exit\n1) AND\n2) OR\n3) NOT\n4) 1's complement\n5) 2's complement\n6) 2's complement*\n");
}
void input_binary2(int X[] , int Y[]) {
    printf("Enter the first binary number:\n");
    int i = 0 ;
    while(i < MAX){
        printf("x%d=",i); fflush(stdout);
        scanf("%d", &X[i]);
        if(X[i] != 0 && X[i] != 1){
            printf("\nInvalid Input!! Try again\n");
            continue;
        }
        i++;
    }
    printf("Enter the second binary number:\n");
    i=0;
    while(i < MAX){
        printf("y%d=",i); fflush(stdout);
        scanf("%d", &Y[i]);
        if(Y[i] != 0 && Y[i] != 1){
            printf("\nInvalid Input!! Try again\n");
            continue;
        }
        i++;
    }
}
void input_binary1(int X[]){
    printf("Enter the binary number:\n");
    int i = 0 ;
    while(i < MAX){
        printf("x%d=",i); fflush(stdout);
        scanf("%d", &X[i]);
        if(X[i] != 0 && X[i] != 1){
            printf("\nInvalid Input!! Try again\n");
            continue;
        }
        i++;
    }
}
void out_put(int result[]){
    printf("Binary : ");
    for(int i=0; i < MAX; i = i + 1){
        printf("%d", result[i]);
    }
    printf("\n");
}

int main(void) {
    setbuf(stdout, NULL);
    while(1){printf("\n");
        int x[MAX] , y[MAX], z[MAX] , option;
        welcome_msg();
        scanf("%d",&option);

        if(option == 0){
            printf("Exiting!!!!");
            break;
        }else if(option == 1){
            input_binary2(x,y);
            func_and(x,y,z);
//            printf("The first number AND second binary yield:\n");
        }else if(option == 2){
            input_binary2(x,y);
            func_or(x,y,z);
//            printf("The first number OR second binary yield:\n");
        }else if(option == 3){
            input_binary1(x);
            func_not(x,z);
//            printf("The first number's NOT yield:\n");
        }else if(option == 4){
            input_binary1(x);
            func_1s_comp(x,z);
//            printf("The first number's 1's complement yield:\n");
        }else if(option == 5){
            input_binary1(x);
            func_2s_comp(x,z);
//            printf("The first number 2's complement yield:\n");
        }else if(option == 6) {
            input_binary1(x);
            func_2s_comp_star(x,z);
//            printf("The first number 2's complement* yield:\n");
        }else{
            printf("Invalid Choice\nChoose again!!\n");
            continue;
        }

        printf("Enter the Output Base:\n1) Binary\n2) Octal\n3) Decimal\n4) Hexadecimal\n");
        scanf("%d",&option);

        if(option == 1){
            out_put(z);
        }else if(option == 2){
            to_octal(z);
        }else if(option == 3){
            to_decimal(z);
        }else{
            to_hexadecimal(z);
        }
    }
    return 0;
}