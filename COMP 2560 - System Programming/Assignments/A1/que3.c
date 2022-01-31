#include <unistd.h>		
#include <stdio.h>	
#include <fcntl.h>	

int main() 
{ 
    FILE *fp1; 
    int lines = 0; 
    char check;
  
    fp1 = fopen("que2write.txt", "r"); 
  
    if (fp1 == NULL) 
    { 
        printf("Could not open file"); 
        return 0; 
    } 
    
    while((check=fgetc(fp1))!=EOF) {
        if(check == '\n'){
            lines++;
        }
    }
    
    printf("Text file has %d lines\n", lines); 
  
  
    fclose(fp1); 
    return 0; 
} 
