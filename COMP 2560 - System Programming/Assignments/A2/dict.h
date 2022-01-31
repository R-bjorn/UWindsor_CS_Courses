/*  dict.h  header for data dictionary routines. */

#include <stdio.h>

struct dict_elem{
	char d_name[15]; // name of dictionary member
	int d_start;  //starting position in record
	int d_length; //length of field
	int d_type; //denotes type of data
};

#define ERROR (-1)
#define SUCCESS 0

