#include "smallsh.h"

char *prompt = "Command> ";

int userin(char *p);
void procline(void);

int main()
{
	while (userin(prompt) != EOF)
		procline();
}


