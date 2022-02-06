TITLE

; Name:		Ravi Trivedi
; Date:		2021-01-30
; ID:		105197609
; Description:		A2. Define and initilize each data type

INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib

; Creating Symbolic names for literal Strings
txt1 EQU <"Bonjour ",0>;
txt2 EQU <"Today is a beautiful day!",0>;

.data

	; data declarations go here
	valB BYTE 10h;
	valS SBYTE -10h;
	valW WORD 1010h;
	valSW SWORD -1010h
	valDW DWORD 10101010h;
	valSDW SDWORD -10101010h;
	valQW QWORD 1010101010101010h;
	valTB TBYTE 1010101010101010Ah;
	; Using each symbolic name
	vartxt1 BYTE txt1;
	vartxt2 BYTE txt2;

.code

main PROC

	mov EAX, 0
	; Set register to 0
	mov AL, valB ; Set value to 8bit lower end
	call WriteInt ; Print out register
	call Crlf
	mov EAX, 0
	mov AL, valS
	call WriteInt
	call Crlf
	mov EAX, 0
	mov AX, valW
	call WriteHex
	call Crlf
	mov EAX, 0
	mov AX, valSW
	call WriteInt
	call Crlf
	; Write out symbolic names
	mov EAX, 0 
	mov EDX, OFFSET vartxt1
	call WriteString
	mov EDX, OFFSET vartxt2
	call WriteString

	exit	; exit the procedure

main ENDP
END main