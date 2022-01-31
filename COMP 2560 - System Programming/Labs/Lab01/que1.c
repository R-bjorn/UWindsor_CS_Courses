#include <stdio.h>
#include <fcntl.h>

int main(void)
{
    int filedir;
    filedir = open("file.txt",O_CREAT|O_EXCL);
    if(filedir == -1){
        printf("File already Exist!!\n");
        printf("file open() information: %d\n",filedir);
    }else{
        printf("File successfully created!!\n");    
        printf("file open() information: %d\n",filedir);
    }
}

