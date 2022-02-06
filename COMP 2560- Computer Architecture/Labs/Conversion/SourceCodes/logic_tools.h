//
// Created by ravid on 10/18/2020.
//

#ifndef LAB3_LOGIC_TOOLS_H
#define LAB3_LOGIC_TOOLS_H
#define MAX 8

void func_and(int a[], int b[], int result[]){
    for(int i=0; i < MAX; i = i + 1){
        result[i] = a[i] & b[i];
    }
}
void func_or(int a[], int b[], int result[]){
    for(int i=0; i < MAX; i = i + 1){
        result[i] = a[i] | b[i];
    }
}
void func_not(int a[], int result[]){
    for(int i=0; i < MAX; i = i + 1){
        result[i] = !a[i];
    }
}

#endif //LAB3_LOGIC_TOOLS_H
