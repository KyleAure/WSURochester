*-----------------------------------------------------------
* Title      :  Assignment1.x68
* Written by :  Kyle Jon Aure
* Date       :  10/03/2018
* Description:  If [P] is even output 6 else output 5
* Registers  :  D0: used for P
*-----------------------------------------------------------
        ORG     $1000
START   MOVE    P,D0
        MOVE    #5,D1
        AND     #1,D0
        BNE     DONE
        MOVE    #6,D1
DONE

* OUTPUT
        EXT.L   D1
        MOVE    #3,D0
        TRAP    #15
        STOP    #$2700

* VARIABLES
        ORG     $2000
P       DC.W    2
        END     START        ; last line of source
    
    

*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
