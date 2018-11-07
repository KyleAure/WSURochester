package testing

import (
	"testing"
)

//tag::test[]

func add(a int, b int) int { //<1>
	return a + b
}

func TestAdd(t *testing.T) { //<2>
	var a = 5
	var b = 6
	var c = add(a, b)
	if c != 11 {
		t.Error("Expected ", 11, " got ", c) //<3>
	}
}

//end::test[]
