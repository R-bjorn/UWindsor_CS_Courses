#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h> 
#include <string.h>

int main(int args, char const* argv[])
{
    FILE *fp_in = fopen(argv[1],"r");
    FILE *fp_out = fopen(argv[2],"w");
    
    char c;
    
    if(fp_in == NULL || fp_out == NULL){
        printf("ERROR : FILE CAN'T OPEN");
        exit(1);
    }
    
    fseek(fp_in, 0L, SEEK_END);
    int count = ftell(fp_in);
    char arr[count];    
    fseek(fp_in, 0L, SEEK_SET);
    
    for(int i = 0 ; i < count ; i++){
        c = getc(fp_in);
        arr[i] = c;
    }
    
    for(int i = count-2 ; i >=0 ; i--){
        putc(arr[i],fp_out);
    }
    
    fclose(fp_in);
    fclose(fp_out);
    
    return 0;
    
}
