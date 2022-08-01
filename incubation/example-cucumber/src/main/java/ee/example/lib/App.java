package ee.example.lib;

import static java.lang.Integer.parseInt;

public class App {

    private final MathService mathService;

    private int result;

    public App(final MathService mathService) {
        this.mathService = mathService;
    }

    public static void main(final String[] args) {
        final App app = new App(new MathService());
        app.doMain(args);
    }

    public void doMain(final String[] args) {
        if (args.length < 2) {
            System.out.println("Provide 2 numbers");
            return;
        }
        this.result = mathService.add(
            parseInt(args[0]),
            parseInt(args[1])
        );
        System.out.println("Math, sum is: " + result);
    }

    public int getResult() {
        return result;
    }
}
