package info.setmy.services.text.linguistics;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class ParsingInfo {

    private char character;
    private int position;
    private int lineNumber = 1;

    public char getCharacter() {
        return character;
    }

    public ParsingInfo setCharacter(char character) {
        this.character = character;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public ParsingInfo setPosition(int position) {
        this.position = position;
        return this;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public ParsingInfo setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
        return this;
    }

    public ParsingInfo incrementLineNumber() {
        lineNumber++;
        return this;
    }
}
