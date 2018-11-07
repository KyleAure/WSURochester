package main

import "fmt"

func main () {
  //tag::structure[]
  type Dog struct {Name string; Breed string;} //<1>
  d := Dog{Name : "Max", Breed : "Pit Bull"}  //<2>
  fmt.Println("My dog's name is ", d.Name)     //<3>
  //end::structure[]
}
