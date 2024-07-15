;	Author:     221003350 Ariel Sischy
;	Computer Science 3B - PE1
.386
.MODEL FLAT, stdcall
.STACK 4096

INCLUDE io.inc
.CODE

; LibMain function tests which action is currently beign performed
; The function returns if the DLL is loaded correctly or not.
; We avoid the complex logic here for simplicity.
LibMain proc instance:DWORD, reason:DWORD, unused:DWORD
	mov eax, 1
  ret
LibMain ENDP

aliquotSum PROC NEAR32
	; Entry code
	PUSH	ebp
	MOV		ebp, esp
	SUB esp, 4
	PUSH	ebx
	PUSH	ecx
	PUSH	edx
	PUSHFD
	
	MOV ecx, 0


	MOV eax,  [ebp+8]
	MOV [ebp-8], eax

	startLoop:
	MOV eax, [ebp-8]
	DEC eax
	MOV [ebp-8], eax

	MOV eax, [ebp+8]
	MOV ebx, [ebp-8]

	MOV edx, 0

	CDQ
	IDIV ebx



	CMP edx, 0
	JG skipflag
	ADD ecx, [ebp-8]


	skipflag:

	MOV eax, [ebp-8]


	CMP eax, 1

	JZ endLoop
	JNE startLoop
	endLoop:

	MOV eax, ecx
	; Exit code
	POPFD
	POP		edx
	POP		ecx
	POP		ebx
	MOV		esp, ebp
	POP		ebp
	RET		4					; params are X bytes
aliquotSum ENDP

classify PROC NEAR32
	; Entry code
	PUSH	ebp
	MOV		ebp, esp
	PUSH	ebx
	PUSH	ecx
	PUSH	edx
	PUSHFD
	
	MOV ecx, [ebp+8]

	PUSH [ebp+8]
	CALL aliquotSum

	CMP eax, ecx
	JG greateZero
	JZ zeroFlag
	JMP smallerZero

	greateZero:
	MOV eax, 1
	JMP endFlag

	zeroFlag:
	MOV eax, 0
	JMP endFlag

	smallerZero:
	MOV eax, -1

	endFlag:

	; Exit code
	POPFD
	POP		edx
	POP		ecx
	POP		ebx
	MOV		esp, ebp
	POP		ebp
	RET		4					; params are X bytes
classify ENDP

end LibMain
