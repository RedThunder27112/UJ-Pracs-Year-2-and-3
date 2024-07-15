;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - P02
.386
.MODEL FLAT ; Flat memory model
.STACK 4096 ; 4096 bytes

INCLUDE io.inc ; Directive to use the IO Library

; Exit function
ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

; The data section stores all global variables
.DATA

;String input messages
strInputA0 BYTE "Please enter A0:",0
strInputA1 BYTE "Please enter A1:",0

strInputW0 BYTE "Please enter W0:",0
strInputW1 BYTE "Please enter W1:",0

strInputExit BYTE "Please enter 0 to exit, or 1 to continue:",0

strOutputMax BYTE "The max is: ",0
;A and W input variablesS
A0 DWORD ?
A1 DWORD ?
W0 DWORD ?
W1 DWORD ?

;OUTPUT
maxFeature DWORD ?



.CODE
_start:
	
	;get variables while exiting loop
	MOV ecx, 1
whileLoopCondition:
	;if exit has been specified, exit loop
	CMP ecx, 0
	JZ whileLoopEnd

whileLoopBody:

	;Msg asking while A0 input from user
	INVOKE OutputStr, ADDR strInputA0
	INVOKE InputInt
	MOV A0, eax

	;Msg asking while A1 input from user
	INVOKE OutputStr, ADDR strInputA1
	INVOKE InputInt
	MOV A1, eax

	;Msg asking while W0 input from user
	INVOKE OutputStr, ADDR strInputW0
	INVOKE InputInt
	MOV W0, eax

	;Msg asking while W1 input from user
	INVOKE OutputStr, ADDR strInputW1
	INVOKE InputInt
	MOV W1, eax

	;Msg asking to exit loop
	INVOKE OutputStr, ADDR strInputExit
	INVOKE InputInt
	MOV ecx, eax


	JMP whileLoopCondition ;jump to start of loop

whileLoopEnd:

	;divide A0 by W0
	MOV eax, A0
	MOV ebx, W0
	CDQ
	IDIV ebx

	;check if remainder is 0
	CMP edx, 0 
	JZ notZeroCheck0

	;if not zero, increment by 1
	INC eax
notZeroCheck0:
	MOV ecx, eax ; store eax in ecx



	;divide A1 by W1
	MOV eax, A1
	MOV ebx, W1
	CDQ
	IDIV ebx

	;check if remainder is 0
	CMP edx, 0 
	JZ notZeroCheck1

	;if not zero, increment by 1
	INC eax
notZeroCheck1:
	MOV edx, eax

	;now compare which is the max
	CMP ecx, edx
	
	JNS sumZeroOrBigger
	;then edx is max
	JMP edxIsBigger

	sumZeroOrBigger:
	JNZ ecxIsBigger;handles if both equal... not needed
	JMP ecxIsBigger;jump to where ecx is bigger


	edxIsBigger:; where edx is bigger
	MOV maxFeature, edx
	JMP goToEnd

	ecxIsBigger: ; where ecx is bigger
	MOV maxFeature, ecx

	goToEnd:
	INVOKE OutputStr, ADDR strOutputMax ;output max
	INVOKE OutputInt, maxFeature


	INVOKE ExitProcess, 0
Public _start
END
