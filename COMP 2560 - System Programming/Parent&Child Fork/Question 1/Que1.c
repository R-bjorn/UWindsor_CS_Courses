#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define MAX 100 
static char buf[MAX+1];
static float arr[MAX+1];        // Array of floating point numbers 


float *getflotingpt(FILE *infd){
    int c , count = 0 ;
    
    do{                 // Checking if the char is space 
        c = getc(infd);
    }while(isspace(c));
    ungetc(c,infd);         // pushing the character back to the file (which is not a space)
    
    if(c == EOF){       // Checking if the file reached to the end
        return 0;
    }
    
    fscanf(infd,"%f",&arr[count++]);        // use fscanf to get the floating points 
    
    return arr;         // return arr of floating point numbers 
}


int main()
{
    FILE *fp_in = fopen("records.txt","r");
    int i = 0;
    
    printf("Floating Points from File : \n");
    while(getflotingpt(fp_in)){
        printf("%.1f\n",*arr);
        *arr += 10 ;
        
        FILE *fp_res = fopen("result.txt","a+");
        
        putc( (char)*arr , fp_res);
        
        fclose(fp_res);
        
    }
    
    
    FILE *fp_re = fopen("result.txt","r");
    
    char ch;
    
    while( !feof(fp_re)){
        ch = getc(fp_re);
        printf("%c\n",ch);
    }
    
    fclose(fp_in);
    fclose(fp_re);
    
    
    return 0;
}

