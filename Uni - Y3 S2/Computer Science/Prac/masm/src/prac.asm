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
marks DWORD 4 DUP (90,74,40,42)
; The code section may contain multiple tags such as _start, which is the entry
; point of this assembly program

.CODE

_funcS PROC NEAR32

	PUSH ebp
	MOV ebp, esp
	SUB esp, 8

	PUSH ebx
	PUSH ecx
	PUSH edx
	PUSHFD

	LEA ebx, marks
	MOV ecx, [ebp+16]

	startLoopx:

	INVOKE InputInt

	IMUL eax, [ebx]

	
	MOV [ebp-4], ecx
	MOV ecx, 100
	cdq
	DIV [ebx]
	MOV ecx, [ebp-4]
	

	MOV [ebx], eax
	ADD [ebp+8], eax

	ADD ebx, 4
	LOOP startLoopx


	MOV eax, [ebp+8]





	POPFD
	POP edx
	POP ecx
	POP ebx

	MOV esp, ebp
	POP ebp

	RET


_funcS ENDP


_start:

	PUSH ebp
	MOV ebp, esp
	SUB esp, 12
	PUSH 4
	PUSH 0
	PUSH 0

	CALL _funcS

	INVOKE OutputInt, eax
	MOV esp, ebp
	POP ebp
	
	
	INVOKE ExitProcess, 0
Public _start
END
