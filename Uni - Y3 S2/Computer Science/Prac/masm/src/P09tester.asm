;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - P09

;createdll
.386
.MODEL FLAT ; Flat memory model
.STACK 4096 ; 4096 bytes

INCLUDE io.inc ; Directive to use the IO Library

; Exit function
ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

; The data section stores all global variables
.DATA

; The code section may contain multiple tags such as _start, which is the entry
; point of this assembly program
.CODE

min PROC NEAR32
	; Entry code
	PUSH	ebp
	MOV		ebp, esp
	PUSH	ebx
	PUSH	ecx
	PUSH	edx
	PUSHFD

	MOV		eax, 1

	; Exit code
	POPFD
	POP		edx
	POP		ecx
	POP		ebx
	MOV		esp, ebp
	POP		ebp
	RET		0					; params are X bytes
min ENDP

max PROC NEAR32
	; Entry code
	PUSH	ebp
	MOV		ebp, esp
	PUSH	ebx
	PUSH	ecx
	PUSH	edx
	PUSHFD

	MOV		eax, 1

	; Exit code
	POPFD
	POP		edx
	POP		ecx
	POP		ebx
	MOV		esp, ebp
	POP		ebp
	RET		0					; params are X bytes
max ENDP

normalise PROC NEAR32
	; Entry code
	PUSH	ebp
	MOV		ebp, esp
	PUSH	ebx
	PUSH	ecx
	PUSH	edx
	PUSHFD

	; Exit code
	POPFD
	POP		edx
	POP		ecx
	POP		ebx
	MOV		esp, ebp
	POP		ebp
	RET		0					; params are X bytes
normalise ENDP

_start:
	; Most of your initial code will be under the _start tag.
	; The _start tag is just a memory address referenced by the Public indicator
	; highlighting which functions are available to calling functions.
	; _start gets called by the operating system to start this process.
 

	; We call the Operating System ExitProcess system call to close the process.
	INVOKE ExitProcess, 0
Public _start
END
