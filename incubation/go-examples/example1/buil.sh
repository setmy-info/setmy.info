GOPATH=`pwd`
go build hello-world.go
strip ./hello-world
go run hello-world.go
go test
./hello-world
