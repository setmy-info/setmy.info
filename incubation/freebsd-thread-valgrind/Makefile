all:
	rm -f ./program
#	c++ main.cpp -lpthread -o program
	c++ main.cpp -pthread -o program
	ldd ./program
	valgrind --leak-check=full --show-leak-kinds=all --track-origins=yes --verbose --log-file="leak.out" ./program
	freebsd-version
