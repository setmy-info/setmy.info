package main

import "testing"

func TestExample1(t *testing.T) {
    got := 1
        if got != 1 {
        t.Errorf("got = %d; want 1", got)
    }
}

func TestExample2(t *testing.T) {
    got := 1
        if got != 1 {
        t.Errorf("got = %d; want 1", got)
    }
}
