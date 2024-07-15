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

msg1 BYTE "Size: ",0
msg2 BYTE " ",10,0
array DWORD 4 DUP (2,3,4,5)

.CODE

_calc PROC NEAR32

PUSH ebp
MOV ebp, esp
SUB esp, 8
PUSH ebx
PUSH ecx
PUSH edx
PUSHFD
;;;code

MOV ebx, [ebp+12];array pointer
MOV ecx, [ebp+8]; size
MOV eax, 0
MOV [ebp-8], eax

beginLoop:
CMP ecx, 0
JZ endLoop

MOV eax, [ebx]
MOV [ebp-4], eax

FINIT

FILD DWORD PTR [ebp-4]
MOV eax, 2
MOV [ebp-4], eax
FILD DWORD PTR [ebp-4]
FMUL

FST REAL4 PTR [ebp-4]

FLD REAL4 PTR [ebp-4]
FLD REAL4 PTR [ebp-8]

FADD

FST REAL4 PTR [ebp-8]



ADD ebx, 4
DEC ecx
JMP beginLoop
endLoop:

MOV eax, REAL4 PTR [ebp-8]





;;;;code
POPFD
POP edx
POP ecx
POP ebx

MOV esp, ebp
POP ebp

RET 8
_calc ENDP




_start:
	
	

	INVOKE OutputStr, ADDR msg1
	INVOKE InputInt
	MOV ebx, eax
	LEA ecx, array

	PUSH ecx;+12
	PUSH ebx;+8

	CALL _calc 
	INVOKE OutputFloat, eax

	INVOKE ExitProcess, 0
Public _start
END
