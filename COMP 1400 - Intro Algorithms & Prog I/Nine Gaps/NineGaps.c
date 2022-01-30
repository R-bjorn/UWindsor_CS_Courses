#include <stdio.h> 				// C Standard Library for input/output functions 
#include <stdlib.h>				 // C Standard Library for some utility functions,     
					// such as rand() 
#include <time.h> 				// C Standard Library for date and time functions 


					// Here you will later on (in the next assignments) declare some variables  
					// to use them inside the program 
int main(void) { 

            	int level , play_again;
            	int Value = 1 , loop ;
	int Empty_box;
	int rand_num =  1 ;
	int box[3][3], i, j ;
            	
            					  // Show a Welcome message to the player.  
            	printf("Welcome to Nine-Gaps game!...\n");
            	printf("*****************************\n");  
            	printf("      *****************      \n");  
            	printf("                  *****            \n");  
            	printf("                      *              \n"); 
            					 
            
            					 // Start the outer loop to ask the level of difficulty from the   
            					// player. This loop gives the player the opportunity to play again  
            					// after the game is over. 
             
            	while(1) { 
		printf("Enter the level of Difficulty!!!\n");
             
            					// Ask the player to select the level of difficulty of the game, and   
            					// check the validity of the user’s input. If the user enters an   
            					// invalid level, program should provide a proper message and   
            					// control should go the beginning of the outer loop. 
            
             		
            		printf("1.Beginner \n2.Intermediate \n3.Advance \n4.Expert \n---->");
            		scanf("%d",&level);
            		if(level == 1 ){
            						// Beginner Level
			Empty_box = 3;
            		}
            		else if(level == 2){
            						// Intermediate Levl
			Empty_box = 5;
            		}
            		else if(level == 3){
            						// Advance Level	
			Empty_box = 7;
            		}
            		else if(level == 4){
            						// Expert Level
			Empty_box = 9;
            		}
            		else {
            			printf("\nInvalid Number\n");
            			printf("Please enter a valid Level Number\n\n");
            			printf("Restarting ...... \n");
            			continue ;
            		}
             
             
                
            					// Start the inner loop to repeatedly ask the player to enter a value   	
            					// between 1 and 9, and selected row and column, each one between 1   
            					// and 3. (The sentinel value is 0). If the inputs are not valid, show 
            					// a proper message and control should go the beginning of the inner loop. 
          	  loop = 1;
          	  while(loop <= Empty_box ){
		Value = 0 ;
    		for(i =1 ; i <= Empty_box ; i++){
    			for(j=1 ; j<= Empty_box ; j++){
    				printf("Enter the Correct Number in Empty Box between 1 to 9\n---->");
    				scanf("%d",&box[i][j]);
	
				if(box[i][j] == rand_num){
					Value = 1;
				}
				break ;
    			}
    			break;
    		} 
		loop = loop + 1;   				

					 // Show a success or fail message and end the inner loop.
                         		
            		if(Value == 1 ){
            			printf("Congratulations!!!! \nYou have solved the puzzle\n");
            		}
            		else{
            			printf("OOPS!!!! \nSeems like You have lost the puzzle\n");
            			printf("Better luck next time\n");
            		}

	}
             
             
            					// Ask the player if s/he wants to play again. If yes, control should   
            					// go to the beginning of the outer loop. Otherwise, loop will end and   
            					// program should exit after showing a Goodbye message to the player.   
             			
            		printf("Do you want to play again?\n");
            		printf("1.YES \n2.NO\n---->");
            		scanf("%d",&play_again);
            
            		if(play_again == 2){
            			break ;		
            		}
            	}
            	printf("\nIt was nice spending time with you\n");
            	printf("Goodbye!!! \nHave a Nice Day");

} 
 
 