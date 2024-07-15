;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - P01

.386
.MODEL FLAT ; Flat memory model
.STACK 4096 ; 4096 bytes

INCLUDE io.inc ; Directive to use the IO Library

; Exit function
ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

; The data section stores all global variables
.DATA

strAvg BYTE "The average is: ", 0
strDot BYTE ".", 0

strInputY0 BYTE "Please Input Y0: ", 0
strInputY1 BYTE "Please Input Y1: ", 0
strInputY2 BYTE "Please Input Y2: ", 0
strInputY3 BYTE "Please Input Y3: ", 0

strInputK0 BYTE "Please Input K0: ", 0
strInputK1 BYTE "Please Input K1: ", 0
strInputK2 BYTE "Please Input K2: ", 0
strInputK3 BYTE "Please Input K3: ", 0

Y0 DWORD ?
Y1 DWORD ?
Y2 DWORD ?
Y3 DWORD ?

K0 DWORD ?
K1 DWORD ?
K2 DWORD ?
K3 DWORD ?


Average DWORD ?


.CODE
_start:

	;Input and output to get Y values
	INVOKE OutputStr, ADDR strInputY0 ;output msg
	INVOKE InputInt ;get user input
	MOV Y0, eax ; store Y0

	INVOKE OutputStr, ADDR strInputY1 ;output msg
	INVOKE InputInt ;get user input
	MOV Y1, eax ; store Y1

	INVOKE OutputStr, ADDR strInputY2 ;output msg
	INVOKE InputInt ;get user input
	MOV Y2, eax ; store Y2

	INVOKE OutputStr, ADDR strInputY3 ;output msg
	INVOKE InputInt ;get user input
	MOV Y3, eax ; store Y3


	;Input and output to get K values
	INVOKE OutputStr, ADDR strInputK0 ;output msg
	INVOKE InputInt ;get user input
	MOV K0, eax ; store K0

	INVOKE OutputStr, ADDR strInputK1 ;output msg
	INVOKE InputInt ;get user input
	MOV K1, eax ; store K1

	INVOKE OutputStr, ADDR strInputK2 ;output msg
	INVOKE InputInt ;get user input
	MOV K2, eax ; store K2

	INVOKE OutputStr, ADDR strInputK3 ;output msg
	INVOKE InputInt ;get user input
	MOV K3, eax ; store K3


	; block to create Y0 * K0
	MOV eax, Y0
	IMUL eax, K0
	Mov Average, eax

	; block to create Y1 * K1
	MOV eax, Y1
	IMUL eax, K1
	ADD Average, eax

	; block to create Y2 * K2
	MOV eax, Y2
	IMUL eax, K2
	ADD Average, eax

	; block to create Y3 * K3
	MOV eax, Y3
	IMUL eax, K3
	ADD Average, eax

	;block to divide average = everything/4

	MOV eax, Average ;move average to eax
	
	
	MOV ebx, 4; ebx = 4
	cdq ;get ready for division

	idiv ebx ; divide eax by ebx



	;block to output final average
	Mov Average, eax

	INVOKE OutputStr, ADDR strAvg
	INVOKE OutputInt, Average


	;outpud dot
	INVOKE OutputStr, ADDR strDot

	;block to turn remainder into decimal
	MOV eax, edx
	IMUL eax, 100
	MOV ebx, 4
	cdq ;get ready for division
	iDIV ebx

	;output decimal
	INVOKE OutputInt, eax


	INVOKE ExitProcess, 0
Public _start
END
