class Greeter {
    greeting: string;
    constructor(message: string) {
        this.greeting = message;
    }
    greet() {
        return "Hello, world" + this.greeting;
    }
}

let greeter = new Greeter("world");
