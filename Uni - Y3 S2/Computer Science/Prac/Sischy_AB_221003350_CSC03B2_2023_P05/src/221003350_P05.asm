;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - P0X
.386
.MODEL FLAT ; Flat memory model
.STACK 4096 ; 4096 bytes

INCLUDE io.inc ; Directive to use the IO Library


; Exit function
ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

; The data section stores all global variables
.DATA

;;array
array DWORD 10 DUP (?)

;display messages
msgDisplay BYTE "Displaying Array:",10,0
msgInput BYTE "Entering Values Into Array:",10,0
msgInputValue BYTE "Please Enter Array Value: ",0
msgAverage BYTE "Now Calulating Average: ",10,0
msgDivide BYTE "Now Dividing Array:",10,0

msgClamp BYTE "Now Calculating Clamp:",10,0
msgInputClamp BYTE "Enter Clamp Value:",0

msgExit BYTE "Enter 0 to exit, 1 to continue: ",0

;display format messages
msgSpace BYTE ", ",0
msgLine BYTE " ",10,0


.CODE

_displayFunc PROC NEAR32 ;display array function 
	;BLOCK ENTRY
	;CREATE stack to add eax-edx to
	PUSH ebp
	MOV ebp,esp
	PUSH edx
	PUSH ecx
	PUSH ebx
	PUSH eax
	PUSHFD ; save flags register

	;BLOCK function body

	;while loop to display 10 items in array
	INVOKE OutputStr, ADDR msgLine ;line format
	INVOKE OutputStr, ADDR msgDisplay ;display message
	MOV ecx, 10 ; set ecx to 10
	LEA ebx, array ;set array memory address

	whileDisplayCondition:
	CMP ecx, 0 ;check if ecx is 0
	JZ whileDisplayEnd
	whileDisplayBody:

	INVOKE OutputInt, [ebx]

	ADD ebx, 4 ; go to next array slot
	DEC ecx ;ecx -1

	INVOKE OutputStr, ADDR msgSpace ;add format space
	JMP whileDisplayCondition ; jump to while condition check
	whileDisplayEnd:

	;BLOCK exit
	;RESTORE eax-edx flags to before values
	POPFD
	POP eax
	POP ebx
	POP ecx
	POP edx
	MOV esp, ebp ;destroy stack
	POP ebp
	RET 	;return call function

_displayFunc ENDP


_inputFunc PROC NEAR32 ;display array function 
	;BLOCK ENTRY
	;CREATE stack to add eax-edx to
	PUSH ebp
	MOV ebp,esp
	PUSH edx
	PUSH ecx
	PUSH ebx
	PUSH eax
	PUSHFD ; save flags register

	;BLOCK function body

	;while loop to display 10 items in array
	INVOKE OutputStr, ADDR msgLine ;line format
	INVOKE OutputStr, ADDR msgInput ;display message
	MOV ecx, 10 ; set ecx to 10
	LEA ebx, array ;set array memory address

	whileInputCondition:
	CMP ecx, 0 ;check if ecx is 0
	;INVOKE OutputStr, ADDR msgSpace ;add format space
	JZ whileInputEnd
	whileInputBody:

	INVOKE OutputStr, ADDR msgInputValue
	INVOKE InputInt
	MOV [ebx], eax

	ADD ebx, 4 ; go to next array slot
	DEC ecx ;ecx -1

	JMP whileInputCondition ; jump to while condition check
	whileInputEnd:

	;BLOCK exit
	;RESTORE eax-edx flags to before values
	POPFD
	POP eax
	POP ebx
	POP ecx
	POP edx
	MOV esp, ebp ;destroy stack
	POP ebp
	RET 	;return call function

_inputFunc ENDP

_averageFunc PROC NEAR32 ;display array function 
	;BLOCK ENTRY
	;CREATE stack to add eax-edx to
	PUSH ebp
	MOV ebp,esp
	PUSH edx
	PUSH ecx
	PUSH ebx
	PUSHFD ; save flags register

	;BLOCK function body

	;while loop to display 10 items in array
	INVOKE OutputStr, ADDR msgLine ;line format
	INVOKE OutputStr, ADDR msgLine ;line format
	INVOKE OutputStr, ADDR msgAverage ;display message
	MOV ecx, 10 ; set ecx to 10
	LEA ebx, array ;set array memory address
	MOV eax, 0
	whileAverageCondition:
	CMP ecx, 0 ;check if ecx is 0
	;INVOKE OutputStr, ADDR msgSpace ;add format space
	JZ whileAverageEnd
	whileAverageBody:

	ADD eax, [ebx] ; add array to eax

	ADD ebx, 4 ; go to next array slot
	DEC ecx ;ecx -1

	JMP whileAverageCondition ; jump to while condition check
	whileAverageEnd:

	cdq ; prepare divisor
	MOV ebx, 10 
	IDIV ebx ; divde sum by 10

	;BLOCK exit
	;RESTORE eax-edx flags to before values
	POPFD
	POP ebx
	POP ecx
	POP edx
	MOV esp, ebp ;destroy stack
	POP ebp
	RET 	;return call function

