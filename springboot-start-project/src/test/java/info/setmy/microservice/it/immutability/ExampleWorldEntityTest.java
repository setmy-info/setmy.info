package info.setmy.microservice.it.immutability;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExampleWorldEntityTest {

    ExampleWorldEntityService exampleWorldEntityService = new ExampleWorldEntityService();

    @Test
    void myExampleWorldEntityInitialState() {
        ExampleWorldEntity exampleWorldEntity = ExampleWorldEntity.builder().build();
        assertThat(exampleWorldEntity.getFoo()).isNull();
        assertThat(exampleWorldEntity.getBar()).isNull();
        assertThat(exampleWorldEntity.getBaz()).isNull();
        assertThat(exampleWorldEntity.getCountries()).isNull();
        assertThat(exampleWorldEntity.getPlain()).isNull();
        assertThat(exampleWorldEntity.isDone()).isFalse();
    }

    @Test
    void test() {
        ExampleWorldEntity result = exampleWorldEntityService.allDoingMethod();
        assertThat(result).isNotNull();
        assertThat(result.getFoo()).isEqualTo("Foo!");
        assertThat(result.getBar()).isEqualTo(1234L);
        assertThat(result.getBaz()).isEqualTo(new BigDecimal("1234.56"));
        assertThat(result.getPlain()).isEqualTo("nfTy46+hAEA8LDDoCgCm9A==");
        assertThat(result.isDone()).isTrue();
    }

    @Test
    void foo_isPureFunction() {
        List<ExampleCountry> countries = new ArrayList<>();
        ExampleCountry country = ExampleCountry.builder().name("Utopia").build();
        countries.add(country);
        ExampleWorldEntity exampleWorldEntity = ExampleWorldEntity.builder()
            .foo("Like a foo")
            .countries(countries)
            .build();

        ExampleWorldEntity result = exampleWorldEntityService.foo(exampleWorldEntity);

        assertThat(result).isNotNull();
        assertThat(exampleWorldEntity.getFoo()).isEqualTo("Like a foo");// Does not change foo value for param
        assertThat(result).isNotSameAs(exampleWorldEntity);// Not the same as input, new object is created
        assertThat(result.getFoo()).isEqualTo("Foo");// foo method logic set value is set correctly
        assertThat(result.getCountries()).isSameAs(countries);// Lists are still same - reference is copied like any other value
        assertThat(result.getCountries().getFirst()).isSameAs(country); // Same element in list
        assertThat(result.isDone()).isFalse();
    }
}
