TITLE
; Name: Ravi Trivedi
; Date: 01/20/21
; ID: 105197609
; Description: Assignemnt 1, Add 4H + 5H - 2h with a break point between each operation.

INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib

.data
; data declarations go here

.code

main PROC
; program syntax here
MOV eax, 4h ; Moving EAX 4h
call DumpRegs 
ADD eax, 5h ; adding 5h to EAX
call DumpRegs
SUB eax, 2h ; subtracting 2h from EAX
call DumpRegs ; displays registers in console
call WriteDec ; displays value of EAX, solution.
exit
main ENDP
END main
