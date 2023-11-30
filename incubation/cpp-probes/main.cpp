#include <iostream>
#include <memory>

using namespace std;

int global = 1;
const int constant = 112233;

class Example {
public:
    int a;

    // ctor
    Example() : a(0) {
        cout << "ctor Example() : " << this << endl;
    }

    // ctor
    Example(int a_value) : a(a_value) {
        cout << "ctor Example(a) : " << a << " " << this << endl;
    }

    // cctor
    Example(const Example &other) : a(other.a) {
        cout << "cctor Example(const Example& other) : " << this << " from " << &other << endl;
    }

    // Move ctor (mctor)
    Example(Example &&other) noexcept: a(other.a) {
        other.a = 0;
        cout << "mctor Example(Example&& other) : " << this << " from " << &other << endl;
    }

    // dtor
    ~Example() {
        cout << "dtor ~Example() : " << this << endl;
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
    cout << "l=" << &l << " example=" << &example << "k=" << &k << endl;
    cout << "======== End get_example_by_variable" << endl;
    return example;
}

void five() {
    cout << "======== Begin FIVE" << endl;
    int i;
    const Example example = get_example_return_by_value();
    int j;
    cout << "j=" << &j << " example=" << &example << " i=" << &i << endl;
    cout << "======== End FIVE" << endl;
}

Example *get_example_return_by_pointer() {
    cout << "======== Begin get_example_return_by_pointer" << endl;
    Example *example = new Example(123);
    cout << "======== End get_example_return_by_pointer" << endl;
    return example;
}

void five_two() {
    cout << "======== Begin FIVE TWO" << endl;
    std::unique_ptr<Example> example_smart_pointer(get_example_return_by_pointer());
    cout << "======== End FIVE TWO" << endl;
}

Example &get_new_example_return_by_reference() {
    cout << "======== Begin get_example_return_by_pointer" << endl;
    Example *example = new Example(123);
    cout << "======== End get_example_return_by_pointer" << endl;
    return *example;
}

void five_three() {
    cout << "======== Begin FIVE THREE" << endl;
    // Most cases returning reference means that function in returning static or global value reference and that should not be released. Therefore, that is not good practice.
    std::unique_ptr<Example> example_smart_pointer(&get_new_example_return_by_reference());
    cout << "======== End FIVE THREE" << endl;
}

Example &get_example_return_by_reference() {
    cout << "======== Begin get_example_by_variable" << endl;
    int k;
    Example example(123);
    int l;
    cout << "l=" << &l << " example=" << &example << "k=" << &k << endl;
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
    cout << "j="
         << &j
         << " example="
         << &example // example address is 0, so getting segmentation fault on calling anything on that instance.
         << " i="
         << &i
         << endl;
    cout << "======== End SIX" << endl;
}

class MyException : public std::exception {
public:
    // ctor
    MyException() {
        cout << "ctor MyException() : " << this << endl;
    }

    // cctor
    MyException(const MyException &other) {
        cout << "cctor MyException(const MyException& other) : " << this << " from " << &other << endl;
    }

    // mctor
    MyException(MyException &&other) noexcept {
        cout << "mctor MyException(MyException&& other) : " << this << " from " << &other << endl;
    }

    // dtor
    ~MyException() {
        cout << "dtor ~MyException() : " << this << endl;
    }

    // Copy operator
    MyException &operator=(const MyException &other) {
        cout << "operator = : " << this << " from " << &other << endl;
        return *this;
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
    cout << "l=" << &l << " example=" << &example << " k=" << &k << endl;
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

struct A {
    char a;
};

struct B {
    char a;
    char b;
};

struct C {
    char a;
    char b;
    char c;
};

struct D {
    char a;
    char b;
    char c;
    char d;
};

struct E {
    char a;
    short b;
    int c;
};

void eight() {
    cout << "Size of char=" << sizeof(char) << endl;
    cout << "Size of short=" << sizeof(short) << endl;
    cout << "Size of int=" << sizeof(int) << endl;
    cout << "Size of long=" << sizeof(long) << endl;
    cout << "Size of long long=" << sizeof(long long) << endl;
    cout << "Size of A=" << sizeof(A) << endl;
    cout << "Size of B=" << sizeof(B) << endl;
    cout << "Size of C=" << sizeof(C) << endl;
    cout << "Size of D=" << sizeof(D) << endl;
    cout << "Size of E=" << sizeof(E) << endl;
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
    five_two();
    five_three();
    six();
    seven();
    eight();
    delete j;
}
