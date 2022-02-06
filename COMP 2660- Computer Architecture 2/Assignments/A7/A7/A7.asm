TITLE

; Name:		Ravi Trivedi
; Date:		28/03/2021
; ID:		105197609
; Description:	Assignment 7

INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib

.data	

	; data declarations go here
	txt1	byte	"What do you want to do now? > ", 0
	txt2	byte	"What is the size N of Array? (1 to 20) > ", 0
	txt3	byte	"What are the ", 0
	txt4	byte	" values in Array? > ", 0
	txt5	byte	"Size of Array is N = ", 0
	txt6	byte	"Array = ", 0
	txt7	byte	"Stack is empty", 0
	txt8	byte	"Array is ", 0
	txt9	byte	"before ArrayToStack", 0
	txt10	byte	"Stack is ", 0
	txt11	byte	"after ArrayToStack", 0
	txt12	byte	"Stack not empty", 0
	txt13	byte	"before StackToArray", 0
	txt14	byte	"after StackToArray", 0
	txt15	byte	"before StackReverse", 0
	txt16	byte	"after StackReverse", 0
	
	welcomeMsg	byte	"0 create an array || 1 move array to stack || 2 move stack to array || 3 reverse array || -1 exit", 0 
	byeMsg	byte	"Exiting, thank you for doing assignment 7 :-)", 0
	emtStack	byte	"Error - Stack is empty: Cannot perform StackToArray", 0
	errorInput	byte	"Invalid entry, please try again!", 0
	space	byte	"  ", 0

	Array	dword	20 dup(?)
	N		dword	0
	MAX		dword	20





.code
main PROC

	; program syntax here
mov		edi, esp								;storing the stack empty position in register edi(not used here elsewhere) to check empty stack
do1:											;do/while loop for the user menu
	mov		edx, offset welcomeMsg					;additional message to show user options
	call	writeString
	call	crlf
	call	crlf
	MOV		edx, offset txt1					;message prompting user to make an entry
	call	writeString
	call	readInt								;reading user entry
	call	crlf

