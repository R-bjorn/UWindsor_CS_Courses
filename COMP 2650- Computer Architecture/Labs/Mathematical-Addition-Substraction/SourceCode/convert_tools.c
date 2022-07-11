//
// Created by ravid on 11/4/2020.
//
#include "convert_tools.h"
#include<stdio.h>
#include <stdlib.h>
#include<math.h>
#define MAX 8



void to_decimal(int *a) {
    long int total=0;
    int pos = MAX-2;
    for(int i = 1;i < MAX;i++){
        total += (long)pow(2,pos)*a[i];
        pos--;
    }
    char sign = '+';
    if(a[0] == 1){      // Negative decimal
        sign = '-';
    }
    printf("Decimal : %c%ld\n",sign,total);
}

void to_octal(const int *a) {
    long int total=0;
    int pos = MAX-2;
    for(int i = 1;i < MAX;i++){
        total += (long)pow(2,pos)*a[i];
        pos--;
    }
    int oct_a[MAX] = {0};
    if(a[0] == 1){
        oct_a[MAX-1] = rand() % 6 + 1;
    }
    int i = 0;
    while(total > 0){
        oct_a[i]= total % 8;
        total /= 8;
        i++;
    }
    printf("Octal : ");
    for(int j = MAX-1 ; j>=0 ; j--){
        printf("%d",oct_a[j]);
    }
    printf("\n");
}

void to_hexadecimal(const int *a) {
    long int total=0;
    int pos = MAX-2;
    for(int i = 1;i < MAX;i++){
        total += (long)pow(2,pos)*a[i];
        pos--;
    }
    int hex_a[MAX] = {0};
    if(a[0] == 1){
        hex_a[MAX-1] = rand() % 14 + 1;
    }
    int i = 0;
    while(total > 0){
        hex_a[i]= total % 16;
        total /= 16;
        i++;
    }
    printf("\nHexadecimal : ");
    for(int j = MAX-1 ; j>=0 ; j--){
        if(hex_a[j] >= 0 && hex_a[j] <= 9){
            printf("%d",hex_a[j]);
        }else if(hex_a[j] == 10){
            printf("A");
        }else if(hex_a[j] == 11){
            printf("B");
        }else if(hex_a[j] == 12){
            printf("C");
        }else if(hex_a[j] == 13){
            printf("D");
        }else if(hex_a[j] == 14){
            printf("E");
        }else if(hex_a[j] == 15){
            printf("F");
        }else{
            printf("0");
        }
    }
    printf("\n");
}
