TITLE

; Name:	Ravi Trivedi 
; Date:	2021-02-25
; ID:	TRIVE11W
; Description:	Program that uses addition and subtraction to set and clear the Carry flag. 

INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib

.data

	; data declarations go here
	
.code
main PROC

	; program syntax here

	mov al, 0FFh  ; setting al to 255
	add al,1	; adding 1 to al which will roll al over to zero 
	call DumpRegs ; displays registers in console
	add al,1	
	call DumpRegs

	mov al,0
	sub al,1	; Substracting larger number from smaller num 
	call DumpRegs
	sub al,1		; al = 255, CF = 0 unchanged 
	call DumpRegs

	exit

main ENDP
END main