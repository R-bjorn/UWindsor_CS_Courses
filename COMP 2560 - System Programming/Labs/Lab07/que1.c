/*
Name: Ravi Trivedi
Std No.: 105197609
*/


#include <stdio.h>          // this header is for printf and while loop
#include <string.h>         // this header contains strsignal() function
#include <signal.h>         // this header contains NSIG value.. which we can use to iterate through every signal


int main() {
    int count = NSIG - 1;   // NSIG is (number of signals) + 1
    int i = 0; 
    while(i++ < count) {       // while loop for looping through all the signal and printfing it
        printf("%d -> %s\n", i, strsignal(i));      //strsignal() returns a string describing a given signal number
        //If the signal number is invalid, it returns the unknown signal message.
    }
    return 0;
}

// As we can see from the output, we get two unknown signals. and 62 other running signals supported by linux server.
/*
1 -> Hangup
2 -> Interrupt
3 -> Quit
4 -> Illegal instruction
5 -> Trace/breakpoint trap
6 -> Aborted
7 -> Bus error
8 -> Floating point exception
9 -> Killed
10 -> User defined signal 1
11 -> Segmentation fault
12 -> User defined signal 2
13 -> Broken pipe
14 -> Alarm clock
15 -> Terminated
16 -> Stack fault
17 -> Child exited
18 -> Continued
19 -> Stopped (signal)
20 -> Stopped
21 -> Stopped (tty input)
22 -> Stopped (tty output)
23 -> Urgent I/O condition
24 -> CPU time limit exceeded
25 -> File size limit exceeded
26 -> Virtual timer expired
27 -> Profiling timer expired
28 -> Window changed
29 -> I/O possible
30 -> Power failure
31 -> Bad system call
32 -> Unknown signal 32         // signal number 32 is invalid 
33 -> Unknown signal 33         // signal number 33 is invalid 
34 -> Real-time signal 0
35 -> Real-time signal 1
36 -> Real-time signal 2
37 -> Real-time signal 3
38 -> Real-time signal 4
39 -> Real-time signal 5
40 -> Real-time signal 6
41 -> Real-time signal 7
42 -> Real-time signal 8
43 -> Real-time signal 9
44 -> Real-time signal 10
45 -> Real-time signal 11
46 -> Real-time signal 12
47 -> Real-time signal 13
48 -> Real-time signal 14
49 -> Real-time signal 15
50 -> Real-time signal 16
51 -> Real-time signal 17
52 -> Real-time signal 18
53 -> Real-time signal 19
54 -> Real-time signal 20
55 -> Real-time signal 21
56 -> Real-time signal 22
57 -> Real-time signal 23
58 -> Real-time signal 24
59 -> Real-time signal 25
60 -> Real-time signal 26
61 -> Real-time signal 27
62 -> Real-time signal 28
63 -> Real-time signal 29
64 -> Real-time signal 30
*/
