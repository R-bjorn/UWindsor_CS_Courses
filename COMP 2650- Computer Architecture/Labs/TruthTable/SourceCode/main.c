/*
 * NAME : RAVI TRIVEDI
 * Std NO.: 105197609
 * UwinID: TRIVE11W
 * */

#include <stdio.h>
#include <math.h>

#define INPUT_VARIABLE_COUNT 3
#define OUTPUT_VARIABLE_COUNT 1

int TRUTH_TABLE_ROW_COUNT ;

void func_increment(int start[]){
    const int add[] = {0,0,1};
    int sum, carry =0 ;
    for(int i = 2; i >= 0 ; i--){
        sum = start[i] + add[i] + carry;
        carry = sum/2 ;
        if(sum > 1){
            start[i] = (int)sum%2;
        }else{
            start[i] = (int)sum;
        }
    }
}

void build_left_side(int truth_table[][INPUT_VARIABLE_COUNT + OUTPUT_VARIABLE_COUNT]) {
    int row[INPUT_VARIABLE_COUNT] = {0,0,0};
    for(int i = 0 ; i < TRUTH_TABLE_ROW_COUNT ; i++) {
        for(int j = 0 ; j < INPUT_VARIABLE_COUNT ; j++) {
            truth_table[i][j] = row[j];
        }
        func_increment(row);
    }
}

void build_right_side(int truth_table[][INPUT_VARIABLE_COUNT + OUTPUT_VARIABLE_COUNT]){
    //build the right side of truth table for the output variables
    for(int i = 0; i < TRUTH_TABLE_ROW_COUNT; i = i + 1){

        //for each output variable F1, F2, ...
        for(int j = INPUT_VARIABLE_COUNT ; j < INPUT_VARIABLE_COUNT + OUTPUT_VARIABLE_COUNT; j = j + 1){
            printf("output value for row# %d of F%d output variable:",i,(i+1));
            scanf("%d",&truth_table[i][j]);
            if (truth_table[i][j] != 0 && truth_table[i][j] != 1) {
                printf("\nInvalid Input!! Try again\n");
                i--;
                continue;
            }
        }
    }
}

int main(void) {

    setbuf(stdout, NULL);

    TRUTH_TABLE_ROW_COUNT = (int) pow(2,INPUT_VARIABLE_COUNT);
    int truth_table[8][INPUT_VARIABLE_COUNT + OUTPUT_VARIABLE_COUNT] = {0};
    const char variables[INPUT_VARIABLE_COUNT + OUTPUT_VARIABLE_COUNT] =  {'Z', 'Y', 'X', 'F'};

    build_left_side(truth_table);
    build_right_side(truth_table);

    //printing the header of truth table with variable names for inputs and outputs

    printf("\nTruth Table : \n");
    //printing the header for input variables
    for(int i = 0; i < INPUT_VARIABLE_COUNT; i = i + 1){
        printf("%c, ", variables[i]);
    }
    printf(" : ");

    //printing the header for output variables
    for(int i = INPUT_VARIABLE_COUNT; i < INPUT_VARIABLE_COUNT + OUTPUT_VARIABLE_COUNT; i = i + 1){
        printf("%c", variables[i]);
    }
    printf("\n");

    //printing the content of each row
    for(int i = 0; i < TRUTH_TABLE_ROW_COUNT; i = i + 1){

        //printing the content of each row regarding the input variables
        for(int j = 0; j < INPUT_VARIABLE_COUNT; j = j + 1){
            printf("%d, ", truth_table[i][j]);
        }
        printf(" : ");

        //printing the content of each row regarding the output variables
        for(int j = INPUT_VARIABLE_COUNT; j < INPUT_VARIABLE_COUNT + OUTPUT_VARIABLE_COUNT; j = j + 1){
            printf("%d", truth_table[i][j]);
        }
        printf("\n");
    }

    return 0;
}


