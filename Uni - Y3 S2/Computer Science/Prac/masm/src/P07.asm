;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - P07
.386
.MODEL FLAT ; Flat memory model
.STACK 4096 ; 4096 bytes

INCLUDE io.inc ; Directive to use the IO Library

; Exit function
ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

; The data section stores all global variables
.DATA

;string promts
msgExit BYTE "Enter 0 to exit, or 1 to re-do program: ", 0
msgLine BYTE " ",10,0
msgSpace BYTE " - ",0
msgInputStr1 BYTE "Enter string 1: ",0
msgInputStr2 BYTE "Enter string 2: ",0
msgLength BYTE "DistHamming Str1 and Str2 lengths differ: No Answer",0

msgDistHamming BYTE "distHamming final answer: ",0
msgLeven BYTE "levenEnd final answer: ",0

;2 string values
str1 BYTE 50 DUP (?) 
str2 BYTE 50 DUP (?)

tests DWORD ?


.CODE

_distHamming PROC NEAR32

;allocating stack 
PUSH ebp
MOV ebp, esp
;pushing variables
PUSH ebx
PUSH ecx
PUSH edx
PUSHFD





MOV ecx, [ebp+8] ;length of str1
CMP ecx, [ebp+12] ;see if length is same
JE sameLength
JMP diffLengthS
sameLength:



LEA ecx, str1;load string address into registers
LEA edx, str2

;MOV ecx, 1 ;used for length of loop


hammingLoopS:

;compare each string character eachother
MOV BL, [ecx]
MOV AL, [edx]


CMP AL, BL

JE noDifference ; if no difference, skip


;INC eax ; else increment eax
MOV eax, [ebp+20]
INC eax
MOV [ebp+20], eax


noDifference:

MOV eax, [ebp+16]
CMP eax, [ebp+12] ; check if every character has been gone through
JE hammingLoopE ;exit loop
	
INC ecx
INC edx ;increase edx and ebx

MOV eax, [ebp+16]
INC eax
MOV [ebp+16], eax

JMP hammingLoopS
hammingLoopE:


JMP diffLengthE ;skip diff length part
diffLengthS:

MOV eax, -1
MOV [ebp+20], eax

diffLengthE:
;popping stack and variables
MOV eax, [ebp+20]
POPFD
POP edx
POP ecx
POP ebx
;destroying stack
MOV esp, ebp
POP ebp

RET 8

_distHamming ENDP

_distLevenshtein PROC NEAR32

;allocating stack 
PUSH ebp
MOV ebp, esp
SUB esp, 12
;pushing variables
PUSH ebx
PUSH ecx
PUSH edx
PUSHFD


; if i = 0
MOV eax, [ebp+12]
CMP eax, 0

MOV eax, [ebp+20]
JE levenEnd
; if j = 0
MOV eax, [ebp+20]
CMP eax, 0

MOV eax, [ebp+12]

JE levenEnd

; if a i-1 = b j-1
MOV eax, [ebp+8]
MOV edx, [ebp+12]
DEC edx
ADD eax, edx

MOV BL, [eax];str2

MOV eax, [ebp+16];str1
MOV edx, [ebp+20]
DEC edx
ADD eax, edx
MOV CL, [eax]


CMP BL, CL
JE  thirdLevenS
JMP thirdLevenE

thirdLevenS:

MOV edx, [ebp+20]
DEC edx

MOV eax, [ebp+12]
DEC eax

PUSH edx ; +20 str1 length
PUSH [ebp+16]  ; +16 str1
PUSH eax  ;+12 str2
PUSH [ebp+8] ;+8 str2 length

CALL _distLevenshtein


JMP levenEnd
thirdLevenE:

;a,i-1,b,i
MOV ecx, [ebp+12]
DEC ecx

PUSH [ebp+20] ; +20 str1 length
PUSH [ebp+16]  ; +16 str1
PUSH ecx  ;+12 str2
PUSH [ebp+8] ;+8 str2 length
CALL _distLevenshtein
MOV [ebp-4], eax

;a,i,b,i-1
MOV ecx, [ebp+20]
DEC ecx

PUSH ecx ; +20 str1 length
PUSH [ebp+16]  ; +16 str1
PUSH [ebp+12]  ;+12 str2
PUSH [ebp+8] ;+8 str2 length
CALL _distLevenshtein
MOV [ebp-8], eax

;a,i-1,b,i-1
MOV ecx, [ebp+20]
DEC ecx

