;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - P08
.386
.MODEL FLAT ; Flat memory model
.STACK 4096 ; 4096 bytes

INCLUDE io.inc ; Directive to use the IO Library

; Exit function
ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

; The data section stores all global variables
.DATA

msgExit BYTE "Enter 0 to exit, 1 to run again: ",0
msgLine BYTE " ",10,0
msgR BYTE "Enter value for R: ",0
msgG BYTE "Enter value for G: ",0
msgB BYTE "Enter value for B: ",0

msgCPU BYTE "CPU output: ",0
msgFPU BYTE "FPU output: ",0

;msgRC BYTE "Enter value for RC: ",0
;msgGC BYTE "Enter value for GC: ",0
;msgBC BYTE "Enter value for BC: ",0


; The code section may contain multiple tags such as _start, which is the entry
; point of this assembly program
.CODE


_grayScaleCPU PROC NEAR32
;create stack
PUSH ebp
MOV ebp, esp
SUB esp, 12 ; ints
PUSH ebx
PUSH ecx
PUSH edx
PUSHFD

;code of method

;add calculation variables to stack



;load variables into CPU

;R multiplication
MOV eax, [ebp+8] ;R
IMUL eax, 3

cdq ; prepare divisor
MOV ebx, 10
IDIV ebx ; divide eax by 10

MOV [ebp-4], eax

;G multiplication
MOV eax, [ebp+12] ;G
IMUL eax, 6

cdq ; prepare divisor
MOV ebx, 10
IDIV ebx ; divide eax by 10

MOV [ebp-8], eax

MOV eax, [ebp+16] ; add B
cdq ; prepare divisor
MOV ebx, 10
IDIV ebx ; divide eax by 10
MOV [ebp+16],eax

MOV eax, 0
ADD eax, [ebp-4] ;add R calc
ADD eax, [ebp-8] ; add G calc
ADD eax, [ebp+16] ; add B




;pop stack
POPFD
POP edx
POP ecx
POP ebx
MOV esp, ebp
POP ebp

RET 12

_grayScaleCPU ENDP




_grayScaleFPU PROC NEAR32
;create stack
PUSH ebp
MOV ebp, esp
SUB esp, 12 ; ints
SUB esp, 16 ; floating points
PUSH eax
PUSH ebx
PUSH ecx
PUSH edx
PUSHFD

;code of method


FINIT ;initialise floating point unit

;get 0.144
MOV eax, 114
MOV [ebp-4], eax

;get 
MOV eax, 587
MOV [ebp-8], eax

;get 
MOV eax, 299
MOV [ebp-12], eax

;load variables into FPU
FILD DWORD PTR [ebp+16] ; B
;FLD temp11 ; 0.144
FILD REAL4 PTR [ebp-4] ; 144

FMUL ; B*114
FST REAL4 PTR [ebp-16]



FILD DWORD PTR [ebp+12] ; G
FILD REAL4 PTR [ebp-8] ; 587

FMUL ; G*(587)

FST REAL4 PTR [ebp-20]



FILD DWORD PTR [ebp+8] ; R
FILD REAL4 PTR [ebp-12] ; 299

FMUL ; R*299
FST REAL4 PTR [ebp-24]



FLD REAL4 PTR [ebp-16] ; B*144
FLD REAL4 PTR [ebp-20] ; G*587
FLD REAL4 PTR [ebp-24] ; R*299

FADD
FADD
FST REAL4 PTR [ebp-28]   ; final answer


FLD REAL4 PTR [ebp-28] ; R*0.299

;divide by 1000, so that final naswer is proper decimal
MOV eax, 1000
MOV [ebp-16], eax
FILD DWORD PTR [ebp-16]

FDIV 
FST REAL4 PTR [ebp-28]

LEA eax, msgFPU ;FPU output
PUSH eax
CALL OutputStr
	
PUSH [ebp-28]
CALL OutputFloat

;Next Line formating
LEA eax, msgLine 
PUSH eax
CALL OutputStr
MOV edx, eax

;Next Line formating
LEA eax, msgLine 
PUSH eax
CALL OutputStr
MOV edx, eax

;
;pop stack
POPFD
POP edx
POP ecx
POP ebx
POP eax
MOV esp, ebp
POP ebp

RET 12

_grayScaleFPU ENDP

_start:

	PUSH ebp
	MOV ebp, esp
	SUB esp, 12
	startLoop:

	;create stack



	;ask user for R
	LEA eax, msgR 
	PUSH eax
	CALL OutputStr
	CALL InputInt
	MOV [ebp-4], eax ;move R to ebp-4

	;ask user for G
	LEA eax, msgG 
	PUSH eax
	CALL OutputStr
	CALL InputInt
	MOV [ebp-8], eax ;move G to ebp-8

	;ask user for B
	LEA eax, msgB
	PUSH eax
	CALL OutputStr
	CALL InputInt
	MOV [ebp-12], eax ;move B to ebp-12

	;Next Line formating
	LEA eax, msgLine 
	PUSH eax
	CALL OutputStr
	MOV edx, eax


	PUSH [ebp-12] ;+16 B
	PUSH [ebp-8] ;+12  G
	PUSH [ebp-4] ;+8   R
	CALL _grayScaleCPU
	MOV edx, eax

	;OUTPUT CPU
	LEA eax, msgCPU
	PUSH eax
	CALL OutputStr
	
	PUSH edx
	CALL OutputInt

	;Next Line formating
	LEA eax, msgLine 
	PUSH eax
	CALL OutputStr
	MOV edx, eax

	PUSH [ebp-12] ;+16 B
	PUSH [ebp-8] ;+12  G
	PUSH [ebp-4] ;+8   R
	CALL _grayScaleFPU



	;Ask user if they want to exit loop or not
	LEA eax, msgExit ;ask user if they want to exit
	PUSH eax
	CALL OutputStr
	CALL InputInt

	;end or redo program
	MOV ecx, eax
	CMP ecx, 0
	JNZ startLoop

	;pop stack
	MOV esp, ebp
	POP ebp

	
	INVOKE ExitProcess, 0
Public _start
END
