package info.setmy.io.lib;

public class App {

    private int result;

    public static void main(final String[] args) {
        final App app = new App();
        app.doMain(args);
    }

    public void doMain(final String[] args) {
        if (args.length < 2) {
            System.out.println("Provide 2 numbers");
            return;
        }
        this.result = 0;
        // TODO

        this.result = Integer.parseInt(args[0]) + Integer.parseInt(args[1]);

        System.out.println("Math, sum is: " + result);
    }

    public int getResult() {
        return result;
    }
}
