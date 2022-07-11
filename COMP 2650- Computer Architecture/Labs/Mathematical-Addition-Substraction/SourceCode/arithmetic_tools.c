//
// Created by ravid on 11/4/2020.
//

#include <stdio.h>
#include <math.h>

#define MAX 8//Byte = 8 bits

double to_dec(int a[]){
    double total=0;
    int pos = MAX-1;
    for(int i = 1;i < MAX;i++){
        total += pow(2,pos)*a[i];
        pos--;
    }
    return total;
}

int greaterDigit(int a[], int b[]) {
    double x , y;

    x = to_dec(a);
    y = to_dec(b);
    if (x > y) {
        return 0;
    }

//    }else if(a[0] == 1 && b[0] == 1){
//        x = to_decimal(a);
//        y = to_decimal(b);
//        if(x < y){
//
//        }
//    }
    return 1;
}

void func_signed_mag_addition(int *a, int *b, int *result) {
    if(a[0] == 0 && b[0] == 1){       // (+X)+(-Y) = X-Y
        if(greaterDigit(a,b)){    // X >
            result[0] = 1;  // Negative
        }else{
            result[0] = 0;  //Positive
        }
        int borrow = 0;
        for(int i = MAX-1; i>0;i--){
            borrow = 0;
            int temp = a[i] - b[i];
            if(temp < 0){
                a[i-1] = a[i-1] - 1;
                borrow = 2;
            }
            result[i] = (a[i] + borrow) - b[i];

        }
        if(borrow != 0){    // last borrow
            int big[MAX] = {1,0,0,0,0,0,0,0};
            for(int i = MAX-1; i>0;i--){
                borrow = 0;
                int temp = big[i] - result[i];
                if(temp < 0){
                    big[i-1] = big[i-1] - 1;
                    borrow = 2;
                }
                result[i] = (big[i] + borrow) - result[i];
            }
        }
    }else if(a[0] == 1 && b[0] == 0){               // (-X)+(+Y) = Y-X
        if(greaterDigit(a,b)){    // X > Y
            result[0] = 0;  // Positive
        }else{
            result[0] = 1;  //Negative
        }
        int borrow = 0;
        for(int i = MAX-1; i>0;i--){
            borrow = 0;
            int temp = b[i] - a[i];
            if(temp < 0){
                b[i-1] = b[i-1] - 1;
                borrow = 2;
            }
            result[i] = (b[i] + borrow) - a[i];

        }
        if(borrow != 0){    // last borrow
            int big[MAX] = {1,0,0,0,0,0,0,0};
            for(int i = MAX-1; i>0;i--){
                borrow = 0;
                int temp = big[i] - result[i];
                if(temp < 0){
                    big[i-1] = big[i-1] - 1;
                    borrow = 2;
                }
                result[i] = (big[i] + borrow) - result[i];
            }
        }
    }else{
        int carry = 0,sum;
        for(int i=MAX-1; i > 0;i--){
            sum = a[i] + b[i] + carry;
            carry = sum/2;
            if(sum > 1){
                result[i] = (int)sum%2;
            }else{
                result[i] = (int)sum;
            }
        }
        if(carry == 0){
            if(a[0] == 0 && b[0] == 0 ){   // both positive
                result[0] = 0;
            }
            else if(a[0] == 1 && b[0] == 1){ // both negative
                result[0] = 1;
            }
        }else{      // Carry is available.. Overflow
            printf("\nOverflow: The result is not reliable!\n\n");
        }
    }

}

void func_signed_mag_subtraction(int *a, int *b, int *result) {
    if(a[0] == 0 && b[0] == 1){         // (+X) - (-Y) = X+Y
        int carry = 0,sum;
        for(int i=MAX-1; i > 0;i--){
            sum = a[i] + b[i] + carry;
            carry = sum/2;
            if(sum > 1){
                result[i] = (int)sum%2;
            }else{
                result[i] = (int)sum;
            }
        }

        if(carry != 0){
            printf("\nOverflow: The result is not reliable!\n\n");
            sum = a[0] + b[0] + carry;
            if(sum > 1){
                result[0] = (int)sum%2;
            }else{
                result[0] = (int)sum;
            }
        }else {
            result[0] = 0;  // result is positive
        }
    }else if(a[0] == 1 && b[0] == 0){   // (-X) - (+Y) = -(X+Y)
        int carry = 0,sum;
        for(int i=MAX-1; i > 0;i--){
            sum = a[i] + b[i] + carry;
            carry = sum/2;
            if(sum > 1){
                result[i] = (int)sum%2;
            }else{
                result[i] = (int)sum;
            }
        }
        result[0] = 1;  // result is negative
    }else if(a[0] == 0 && b[0] == 0){   // (+X) - (+Y) = (X-Y)
        if(greaterDigit(a,b)){    // X > Y
            result[0] = 1;  // Negative
        }else{
            result[0] = 0;  //Positive
        }
        int borrow = 0,sum;
        for(int i = MAX-1; i>0;i--){
            borrow = 0;
            int temp = a[i] - b[i];
            if(temp < 0){
                a[i-1] = a[i-1] - 1;
                borrow = 2;
            }
            result[i] = (a[i] + borrow) - b[i];

        }
        if(borrow != 0){    // last borrow
            int big[MAX] = {1,0,0,0,0,0,0,0};
            for(int i = MAX-1; i>0;i--){
                borrow = 0;
                int temp = big[i] - result[i];
                if(temp < 0){
                    big[i-1] = big[i-1] - 1;
                    borrow = 2;
                }
                result[i] = (big[i] + borrow) - result[i];
            }
        }
    }else if(a[0] == 1 && b[0] == 1){   // (-X)-(-Y) = -X+Y = Y - X
        if(greaterDigit(a,b)){    // X > Y
            result[0] = 0;  // Positive
        }else{
            result[0] = 1;  //Negative
        }
        int borrow = 0;
        for(int i = MAX-1; i>0;i--){
            borrow = 0;
            int temp = b[i] - a[i];
            if(temp < 0){
                b[i-1] = b[i-1] - 1;
                borrow = 2;
            }
            result[i] = (b[i] + borrow) - a[i];

        }
        if(borrow != 0){    // last borrow
            int big[MAX] = {1,0,0,0,0,0,0,0};
            for(int i = MAX-1; i>0;i--){
                borrow = 0;
                int temp = big[i] - result[i];
                if(temp < 0){
                    big[i-1] = big[i-1] - 1;
                    borrow = 2;
                }
                result[i] = (big[i] + borrow) - result[i];
            }
        }
    }
}
