
all: hello

hello: hello.o
	gfortran -s -o hello hello.o

hello.o:
	gfortran -s -c hello.f90

clean:
	rm -f hello.o

run:
	./hello

.PHONY: all clean run
