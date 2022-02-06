TITLE

; Name:		Ravi Trivedi
; Date:		05/02/2021
; ID:		105197609
; Description:		Solve the following Arithmetic : EAX = -val2 + 7 - val3 + val1

INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib

.data
	; data declarations go here
	val1 SDWORD 8
	val2 SDWORD -15
	val3 SDWORD 20

.code
main PROC

	; program syntax here

	MOV eax,val2	; EAX = fffffff1h	; Moving val2 to eax
	
	neg eax			; EAX = 0000000fh	; Negating eax
	
	ADD eax,7		; EAX = 00000016h	; Adding 7 to eax
	
	sub eax,val3	; EAX = 00000002h	; Substraction val3 from eax
	
	ADD eax,val1	; EAX = 0000000Ah	; Adding val1 to eax

	call DumpRegs ; displays registers in console

	exit

main ENDP
END main