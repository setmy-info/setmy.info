package muffin

import (
	"fmt"
)

var a int32 = 5
const b int32 = 6

func all() {
	foo()
	externalPointers()
}

func foo()  {
	i, j := 42, 2701

	p := &i
	fmt.Println(*p)
	fmt.Println(p)
	*p = 21
	fmt.Println(i)

	p = &j
	*p = *p / 37
	fmt.Println(j)
}

func externalPointers() {
	fmt.Println("== externalPointers Begin ==")
	var x int32 = 1
	var y int32 = 4
	const z int32 = 5
	fmt.Println(&a)
	fmt.Println(&x)
	fmt.Println(&y)	//fmt.Println(&z) - can't show const pointers //fmt.Println(&b)
	fmt.Println(b)

	//var po uintptr = (uintptr) &x
	fmt.Println("== externalPointers End ==")
}
