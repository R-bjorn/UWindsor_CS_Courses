#include<stdio.h>
#include<math.h>
#define MAX 8

void to_decimal(int a[]){
    long int total=0;
    int pos = MAX-1;
    for(int i = 0;i < MAX;i++){
        total += pow(2,pos)*a[i];
        pos--;
    }
    printf("Decimal : %d\n",total);
}
void to_octal(int a[]){
    long int total=0;
    int pos = MAX-1;
    for(int i = 0;i < MAX;i++){
        total += pow(2,pos)*a[i];
        pos--;
    }
    int oct_a[MAX];
    int i = 0;
    while(total > 0){
        oct_a[i]= total % 8;
        total /= 8;
        i++;
    }
    printf("Octal : ");
    for(int j = i-1 ; j>=0 ; j--){
        printf("%d",oct_a[j]);
    }
    printf("\n");
}
void to_hexadecimal(int a[]){
    long int total=0;
    int pos = MAX-1;
    for(int i = 0;i < MAX;i++){
        total += pow(2,pos)*a[i];
        pos--;
    }
    int hex_a[MAX];
    int i = 0;
    while(total > 0){
        hex_a[i]= total % 16;
        total /= 16;
        i++;
    }
    printf("Hexadecimal : ");
    for(int j = i-1 ; j>=0 ; j--){
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
        }else{
            printf("F");
        }
    }
    printf("\n");
}