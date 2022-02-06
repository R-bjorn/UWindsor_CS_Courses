//
// Created by ravid on 11/9/2020.
//

#include "convert_tools.h"
#include "arithmetic_tools.h"
#include "comp_tools.h"
#include<stdio.h>
#include<math.h>
#define MAX 8

void to_decimal(int *a) {
    char sign = '+';
    if(bigger(a)){      // Negative decimal
        sign = '-';
        func_2s_comp(a,a);
    }
    long int total=0;
    int pos = MAX-1;
    for(int i = 0;i < MAX;i++){
        total += (long)pow(2,pos)*a[i];
        pos--;
    }
    printf("Decimal : %c(%ld)\n",sign,total);
}

void to_octal(int *a) {
    long int total=0;
    int pos = MAX-1;
    for(int i = 0;i < MAX;i++){
        total += (long)pow(2,pos)*a[i];
        pos--;
    }
    int oct_a[MAX] = {0};
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
    if(bigger(a)){
        char p;
        printf("This result is in Negative: Press Y to see the result in Positive :");
        getchar();
        scanf("%c",&p);
        if(p == 'Y'){
            func_2s_comp(a,a);
            long int total=0;
            int pos = MAX-1;
            for(int i = 0;i < MAX;i++){
                total += (long)pow(2,pos)*a[i];
                pos--;
            }
            int oct_a[MAX] = {0};
            int i = 0;
            while(total > 0){
                oct_a[i]= total % 8;
                total /= 8;
                i++;
            }
            printf("Octal : -(");
            for(int j = MAX-1 ; j>=0 ; j--){
                printf("%d",oct_a[j]);
            }
            printf(")\n");
        }
    }
}

void to_hexadecimal(int *a) {
    long int total = 0;
    int pos = MAX - 2;
    for (int i = 1; i < MAX; i++) {
        total += (long) pow(2, pos) * a[i];
        pos--;
    }
    int hex_a[MAX] = {0};
    int i = 0;
    while (total > 0) {
        hex_a[i] = total % 16;
        total /= 16;
        i++;
    }
    printf("\nHexadecimal : ");
    for (int j = MAX - 1; j >= 0; j--) {
        if (hex_a[j] >= 0 && hex_a[j] <= 9) {
            printf("%d", hex_a[j]);
        } else if (hex_a[j] == 10) {
            printf("A");
        } else if (hex_a[j] == 11) {
            printf("B");
        } else if (hex_a[j] == 12) {
            printf("C");
        } else if (hex_a[j] == 13) {
            printf("D");
        } else if (hex_a[j] == 14) {
            printf("E");
        } else if (hex_a[j] == 15) {
            printf("F");
        } else {
            printf("0");
        }
    }
    printf("\n");
    if (bigger(a)) {
        char p;
        printf("This result is in Negative: Press Y to see the result in Positive :");
        getchar();
        scanf("%c", &p);
        if (p == 'Y') {
            func_2s_comp(a, a);
            long int total = 0;
            int pos = MAX - 1;
            for (int i = 0; i < MAX; i++) {
                total += (long) pow(2, pos) * a[i];
                pos--;
            }
            int hex_a[MAX] = {0};
            int i = 0;
            while (total > 0) {
                hex_a[i] = total % 16;
                total /= 16;
                i++;
            }
            printf("\nHexadecimal : -(");
            for (int j = MAX - 1; j >= 0; j--) {
                if (hex_a[j] >= 0 && hex_a[j] <= 9) {
                    printf("%d", hex_a[j]);
                } else if (hex_a[j] == 10) {
                    printf("A");
                } else if (hex_a[j] == 11) {
                    printf("B");
                } else if (hex_a[j] == 12) {
                    printf("C");
                } else if (hex_a[j] == 13) {
                    printf("D");
                } else if (hex_a[j] == 14) {
                    printf("E");
                } else if (hex_a[j] == 15) {
                    printf("F");
                } else {
                    printf("0");
                }
            }
            printf(")\n");
        }
    }
}

void out_put(int *result) {
    printf("Binary : ");
    for(int i=0; i < MAX; i = i + 1){
        printf("%d", result[i]);
    }
    printf("\n");
    if(bigger(result)){
        char p;
        printf("This result is in Negative: Press Y to see the result in Positive :");
        getchar();
        scanf("%c",&p);
        if(p == 'Y'){
            func_2s_comp(result,result);
            printf("Binary : -(");
            for(int i=0; i < MAX; i = i + 1){
                printf("%d", result[i]);
            }
            printf(")\n");
        }
    }
}
