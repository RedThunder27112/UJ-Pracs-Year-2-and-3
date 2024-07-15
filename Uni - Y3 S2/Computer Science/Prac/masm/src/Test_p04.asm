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
strHEading BYTE "r*c+c value  row  col",10,0

strInputBracket BYTE " ): ", 0
strInputSpace BYTE " ", 0
strTimes BYTE "*", 0
strPlus BYTE "+",0
strEqual BYTE "=",0

strTest BYTE " ",10, 0



str00 BYTE "Sum for O 00 ",10,0
str10 BYTE "Sum for O 10 ",10,0
str20 BYTE "Sum for O 20 ",10,0

str01 BYTE "Sum for O 01 ",10,0
str11 BYTE "Sum for O 11 ",10,0
str21 BYTE "Sum for O 21 ",10,0

str02 BYTE "Sum for O 02 ",10,0
str12 BYTE "Sum for O 12 ",10,0
str22 BYTE "Sum for O 22 ",10,0


;arrays
imageArray DWORD 16 DUP (?)
kernalArray DWORD 4 DUP (?)
outputArray DWORD 9 DUP (?)

;numbers
cutoff DWORD ?

temp DWORD ?
a DWORD 1

i DWORD ?
k DWORD ?
d DWORD ?
cc DWORD ?

o1 DWORD ?
o2 DWORD ?
o3 DWORD ?
o4 DWORD ?
o5 DWORD ?
o6 DWORD ?
o7 DWORD ?
o8 DWORD ?
o9 DWORD ?
o10 DWORD ?
o11 DWORD ?
o12 DWORD ?
o13 DWORD ?
o14 DWORD ?
o15 DWORD ?
o16 DWORD ?
o17 DWORD ?

.CODE
_start:
	



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

	;'INVOKE OutputStr, ADDR strInputI
;INVOKE OutputInt, edx
	;INVOKE OutputStr, ADDR strInputSpace
	;INVOKE OutputInt, esi
	;INVOKE OutputStr, ADDR strInputBracket
	;INVOKE InputInt
	MOV eax, a
	MOV [ebx], eax;move user input to array
	INC a

	INC edx
	DEC esi

	ADD ebx, 4 ; as DWORD, size 4
	LOOP whileBodyI

	whileEndI:


	
	;-----------------------------;
	;Block for fulling I in while Loop - END
	;------------------------------;

	MOV a, 1
	;-----------------------------;
	;Block for fulling K in while Loop - START
	;------------------------------;
	MOV ecx, 4

	LEA ebx, kernalArray ;load address of kernalArray
	JECXZ whileEndK ; if ecx is zero, error/end loop

	whileBodyK:


	;MOV edx, 1
	;Msg asking to for K value
	;INVOKE OutputStr, ADDR strInputK
	;INVOKE OutputInt, edx
	;INVOKE OutputStr, ADDR strInputSpace
	;INVOKE OutputInt, edx
	;INVOKE OutputStr, ADDR strInputBracket
	;INVOKE InputInt
	
		MOV eax, a
	MOV [ebx], eax;move user input to array
	INC a
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
	;SUB esp, 288; reserve 8*9*4=288 DWORDS on stack 

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

	MOV cc, ecx
	MOV d, edx
	MOV i, eax
	MOV eax, [ebx]
	MOV k, eax
	INVOKE OutputStr, ADDR strTest
	INVOKE OutputStr, ADDR strTest
	INVOKE OutputStr, ADDR strHEading
	INVOKE OutputInt, i
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, k
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, cc
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, d

	;----------------------------
	;BLOCK TO CHECK ROW COL BOUND START
	;----------------------------
	;store edx and ecx
	MOV esi, ecx
	MOV edi, edx
	;if to check if row is in 0-1 bound
	CMP ecx, 2
	JGE outOfBoundsC1 ; if ecx >= 2
	JMP endBoundCheckC1
	outOfBoundsC1:
	CMP ecx, 3 ; if ecx >= 3
	JGE setToOneC1
	MOV ecx, 0
	JMP endBoundCheckC1

	setToOneC1:
	MOV ecx, 1

	endBoundCheckC1:

	CMP edx, 2
	JGE outOfBoundsD1 ; if ecx >= 2
	JMP endBoundCheckD1
	outOfBoundsD1:
	CMP edx, 3 ; if ecx >= 3
	JGE setToOneD1
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
	IMUL eax, 2 ; eax = row*numCol
	ADD eax, edx; eax = row*numCol + col

	IMUL eax, 4 ; as dword is size 4

	;move a value from K array to stack
	LEA ebx, kernalArray ;load address of imageArray
	ADD ebx, eax ; as DWORD, size 4.... set size to what i want
	PUSH [ebx] ; add the K value from array to stack


	MOV cc, ecx
	MOV d, edx
	MOV i, eax
	MOV eax, [ebx]
	MOV k, eax
	
	INVOKE OutputStr, ADDR strTest
	INVOKE OutputInt, i
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, k
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, cc
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, d

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

	MOV cc, ecx
	MOV d, edx
	MOV i, eax
	MOV eax, [ebx]
	MOV k, eax
	
	INVOKE OutputStr, ADDR strTest
	INVOKE OutputInt, i
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, k
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, cc
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, d

	;----------------------------
	;BLOCK TO CHECK ROW COL BOUND START
	;----------------------------
	;store edx and ecx
	MOV esi, ecx
	MOV edi, edx
	;if to check if row is in 0-1 bound
	CMP ecx, 2
	JGE outOfBoundsC2 ; if ecx >= 2
	JMP endBoundCheckC2
	outOfBoundsC2:
	CMP ecx, 3 ; if ecx >= 3
	JGE setToOneC2
	MOV ecx, 0
	JMP endBoundCheckC2

	setToOneC2:
	MOV ecx, 1

	endBoundCheckC2:

	CMP edx, 2
	JGE outOfBoundsD2 ; if ecx >= 2
	JMP endBoundCheckD2
	outOfBoundsD2:
	CMP edx, 3 ; if ecx >= 3
	JGE setToOneD2
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
	IMUL eax, 2 ; eax = row*numCol
	ADD eax, edx; eax = row*numCol + col

	IMUL eax, 4 ; as dword is size 4

	;move a value from K array to stack
	LEA ebx, kernalArray ;load address of imageArray
	ADD ebx, eax ; as DWORD, size 4.... set size to what i want
	PUSH [ebx] ; add the K value from array to stack

	MOV cc, ecx
	MOV d, edx
	MOV i, eax
	MOV eax, [ebx]
	MOV k, eax
	
	INVOKE OutputStr, ADDR strTest
	INVOKE OutputInt, i
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, k
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, cc
	INVOKE OutputStr, ADDR strInputBracket
	INVOKE OutputInt, d
	MOV edx, edi ;reset values
	MOV ecx, esi

	
	DEC edx ;now deincrement col
	INC ecx ;now increment row


	;----------------------------
	;BLOCK TO CHECK ROW COL BOUND START for out of 4 range
	;----------------------------

	;if to check if row is in 0-3 bound
	CMP ecx, 4
	JGE outOfBounds4 ; if ecx >= 4
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

	



