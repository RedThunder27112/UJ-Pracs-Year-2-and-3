;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - P04
.386
.MODEL FLAT ; Flat memory model
.STACK 4096 ; 4096 bytes

INCLUDE io.inc ; Directive to use the IO Library

; Exit function
ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

; The data section stores all global variables
.DATA



;string user input messages
strInputExit BYTE "Please enter 0 to exit, or 1 to continue: ",0
strInputI BYTE "Please enter number for I (row col) ( ",0
strInputK BYTE "Please enter number for K (row col) ( ",0
strInputCutOff BYTE "Please enter cutoff value: ",0

strInputBracket BYTE " ): ", 0
strInputSpace BYTE " ", 0

strTest BYTE "Output: ",10, 0

;arrays
imageArray DWORD 16 DUP (?)
kernalArray DWORD 4 DUP (?)
outputArray DWORD 9 DUP (?)

;numbers
cutoff DWORD ?

temp DWORD ?


.CODE
_start:
	
	;set ecx variable for start
	MOV ecx, 1

	whileLoopCondition: ; while(ecx != 0)
	CMP ecx, 0;check if loop has ended
	JZ whileLoopEnd
	whileLoopBody:

	;Msg asking to for cutoff
	INVOKE OutputStr, ADDR strInputCutoff
	INVOKE InputInt
	MOV cutoff, eax


	;-----------------------------;
	;Block for fulling I in while Loop - START
	;------------------------------;
	MOV ecx, 16 ; size of array
	MOV edx, 1 ; row
	MOV esi, 1 ; col

	LEA ebx, imageArray ;load address of imageArray
	JECXZ whileEndI ; if ecx is zero, error/end loop

	whileBodyI:

	;Msg asking to for I value

	INVOKE OutputStr, ADDR strInputI
	INVOKE OutputInt, edx
	INVOKE OutputStr, ADDR strInputSpace
	INVOKE OutputInt, esi
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE InputInt
	MOV [ebx], eax;move user input to array

	INC edx
	DEC esi

	ADD ebx, 4 ; as DWORD, size 4
	LOOP whileBodyI

	whileEndI:
	;-----------------------------;
	;Block for fulling I in while Loop - END
	;------------------------------;


	;-----------------------------;
	;Block for fulling K in while Loop - START
	;------------------------------;
	MOV ecx, 4

	LEA ebx, kernalArray ;load address of kernalArray
	JECXZ whileEndK ; if ecx is zero, error/end loop

	whileBodyK:


	MOV edx, 1
	;Msg asking to for K value
	INVOKE OutputStr, ADDR strInputK
	INVOKE OutputInt, edx
	INVOKE OutputStr, ADDR strInputSpace
	INVOKE OutputInt, edx
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE InputInt
	MOV [ebx], eax;move user input to array

	ADD ebx, 4 ; as DWORD, size 4
	LOOP whileBodyK

	whileEndK:
	;-----------------------------;
	;Block for fulling K in while Loop - END
	;------------------------------;

	;-----------------------------;
	;Block for adding K and I to stack - start
	;------------------------------;
	;MOV ecx 25; 16 + 9 = 25 size of stack
	PUSH ebp
	MOV ebp, esp ;create stack frame
	SUB esp, 288; reserve 8*9*4=288 DWORDS on stack 

	MOV ecx, 0 ;row
	MOV edx, 0 ; col

	MOV temp, esp
	ADD temp, -288
	JMP stackStart
	stackCondition:
	CMP esp, temp ;while(esp > 0)
	
	JZ stackEnd
	stackStart:
	


	
	;calc to get row,col of I array
	MOV eax, ecx ; eax = row
	IMUL eax, 4 ; eax = row*numCol
	ADD eax, edx; eax = row*numCol + col

	IMUL eax, 4 ; as dword is size 4

	;move a value from I array to stack
	LEA ebx, imageArray ;load address of imageArray
	ADD ebx, eax ; as DWORD, size 4.... set size to what i want
	PUSH [ebx] ; add the I value from array to stack

	;----------------------------
	;BLOCK TO CHECK ROW COL BOUND START
	;----------------------------
	;store edx and ecx
	MOV esi, ecx
	MOV edi, edx
	;if to check if row is in 0-1 bound
	CMP ecx, 2
	JZ outOfBoundsC1 ; if ecx >= 2
	JMP endBoundCheckC1
	outOfBoundsC1:
	CMP ecx, 3 ; if ecx >= 3
	JZ setToOneC1
	MOV ecx, 0
	JMP endBoundCheckC1

	setToOneC1:
	MOV ecx, 1

	endBoundCheckC1:

	CMP edx, 2
	JZ outOfBoundsD1 ; if ecx >= 2
	JMP endBoundCheckD1
	outOfBoundsD1:
	CMP edx, 3 ; if ecx >= 3
	JZ setToOneD1
	MOV edx, 0
	JMP endBoundCheckD1

	setToOneD1:
	MOV edx, 1

	endBoundCheckD1:
	;----------------------------
	;BLOCK TO CHECK ROW COL BOUND END
	;----------------------------



	;calc to get row,col of K array
	MOV eax, ecx ; eax = row
	IMUL eax, 4 ; eax = row*numCol
	ADD eax, edx; eax = row*numCol + col

	IMUL eax, 4 ; as dword is size 4

	;move a value from K array to stack
	LEA ebx, kernalArray ;load address of imageArray
	ADD ebx, eax ; as DWORD, size 4.... set size to what i want
	PUSH [ebx] ; add the K value from array to stack

	MOV edx, edi ;reset values
	MOV ecx, esi


	;Now do the same as above, but with col+1
	INC edx

	;calc to get row,col of I array
	MOV eax, ecx ; eax = row
	IMUL eax, 4 ; eax = row*numCol
	ADD eax, edx; eax = row*numCol + col

	IMUL eax, 4 ; as dword is size 4

	;move a value from I array to stack
	LEA ebx, imageArray ;load address of imageArray
	ADD ebx, eax ; as DWORD, size 4.... set size to what i want
	PUSH [ebx] ; add the I value from array to stack


	;----------------------------
	;BLOCK TO CHECK ROW COL BOUND START
	;----------------------------
	;store edx and ecx
	MOV esi, ecx
	MOV edi, edx
	;if to check if row is in 0-1 bound
	CMP ecx, 2
	JZ outOfBoundsC2 ; if ecx >= 2
	JMP endBoundCheckC2
	outOfBoundsC2:
	CMP ecx, 3 ; if ecx >= 3
	JZ setToOneC2
	MOV ecx, 0
	JMP endBoundCheckC2

	setToOneC2:
	MOV ecx, 1

	endBoundCheckC2:

	CMP edx, 2
	JZ outOfBoundsD2 ; if ecx >= 2
	JMP endBoundCheckD2
	outOfBoundsD2:
	CMP edx, 3 ; if ecx >= 3
	JZ setToOneD2
	MOV edx, 0
	JMP endBoundCheckD2

	setToOneD2:
	MOV edx, 1

	endBoundCheckD2:
	;----------------------------
	;BLOCK TO CHECK ROW COL BOUND END
	;----------------------------

	;calc to get row,col of K array
	MOV eax, ecx ; eax = row
	IMUL eax, 4 ; eax = row*numCol
	ADD eax, edx; eax = row*numCol + col

	IMUL eax, 4 ; as dword is size 4

	;move a value from K array to stack
	LEA ebx, kernalArray ;load address of imageArray
	ADD ebx, eax ; as DWORD, size 4.... set size to what i want
	PUSH [ebx] ; add the K value from array to stack

	MOV edx, edi ;reset values
	MOV ecx, esi

	
	DEC edx ;now deincrement col
	INC ecx ;now increment row


	;----------------------------
	;BLOCK TO CHECK ROW COL BOUND START for out of 4 range
	;----------------------------

	;if to check if row is in 0-3 bound
	CMP ecx, 4
	JZ outOfBounds4 ; if ecx >= 4
	JMP endBoundCheck4
	outOfBounds4:
	MOV ecx, 0
	INC edx

	endBoundCheck4:
	;----------------------------
	;BLOCK TO CHECK ROW COL BOUND END
	;----------------------------


	JMP stackCondition ; go back to start
	stackEnd:
	
	;-----------------------------;
	;Block for adding K and I to stack - end
	;------------------------------;


	;-----------------------------;
	;Block for outputting stack START
	;------------------------------;

	stackOutputStart:

	




MOV edx, 0
	POP eax
	POP ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	POP ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	POP ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	POP ecx
	imul eax, ecx
	ADD edx, eax

	INVOKE OutputStr, ADDR strTest
	INVOKE OutputInt, edx




	;-----------------------------;
	;Block for outputting stack END
	;------------------------------;

	;Msg asking to exit loop
	INVOKE OutputStr, ADDR strInputExit
	INVOKE InputInt
	MOV ecx, eax

	JMP whileLoopCondition ;jump to start of loop

	whileLoopEnd:






	INVOKE ExitProcess, 0
Public _start
END
