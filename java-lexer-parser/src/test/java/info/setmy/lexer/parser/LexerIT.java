package info.setmy.lexer.parser;

import org.junit.jupiter.api.Test;

public class LexerIT {

    @Test
    public void parse() {
        String fileName = "./src/test/resources/ExampleClassForTest.java";
        /*
        GroovyLexer lexer = new GroovyLexer(CharStreams.fromFileName(fileName));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GroovyParser parser = new GroovyParser(tokens);
        ParseTree tree = parser.compilationUnit();
        System.out.println(tree.toStringTree(parser));
        */
    }
}
