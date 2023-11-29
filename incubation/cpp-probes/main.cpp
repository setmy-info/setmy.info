#include <iostream>
#include <memory>

using namespace std;

int global = 1;

class Example {
    public:
    int a;
    // Ctor
    Example() : a(0) {
	cout << "Example() : " << this << endl;
    }
    // Ctor
    Example(int a) : a(a) {
	cout << "Example(a) : " << a << this << endl;
    }
    // CCtor
    Example(const Example& other) : a(other.a) {
        cout << "Example(const Example& other) : " << this << " from " << &other << endl;
    }
    // Move Ctor
    Example(Example&& other) noexcept : a(other.a) {
        other.a = 0;
        cout << "Example(Example&& other) : " << this << " from " << &other << endl;
    }
    // Dtor
    ~Example() {
	cout << "~Example() : " << this << endl;
    }
};

void one() {
    cout << "======== Begin ONE" << endl;
    Example e;
    Example e2(2);
    cout << &e  << " : e "  << endl;
    cout << &e2 << " : e2 " << endl;
    cout << "======== End ONE" << endl;
}

void two() {
    cout << "======== Begin TWO" << endl;
    Example *e = new Example();
    cout << e << " : e " << endl;
    delete e;
    cout << "======== End TWO" << endl;
}

void three() {
    cout << "======== Begin THREE" << endl;
    std::unique_ptr<Example> example_smart_pointer = std::make_unique<Example>();
    Example* rawPointer = example_smart_pointer.get();
    cout << &example_smart_pointer << " : example_smart_pointer" << ", with size: "  << sizeof(example_smart_pointer) << endl;
    cout << rawPointer << " : rawPointer"  << ", with size: "  << sizeof(*rawPointer) << endl;
    cout << "======== End THREE" << endl;
}

void four() {
    cout << "======== Begin FOUR" << endl;
    Example* e = new Example();
    std::unique_ptr<Example> example_smart_pointer(e);
    Example* rawPointer = example_smart_pointer.get();
    cout << &example_smart_pointer << " : example_smart_pointer" << ", with size: "  << sizeof(example_smart_pointer) << endl;
    cout << rawPointer << " : rawPointer"  << ", with size: "  << sizeof(*rawPointer) << endl;
    cout << "======== End FOUR" << endl;
}

int main(){
    int i = 0;
    int *j = new int;
    cout << &i << " : i with size: " << sizeof(i) << endl;
    cout << j  << " : j with size: " << sizeof(*j) << ", with pointer size: " << sizeof(j) << endl;
    cout << &global << " : global with size: " << sizeof(*j) << endl;
    one();
    two();
    three();
    four();
    delete j;
}