MOV edx, [ebp+12]
DEC edx

PUSH ecx ; +20 str1 length
PUSH [ebp+16]  ; +16 str1
PUSH edx  ;+12 str2
PUSH [ebp+8] ;+8 str2 length
CALL _distLevenshtein
MOV [ebp-12], eax


;find which is min
MOV ebx, [ebp-12]
MOV ecx, [ebp-8]
MOV edx, [ebp-4]

CMP ebx, ecx
JG ebxG
JMP ecxG

ebxG:
CMP edx, ecx
JG edxG
MOV eax, edx ;edx is smallest
INC eax
JMP levenEnd 

ecxG:
CMP edx, ebx
JG edx2G
MOV eax, edx ;ecx is smallest
INC eax

edxG:
MOV eax, ecx ;ecx is smallest
INC eax
JMP levenEnd 

edx2G:
MOV eax, ebx ;ebx is smallest
INC eax
JMP levenEnd 

levenEnd:

;popping stack and variables
POPFD
POP edx
POP ecx
POP ebx
;destroying stack
MOV esp, ebp
POP ebp

RET 8

_distLevenshtein ENDP

_getLength PROC NEAR32

;allocating stack 
PUSH ebp
MOV ebp, esp
;pushing variables
PUSH ebx
PUSH ecx
PUSH edx
PUSHFD

MOV ecx, [ebp+8]
MOV edx, 0
startLength:
MOV BL, [ecx]

;go through array till you reach 0 (null)
INC edx
INC ecx
CMP BL, 0
JNE startLength

MOV eax, edx
DEC eax
;popping stack and variables
POPFD
POP edx
POP ecx
POP ebx
;destroying stack
MOV esp, ebp
POP ebp

RET 4

_getLength ENDP


_start:

	;start stack
	PUSH ebp
	MOV ebp, esp
	
	;SUB esp, 100 ; size of st1 + st2 for distLevenshtein
	startLoop:

	;get strings from user

	LEA eax, msgInputStr1 ;str1 string message
	PUSH eax
	CALL OutputStr

	PUSH 50 ; max size of string
	LEA eax, str1
	PUSH eax ;address of string
	CALL InputStr

	LEA eax, msgInputStr2 ;str2 string message
	PUSH eax
	CALL OutputStr

	PUSH 50 ; max size of string
	LEA eax, str2
	PUSH eax ;address of string
	CALL InputStr

	LEA eax, msgLine ; next line
	PUSH eax
	CALL OutputStr
	
	;get lengths
	LEA eax, str1 ;+12
	PUSH eax
	CALL _getLength
	MOV ebx, eax

	LEA eax, str2 ;+8
	PUSH eax
	CALL _getLength
	MOV ecx, eax


	PUSH 0 ; +20
	PUSH 1 ; +16
	PUSH ebx ;+12
	PUSH ecx ;+8

	
	CALL _distHamming
	MOV edx, eax

	CMP edx, -1
	JE nullMessageS

	LEA eax, msgDistHamming ;DistHamming string message
	PUSH eax
	CALL OutputStr

	PUSH edx ;DistHamming final answer
	CALL OutputInt

	LEA eax, msgLine ; next line
	PUSH eax
	CALL OutputStr
	
	JMP nullMessageE
	nullMessageS:;message for when lengths of str1 and str2 differ

	LEA eax, msgLength ;DistHamming string message
	PUSH eax
	CALL OutputStr

	LEA eax, msgLine ; next line
	PUSH eax
	CALL OutputStr

	nullMessageE:

	LEA eax, str1
	LEA edx, str2

	PUSH ebx ; +20 str1 length
	PUSH eax ; +16 str1
	PUSH ecx ;+12 str2 length
	PUSH edx;+8 str2 
	CALL _distLevenshtein
	MOV edx, eax

	LEA eax, msgLeven ;msgLeven string message
	PUSH eax
	CALL OutputStr

	PUSH edx ;msgLeven final answer
	CALL OutputInt

	LEA eax, msgLine ; next line
	PUSH eax
	CALL OutputStr
	





	;Ask user if they want to exit loop or not
	LEA eax, msgExit ;ask user if they want to exit
	PUSH eax
	CALL OutputStr
	CALL InputInt

	;check if user entered 0 not (0 = continue/redo)
	MOV ecx, eax
	CMP ecx, 0
	JNZ startLoop

	;destroy stack
	MOV esp, ebp
	POP ebp

	INVOKE ExitProcess, 0
Public _start
END
