#include <stdio.h>
#include <stdlib.h>
#include<time.h>
 
int main() {
                          int c, n;
                          int num_1,num_2;
                          int total ;

			// Bonus Part ---- 
                          srand(time(0)); 
                          
                        													  // Part----a
                         
                          num_1 = rand() %10 ;
                          num_2 = rand() %10 ;
                          
                          printf("Two random numbers are: %d   %d\n",num_1,num_2);
                          
                          													// Part----b
                        	 
                          for (c = 1; c <= 1; c++) {
                                    do{
                                        n = rand() %100 ;
                                    }while(n > 2);
                                    printf("Operation is : %d\n", n);
                          }
                          
                          													//Part----c
                          
                          if(n == 0){
                                      total = num_1 + num_2;
                                      printf("Sum of Two Random Numbers : %d and %d  is == %d",num_1,num_2,total);
                          }
                          else if(n == 1){
                                      total = num_1 - num_2 ;
                                      printf("Substraction of Two Random Numbers : %d and %d  is == %d",num_1,num_2,total);
                          }
                          else {
                                      total = num_1*num_2;
                                      printf("Multiplication of Two Random Numbers : %d and %d  is == %d",num_1,num_2,total);
                          }
}