package info.setmy.microservice.it.immutability;

import java.math.BigDecimal;

import static info.setmy.microservice.it.immutability.CryptoService.getInstance;

public class ExampleWorldEntityService {

    private final CryptoService cryptoService = getInstance();

    public ExampleWorldEntity allDoingMethod() {
        return allDoingMethod(ExampleWorldEntity.builder().build());
    }

    public ExampleWorldEntity allDoingMethod(ExampleWorldEntity exampleWorldEntity) {
        ExampleWorldEntity result = crypt(plain(baz(bar(foo(exampleWorldEntity))))).toBuilder()
            .done(true)
            .build();
        return result
            .withFoo(result.getFoo() + "!")
            /*.withPlain(cryptoService.encrypt(result.getPlain()))*/;
    }

    public ExampleWorldEntity foo(ExampleWorldEntity exampleWorldEntity) {
        return exampleWorldEntity.toBuilder()
            .foo("Foo")
            .build();
    }

    public ExampleWorldEntity bar(ExampleWorldEntity exampleWorldEntity) {
        return exampleWorldEntity.toBuilder()
            .bar(1234L)
            .build();
    }

    public ExampleWorldEntity baz(ExampleWorldEntity exampleWorldEntity) {
        return exampleWorldEntity.toBuilder()
            .baz(new BigDecimal("1234.56"))
            .build();
    }

    public ExampleWorldEntity plain(ExampleWorldEntity exampleWorldEntity) {
        return exampleWorldEntity.toBuilder()
            .plain("Plain text")
            .build();
    }

    private ExampleWorldEntity crypt(ExampleWorldEntity exampleWorldEntity) {
        return exampleWorldEntity.toBuilder()
            .plain(
                cryptoService.encrypt(exampleWorldEntity.getPlain())
            )
            .build();
    }
}
