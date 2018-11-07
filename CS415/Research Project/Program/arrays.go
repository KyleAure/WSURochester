package main

import "fmt"

func main() {
	//tag::array[]
	var a [5]int                             //<1>
	a[1] = 5                                 //<2>
	b := [5]float32{1.2, 1.3, 1.4, 1.5, 1.6} //<3>
	var c [2][3]int                          //<4>
	//end::array[]

	fmt.Println("Array a : ", a, "\n")
	fmt.Println("Array b : ", b, "\n")
	fmt.Println("Array c : ", c, "\n")
}
