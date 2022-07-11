#include <stdio.h>
int main(void) {

    while(1){
        int x, y;
        int option;
        printf("Enter the command Number:\n 1) AND\n 2) OR\n 3) NOT\n 4) Exit\n");
        scanf("%d",&option);

        if (option == 4) {
            break;
        }

        fflush(stdout);
        printf("X=");
        scanf("%d", &x);

        if (option != 3) {
             printf("Y=");
             scanf("%d", &y);
        }

        if (option == 1) {
            printf("%d AND %d is %d\n\n", x, y, x & y);
        }else if(option == 2) {
            printf("%d or %d is %d\n\n", x, y, x | y);
        }else{
            printf("%d not is %d\n\n",x,!x);
        }
    }
    printf("Exiting!!!");
}
