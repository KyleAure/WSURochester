*------------------------------------------------------------------------------- 
* Title         : Assignment3.x68
* Written by    : Kyle Jon Aure
* Date          : 11/18/18
* Description   : Recursive implentation of Egyption Muliplication
* Registers used and purposes:  [D4] = m, [D5] = n, Product = [D0]
*                               [A7] = StackPointer, [A0] = FramePointer
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
*   | Local TM = M/2   |-4(A0)       TN = 2 * N
*   |------------------|             TM = M / 2
*   | Local TN = 2*N   |-2(A0)     IF M is even THEN
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
*#### Inputs and Outputs ####
         ORG      $200
Input1   DC.W      5        ;Input1 <- m
Input2   DC.W      2        ;Input2 <- n
PROD     DS.W      1        ;m*n if no overflow, -1 otherwise

*### Main Program ####
START    ORG      $400
         LEA      $2000,A7          ;Set up stack pointer
         MOVE.W   Input1,-(A7)      ;Push m (-2)
         MOVE.W   Input2,-(A7)      ;Push n (-2)
         BSR      EGP               ;CALL egp(m,n)
         LEA      4(A7),A7          ;Cleanup stack

*### Output Results ###
         MOVE.W   PROD,D1           ;Move RPOD to D1 to be displayed
         MOVE     #3,D0             ;Move directive #3 to D0
         TRAP     #15               ;Display D1
         STOP     #$2700


*------------------------------------------------------------------------------- 
* Subroutine : egp
* Written by : Kyle Jon Aure

* Date       : 11/18/18
* Description: Recursive Subroutine for 
* Registers used and purposes: [D0] = result of this iteration
*-------------------------------------------------------------------------------
EGP                                 ;Store return address on stack (-4)
         LINK      A0,#-4           ;Create FramePointer
         MOVEM.L   D0,-(A7)         ;Save register D0
TN       EQU       -2               ;Location of TN relative to A0
TM       EQU       -4               ;Location of TM relative to A0 
N        EQU       8                ;Location of N relative to A0 
M        EQU       10               ;Location of M relative to A0 
* Temporary code to make sure everything else works correctly         
         MOVE.W    N(A0),D0         ;[D0] <- N
         MULU      M(A0),D0         ;[D0] <- M*N
         MOVE.W    D0,PROD          ;(PROD) <- [D0]
         MOVEM.L   (A7)+,D0         ;Restore D0  
         UNLK      A0               ;Return StackPointer to A0
         RTS                        ;End of Subroutine EGP
         

         END       START

*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
