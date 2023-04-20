package info.setmy.models.textfunctions.register;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Short.parseShort;

public class KeywordFunctionMapperFactory {

    public static KeywordFunctionMapper newInstance() {
        final KeywordFunctionMapper keywordFunctionMapper = new KeywordFunctionMapper();
        keywordFunctionMapper.put("character", string -> string.toCharArray()[0]);
        keywordFunctionMapper.put("boolean", string -> parseBoolean(string));
        keywordFunctionMapper.put("short", string -> parseShort(string));
        keywordFunctionMapper.put("int", string -> parseInt(string));
        keywordFunctionMapper.put("long", string -> parseLong(string));
        keywordFunctionMapper.put("float", string -> parseFloat(string));
        keywordFunctionMapper.put("double", string -> parseDouble(string));
        keywordFunctionMapper.put("string", string -> string);
        // TODO : date, time, datetime, lists, sets, detect - type by investigated properties

        keywordFunctionMapper.getSynonyms().add("character", "char", "Char");
        keywordFunctionMapper.getSynonyms().add("boolean", "bool", "Boolean", "Bool");
        keywordFunctionMapper.getSynonyms().add("int", "Int", "Integer", "integer");

        return keywordFunctionMapper;
    }

}
