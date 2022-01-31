#include <stdio.h>
#include <stdlib.h>

int main(int args, char const* argv[])
{
    // This file will be used to read the text files
    FILE *fp ;
    
    // This file will be used to combine all the files thta user wants to copy into one file 
    FILE *comblinedFile = fopen(argv[args-1],"w");
    
    
    // char c to read the character from file 
    char c;
    
    
    //Checking if the comblinedFile opens or not
    if(comblinedFile == NULL){
        printf("ERROR : FILE CAN'T OPEN");
        exit(1);
    }
    
    // we have to print all the files in reverse order 
    for(int i = args-2 ; i >= 1 ; --i){
        // Now here we will open all the text files
        fp = fopen(argv[i],"r");
        
        // Checking if the fp file opens successfully or not 
        if(fp == NULL){
            printf("ERROR : FILE CAN'T OPEN");
            exit(1); 
        }
        
        // copying the text file into comblinedFile text by character by character
        while((c = getc(fp)) != EOF){
            putc(c,comblinedFile);
        }
	// Just to put the new file into new line 
        putc('\n',comblinedFile);
        
        // close the file fp so that we can use this again 
        fclose(fp);
    }
    
    // clase the comblinedFile
    fclose(comblinedFile);

    return 0;
}

