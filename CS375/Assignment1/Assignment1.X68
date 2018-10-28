*-----------------------------------------------------------
* Title      :  Assignment1.x68
* Written by :  Kyle Jon Aure
* Date       :  10/03/2018
* Description:  Program to multiply two positive integers using Egyptian multiplication.
*               A way to multiply using reptative addition where processing time is equivalent to O(n) = log(n)
* Registers  :  [D2] = M  [D3] = N  [D1] = PRODUCT
*-----------------------------------------------------------

* START PROGRAM
        ORG     $1000
START   MOVE    M,D2    ;D2 <- M
        MOVE    N,D3    ;D3 <- N
        CLR     D4      ;D1 <- #0
WHILE   CMP     #0,D2   ;WHILE (M!=0)
        BEQ     ENDW 
        ASR     #1,D2   ;D2 <- D2 / 2  C <- REMAINDER
        BCS     ADD     ;IF REMAINDER => M WAS ODD > BRANCH TO ADD
        BRA     NOADD   ;ELSE BRANCH TO NOADD
ADD     ADD     D3,D1   ;D1 <- D1 + D3 C <- CARRY
        BCS     OFLOW   ;IF CARRY => MULIPLICATIVE OVERFLOW > BRANCH TO OFLOW
        BVS     OFLOW   ;IF OVERFLOW => MULTIPLICATIVE OVERFLOW > BRACH TO OFLOW
NOADD   ASL     #1,D3   ;D3 <- D3 * 2 
        BRA     WHILE   ;LOOP
OFLOW   MOVE    #-1,D1  ;D1 <- #-1
ENDW    MOVE    D1,PRODUCT  ;PRODUCT <- [D1]

* OUTPUT RESULT
        EXT.L   D1
        MOVE    #3,D0
        TRAP    #15
        STOP    #$2700

* DECLARE VARIABLES
        ORG     $2000   ;DATA STARTS HERE
M       DC.W    122       ;FIRST INPUT
N       DC.W    503       ;SECOND INPUT
PRODUCT DS.W    1       ;OUTPUT
        END     START   ;END PROGRAM

        



*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
