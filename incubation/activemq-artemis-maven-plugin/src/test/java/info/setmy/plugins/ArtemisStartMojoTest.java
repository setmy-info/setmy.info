package info.setmy.plugins;


import org.apache.maven.plugin.testing.WithoutMojo;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArtemisStartMojoTest {

    @BeforeEach
    protected void before() {
    }

    @AfterEach
    protected void after() {
    }

    @Test
    public void testSomething() {
        assertTrue(true);
    }

    @WithoutMojo
    @Test
    public void testSomethingWhichDoesNotNeedTheMojoAndProbablyShouldBeExtractedIntoANewClassOfItsOwn() {
        assertTrue(true);
    }
}

