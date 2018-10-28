*-----------------------------------------------------------
* Title       : ASSIGNMENT2.X68
* Written by  : KYLE JON AURE
* Date        : 10/23/2018
* Description : PROGRAM TO PERFORM A SORT OF BYTE INTERGERS
*               IN ACENDING ORDER
* Sorting Algo: BUBBLE SORT
* Registers   :  A0 POINTS TO LIST
*                A1 POINTS TO ELEMENT
*                D0 COUNT (UNCHANGED)
*                D1 ELEMENT OF ARRAY (y)
*                D2 COUNT ITERATIONS LEFT (x)
*                D3-D4 COMPARISONS
*-----------------------------------------------------------
START   ORG    $400
* SORTING CODE STARTS HERE
        LEA     $3000,A7    ;SET UP STACK POINTER
        LEA     LIST,A0     ;A0 POINTS TO LIST
        MOVE.B  COUNT,D0    ;D0 <- COUNT
        MOVE.B  D0,D2       ;x <- COUNT
        
ALOOP   SUB.B   #1,D2       ;FOR(x = COUNT-1; x >= 0; x--)
        BMI     ENDA        ;{
        MOVEA.L A0,A1       ;A1 <- A0
        MOVE.B  #1,D1       ;y <- 1
BLOOP   CMP.B   D2,D1       ;FOR(y = 1; y <= x; y++)
        BGT     ENDB        ;{
        MOVE.B  (A1)+,D3    ;D3 <- LIST[y]
        MOVE.B  (A1),D4     ;D4 <- LIST[y+1]
        CMP.B   D3,D4       ;IF(y+1 > y)
        BGE     NOSWAP      ;{   
        MOVE.L  A1,-(A7)    ;PUSH ADDRESS OF y+1 
        BSR     SWAP        ;CALL SWAP(y)
        LEA     4(A7),A7    ;CLEAN UP STACK
NOSWAP  ADD.B   #1,D1       ;}END IF and INCREMENT y
        BRA     BLOOP      
ENDB    BRA     ALOOP       ;}END INNER LOOP
ENDA                        ;}END LOOP



* CODE TO OUTPUT DATA     
        MOVE.B  COUNT,D7    ;Size of Array     
        LEA     LIST,A0     ;A0 points to array
LOOP    MOVE.B  (A0)+,D1    ;Put value to display into D1        
        EXT.W   D1          ;Sign extended D1 to word        
        EXT.L   D1          ;  And then to longword        
        MOVE    #3,D0       ;Task number 3 (display [D1])        
        TRAP    #15         ;Display [D1]
        BSR     NEWLINE     ;Display "/n"
        SUB     #1,D7       ;Decrement
        BNE     LOOP        ;Loop
        STOP    #$2700      ;End program
         
* NAME: SWAP

* FUNCTION: TEMP = i, i = j, j = TEMP
* USES: D0 = TEMP AND A0 = ADDRESS OF i
SWAP    MOVEM.L D0/A0,-(A7) ;BACK UP D0 AND A0 (+8)
        MOVEA.L 12(A7),A0   ;A0 <- POINTER TO i
        MOVE.B  -(A0),D0    ;D0 <- i 
        MOVE.B  1(A0),(A0)  ;i = j
        MOVE.B  D0,1(A0)    ;j = i
        MOVEM.L (A7)+,D0/A0 ;RESTORE D0 AND A0
        RTS                 ;RETURN
        
* SUBROUTINE TO DISPLAY CARRIAGE RETURN AND LINE FEED
NEWLINE MOVEM.L D0/A1,-(A7) ;Push D0 & A1
        MOVE    #14,D0      ;Task number 14 (display null-ended string)
        LEA     CRLF,A1     ;Put address of string in A1
        TRAP    #15         ;Display return, linefeed
        MOVEM.L (A7)+,D0/A1 ;Restore D0 & A1
        RTS                 ;Return

* VARIABLES AND CONSTANTS
        ORG $2000
***DATA1
*COUNT   DC.B    4
*LIST    DC.B    2,9,5,3


***DATA2
*COUNT  DC.B    8
*LIST   DC.B    1,2,5,4,7,5,4,2


***DATA3
*COUNT  DC.B    9
*LIST   DC.B    82,73,64,55,46,37,28,4,2

***DATA4
COUNT  DC.B    9
LIST   DC.B    5,10,-3,0,-1,6,9,-2,22

***DATA5
*COUNT  DC.B    10
*LIST   DC.B    1,1,2,3,5,8,13,34,55,89

***DATA5


CRLF    DC.B    $D,$A,0
        END     START







*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
