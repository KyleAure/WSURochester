package main

import "fmt"

var x int = 10
var y = 12

//z := 14   Not allowed for global variables

func main() {
	//tag::typing[]
	var x int = 5 //Static representation <1>
	var y = 6     //Dynamic representation <2>
	z := 7        //Implied representation <3>
	//end::typing[]

	y = '6'

	//If static typed y == 54 (ascii for '6')
	//If dynamic typed y == '6'

	fmt.Printf("x is %d, y is %d, and z is %d \n", x, y, z)
}
//ENDMAIN
