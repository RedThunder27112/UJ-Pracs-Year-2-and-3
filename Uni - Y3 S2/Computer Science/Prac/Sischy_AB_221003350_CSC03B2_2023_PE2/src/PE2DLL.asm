;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - PE2
.386
.MODEL FLAT, stdcall
.STACK 4096
INCLUDE io.inc
.DATA
str2 BYTE 68 DUP (?)
.CODE

; LibMain function tests which action is currently beign performed
; The function returns if the DLL is loaded correctly or not.
; We avoid the complex logic here for simplicity.
LibMain proc instance:DWORD, reason:DWORD, unused:DWORD
	mov eax, 1
  ret
LibMain ENDP

binStringToDecimal PROC NEAR32
	; Entry code
	PUSH	ebp
	MOV		ebp, esp
	SUB esp, 8
	PUSH	ebx
	PUSH	ecx
	PUSH	edx
	PUSHFD
	
	MOV ebx, [ebp+8]
	MOV eax, [ebx]

	;;;
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

	MOV ecx, eax
	;
	;49=1
	;49=0
	MOV ebx, [ebp+8]
	MOV eax, 0
	MOV [ebp-4], eax

	startLoop2:

	MOV DL, [ebx]
	CMP DL, 49
	JE loopOne
	CMP DL, 48
	JE endinnerLoop
	JMP binFalse

	loopOne:
	
	MOV eax, ecx
	DEC eax

	CMP eax, 0
	JE	isOne
	JMP isOneN

	isOne:
	MOV eax, 1
	ADD [ebp-4], eax
	JMP endinnerLoop
	isOneN:

	MOV edx, eax
	MOV eax, 1

	innerLoop:
	IMUL eax, 2
	DEC edx
	CMP edx, 0
	JG innerLoop
	ADD [ebp-4], eax



	endinnerLoop:

	INC ebx
	DEC ecx
	CMP ecx, 0
	JNZ startLoop2

	JMP binEnd
	binFalse:
	MOV eax, -1
	MOV [ebp-4], eax
	binEnd:

	MOV eax,[ebp-4]
	; Exit code
	POPFD
	POP		edx
	POP		ecx
	POP		ebx
	MOV		esp, ebp
	POP		ebp
	RET		4					; params are X bytes
binStringToDecimal ENDP




hexStringToDecimal PROC NEAR32
	; Entry code
	PUSH	ebp
	MOV		ebp, esp
	SUB esp, 8
	PUSH	ebx
	PUSH	ecx
	PUSH	edx
	PUSHFD

	MOV ebx, [ebp+8]
	MOV eax, [ebx]

	;;;
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

	MOV ecx, eax
	;
	;49=1
	;49=0
	MOV eax, [ebp+8]
MOV [ebp-8],eax
	LEA ebx, str2
	startLoop:





	CMP ecx, 0
	JE endLoop
	DEC ecx
	MOV eax, [ebp-8]
	MOV DL, [eax]
	INC eax
	MOV [ebp-8],eax


	CMP DL, 48
	JE is48

	CMP DL, 49
	JE is49

		CMP DL, 50
	JE is50

		CMP DL, 51
	JE is51

		CMP DL, 52
	JE is52

		CMP DL, 53
	JE is53

		CMP DL, 54
	JE is54

		CMP DL, 55
	JE is55

		CMP DL, 56
	JE is56

	CMP DL, 57
	JE is57

	CMP DL, 65
	JE is65

		CMP DL, 66
	JE is66

		CMP DL, 67
	JE is67

		CMP DL, 68
	JE is68

		CMP DL, 69
	JE is69

	CMP DL, 70
	JE is70

	JMP nada

	
	is48:
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
JMP startLoop
	is49:
	MOV eax, 48
		MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV eax, 49
	MOV [ebx], eax
	INC ebx
JMP startLoop
	is50:
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV eax, 49
	MOV [ebx], eax
	INC ebx
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
	JMP startLoop
	is51:
	MOV eax, 48
		MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV eax, 49
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
JMP startLoop
	is52:
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
	MOV eax, 49
	MOV [ebx], eax
	MOV eax, 48
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	JMP startLoop
	is53:
MOV eax, 48
	MOV [ebx], eax
	INC ebx
	MOV eax, 49
	MOV [ebx], eax
	INC ebx
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
	MOV eax, 49
	MOV [ebx], eax
	INC ebx


JMP startLoop
	is54:
MOV eax, 48
		MOV [ebx], eax
	INC ebx
	MOV eax, 49
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
JMP startLoop
	is55:
	MOV eax, 48
		MOV [ebx], eax
	INC ebx
		MOV eax, 49
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
JMP startLoop
	is56:
MOV eax, 49
		MOV [ebx], eax
	INC ebx
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
JMP startLoop
	is57:

	MOV eax, 49
		MOV [ebx], eax
	INC ebx
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV eax, 49
	MOV [ebx], eax
	INC ebx
JMP startLoop
	is65:

	MOV eax, 49
		MOV [ebx], eax
	INC ebx
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
	MOV eax, 49
	MOV [ebx], eax
	INC ebx
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
JMP startLoop
	is66:

	MOV eax, 49
		MOV [ebx], eax
	INC ebx
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
	MOV eax, 49
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
JMP startLoop
	is67:

	MOV eax, 49
		MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	
JMP startLoop
	is68:
	MOV eax, 49
		MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
	MOV eax, 49
	MOV [ebx], eax
	INC ebx

JMP startLoop
	is69:
MOV eax, 49
	
		MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV eax, 48
	MOV [ebx], eax
	INC ebx
JMP startLoop
	is70:
MOV eax, 49
			MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
	MOV [ebx], eax
	INC ebx
JMP startLoop
	nada:
	MOV eax, -1
	JMP endLoop2

	endLoop:
	
	LEA ebx, str2

	

	PUSH ebx

	
	CALL binStringToDecimal


	endLoop2:

	

	; Exit code
	POPFD
	POP		edx
	POP		ecx
	POP		ebx
	MOV		esp, ebp
	POP		ebp
	RET		4					; params are X bytes
hexStringToDecimal ENDP

end LibMain
