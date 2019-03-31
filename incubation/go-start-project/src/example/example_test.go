package example

import (
    "testing"
    "fmt"
)

func TestStructing(t *testing.T) {
	person := structing()
	fmt.Println(person)
}

func TestStringing(t *testing.T) {
	var got = stringing()
	if got != 1 {
        t.Errorf("foo('Hello World') = %d; want 1", got)
    }
}


