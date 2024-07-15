;	Update all of this information to reflect your own details and the prac
;	Author:     Dr J du Toit
;	Template document
.386
.MODEL FLAT ; Flat memory model
.STACK 4096 ; 4096 bytes

; Exit function
ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

; The data section stores all global variables
.DATA

R DWORD 224
G DWORD 82
B DWORD 6
Y DWORD ?
; The code section may contain multiple tags such as _start, which is the entry
; point of this assembly program
.CODE
_start:
	MOV eax, R; eax = R
	IMUL eax, 3; eax = R*3
	MOV Y, eax; Y = R*3
	
	MOV eax, G; eax = G
	IMUL eax, 6; eax = G*6
	ADD Y, eax; Y = R*3 + G*6

	MOV eax, B; eax = B*1
	ADD Y, eax; Y = R*3 + G*6 + B*1

	MOV eax, Y; eax = Y

	MOV ebx, 10; ebx = 10
	cdq
	
	idiv ebx;divide eax by 10 so  (R*3 + G*6 + B*1)/10

	MOV Y, eax; move final answer to Y = R*3/10 + G*6/10 + B*1/10

	; We call the Operating System ExitProcess system call to close the process.
	INVOKE ExitProcess, 0
Public _start
END
