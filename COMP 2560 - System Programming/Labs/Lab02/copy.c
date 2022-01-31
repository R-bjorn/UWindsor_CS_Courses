#include <unistd.h> 
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
int main(int argc, char *argv[]){ 
 int fd1, fd2;
 char buffer[100];
 long int n1, n2;
 if(((fd1 = open(argv[1], O_RDONLY)) == -1) ||  ((fd2 = open(argv[2],  
       O_CREAT|O_WRONLY|O_TRUNC,0700)) == -1)){
     perror("file problem ");  
     exit(1);
 }
 do{
      n2 = write(fd2, buffer, n1); 
      if(n1 == -1){
             perror("Reading problem ");  
             exit(2);
      }
      if(n2 != n1){
             perror("writing problem ");  
             exit(3);
       }
}while((n1=read(fd1, buffer, 100)) > 0);
// Case of an error exit from the loop  
  close(fd1);
  close(fd2);  
  exit(0);
}
