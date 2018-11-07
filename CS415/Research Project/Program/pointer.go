package main

import "fmt"
//tag::pointer[]
func print(i *int) {fmt.Println(*i);} //<1>

func main() {
  j := 5
  print(&j) //<2>
}
//end::pointer[]
