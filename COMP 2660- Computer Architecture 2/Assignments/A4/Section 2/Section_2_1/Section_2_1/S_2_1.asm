TITLE

; Name:		Ravi Trivedi
; Date:		2021-02-28
; ID:		105197609
; Description:	Calculates first 7 Fibonacci numbers in a loop

INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib

.data

	; data declarations go here

.code
main PROC

	; program syntax here
	mov eax, 0 ; 1st element of fibonacci 
	mov ebx, 1 ; 2nd element of fibonacci 
	mov ecx, 7 ; loop 7 times  

	L1:
		mov edx, eax
		add edx, ebx	; Fib(n) = Fib(n-1) + Fib(n-2)
		
		; updating eax and ebx for next loop
		mov eax, ebx
		mov ebx, edx 
		call DumpRegs ; displays registers in console
	loop L1

	exit

main ENDP
END main