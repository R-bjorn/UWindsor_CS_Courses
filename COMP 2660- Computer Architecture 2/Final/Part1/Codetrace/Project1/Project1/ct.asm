TITLE

; FINAL EXAM CODE TRACING QUESTIONS

INCLUDE Irvine32.inc
INCLUDELIB Irvine32.lib

.data

	; data declarations go here
	Msg0	byte	"What do you want to do now? > ", 0
	Msg1	byte	"What is the size N of Array? (1 to 20) > ", 0
	Msg2	byte	"What are the ", 0
	Msg3	byte	" values in Array? > ", 0
	Msg4	byte	"Size of Array is N = ", 0
	Msg5	byte	"Array = ", 0
	Msg6	byte	"Stack is empty", 0
	Msg7	byte	"Array is ", 0
	Msg8	byte	"before ArrayToStack", 0
	Msg9	byte	"Stack is ", 0
	Msg10	byte	"after ArrayToStack", 0
	Msg11	byte	"Stack not empty", 0
	Msg12	byte	"before StackToArray", 0
	Msg13	byte	"after StackToArray", 0
	Msg14	byte	"before StackReverse", 0
	Msg15	byte	"after StackReverse", 0
	Msg16	byte	"Error - Stack is empty: Cannot perform StackToArray", 0
	Msg17	byte	"Exiting, thank you for doing assignment 7 :-)", 0
	Msg18	byte	"  ", 0
	Msg19	byte	"0 create an array || 1 move array to stack || 2 move stack to array || 3 reverse array || -1 exit", 0 
	Msg20	byte	"Invalid entry, please try again!", 0
	Array	dword	20 dup(?)
	N		dword	0
	MAX		dword	20



.code
main PROC

	; program syntax here
mov		edi, esp								
Section1:											
	mov		edx, offset Msg19					
	call	writeString
	call	crlf
	call	crlf
	MOV		edx, offset Msg0					
	call	writeString
	call	readInt								
	call	crlf

Section2:																		
	cmp		eax, 0
	je		SubSection21								
	cmp		eax, 1
	je		SubSection22								
	cmp		eax, 2
	je		SubSection23								
	cmp		eax, 3
	je		SubSection24								
	cmp		eax, -1
	je		SubSection25								
	mov		edx, offset Msg20
	call	writeString
	call	crlf
	call	crlf
	jmp		Section1									

	SubSection21:										
		SubSection211:
			mov		edx, offset Msg1			
			call	writeString
			call	readDec						
			call	crlf
		SubSection212:
			cmp		eax, 0						
			jbe		SubSection213						
			cmp		eax, 20						
			ja		SubSection213						
			jmp		SubSection214					
		SubSection213:
			mov		edx, offset Msg20			
			call	writeString
			call	crlf
			call	crlf
			jmp		SubSection211							
		SubSection214:
			mov		ecx, eax					
			mov		N, eax						
			mov		esi, 0						
			mov		edx, offset Msg2			
			call	writeString
			call	writeDec					
			mov		edx, offset Msg3			
			call	writeString
		SubSection215:
			call	readDec
			mov		Array[4*esi], eax			
			inc		esi							
			loop	SubSection215						
		call	crlf
		mov		eax, N							
		mov		edx, offset Msg4				
		call	writeString
		call	writeDec						
		call	crlf
		mov		edx, offset Msg5				
		call	writeString						
		mov		ecx, eax						
		mov		esi, 0							
		SubSection216:
			mov		eax, Array[4*esi]			
			call	writeDec					
			mov		edx, offset Msg18			
			call	writeString
			inc		esi
			loop	SubSection216						
		call	crlf
		mov		edx, offset Msg6				
		call	writeString
		call	crlf
		call	crlf
		jmp		Section1								

	SubSection22:										
		mov		edx, offset Msg7				
		call	writeString
		mov		ecx, N							
		mov		esi, 0
		SubSection221:
			mov		eax, Array[4*esi]			
			call	writeDec					
			mov		edx, offset Msg18			
			call	writeString
			inc		esi							
			loop	SubSection221						
		mov		edx, offset Msg8				
		call	writeString
		call	crlf
		mov		ecx, N							
		mov		esi, 0
		SubSection222:
			push	Array[4*esi]				
			mov		Array[4*esi], 0			
			inc		esi							
			loop	SubSection222						
		
		mov		edx, offset Msg9				
		call	writeString
		mov		ecx, N
		mov		esi, esp						
		SubSection223:
			mov		eax, [esi]					
			call	writeDec					
			mov		edx, offset Msg18			
			call	writeString
			add		esi, 4						
			dec		ecx							
			cmp		ecx, 0						
			ja		SubSection223						
		mov		edx, offset Msg10				
		call	writeString						
		call	crlf
		mov		edx, offset Msg7				
		call	writeString
		mov		ecx, N							
		mov		esi, 0
		SubSection224:
			mov		eax, Array[4*esi]			
			call	writeDec					
			mov		edx, offset Msg18			
			call	writeString
			inc		esi							
			loop	SubSection224						
		mov		edx, offset Msg10				
		call	writeString
		call	crlf
		mov		edx, offset Msg11				
		call	writeString
		call	crlf
		call	crlf
		jmp		Section1								

	SubSection23:										
		cmp		esp, edi						
		jne		SubSection231						
		mov		edx, offset Msg16				
		call	writeString
		call	crlf
		call	crlf
		jmp		Section1								
		
		SubSection231:
			mov		edx, offset Msg9			
			call	writeString
			mov		ecx, N						
			mov		esi, 0						
			SubSection2311:
				mov		ebx, N					
				sub		ebx, esi				
				sub		ebx, 1					
				pop		eax						
				mov		Array[4*ebx], eax		
				call	writeDec				
				mov		edx, offset Msg18		
				call	writeString
				inc		esi						
				loop	SubSection2311					
			mov		edx, offset Msg12			
			call	writeString
			call	crlf
			mov		edx, offset Msg7			
			call	writeString					 
			mov		ecx, N
			mov		esi, 0
			SubSection2312:
				mov		eax, Array[4*esi]		
				call	writeDec				
				mov		edx, offset Msg18		
				call	writeString
				inc		esi						
				loop	SubSection2312					
			mov		edx, offset Msg13			
			call	writeString
			call	crlf
			mov		edx, offset Msg6			
			call	writeString
			call	crlf
			call	crlf
			jmp		Section1
	
	SubSection24:										
		mov		edx, offset Msg7				
		call	writeString
		mov		ecx, N
		mov		esi, 0
		SubSection241:
			mov		eax, Array[4*esi]			
			call	writeDec
			mov		edx, offset Msg18			
			call	writeString
			push		eax						
			mov		Array[4*esi], 0			
			inc		esi							
			loop	SubSection241						
		mov		edx, offset Msg14				
		call	writeString
		call	crlf
		mov		edx, offset Msg11				
		call	writeString
		call	crlf
		mov		edx, offset Msg7				
		call	writeString
		mov		ecx, N
		mov		esi, 0
		SubSection242:
			pop		eax							
			mov		Array[4*esi], eax			
			call	writeDec					
			mov		edx, offset Msg18			
			call	writeString
			inc		esi							
			loop	SubSection242						
		mov		edx, offset Msg15				
		call	writeString
		call	crlf
		mov		edx, offset Msg6				
		call	writeString
		call	crlf
		call	crlf
		jmp		Section1
		
		
	SubSection25:										
		mov		edx, offset Msg17				
		call	writeString
		call	crlf


	call DumpRegs ; displays registers in console

	exit

main ENDP
END main