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
MOV eax, 4h ; Move or assign EAX 4h
ADD eax, 5h ; add 5h to EAX
SUB eax, 2h ; subtract 2h from EAX
call WriteDec ; displays value of EAX, solution.
call DumpRegs ; displays registers in console
exit
main ENDP