INVOKE OutputStr, ADDR strTest
	MOV edx, 0
	POP eax
	MOV o1, eax
	POP ecx
	MOV o2, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o3, eax
	POP ecx
	MOV o4, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o5, eax
	POP ecx
	MOV o6, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o7, eax
	POP ecx
	MOV o8, ecx
	imul eax, ecx
	ADD edx, eax

	MOV o9, edx

	INVOKE OutputStr, ADDR strTest
	INVOKE OutputStr, ADDR str22
	INVOKE OutputInt, o1
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o2
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o3
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o4
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o5
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o6
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o7
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o8
	INVOKE OutputStr, ADDR strEqual
	INVOKE OutputInt, o9

	



	INVOKE OutputStr, ADDR strTest
	MOV edx, 0
	POP eax
	MOV o1, eax
	POP ecx
	MOV o2, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o3, eax
	POP ecx
	MOV o4, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o5, eax
	POP ecx
	MOV o6, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o7, eax
	POP ecx
	MOV o8, ecx
	imul eax, ecx
	ADD edx, eax

	MOV o10, edx

	INVOKE OutputStr, ADDR strTest
	INVOKE OutputStr, ADDR str12
	INVOKE OutputInt, o1
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o2
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o3
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o4
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o5
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o6
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o7
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o8
	INVOKE OutputStr, ADDR strEqual
	INVOKE OutputInt, o10




	INVOKE OutputStr, ADDR strTest
	MOV edx, 0
	POP eax
	MOV o1, eax
	POP ecx
	MOV o2, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o3, eax
	POP ecx
	MOV o4, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o5, eax
	POP ecx
	MOV o6, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o7, eax
	POP ecx
	MOV o8, ecx
	imul eax, ecx
	ADD edx, eax

	MOV o11, edx

	INVOKE OutputStr, ADDR strTest
	INVOKE OutputStr, ADDR str02
	INVOKE OutputInt, o1
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o2
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o3
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o4
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o5
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o6
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o7
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o8
	INVOKE OutputStr, ADDR strEqual
	INVOKE OutputInt, o11

	INVOKE OutputStr, ADDR strTest
	MOV edx, 0
	POP eax
	MOV o1, eax
	POP ecx
	MOV o2, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o3, eax
	POP ecx
	MOV o4, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o5, eax
	POP ecx
	MOV o6, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o7, eax
	POP ecx
	MOV o8, ecx
	imul eax, ecx
	ADD edx, eax

	MOV o12, edx

	INVOKE OutputStr, ADDR strTest
	INVOKE OutputStr, ADDR str21
	INVOKE OutputInt, o1
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o2
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o3
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o4
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o5
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o6
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o7
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o8
	INVOKE OutputStr, ADDR strEqual
	INVOKE OutputInt, o12


	INVOKE OutputStr, ADDR strTest
	MOV edx, 0
	POP eax
	MOV o1, eax
	POP ecx
	MOV o2, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o3, eax
	POP ecx
	MOV o4, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o5, eax
	POP ecx
	MOV o6, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o7, eax
	POP ecx
	MOV o8, ecx
	imul eax, ecx
	ADD edx, eax

	MOV o13, edx

	INVOKE OutputStr, ADDR strTest
	INVOKE OutputStr, ADDR str11
	INVOKE OutputInt, o1
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o2
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o3
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o4
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o5
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o6
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o7
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o8
	INVOKE OutputStr, ADDR strEqual
	INVOKE OutputInt, o13

	INVOKE OutputStr, ADDR strTest
	MOV edx, 0
	POP eax
	MOV o1, eax
	POP ecx
	MOV o2, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o3, eax
	POP ecx
	MOV o4, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o5, eax
	POP ecx
	MOV o6, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o7, eax
	POP ecx
	MOV o8, ecx
	imul eax, ecx
	ADD edx, eax

	MOV o14, edx

	INVOKE OutputStr, ADDR strTest
	INVOKE OutputStr, ADDR str01
	INVOKE OutputInt, o1
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o2
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o3
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o4
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o5
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o6
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o7
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o8
	INVOKE OutputStr, ADDR strEqual
	INVOKE OutputInt, o14

	INVOKE OutputStr, ADDR strTest
	MOV edx, 0
	POP eax
	MOV o1, eax
	POP ecx
	MOV o2, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o3, eax
	POP ecx
	MOV o4, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o5, eax
	POP ecx
	MOV o6, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o7, eax
	POP ecx
	MOV o8, ecx
	imul eax, ecx
	ADD edx, eax

	MOV o15, edx

	INVOKE OutputStr, ADDR strTest
	INVOKE OutputStr, ADDR str20
	INVOKE OutputInt, o1
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o2
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o3
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o4
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o5
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o6
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o7
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o8
	INVOKE OutputStr, ADDR strEqual
	INVOKE OutputInt, o15

	INVOKE OutputStr, ADDR strTest
	MOV edx, 0
	POP eax
	MOV o1, eax
	POP ecx
	MOV o2, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o3, eax
	POP ecx
	MOV o4, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o5, eax
	POP ecx
	MOV o6, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o7, eax
	POP ecx
	MOV o8, ecx
	imul eax, ecx
	ADD edx, eax

	MOV o16, edx

	INVOKE OutputStr, ADDR strTest
	INVOKE OutputStr, ADDR str10
	INVOKE OutputInt, o1
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o2
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o3
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o4
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o5
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o6
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o7
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o8
	INVOKE OutputStr, ADDR strEqual
	INVOKE OutputInt, o16


