
all: clean diff

clean:
	rm -f ./patch/diff.patch
	cp -f ./a/test.txt ./c/test.txt
	cp -f ./a/test2.txt ./c/test2.txt

diff: ./patch/diff.patch

./patch/diff.patch:
	diff -ruN ./a ./b > $@; echo ""

patching:
	patch -s -p0 < ./patch/diff.patch

.PHONY: all clean diff patching
