#include <unistd.h>		
#include <stdio.h>	
#include <fcntl.h>	

int main()
{
    FILE *fp1;
    fp1 = fopen("que2write.txt","w");
    fprintf(fp1,"%d   %-4s\t%-7s\t%d\n",101,"GM","Buick",2010);
    fprintf(fp1,"%d   %-4s\t%-7s\t%d\n",102,"Ford","Lincoln",2005);
    fclose(fp1);	
    return 0;
}
