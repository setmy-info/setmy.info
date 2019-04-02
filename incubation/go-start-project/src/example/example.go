package example

import "fmt"

// Upercase - export that
type Person struct {
	FirstName string
	LastName string
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
	fmt.Println(Person{"Bob", "Meter 1"})
	fmt.Println(Person{FirstName: "Alice", LastName: "Meter 2"})
	fmt.Println(Person{FirstName: "Fred 3"})
	fmt.Println(&Person{FirstName: "Ann", LastName: "Meter 4"})
	s := Person{FirstName: "Sean", LastName: "Meter 5"}
    fmt.Println(s.FirstName)
    sp := &s
    fmt.Println(sp.LastName)
	sp.LastName = "Meter 6"
    fmt.Println(sp.LastName)
	return person
}