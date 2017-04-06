package com.chen.parser;

/**
 * Created by lenovo on 2017/4/5.
 */
public class Token {
    public enum TokenType {
        LPAR, RPAR,
        PLUS,
        MINUS,
        MULT,
        DIV,
        INT,
        NONE,
    }
    public TokenType tokenType;
    public Object value;

    public Token(TokenType tt, Object v) {
        this.tokenType = tt;
        this.value = v;
    }
}
