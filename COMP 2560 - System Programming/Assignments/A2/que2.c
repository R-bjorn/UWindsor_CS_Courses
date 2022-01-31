#include "dict.h"
#include <string.h>

int writedict(const char *dictname, struct dict_elem *elist){

	int j;
	FILE *outf;

	if ((outf = fopen(dictname, "w")) == NULL){
		return ERROR;
	}

	//cculate length of the aray
	for (j = 0; elist[j].d_length != 0; j++)
		;

	//write out list of dict_elem structures
	if (fwrite((void*)elist, sizeof(struct dict_elem), j, outf)<j){
		fclose(outf);
		return ERROR;
	}


	fclose(outf);
	return SUCCESS;
}

//**************************************************************************


struct dict_elem * readdict(const char *dictname, 
  				     struct dict_elem *inlist, int maxlength)
{

	int i;
	FILE *inf;

	if ((inf = fopen(dictname, "r")) == NULL){
		return NULL;
	}
	//read in dict_elem structures from file
	for (i = 0; i < maxlength - 1; i++)
	{
		if (fread((void*)&inlist[i], sizeof(struct dict_elem), 1, inf)<1)
		{
			break;
		}
	}


	fclose(inf);

      //mark end of list
	inlist[i].d_length = 0;

	return inlist;
}


// your main function goes here.
int main(void)
{

    struct dict_elem dict ;
    strcpy(dict.d_name, "John");
    dict.d_start = 0;
    dict.d_length = 10;
    dict.d_type = 1;
    
    int a;
    a = writedict("que2file.txt",&dict);


    struct dict_elem *dict2 ;
    dict2 = readdict("que2file.txt",&dict,100);
    
    printf("Name of dictionary member : %s\n",dict2->d_name);
    printf("Positions in Record : %d\n",dict2->d_start);
    printf("Length of field : %d\n",dict2->d_length);
    printf("Denotes type of data : %d\n",dict2->d_type);
    

}
