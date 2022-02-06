TITLE
; Name:
; Date:
; ID:
; Description:
INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib
.data
	; data declarations go here
	greeting1 BYTE "Hello World",0
	greeting2 BYTE 'This is Assignment 2',0
	greeting3 BYTE 'A', '2'
	greeting4 BYTE "Section ",0
	greeting5 BYTE '2'
.code
main_b PROC
	mov edx,offset greeting1
	call WriteString
	call WaitMsg
	mov edx,offset greeting2
	call WriteString
	call WaitMsg
	mov edx,offset greeting3
	call WriteString
	call WaitMsg
	mov edx,offset greeting4
	call WriteString
	call WaitMsg
	INVOKE ExitProcess,0
	call DumpRegs ; displays registers in console
	exit
main_b ENDP
END main_b