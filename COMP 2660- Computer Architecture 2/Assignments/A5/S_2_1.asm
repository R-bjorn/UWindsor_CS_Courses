TITLE

; Name:	Ravi Trivedi
; Date:	07-03-2021
; ID:	105197609	
; Description:	displays 20 random strings, each consisting of 10 capital letters A..Z.

INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib

.data
	strin_g BYTE 10 DUP(0), 0
	; data declarations go here

.code
main PROC

	; program syntax here	

	mov ecx, 20		; for outer loop 

L1:push ecx		; loops for 20 times 
mov ecx,10		; for inner loop 
mov esi, OFFSET strin_g  ; to keep track of string index

	L2: mov eax,26 ;

	call RandomRange ;generate random int and mov it to eax
	add eax, 'A'		; convert the int genrate above to cap letters between A-Z
	mov [esi],al		; to store the generated char
	inc esi;			; next char pos 
	loop L2 ;

mov edx, OFFSET strin_g 
call WriteString 
call Crlf
pop ecx
loop L1


	exit

main ENDP
END main