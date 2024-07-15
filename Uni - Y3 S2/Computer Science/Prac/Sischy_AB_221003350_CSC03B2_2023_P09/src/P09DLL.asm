;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - P09
.386
.MODEL FLAT, stdcall
.STACK 4096

INCLUDE io.inc ; Directive to use the IO Library

.CODE

; LibMain function tests which action is currently beign performed
; The function returns if the DLL is loaded correctly or not.
; We avoid the complex logic here for simplicity.
LibMain proc instance:DWORD, reason:DWORD, unused:DWORD
	mov eax, 1
  ret
LibMain ENDP

min PROC NEAR32
	; Entry code
	PUSH	ebp
	MOV		ebp, esp
	SUB esp, 4
	PUSH	ebx
	PUSH	ecx
	PUSH	edx
	PUSHFD

	;ebp+12 = size
	;ebp+8 = array
	;INITIALISE values for loop
	MOV ebx, [ebp+8]
	MOV ecx, [ebp+12]
	MOV eax, [ebx]
	MOV [ebp-4], eax  
	MOV eax, 0

	;eg3
	DEC ecx ; as if there is 3 arrays, you want it to run twice
	

	
	startLoop:


	ADD ebx, 4 ;next array number

	MOV eax, [ebx]
	CMP [ebp-4], eax ; if ([ebp-4] > eax)
	JG newMin
	JMP noMin

	newMin:;if there is a new min, set it
	MOV eax, [ebx]
	MOV [ebp-4], eax
	noMin:

	LOOP startLoop

	MOV	eax, [ebp-4];return value

	; Exit code
	POPFD
	POP		edx
	POP		ecx
	POP		ebx
	MOV		esp, ebp
	POP		ebp
	RET		8					; params are X bytes
min ENDP

max PROC NEAR32
	; Entry code
	PUSH	ebp
	MOV		ebp, esp
	SUB esp, 4
	PUSH	ebx
	PUSH	ecx
	PUSH	edx
	PUSHFD

	;ebp+12 = size
	;ebp+8 = array
	;INITIALISE values for loop
	MOV ebx, [ebp+8]
	MOV ecx, [ebp+12]
	MOV eax, [ebx]
	MOV [ebp-4], eax  
	MOV eax, 0

	;eg3
	DEC ecx ; as if there is 3 arrays, you want it to run twice


	
	startLoop:

	ADD ebx, 4 ;next array number

	MOV eax, [ebx]
	CMP eax, [ebp-4] 
	JG newMax
	JMP noMax

	newMax:;if there is a new max, set it
	MOV eax, [ebx]
	MOV [ebp-4], eax
	noMax:

	LOOP startLoop

	MOV	eax, [ebp-4];return value

	; Exit code
	POPFD
	POP		edx
	POP		ecx
	POP		ebx
	MOV		esp, ebp
	POP		ebp
	RET		8					; params are X bytes
max ENDP

normalise PROC NEAR32
	; Entry code
	PUSH	ebp
	MOV		ebp, esp
	SUB esp, 24
	PUSH	ebx
	PUSH	ecx
	PUSH	edx
	PUSHFD
	

	;ebp+20 maxfactor
	;ebp+16 minFactor
	;ebp+12 = size
	;ebp+8 = array
	;INITIALISE values for loop
	MOV ebx, [ebp+8]
	MOV ecx, [ebp+12]
	
	MOV eax, 0




;get the min value, by pu
	MOV edx, eax ;to keep eax value
	
	PUSH [ebp+12];size
	PUSH [ebp+8];array pointer
	CALL min
	MOV [ebp-4], eax
	
	
	MOV eax, edx

	;get the min value, by pu
	MOV edx, eax ;to keep eax value
	PUSH [ebp+12];size
	PUSH [ebp+8];array pointer
	CALL max
	MOV [ebp-8], eax
	MOV eax, edx
	
	startLoop:

	

	;arrayi - minArray
	MOV eax, [ebx]
	SUB eax, [ebp-4]
	MOV [ebp-12], eax


	;MaxFactor - minFactor
	MOV eax, [ebp+20]
	SUB eax, [ebp+16]
	MOV [ebp-16], eax


	;MaxArray-minArray
	MOV eax, [ebp-8]
	SUB eax, [ebp-4]
	MOV [ebp-20], eax


	;(MaxFactor - minFactor) / (MaxArray-minArray)

	FINIT
	FLD REAL4 PTR [ebp-16] 
	FLD REAL4 PTR [ebp-20]
	FDIV
	FST REAL4 PTR [ebp-20]


	;(arrayi - minArray) * [(MaxFactor - minFactor) / (MaxArray-minArray)]
	FILD DWORD PTR [ebp-12]
	FLD REAL4 PTR  [ebp-20]
	FMUL
	FST REAL4 PTR [ebp-20]



	;add minFactor
	FLD REAL4 PTR [ebp+16]
	FLD REAL4 PTR  [ebp-20]
	FADD
	FST REAL4 PTR [ebp-20]
  	
	;now we add the rounded, and non rounded to stack, to see if rounding occured
	FLD REAL4 PTR [ebp-20]
	FIST DWORD PTR [ebp-16]

	FILD DWORD PTR [ebp-16]
	FLD REAL4 PTR [ebp-20]
	FSUB
	FST REAL4 PTR [ebp-16]


	;this is for hte rounding issue
	MOV eax, [ebp-16]
	CMP eax, 0
	JG add1
	JMP dontadd1


	add1:;this gets rid of the rounding issue
	FLD REAL4 PTR [ebp-20]
	MOV eax, 1
	MOV [ebp-16], eax
	
	FILD DWORD PTR [ebp-16]
	FSUB
	FST REAL4 PTR [ebp-20]
	dontadd1:
	

	;INVOKE OutputFloat, [ebp-16]
	FLD REAL4 PTR [ebp-20]
	FIST DWORD PTR [ebp-20]
	MOV eax, [ebp-20]


	
	;replace array
	MOV [ebx], eax

	ADD ebx, 4 ;next array number
	LOOP startLoop

	endloop:
	MOV	eax, [ebx];return value
	
	; Exit code
	POPFD
	POP		edx
	POP		ecx
	POP		ebx
	MOV		esp, ebp
	POP		ebp
	RET		16					; params are X bytes
normalise ENDP

end LibMain

;leave floating point on floating point stack if it is the return value