_averageFunc ENDP

_divideFunc PROC NEAR32 ;display array function 
	;BLOCK ENTRY
	;CREATE stack to add eax-edx to
	PUSH ebp
	MOV ebp,esp
	PUSH edx
	PUSH ecx
	PUSH ebx
	PUSH eax
	PUSHFD ; save flags register

	;BLOCK function body

	;while loop to display 10 items in array
	INVOKE OutputStr, ADDR msgLine ;line format
	INVOKE OutputStr, ADDR msgDivide ;display message
	MOV ecx, 10 ; set ecx to 10
	LEA ebx, array ;set array memory address

	whileDivideCondition:
	CMP ecx, 0 ;check if ecx is 0
	;INVOKE OutputStr, ADDR msgSpace ;add format space
	JZ whileDivideEnd
	whileDivideBody:

	MOV [ebp+12], ecx
	MOV ecx, [ebp+8]

	MOV eax, [ebx] ;move array value to eax


	cdq ;prepare for division
	IDIV ecx

	MOV ecx, [ebp+12]

	MOV [ebx], eax ; move divison result to array

	ADD ebx, 4 ; go to next array slot
	DEC ecx ;ecx -1

	JMP whileDivideCondition ; jump to while condition check
	whileDivideEnd:

	;BLOCK exit
	;RESTORE eax-edx flags to before values
	POPFD
	POP eax
	POP ebx
	POP ecx
	POP edx
	MOV esp, ebp ;destroy stack
	POP ebp
	RET 	;return call function

_divideFunc ENDP


_clampFunc PROC NEAR32 ;display array function 
	;BLOCK ENTRY
	;CREATE stack to add eax-edx to
	PUSH ebp
	MOV ebp,esp
	PUSH edx
	PUSH ecx
	PUSH ebx
	PUSH eax
	PUSHFD ; save flags register

	;BLOCK function body

	;while loop to display 10 items in array
	INVOKE OutputStr, ADDR msgLine ;line format
	INVOKE OutputStr, ADDR msgLine ;line format
	INVOKE OutputStr, ADDR msgClamp ;display message
	INVOKE OutputStr, ADDR msgInputClamp ;display message
	INVOKE InputInt

	MOV ecx, 10 ; set ecx to 10
	LEA ebx, array ;set array memory address

	whileClampCondition:
	CMP ecx, 0 ;check if ecx is 0
	;INVOKE OutputStr, ADDR msgSpace ;add format space
	JZ whileClampEnd
	whileClampBody:

	CMP [ebx], eax ; if eax >= ebp
	JGE setClamp
	JMP setClampEnd; else skip
	setClamp:
	MOV [ebx], eax
	setClampEnd:

	ADD ebx, 4 ; go to next array slot
	DEC ecx ;ecx -1

	JMP whileClampCondition ; jump to while condition check
	whileClampEnd:

	;BLOCK exit
	;RESTORE eax-edx flags to before values
	POPFD
	POP eax
	POP ebx
	POP ecx
	POP edx
	MOV esp, ebp ;destroy stack
	POP ebp
	RET 	;return call function

_clampFunc ENDP

_start:

	MOV ecx, 1
	whileCondition:
	CMP ecx, 0
	JZ whileEnd
	whileBody:
	CALL _inputFunc
	CALL _displayFunc
	CALL _averageFunc ;returns average in eax

	PUSH ebp ;this pushes average, and 1 spare value for division on stack
	MOV ebp, esp
	SUB esp, 8
	PUSH eax
	CALL _divideFunc
	CALL _displayFunc
	CALL _clampFunc
	CALL _displayFunc

	INVOKE OutputStr, ADDR msgLine
	INVOKE OutputStr, ADDR msgExit ;ask user for if they want to exit program
	INVOKE InputInt
	MOV ecx, eax

	JMP whileCondition
	whileEnd:
	; We call the Operating System ExitProcess system call to close the process.
	INVOKE ExitProcess, 0
Public _start
END
