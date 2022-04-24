#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <stdbool.h>


int main(int args, char argv[]){
	
	const int width = 1000;
	const int height = 1000;

	FILE *imageFile = fopen("picture.ppm", "w");
	if(imageFile == NULL){
		perror("Error!");
		exit(1);
	}

	fprintf(imageFile, "%s\n","P3");
	fprintf(imageFile, "%d %d\n",width, height);
	fprintf(imageFile,"255\n");
	
	srand(time(NULL));
	int i,j, startw = 500, endw = 500;
	bool tri = true;
	for(i = 0 ; i < height; i++)
	{
		for(j = 0 ; j < width ; j++)
		{			
			if(i <= height/2 && j <= width/2){ // green 1st box
				fprintf(imageFile,"%d ", 0);
				fprintf(imageFile,"%d ", 255);
				fprintf(imageFile,"%d   ", 0);
			}
			else if(i <= height/2 && j > width/2){ // pink 2nd Box
				fprintf(imageFile,"%d ", 255);
				fprintf(imageFile,"%d ", 192);
				fprintf(imageFile,"%d   ", 203);
			}
			else if(i >= height/2 && j <= width/2){ // orange 3rd Box
				fprintf(imageFile,"%d ", 255);
				fprintf(imageFile,"%d ", 215);
				fprintf(imageFile,"%d   ", 0);
			}
			else if(i >= height/2 && j > width/2){ // yellow 4th Box
				fprintf(imageFile,"%d ", 255);
				fprintf(imageFile,"%d ", 255);
				fprintf(imageFile,"%d   ", 0);
			}
		}
	}

	fclose(imageFile);

	return 0;
}

/*

// center blue square
			if(i >= 250 && i <= 750) // blue
			{
				if(j >= startw && j <= endw && tri){
					fprintf(imageFile,"%d ", 0);
					fprintf(imageFile,"%d ", 0);
					fprintf(imageFile,"%d   ", 255);
					startw++;
					endw--;
				}
				else if(j >= startw && j <= endw && !tri){
					fprintf(imageFile,"%d ", 0);
					fprintf(imageFile,"%d ", 0);
					fprintf(imageFile,"%d   ", 255);
					startw--;
					endw++;
				}
				if(startw == 250 && endw == 750)
					tri = false;

			}
			// Main Background
			else 


*/
