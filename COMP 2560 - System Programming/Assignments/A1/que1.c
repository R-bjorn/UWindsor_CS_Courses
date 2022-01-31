#include <unistd.h>		
#include <stdio.h>	
#include <fcntl.h>	

int main()
{
  int file = open("que1read.txt",O_RDONLY); 
  int size = lseek(file, 0, SEEK_END); 

  printf("File Size : %d\n", size);

  close(file);		
  
  return 0;
}
