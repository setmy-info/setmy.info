package info.setmy.example.starter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Getter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FooService {

    @Getter
    private static FooService instance;

    public static synchronized FooService getInstance() {
        if (instance == null) {
            instance = new FooService();
        }
        return instance;
    }

    public String sayHello() {
        return "Hello from FooService singleton!";
    }
}
