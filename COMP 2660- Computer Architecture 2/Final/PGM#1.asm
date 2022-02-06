TITLE

; Name:		Ravi Trivedi
; Date:		2021-04-16
; ID:		105197609
; Description:	Final Exam, Question 1 : Write a program to calculate C=A+B and print the value of C in binary, decimal and hex

INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib

.data

	; data declarations go here
	Msg0	byte	"Enter the Value of A > ", 0
	Msg1	byte	"Enter the Value of B > ", 0

.code
main PROC

	; program syntax here
	MOV		edx, offset Msg0					
	call	writeString
	call	readInt								
	call	crlf
	mov ebx, eax	; moving A's value to EBX

	MOV		edx, offset Msg1					
	call	writeString
	call	readInt								
	call	crlf
	mov ecx, eax	; moving B's value to ECX

	mov eax, 0		; Initializing EAX to 0 first.
	add eax, ebx	; Adding A's value to EAX
	add eax, ecx	; Adding B's value to EAX

	call WriteBin	; C's value as Binary
	call crlf

	call WriteDec	; C's value as Decimal 
	call crlf	

	call WriteHex	; C's value as Hex
	call crlf

	exit

main ENDP
END main