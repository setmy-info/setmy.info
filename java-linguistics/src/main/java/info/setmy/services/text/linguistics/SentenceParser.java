package info.setmy.services.text.linguistics;

import info.setmy.services.text.linguistics.model.Block;
import info.setmy.services.text.linguistics.model.Clause;
import info.setmy.services.text.linguistics.model.Quote;
import info.setmy.services.text.linguistics.model.Sentence;
import info.setmy.services.text.linguistics.model.TextItem;
import info.setmy.services.text.linguistics.model.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class SentenceParser {

    private final Detection detection = new Detection();

    private Sentence currentSentence;
    private Clause currentClause;
    private Block currentBlock;
    private Quote currentQuote;
    private Word currentWord;
    private final List<TextItem> sentences = new ArrayList<>();

    public int parse(final String text) {
        init();
        if (text == null) {
            return 0;
        }
        final char[] characters = text.toCharArray();
        return handleCharacters(characters);
    }

    private int handleCharacters(final char[] characters) {
        int position = 0;
        final ParsingInfo parsingInfo = new ParsingInfo();
        for (; position < characters.length; position++) {
            parsingInfo.setCharacter(characters[position]);
            parsingInfo.setPosition(position);
            handleCharacter(parsingInfo);
        }
        return position - 1;
    }

    void handleCharacter(final ParsingInfo parsingInfo) {
        final char character = parsingInfo.getCharacter();
        if (detection.isLineSeparatorCharacter(character)) {
            lineSeparator(parsingInfo);
        } else if (detection.isSentenceSeparator(character)) {
            sentenceSeparator(parsingInfo);
        } else if (detection.isClauseSeparator(character)) {
            clauseSeparator(parsingInfo);
        } else if (detection.isBlockSeparatorBeginning(character)) {
            blockSeparatorBeginning(parsingInfo);
        } else if (detection.isBlockSeparatorEnd(character)) {
            blockSeparatorEnd(parsingInfo);
        } else if (detection.isPairedQuotesBeginning(character)) {
            quoteSeparatorBeginning(parsingInfo);
        } else if (detection.isPairedQuotesEnd(character)) {
            quoteSeparatorEnd(parsingInfo);
        } else if (detection.isUnPairedQuoteSeparator(character)) {
            singleQuoteSeparator(parsingInfo);
        } else if (detection.isWhiteCharacter(character)) {
            whiteSeparator(parsingInfo);
        } else {
            text(parsingInfo);
        }
    }

    private void init() {
        currentSentence = new Sentence();
        currentClause = new Clause();
    }

    private void lineSeparator(final ParsingInfo parsingInfo) {
        parsingInfo.incrementLineNumber();
        finishCurrentWord(parsingInfo);
    }

    private void sentenceSeparator(final ParsingInfo parsingInfo) {
        finishCurrentWord(parsingInfo);
        if (canFinishSentence(parsingInfo)) {
            finishCurrentSentence(parsingInfo);
        }
    }

    private void clauseSeparator(final ParsingInfo parsingInfo) {
        finishCurrentWord(parsingInfo);
    }

    private void blockSeparatorBeginning(final ParsingInfo parsingInfo) {
        finishCurrentWord(parsingInfo);
        // TODO : add info about beginning and ending characters 2 more if* methods
    }

    private void blockSeparatorEnd(final ParsingInfo parsingInfo) {
        finishCurrentWord(parsingInfo);
        // TODO : add info about beginning and ending characters 2 more if* methods
    }

    private void quoteSeparatorBeginning(final ParsingInfo parsingInfo) {
        finishCurrentWord(parsingInfo);
        // TODO : add info about beginning and ending characters 2 more if* methods
    }

    private void quoteSeparatorEnd(final ParsingInfo parsingInfo) {
        finishCurrentWord(parsingInfo);
        // TODO : add info about beginning and ending characters 2 more if* methods
    }

    private void singleQuoteSeparator(final ParsingInfo parsingInfo) {
        finishCurrentWord(parsingInfo);
        // TODO : add info about beginning and ending characters - these should be similar
    }

    private void whiteSeparator(final ParsingInfo parsingInfo) {
        finishCurrentWord(parsingInfo);
    }

    private void text(final ParsingInfo parsingInfo) {
        if (currentWord == null) {// Got first character for new word
            currentWord = new Word();
            currentWord.setFrom(parsingInfo.getPosition());
        }
        currentWord.add(parsingInfo.getCharacter());
    }

    private void finishCurrentSentence(final ParsingInfo parsingInfo) {
        finishCurrentClause(parsingInfo);
        finishCurrentBlock(parsingInfo);
        finishCurrentQuote(parsingInfo);

        sentences.add(currentSentence);
        currentSentence.setTo(parsingInfo.getPosition());
        currentSentence.finish();
        currentSentence = new Sentence();
        currentSentence.setFrom(parsingInfo.getPosition() + 1);
    }

    private void finishCurrentClause(final ParsingInfo parsingInfo) {
        finishItem(currentClause, parsingInfo);
    }

    private void finishCurrentBlock(final ParsingInfo parsingInfo) {
        finishItem(currentBlock, parsingInfo);
    }

    private void finishCurrentQuote(final ParsingInfo parsingInfo) {
        finishItem(currentQuote, parsingInfo);
    }

    private void finishCurrentWord(final ParsingInfo parsingInfo) {
        finishItem(currentWord, parsingInfo);
    }

    private void finishItem(final TextItem textItem, final ParsingInfo parsingInfo) {
        if (textItem != null) {
            textItem.setTo(parsingInfo.getPosition() - 1);
            textItem.finish();
        }
    }

    private boolean canFinishSentence(final ParsingInfo parsingInfo) {
        // TODO : false - can't finish, when it is inside quote, ...
        return true;
    }

    public List<TextItem> getSentences() {
        return Collections.unmodifiableList(sentences);
    }
}
