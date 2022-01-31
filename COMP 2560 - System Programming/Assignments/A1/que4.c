#include <unistd.h>		
#include <stdio.h>	
#include <fcntl.h>	

int main() 
{ 
    FILE *fp1; 
    char file_name[50];
    char check;
    
    printf("Enter File Name : ");
    scanf("%s",file_name);
    fflush(stdin);
    fp1 = fopen(file_name, "a+");
  
    if (fp1 == NULL) 
    { 
        printf("Could not open file"); 
        return 0; 
    } 
    printf("\nIn file : \n");
    while((check=fgetc(fp1))!=EOF) {
        printf("%c",check);
    }
    
    char input_data[1000];
    printf("\nWhat Information you want to append in %s file : ",file_name);
    scanf("%[^.]s",input_data); 
    printf("input data : %s\n",input_data);
    
    fputs(input_data, fp1);
    fclose(fp1);

    fp1 = fopen(file_name,"r");
    char read_file;
    printf("Updated File : \n");
    while((read_file=fgetc(fp1))!=EOF) {
        printf("%c",read_file);
    }
    printf("\n");
    
  
    fclose(fp1); 
    return 0; 
} 
