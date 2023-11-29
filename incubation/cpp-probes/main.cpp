#include <iostream>
#include <memory>

using namespace std;

int global = 1;
const int constant = 112233;

class Example {
public:
    int a;

    // Ctor
    Example() : a(0) {
        cout << "Example() : " << this << endl;
    }

    // Ctor
    Example(int a_value) : a(a_value) {
        cout << "Example(a) : " << a << " " << this << endl;
    }

    // CCtor
    Example(const Example &other) : a(other.a) {
        cout << "Example(const Example& other) : " << this << " from " << &other << endl;
    }

    // Move Ctor
    Example(Example &&other) noexcept: a(other.a) {
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
    cout << &e << " : e " << endl;
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
    Example *rawPointer = example_smart_pointer.get();
    cout << &example_smart_pointer << " : example_smart_pointer" << ", with size: " << sizeof(example_smart_pointer)
         << endl;
    cout << rawPointer << " : rawPointer" << ", with size: " << sizeof(*rawPointer) << endl;
    cout << "======== End THREE" << endl;
}

void four() {
    cout << "======== Begin FOUR" << endl;
    Example *e = new Example();
    std::unique_ptr<Example> example_smart_pointer(e);
    Example *rawPointer = example_smart_pointer.get();
    cout << &example_smart_pointer << " : example_smart_pointer" << ", with size: " << sizeof(example_smart_pointer)
         << endl;
    cout << rawPointer << " : rawPointer" << ", with size: " << sizeof(*rawPointer) << endl;
    cout << "======== End FOUR" << endl;
}

Example get_example_return_by_value() {
    cout << "======== Begin get_example_by_variable" << endl;
    int k;
    Example example(123);
    int l;
    cout << "k=" << &k << " l=" << &l << " example=" << &example << endl;
    cout << "======== End get_example_by_variable" << endl;
    return example;
}

void five() {
    cout << "======== Begin FIVE" << endl;
    int i;
    Example example = get_example_return_by_value();
    int j;
    cout << "i=" << &i << " j=" << &j << " example=" << &example << endl;
    cout << "======== End FIVE" << endl;
}

Example &get_example_return_by_reference() {
    cout << "======== Begin get_example_by_variable" << endl;
    int k;
    Example example(123);
    int l;
    cout << "k=" << &k << " l=" << &l << " example=" << &example << endl;
    cout << "======== End get_example_by_variable" << endl;
    // Returns address of example, but that is function local variable (from stack and disappears after return).
    // Can return global or function static variables (non thread safe).
    return example; // warning: reference to local variable ‘example’ returned [-Wreturn-local-addr]
}

void six() {
    cout << "======== Begin SIX" << endl;
    int i;
    Example &example = get_example_return_by_reference();
    int j;
    cout << "i=" << &i << " j=" << &j << " example="
         << &example // example address is 0, so getting segmentation fault on calling anything on that instance.
         << endl;
    cout << "======== End SIX" << endl;
}

class MyException : public std::exception {
public:
    // Ctor
    MyException() {
        cout << "MyException() : " << this << endl;
    }

    // CCtor
    MyException(const MyException &other) {
        cout << "MyException(const MyException& other) : " << this << " from " << &other << endl;
    }

    // Move Ctor
    MyException(MyException &&other) noexcept {
        cout << "MyException(MyException&& other) : " << this << " from " << &other << endl;
    }

    // Dtor
    ~MyException() {
        cout << "~MyException() : " << this << endl;
    }

    const char *what() const noexcept override {
        return "My custom exception";
    }
};

void function_that_throws_custom_exception() {
    cout << "======== Begin function_that_throws_custom_exception" << endl;
    int k;
    Example example(321);
    int l;
    cout << "k=" << &k << " l=" << &l << " example=" << &example << endl;
    throw MyException();
    cout << "======== Begin function_that_throws_custom_exception" << endl;
}

void seven() {
    cout << "======== Begin SEVEN" << endl;
    int i;
    try {
        function_that_throws_custom_exception();
    } catch (const MyException &e) {
        cerr << "Caught MyException: " << e.what() << " e=" << &e << " i=" << &i << endl;
    }
    cout << "======== End SEVEN" << endl;
}

/**
 * Conclusion
 * 1. Return class instances by value (return by value) like in five() and caller should use const for object got
 * from function to avoid class copy. Compiler optimizes and uses caller instance.
 * 2. Or return pointer and put it immediately into smart pointer, to be released when exiting scope.
 * 3. Exceptions are in heap. Throw Exception classes and catch by reference.
 * */
int main() {
    int i = 0;
    int *j = new int;
    cout << &i << " (in stack): i with size: " << sizeof(i) << endl;
    cout << j << " (in heap): j with size: " << sizeof(*j) << ", with pointer size: " << sizeof(j) << endl;
    cout << &global << " (in data block): global with size: " << sizeof(global) << endl;
    cout << &constant << " (in data block): global constant with size: " << sizeof(constant) << endl;
    one();
    two();
    three();
    four();
    five();
    six();
    seven();
    delete j;
}
