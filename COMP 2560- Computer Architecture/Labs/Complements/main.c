#include <stdio.h>
#define MAX 8

void welcome_msg(){
    printf("\n\nEnter the command number:\n");
    printf("0) Exit\n1) AND\n2) OR\n3) NOT\n4) 1's complement\n5) 2's complement\n6) 2's complement*\n");
}

void input_binary2(int X[] , int Y[]) {
    printf("Enter the first binary number:\n");
    int i = 0 ;
    while(i < MAX){
        printf("x%d=",i); fflush(stdout);
        scanf("%d", &X[i]);
        if(X[i] != 0 && X[i] != 1){
            printf("\nInvalid Input!! Try again\n");
            continue;
        }
        i++;
    }
    printf("Enter the second binary number:\n");
    i=0;
    while(i < MAX){
        printf("x%d=",i); fflush(stdout);
        scanf("%d", &Y[i]);
        if(Y[i] != 0 && Y[i] != 1){
            printf("\nInvalid Input!! Try again\n");
            continue;
        }
        i++;
    }
}
void input_binary1(int X[]){
    printf("Enter the binary number:\n");
    int i = 0 ;
    while(i < MAX){
        printf("x%d=",i); fflush(stdout);
        scanf("%d", &X[i]);
        if(X[i] != 0 && X[i] != 1){
            printf("\nInvalid Input!! Try again\n");
            continue;
        }
        i++;
    }
}
void out_put(int result[]){
    for(int i=0; i < MAX; i = i + 1){
        printf("%d", result[i]);
    }
}

// Start all the functions
void func_and(int a[], int b[], int result[]){
    for(int i=0; i < MAX; i = i + 1){
        result[i] = a[i] & b[i];
    }
}
void func_or(int a[], int b[], int result[]){
    for(int i=0; i < MAX; i = i + 1){
        result[i] = a[i] | b[i];
    }
}
void func_not(int a[], int result[]){
    for(int i=0; i < MAX; i = i + 1){
        result[i] = !a[i];
    }
}
void func_1s_comp(int a[], int result[]){
    func_not(a,result);
}
void func_2s_comp(int a[], int result[]){
    func_1s_comp(a,result);
    int sum[MAX] = {0,0,0,0,0,0,0,0};
    int arr_one[MAX] = {0,0,0,0,0,0,0,1};
    int c = 0;
    for(int i = 7; i >= 0 ; i--){
        sum[i] = ((arr_one[i] ^ result[i]) ^ c);
        c = ((arr_one[i] & result[i]) | (arr_one[i] & c)) | (result[i] & c);
    }
    for(int i = 0 ; i<MAX ; i++){
        result[i] = sum[i];
    }
}
void func_2s_comp_star(int a[], int result[]){
    int allow = 0;
    for(int i = MAX-1 ; i >= 0 ; i--){
        if(allow == 1){
            result[i] = !a[i];
        }else{
            result[i] = a[i];
        }

        if(a[i] == 1){
            allow = 1;
        }
    }
}

//End All the functions

int main(void) {
	setbuf(stdout, NULL);
	while(1){
        int x[MAX] , y[MAX], z[MAX] , option;
        welcome_msg();
        scanf("%d",&option);

        if(option == 0){
            printf("Exiting!!!!");
            break;
        }else if(option == 1){
            input_binary2(x,y);
            func_and(x,y,z);
            printf("The first number AND second binary yield:\n");
            out_put(z);
        }else if(option == 2){
            input_binary2(x,y);
            func_or(x,y,z);
            printf("The first number OR second binary yield:\n");
            out_put(z);
        }else if(option == 3){
            input_binary1(x);
            func_not(x,z);
            printf("The first number's NOT yield:\n");
            out_put(z);
        }else if(option == 4){
            input_binary1(x);
            func_1s_comp(x,z);
            printf("The first number's 1's complement yield:\n");
            out_put(z);
        }else if(option == 5){
            input_binary1(x);
            func_2s_comp(x,z);
            printf("The first number 2's complement yield:\n");
            out_put(z);
        }else if(option == 6) {
            input_binary1(x);
            func_2s_comp_star(x,z);
            printf("The first number 2's complement* yield:\n");
            out_put(z);
        }else{
            printf("Invalid Choice\nChoose again!!\n");
            continue;
        }
	}
	return 0;
}
