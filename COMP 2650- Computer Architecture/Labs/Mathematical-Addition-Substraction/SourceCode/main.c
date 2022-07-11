#include <stdio.h>
#include "logic_tools.h"
#include "comp_tools.h"
#include "convert_tools.h"
#include "arithmetic_tools.h"
#define MAX 8

void welcome_msg(){
    printf("\n\nEnter the command number:\n");
    printf("0) Exit\n1) Addition in signed-magnitude\n2) Subtraction in signed-magnitude\n");
}
void input_binary2(int X[] , int Y[]) {
    printf("Enter the first binary number:\n");
    int i = 0 ;
    while(i < MAX){
        printf("x%d=",(i+1)); fflush(stdout);
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
        printf("y%d=",(i+1)); fflush(stdout);
        scanf("%d", &Y[i]);
        if(Y[i] != 0 && Y[i] != 1){
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
        int x[MAX] , y[MAX] , z[MAX] , option;
        welcome_msg();
        scanf("%d",&option);

        if(option == 0){
            printf("Exiting!!!!");
            break;
        }else if(option == 1){
            input_binary2(x,y);
            func_signed_mag_addition(x,y,z);
        }else if(option == 2){
            input_binary2(x,y);
            func_signed_mag_subtraction(x,y,z);
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