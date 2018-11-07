package main

import "fmt"
//tag::function[]
func split(sum int) (x, y int) {
	x = sum * 4 / 9
	y = sum - x
	return
}

type DogFunc func(string, int)

func main() {
	fmt.Println(split(17))

  DogFuncs := []DogFunc {
    func(name string, age int){fmt.Printf("Name: %s, Age: %d\n", name, age)},
    func(name string, age int){fmt.Printf("Age: %d, Name: %s\n", age, name)},
  }

  DogFuncs[0]("Max", 12)
  DogFuncs[1]("Toby", 6)
}
//end::function[]
