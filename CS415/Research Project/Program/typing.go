package main
import "fmt"

func main() {
  var x int = 5 //Static representation
  var y = 6     //Dynamic represetnation
  y = '6'

  //If static y == 54 (ascii for '6')
  //If dynamic y == '6'

  fmt.Printf("x is %d, y is ?? %d", x, y)
}
