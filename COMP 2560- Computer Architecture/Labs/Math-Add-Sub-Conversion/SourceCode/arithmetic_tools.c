//
// Created by ravid on 11/9/2020.
//
#include <stdio.h>
#include <math.h>
#include "comp_tools.h"
#include "arithmetic_tools.h"

#define MAX 8//Byte = 8 bits

double to_dec(int a[]){
    long int total=0;
    int pos = MAX-1;
    for(int i = 0;i < MAX;i++){
        total += (long)pow(2,pos)*a[i];
        pos--;
    }
    return (double)total;
}

int bigger(int result[]){
    int _2nd_comp_dec = 127;        // (01111111)2 -> (127)10
    int result_dec = to_dec(result);
    if(result_dec < _2nd_comp_dec){
        return 0;
    }
    return 1;
}

void func_signed_2s_addition(int a[], int b[], int result[]) {
    int carry=0 , sum;
    for(int i = MAX-1 ; i >= 0 ; i--){
        sum = a[i] + b[i] + carry;
        carry = sum/2 ;
        if(sum > 1){
            result[i] = (int)sum%2;
        }else{
            result[i] = (int)sum;
        }
    }
    if(carry != 0){
        if( (a[0] == 1 && b[0] == 1) || (a[0] == 0 && b[0] == 0) ){
            printf("\nOverflow: The result is not reliable!\n\n"); 
        }
    }
}

void func_signed_2s_subtraction(int a[], int b[], int result[]) {
    func_2s_comp(b,b);
    int carry=0 , sum;
    for(int i = MAX-1 ; i >= 0 ; i--){
        sum = a[i] + b[i] + carry;
        carry = sum/2 ;
        if(sum > 1){
            result[i] = (int)sum%2;
        }else{
            result[i] = (int)sum;
        }
    }
    if(carry != 0){
        if( (a[0] == 1 && b[0] == 1) || (a[0] == 0 && b[0] == 1) ){
            printf("\nOverflow: The result is not reliable!\n\n");  
        }
    }
}