INVOKE OutputStr, ADDR strTest
	MOV edx, 0
	POP eax
	MOV o1, eax
	POP ecx
	MOV o2, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o3, eax
	POP ecx
	MOV o4, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o5, eax
	POP ecx
	MOV o6, ecx
	imul eax, ecx
	ADD edx, eax

	POP eax
	MOV o7, eax
	POP ecx
	MOV o8, ecx
	imul eax, ecx
	ADD edx, eax

	MOV o17, edx

	INVOKE OutputStr, ADDR strTest
	INVOKE OutputStr, ADDR str00
	INVOKE OutputInt, o1
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o2
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o3
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o4
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o5
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o6
	INVOKE OutputStr, ADDR strPlus
	INVOKE OutputInt, o7
	INVOKE OutputStr, ADDR strTimes
	INVOKE OutputInt, o8
	INVOKE OutputStr, ADDR strEqual
	INVOKE OutputInt, o17

INVOKE OutputStr, ADDR strTest
INVOKE OutputStr, ADDR strTest
INVOKE OutputStr, ADDR strTest

INVOKE OutputInt, o17
INVOKE OutputStr, ADDR strInputSpace
INVOKE OutputInt, o14
INVOKE OutputStr, ADDR strInputSpace
INVOKE OutputInt, o11
INVOKE OutputStr, ADDR strInputSpace
INVOKE OutputStr, ADDR strTest


INVOKE OutputInt, o16
INVOKE OutputStr, ADDR strInputSpace
INVOKE OutputInt, o13
INVOKE OutputStr, ADDR strInputSpace
INVOKE OutputInt, o10
INVOKE OutputStr, ADDR strInputSpace
INVOKE OutputStr, ADDR strTest


INVOKE OutputInt, o15
INVOKE OutputStr, ADDR strInputSpace
INVOKE OutputInt, o12
INVOKE OutputStr, ADDR strInputSpace
INVOKE OutputInt, o9
INVOKE OutputStr, ADDR strInputSpace
INVOKE OutputStr, ADDR strTest








	INVOKE ExitProcess, 0
Public _start
END