while1:											;while conditions for the do							
	cmp		eax, 0
	je		cycle0								;if 0 go to cycle0 (create array)
	cmp		eax, 1
	je		cycle1								;if 1 go to cycle1 (array to stack)
	cmp		eax, 2
	je		cycle2								;if 2 go to cycle2 (stack to array)
	cmp		eax, 3
	je		cycle3								;if 3 go to cycle3 (reverse array)
	cmp		eax, -1
	je		stop								;if -1 goto stop (end program)
	mov		edx, offset errorInput
	call	writeString
	call	crlf
	call	crlf
	jmp		do1									;while none of the above, show error message and get back to do1

	cycle0:										;for option 0, we are creating the array
		do2:
			mov		edx, offset txt2			;message prompting for the size of the array
			call	writeString
			call	readDec						;reading integer entered from user
			call	crlf
		while2:
			cmp		eax, 0						;comparing user input to 0
			jbe		detour						;if equal to 0 or below, jump to detour
			cmp		eax, 20						;comparing user input to 20
			ja		detour						;if above 20, jump to detour
			jmp		continue1					;if satisfies conditions (1-20) then jump to continue1
		detour:
			mov		edx, offset errorInput			;displaying message of invalid entry
			call	writeString
			call	crlf
			call	crlf
			jmp		do2							;jumping back to do2 to prompt the user again for the size of the array
		continue1:
			mov		ecx, eax					;moving user input into ecx
			mov		N, eax						;moving register eax to variable N (the size)
			mov		esi, 0						;index register
			mov		edx, offset txt3			;displaying "What are the "
			call	writeString
			call	writeDec					;writing user integer entry of array size
			mov		edx, offset txt4			;displaying second part of message " values in Array?>"
			call	writeString
		cycle00:
			call	readDec
			mov		Array[4*esi], eax			;moving eax to 4 bytes at array address esi 
			inc		esi							;incrementing the memory address esi
			loop	cycle00						;looping cycle00 until all the user array inputs are read
		call	crlf
		mov		eax, N							;moving N(the size) to eax
		mov		edx, offset txt5				;displaying the size of the array given by the user
		call	writeString
		call	writeDec						;writing user entry of array size
		call	crlf
		mov		edx, offset txt6				;displaying the array input given from the user
		call	writeString						
		mov		ecx, eax						;moving eax to ecx
		mov		esi, 0							;index register
		print1:
			mov		eax, Array[4*esi]			;moving 4 bytes at address esi into eax
			call	writeDec					;writing the decimal user inputed
			mov		edx, offset space			;displaying a space between each integer
			call	writeString
			inc		esi
			loop	print1						;repeatedly doing so for each integer
		call	crlf
		mov		edx, offset txt7				;displaying initial statement that stack is currently empty-there is no stack
		call	writeString
		call	crlf
		call	crlf
		jmp		do1								;jump back to displaying user menu

	cycle1:										;for option 1, we are filling in a stack from the user given array
		mov		edx, offset txt8				;displaying what the array inputted is
		call	writeString
		mov		ecx, N							
		mov		esi, 0
		print2:
			mov		eax, Array[4*esi]			;moving 4 bytes at address esi into eax
			call	writeDec					;displaying the integer entered by the user
			mov		edx, offset space			;displaying a space between each integer
			call	writeString
			inc		esi							;move to the next integer
			loop	print2						;looping to repeatedly print for each input
		mov		edx, offset txt9				;displaying "before ArraytoStack"
		call	writeString
		call	crlf
		mov		ecx, N							
		mov		esi, 0
		push1:
			push	Array[4*esi]				;pushing each integer onto the stack
			mov		Array[4*esi], 0			;moving 0 to each array index that was pushed into the stack 
			inc		esi							;move to the next integer
			loop	push1						;loop for size N
		
		mov		edx, offset txt10				;displaying "Stack is" 
		call	writeString
		mov		ecx, N
		mov		esi, esp						;moving the pointer to stack register esp to esi
		peak1:
			mov		eax, [esi]					;moving the 4 bytes in memory at the adress contained in esi into eax
			call	writeDec					;displaying each integer in the stack
			mov		edx, offset space			;displaying a space
			call	writeString
			add		esi, 4						;point to next integer
			dec		ecx							;decrement ecx
			cmp		ecx, 0						;compare ecx to 0
			ja		peak1						;jump back to peak1 if ecx is above 0
		mov		edx, offset txt11				;displaying "after ArraytoStack"
		call	writeString						
		call	crlf
		mov		edx, offset txt8				;displaying "Array is " after ArraytoStack
		call	writeString
		mov		ecx, N							;set inner loop count for N
		mov		esi, 0
		print3:
			mov		eax, Array[4*esi]			;moving 4 bytes at array address esi into eax
			call	writeDec					;writing each integer
			mov		edx, offset space			;displaying a space
			call	writeString
			inc		esi							;move to the next integer
			loop	print3						;loop through for each integer
		mov		edx, offset txt11				;displaying "after ArrayToStack"
		call	writeString
		call	crlf
		mov		edx, offset txt12				;displaying "Stack not empty" - we just filled it
		call	writeString
		call	crlf
		call	crlf
		jmp		do1								;jump back to the menu prompt for the user

	cycle2:										;for option 2, we are filling in an array from the stack
		cmp		esp, edi						;comparing what's stored in the Stack to register edi
		jne		continue2						;jump to continue2 if not empty stack
		mov		edx, offset emtStack				;displaying an error message - the stack is empty
		call	writeString
		call	crlf
		call	crlf
		jmp		do1								;jump back to the menu prompt for the user
		
		continue2:
			mov		edx, offset txt10			;displaying "Stack is " 
			call	writeString
			mov		ecx, N						
			mov		esi, 0						
			pop1:
				mov		ebx, N					;move N to ebx
				sub		ebx, esi				;subtracts what's stored in ebx by the index register esi
				sub		ebx, 1					;subtracts ebx by 1
				pop		eax						;pop off each stack element to eax
				mov		Array[4*ebx], eax		;move eax to the 4 bytes of data at array address 4*ebx (starting from the last element)
				call	writeDec				;write the integer
				mov		edx, offset Msg18		;displaying space between each integer
				call	writeString
				inc		esi						;increment the index
				loop	pop1					;loop until all the stacks elements are popped off
			mov		edx, offset txt13			;displaying "before StackArray"
			call	writeString
			call	crlf
			mov		edx, offset txt8			;displaying 'Array is"
			call	writeString					 
			mov		ecx, N
			mov		esi, 0
			print4:
				mov		eax, Array[4*esi]		;moving 4 bytes at address esi into eax
				call	writeDec				
				mov		edx, offset space		;displaying a space
				call	writeString
				inc		esi						;incrementing the index
				loop	print4					;loop for each index
			mov		edx, offset txt14			;displaying "after StackToArray"
			call	writeString
			call	crlf
			mov		edx, offset Msg6			;displaying "Stack is empty"
			call	writeString
			call	crlf
			call	crlf
			jmp		do1
	
	cycle3:										;for option 3, we are reversing the array using the stack 
		mov		edx, offset txt8				;displaying "Array is"
		call	writeString
		mov		ecx, N
		mov		esi, 0
		print5:
			mov		eax, Array[4*esi]			;moving 4 bytes at array address esi into eax
			call	writeDec
			mov		edx, offset space			;displaying a space
			call	writeString
			push		eax						;push the element into eax
			mov		Array[4*esi], 0			;moving 0 to 4 bytes at array address esi 
			inc		esi							;incrementing the index
			loop	print5						;loop for each element
		mov		edx, offset txt15				;displaying before StackReverse"
		call	writeString
		call	crlf
		mov		edx, offset txt12				;displaying "Stack not empty"
		call	writeString
		call	crlf
		mov		edx, offset txt8				;displaying "Array is "
		call	writeString
		mov		ecx, N
		mov		esi, 0
		pop2:
			pop		eax							;pop off the stack element to eax
			mov		Array[4*esi], eax			;moving 4 bytes at array address esi into eax
			call	writeDec					;writing that integer
			mov		edx, offset Msg18			;displaying a space
			call	writeString
			inc		esi							;incrementing the index
			loop	pop2						;loop until all the elements from the stack are popped off
		mov		edx, offset txt16				;displaying "after StackReverse"
		call	writeString
		call	crlf
		mov		edx, offset Msg6				;displaying "Stack is empty"
		call	writeString
		call	crlf
		call	crlf
		jmp		do1
		
		
	stop:										;if user enters -1, the program will end
		mov		edx, offset byeMsg				;displaying the exit message
		call	writeString
		call	crlf


	call DumpRegs ; displays registers in console

	exit


main ENDP
END main