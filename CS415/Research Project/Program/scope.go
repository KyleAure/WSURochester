package main

import "fmt"

//tag::scope[]
var a = 1 //<1>

func main() {
	fmt.Println("Global scope, a is", a)

	var a = 2 //<2>
	fmt.Println("Local scope, a is", a)
	{
		var a = 3 //<3>
		fmt.Println("Inner scope, a is", a)
	}
}

//end::scope[]
