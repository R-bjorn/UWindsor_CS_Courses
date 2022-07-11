#define MAX 8
#include "logic_tools.h"

void func_1s_comp(int a[], int result[]){
    func_not(a,result);
}
void func_2s_comp(int a[], int result[]){
    func_1s_comp(a,result);
    int sum[MAX] = {0,0,0,0,0,0,0,0};
    int arr_one[MAX] = {0,0,0,0,0,0,0,1};
    int c = 0;
    for(int i = 7; i >= 0 ; i--){
        sum[i] = ((arr_one[i] ^ result[i]) ^ c);
        c = ((arr_one[i] & result[i]) | (arr_one[i] & c)) | (result[i] & c);
    }
    for(int i = 0 ; i<MAX ; i++){
        result[i] = sum[i];
    }
}
void func_2s_comp_star(int a[], int result[]){
    int allow = 0;
    for(int i = MAX-1 ; i >= 0 ; i--){
        if(allow == 1){
            result[i] = !a[i];
        }else{
            result[i] = a[i];
        }

        if(a[i] == 1){
            allow = 1;
        }
    }
}