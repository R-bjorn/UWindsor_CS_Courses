/*
 * Name : Ravi Trivedi
 * St. No: 105197609
 * */

#include "smallsh.h"
#include <stdlib.h>
#include <string.h>

// removed the tokbuf[] ...
static char inpbuf[MAXBUF],*ptr = inpbuf;
char *tok;

char del[] = " ";       // Space char*
int number = 0;

int inarg(char c);

int userin(char *p)
{
    number = 0 ;        // Resetting number
    int c, count;
    ptr = inpbuf;

    printf("%s", p);
    count = 0;
    while (1)
    {
        if ((c = getchar()) == EOF)
            return(EOF);
        if (count < MAXBUF)
            inpbuf[count++] = c;
        if (c == '\n' && count < MAXBUF)
        {
            inpbuf[count] = '\0';
            return count;
        }

        if (c == '\n')
        {
            printf("smallsh: input line too long\n");
            count = 0;
            printf("%s ", p);
        }
    }
}

/* In order to get the token, I used strtok function from string.h ... 
 * First, I created a char array same as inpbuf to make a copy of inpbuf to temp...
 * Then I used strtok to split all the white spaces from the temp and get the token. 
 * I used inpbuf to keep track of type. 
 * number is used for the first time, we need to make a copy of inpbuf, that should only happen one time, the second time, it should use NULL parameter in the strtok, that's why i used number variable
 * Continue to runcommand.c ...
 * */ 


int gettok(char **outptr)
{
    int type;

    if (number++ == 0){
        char temp[MAXBUF];  // Making a copy of input array to this new temp array
        strcpy(temp,inpbuf);
        tok = strtok(temp,"\n");    //Removing last \n
        tok = strtok(temp,del);     //Removing all the spaces
    }else{
        tok = strtok(NULL,del);
    }
    *outptr = tok;

    while (*ptr == ' ' || *ptr == '\t') {
        ptr++;
    }

    switch (*ptr++) {
        case '\n':
            type = EOL;
            break;
        case '&':
            type = AMPERSAND;
            break;
        case ';':
            type = SEMICOLON;
            break;
        default:
            type = ARG;

            while (inarg(*ptr)) {
                *ptr++;
            }
    }
    return type;
}

//****************************************************************************************
static char special[] = { ' ', '\t', '&', ';', '\n', '\0' };
int inarg(char c)
{
    char *wrk;
    for (wrk = special; *wrk; wrk++)
    {
        if (c == *wrk)
            return (0);
    }
    return (1);
}

