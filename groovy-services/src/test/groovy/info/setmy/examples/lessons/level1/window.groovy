import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class HelloWorld extends Application {

    void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!")
        def btn = new Button()
        btn.setText("Say 'Hello World'")
        btn.setOnAction({
            event -> System.out.println("Hello World!")
        })

        def root = new StackPane()
        root.getChildren().add(btn)
        primaryStage.setScene(new Scene(root, 300, 250))
        primaryStage.show()
    }

    static void main(String[] args) {
        launch(HelloWorld, args)
    }
}