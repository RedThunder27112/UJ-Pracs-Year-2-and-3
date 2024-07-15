;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - P06
.386
.MODEL FLAT ; Flat memory model
.STACK 4096 ; 4096 bytes

INCLUDE io.inc ; Directive to use the IO Library

; Exit function
ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

; The data section stores all global variables
.DATA

msgOutput BYTE "Final Answer = ",0
msgInput BYTE "Please Enter an Integer: ",0
msgExit BYTE "Enter 0 to exit, and 1 to continue: ",0
msgSpace BYTE " ",10,0


.CODE

_falkor PROC NEAR32 ;f(n)

;push variables on stack to pop after function
PUSH ebp
MOV ebp, esp
PUSH edx
PUSH ecx
PUSH ebx
PUSHFD

MOV ecx, [ebp+8]

CMP ecx, 3
JG GreaterStart ;if n > 3

MOV ebx, 0
CMP ebx, ecx
JGE SmallerStart ;if n <= 0
JMP MiddleStart ; else 0 < n <= 3


SmallerStart:;return 1
MOV eax, 1

JMP RecursionEnd;jump to en

MiddleStart:;return n << 2

MOV eax, [ebp+8]
SHL eax, 2


JMP RecursionEnd ;jump to end
GreaterStart:
MOV ebx, [ebp+8];store n in ecx and ebx
MOV ecx, [ebp+8]

SUB ebx, 2 ; n-2
PUSH ebx
CALL _falkor ; falkor(n-2)
MOV edx, eax ; store eax in edx

SHR ecx, 2 ; n >> 2
PUSH ecx
CALL _falkor ; falkor(n >> 2)

ADD eax, edx
JMP RecursionEnd ;jump to end

RecursionEnd:

;pop variables from stack to reset varaibe values
POPFD
POP ebx
POP ecx
POP edx
MOV esp, ebp
POP ebp
RET 4


_falkor ENDP

_start:
	PUSH ebp;stack for function paramater
	MOV ebp, esp
	SUB esp, 4
	BeginLoop:
	LEA eax, msgSpace ;space message
	PUSH eax
	CALL OutputStr
	
	LEA eax, msgInput ;get user input of n
	PUSH eax
	CALL OutputStr
	 
	CALL InputInt

	;add eax/n variable as paramator to falkor

	PUSH eax
	CALL _falkor

	MOV ebx, eax

	LEA eax, msgSpace ;space message
	PUSH eax
	CALL OutputStr

	LEA eax, msgOutput ;space output
	PUSH eax
	CALL OutputStr
	
	PUSH ebx ; outputing final answer
	CALL OutputInt

	LEA eax, msgSpace ;space message
	PUSH eax
	CALL OutputStr




	LEA eax, msgSpace ;space message
	PUSH eax
	CALL OutputStr

	LEA eax, msgExit ;ask user if they want to exit
	PUSH eax
	CALL OutputStr
	 
	CALL InputInt
	MOV ecx, eax
	CMP ecx, 0
	JNZ BeginLoop ; if ecx != 0, loop back

	MOV esp, ebp ; pop array
	POP ebp

	PUSH 0	; ExitProcess, 0
	CALL ExitProcess

Public _start
END
