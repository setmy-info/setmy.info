package main

import (
    "encoding/json"
    "fmt"
    "net/http"
)

type Message struct {
    // In lowercase that will not marshal
    Message string `json:"msg"`
}

func main() {
    http.HandleFunc("/", rootHandler)
    http.HandleFunc("/example/", exampleHandler)
    http.ListenAndServe(":8080", nil)
}

func rootHandler(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintf(w, "Hello World!")
}

func exampleHandler(w http.ResponseWriter, r *http.Request) {

    message := Message{Message: "Hello World!"}
    response, error := json.Marshal(message)
    if error != nil {
        panic(error)
    }
    w.Write(response)
}
