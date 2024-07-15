;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - P03
.386
.MODEL FLAT ; Flat memory model
.STACK 4096 ; 4096 bytes

INCLUDE io.inc ; Directive to use the IO Library

; Exit function
ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

; The data section stores all global variables
.DATA

;String input messages
strInputArray BYTE "Please enter input for image array: ",0
strInputBias BYTE "Please enter input for bias: ", 0
strInputKernal BYTE "Please enter input for kernal: ", 0


strInputExit BYTE "Please enter 0 to exit, or 1 to continue: ",0

strOutputMax BYTE "The output feature is (Input -> Output): ",0
strArrow BYTE " -> ",0
strSpace BYTE " ",10, 0

strOutputKernal BYTE "The kernal value is: ", 0
strOutputBias BYTE "The bias value is: ", 0
;input variablesS
kernal DWORD ?
bias DWORD ?
temp DWORD ?
imageArray DWORD 4 DUP (?)




.CODE
_start:
	
	;get variables while exiting loop
	MOV ecx, 1
whileLoopCondition: 
	;if exit has been specified, exit loop
	CMP ecx, 0
	JZ whileLoopEnd

whileLoopBody:

	;Msg asking for kernal
	INVOKE OutputStr, ADDR strInputKernal
	INVOKE InputInt
	MOV kernal, eax

	;Msg asking for bias
	INVOKE OutputStr, ADDR strInputBias
	INVOKE InputInt
	MOV bias, eax

	;START FOR GETTING VALUES FOR INPUT ARRAY FROM USER
	;Msg asking user for image array inputs
	MOV ecx, 4 ; loop 4 times

	LEA ebx, imageArray ;load address of imageArray
	JECXZ inputLoopEnd ; if ecx is zero, error/end loop

	inputLoopStart:

	;get user input
	INVOKE OutputStr, ADDR strInputArray
	INVOKE InputInt
	MOV [ebx], eax;move user input to array
	
	ADD ebx, 4 ; as DWORD, size 4
	LOOP inputLoopStart

	inputLoopEnd:
	INVOKE OutputStr, ADDR strSpace

	INVOKE OutputStr, ADDR strOutputBias
	INVOKE OutputInt, bias
	INVOKE OutputStr, ADDR strSpace

	INVOKE OutputStr, ADDR strOutputKernal
	INVOKE OutputInt, kernal
	INVOKE OutputStr, ADDR strSpace
	

	;START OF OUTPUTTING ARRAY
	MOV ecx, 4 ; loop 4 times

	LEA ebx, imageArray ;load address of imageArray
	JECXZ otuputLoopStart ; if ecx is zero, error/end loop

	otuputLoopStart:

	;calculate o value

	;get user input
	;INVOKE OutputStr, ADDR strOutputMax
	;INVOKE OutputInt, [ebx]
	;INVOKE OutputStr, ADDR strSpace

;;;;
;calculate o value
	


	INVOKE OutputStr, ADDR strOutputMax
	INVOKE OutputInt, [ebx]


	MOV eax, [ebx]
	MOV esi, ebx ; store ebx value temporari;y in esi
	MOV ebx, kernal ; move kernal to ebx
	
	CDQ
	IDIV ebx ; divide arrayI by kernal

	MOV ebx, esi ; return esi to ebx
	
	ADD eax, bias
	MOV [ebx], eax
	;if statment to check if eax is above 255
	CMP eax, 255 ; compare if eax is above 255

	JNS above255; if eax is above 255, keep at 255
	JMP below255; if eax is below 255, set bvalue in array to eax

	below255:
	INVOKE OutputStr, ADDR strArrow
	INVOKE OutputInt, [ebx]
	INVOKE OutputStr, ADDR strSpace
	JMP after255
	above255:
	;end if statement
	INVOKE OutputStr, ADDR strArrow
	INVOKE OutputInt, 255
	INVOKE OutputStr, ADDR strSpace
	after255:


	ADD ebx, 4 ; as DWORD, size 4
	LOOP otuputLoopStart

	otuputLoopEnd:
	INVOKE OutputStr, ADDR strSpace




	;Msg asking to exit loop
	INVOKE OutputStr, ADDR strInputExit
	INVOKE InputInt
	MOV ecx, eax

	JMP whileLoopCondition ;jump to start of loop

whileLoopEnd:


	INVOKE ExitProcess, 0
Public _start
END
