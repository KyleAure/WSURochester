*------------------------------------------------------------------------------- 
* Title         : Assignment3.x68
* Written by    : Kyle Jon Aure
* Date          : 11/21/18
* Description   : Recursive implentation of Egyption Muliplication
* Registers used and purposes:  [D0] = Product [D4] = Input1  [D5] = Input2
*                               [A7] = StackPointer
*-------------------------------------------------------------------------------
* Recurrence of Egyptian Multiplication:
*     BASE CASE:   egp(m, n) = 0, if m = 0 
*     Recursion 1: egp(m/2, 2n), if m is even
*     Recursion 2: egp(m/2, 2n) + n, if m is odd
*
* ACTIVATION RECORD and STACK FRAME  Algorithm (without handling overflow):
*   --------------------
*   | Saved Registers  |(A7)      EGP(M, N) 
*   |------------------|             IF M = 0 THEN RETURN 0  // RET. VAL. = 0
*   | Local TN = M*2   |-4(A0)       TN = 2 * N
*   |------------------|             TM = M / 2
*   | Local TM = M/2   |-2(A0)     IF M is even THEN
*   |------------------|             RETURN EGP(TM, TN)
*   | [A0]             |(A0)       ELSE
*   |------------------|             RETURN EGP(TM, TN) + N
*   | RETURN ADDRESS   |4(A0)
*   |------------------|
*   | INPUT PARAMETER N|8(A0)
*   |------------------|
*   | INPUT PARAMETER M|10(A0)
*    -------------------
*-------------------------------------------------------------------------------
*#### INPUTS AND OUTPUTS ####
         ORG      $200
Input1   DC.W     2     ;Input1
Input2   DC.W     16000        ;Input2
PROD     DS.W     1        ;M*N if no overflow, -1 otherwise
*### MAIN PROGRAM ####
START    ORG      $400
         LEA      $2000,A7          ;Set up stack pointer
         MOVE.W   Input1,D4         ;[D4] <- Input1
         MOVE.W   Input2,D5         ;[D5] <- Input2
         CMP.W    D5,D4             ;IF Input1 <= Input2
         BLE      PUSH              ;    Push values as is
         EXG      D4,D5             ;ELSE Exchange D4 <-> D5 so M is the least of the two.
PUSH     CLR      D0                ;[D0] <- 0
         MOVE.W   D4,-(A7)          ;Push M (-2) 
         MOVE.W   D5,-(A7)          ;Push N (-2)
         BSR      EGP               ;CALL EGP(M,N)
         LEA      4(A7),A7          ;Cleanup stack
*### PRINT RESULTS ###
         MOVE.W   D0,D1             ;Move D0 to D1 to be displayed
         EXT.L    D1                ;Extend D1 to Longword
         MOVE     #3,D0             ;Move directive #3 to D0
         TRAP     #15               ;Display D1
         STOP     #$2700
*------------------------------------------------------------------------------- 
* Subroutine : EGP - Egyption Multiplication
* Written by : Kyle Jon Aure
* Date       : 11/21/18
* Description: Recursive Subroutine for egyption multiplication 
* Registers used and purposes: [D0] = result of this iteration
*                              [D1] = temp holder for addition
*                              [A0] = frame pointer
*-------------------------------------------------------------------------------
EGP                                 ;Store return address on stack (-4)
         CMP.W     #-1,D0           ;If D0 == -1
         BEQ       OFLOW            ;   Overflow happened in previous iternation.
         LINK      A0,#-4           ;Create FramePointer and define space for 2 Words
*### SYMBOLIC OFFSETS ###
TM       EQU       -2               ;Location of TM relative to A0
TN       EQU       -4               ;Location of TN relative to A0 
N        EQU       8                ;Location of N relative to A0 
M        EQU       10               ;Location of M relative to A0 
*### BASE CASE ###
         CMP       #0,M(A0)         ;IF M == 0
         BEQ       RETURN           ;     Return [D0]
         MOVE.W    M(A0),TM(A0)     ;Copy M to TM
         MOVE.W    N(A0),TN(A0)     ;Copy N to TN
         ASL.W     TN(A0)           ;TN <- N*2
         ASR.W     TM(A0)           ;TM <- M/2 and set CCR(C) if M is Odd
         BCS       ODD              ;IF    CCR(C) was set branch to ODD
         BRA       EVEN             ;ELSE  Branch to EVEN
*### M IS ODD ###
ODD      MOVE.W    N(A0),D1         ;[D1] <- N and set CCR(N) if N is Negative 
         BMI       CATCHV           ;IF   CCR(N) then previous addition or ASR overflowed Branch to CATCHV 
         ADD.W     D1,D0            ;ELSE [D0] <- [D0] + N
         ;Continue onto Recursion                
*### M IS EVEN ###
EVEN     ;Continue onto Recursion      
*### RECURSION ###
*TM and TN are already on the stack in the correct order
         BSR       EGP              ;Call EGP(TM,TN)          
RETURN   UNLK      A0               ;Return StackPointer to A0 and discard 
         RTS                        ;End of Subroutine EGP        
*### OVERFLOW ###
CATCHV   MOVE.W    D0,(A7)+         ;[D0] <- Prev[D0]
         MOVE.W    #-1,D0
         UNLK      A0
OFLOW    RTS 
*--------------------END SUBROUTINE-------------------------------         

*END OF PROGRAM
         END       START


*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
