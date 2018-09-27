*-----------------------------------------------------------
* Title      :  FIRST PROGRAM
* Written by :  KYLE JON AURE
* Date       :  SEP 24 2018
* Description:  TRYING OUT EXAMPLE FROM TEST BOOK TO SEE
*               HOW IT WORKS
*-----------------------------------------------------------
        ORG     $1000
* PROGRAM STARTS HERE
START:  MOVE    P,D0    ; D0 <- P
        ADD     Q,D0    ; D0 <- D0 + Q
        MOVE    D0,R    ; R  <- D0
            
        MOVE    R,D1    ; D1 <- R
        MOVE    #3,D0   ; D0 <- 3 (SERVICE 3 FROM M68K)
        TRAP    #15     ; RUN SERVICE IN D0
            
        STOP    #$2700  ; STOP PROGRAM
            
* VARIABLES AND CONSTANTS START HERE
        ORG     $2000

P       DC.W    2
Q       DC.W    4
R       DS.W    1            
            
        END     START   ; last line of source
*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
