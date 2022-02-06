TITLE

; Name:		Ravi Trivedi
; Date:		05/02/2021
; ID:		105197609
; Description:		Write instructions that use direct-offset addressing to move the four values in Uarray to the EAX, EBX, ECX, and EDX registers.

INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib

.data
	; data declarations go here
	Uarray WORD 1000h,2000h,3000h,4000h
	Sarray SWORD -1,-2,-3,-4

.code
main PROC

	; program syntax here
	
	;Moving all data in Uarray to different registers 
	movzx eax, Uarray		; Moving first  data of Uarray to EAX
	movzx ebx, Uarray+2		; Moving Secong data of Uarray to EBX
	movzx ecx, Uarray+4		; Moving Third  data of Uarray to ECX
	movzx edx, Uarray+6		; Moving Forth  data of Uarray to EDX

	call DumpRegs ; Displays registers in console

	;Moving all data in Sarray to different registers 
	movsx eax, Sarray  		; Moving first  data of Sarray to EAX
	movsx ebx, Sarray+2		; Moving Second data of Sarray  to EBX
	movsx ecx, Sarray+4		; Moving Third  data of Sarray  to ECX
	movsx edx, Sarray+6		; Moving Forth  data of Sarray  to EDX

	call DumpRegs ; displays registers in console

	exit

main ENDP
END main