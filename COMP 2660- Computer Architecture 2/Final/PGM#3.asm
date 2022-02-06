TITLE

; Name:		Ravi Trivedi
; Date:		2021-04-16
; ID:		105197609
; Description:	Final Exam, Question 3 : Write a simple fibonacci program for nth element where n is user input

INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib

.data

	; data declarations go here
	Msg0	byte	"Enter the size offset Fibonacci Series > ", 0
	Msg1	byte	"Fibonacci Sequence is : > ", 0
	space	byte	"  ", 0

.code
main PROC

	; program syntax here
	; Taking input from the user. and store it to EAX
	MOV		edx, offset Msg0					
	call	writeString
	call	readInt								
	call	crlf

	; Moving the data from EAX to ECX
	mov ecx, eax ; loop these many times

	mov eax, 0 ; 1st element of fibonacci 
	mov ebx, 1 ; 2nd element of fibonacci 

	MOV		edx, offset Msg1					
	call	writeString

	mov esi, eax	; temparary moving eax value to esi
	mov eax, ebx	; temparary moving ebx value to eax to print the first number of the sequence
	call writeDec	; write it to output
	MOV		edx, offset space		; writing space					
	call	writeString	
	mov eax, esi			; moving esi back to eax	

	L1:
		mov edx, eax
		add edx, ebx	; Fib(n) = Fib(n-1) + Fib(n-2)
		
		; updating eax and ebx for next loop
		mov eax, ebx
		mov ebx, edx 

		; For printing 
		mov esi, eax	; esi is like temp
		mov eax, edx
		call writeDec	; displays registers in console
		MOV		edx, offset space					
		call	writeString
		mov eax, esi	; 
	loop L1

	call crlf
	call crlf
	exit

main ENDP
END main
