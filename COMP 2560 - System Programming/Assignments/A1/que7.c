#include <unistd.h> 
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>


void filecopy(int ifd, int ofd)
{
    long int n1,n2;
    char buffer[100];
    n1=read(ifd, buffer, 100);
    while((n1=read(ifd, buffer, 100)) > 0){
	    n2 = write(ofd, buffer, n1);
        if(n2 != n1){
            perror("writing problem ");  exit(3);
        }
    }
}

int main(int argc, char* argv[])
{
    int fd;
    
    if (argc == 1){
        filecopy(STDIN_FILENO, STDOUT_FILENO);
    }else{
        while(--argc >0){
            if ((fd = open(argv[1], O_RDONLY)) == -1)/*(fp = fopen(*++argv, "r")) == NULL)*/{
                printf("cat: can not open %s\n", *argv);
                return 1;
            }else{
                filecopy(fd, STDOUT_FILENO);
                close(fd);
            }
        }
    }
    return 0;
}

