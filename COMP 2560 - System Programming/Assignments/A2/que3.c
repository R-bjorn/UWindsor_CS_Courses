#include <stdio.h>
#include <stdlib.h> 

void Linereverse(FILE *fp1, FILE *fp2)
{
    char buf[128];
 
    if(fgets(buf, sizeof(buf), fp1) != NULL) {
        Linereverse(fp1, fp2);
    }
    
    fputs(buf, fp2);
}

int main(int argc, char *argv[])
{
    FILE *fp_in = fopen(argv[1], "r");
    FILE *fp_out = fopen(argv[2], "w");
    
    if(fp_in == NULL) {
        perror("Unable to open file!");
        exit(1);
    }
    
    Linereverse(fp_in, fp_out);
    
    fclose(fp_in);
    fclose(fp_out);
    return 0;
}
