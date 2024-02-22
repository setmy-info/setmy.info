package info.setmy.linguistics.models.token;

public enum EventType {
    PARSING_BEGIN,
    PARSING_END,

    NULL_TO_ANY,//*

    CLASS_TYPE_CHANGE,//*

    ALPHABETIC_TO_WHITE,
    ALPHA_NUMERIC_TO_WHITE,

    ALPHABETIC_TO_ANY,
    ALPHA_NUMERIC_TO_ANY,//*

    WHITE_TO_ALPHABETIC,
    WHITE_TO_ALPHA_NUMERIC,

    ANY_TO_ALPHABETIC,
    ANY_TO_ALPHA_NUMERIC//*
}
