package example

import "fmt"
import "os"

type Person struct {
	FirstName string
	LastName string
}

func main() {
	fmt.Println("Hello, 世界")
	os.Exit(0)
}

func foo(name string) int {
	var expected = "Hello World!"
	if name == expected {
		return 1
	}
	return 0
}

func stringing() int {
	var str = "Hello World!"
	got := foo(str) // nil not allowed
	return got
}

func structing() *Person {
	person := new(Person)
	person.FirstName = "Peeter-Meeter"
	person.LastName = "Sentimeeter"
	fmt.Println(Person{"Peeter", "Meeter 1"})
	fmt.Println(Person{FirstName: "Petrof", LastName: "Meeter 2"})
	fmt.Println(Person{FirstName: "Meeter 3"})
	fmt.Println(&Person{FirstName: "Piter", LastName: "Meeter 4"})
	s := Person{FirstName: "Senti", LastName: "Meeter 5"}
    fmt.Println(s.FirstName)
    sp := &s
    fmt.Println(sp.LastName)
	sp.LastName = "Meter 6"
    fmt.Println(sp.LastName)
	return person
}