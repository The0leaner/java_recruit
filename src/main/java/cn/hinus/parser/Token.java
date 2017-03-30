package cn.hinus.parser;

import org.jetbrains.annotations.NotNull;

/**
 * Created by hinus on 2016/12/19.
 */
public class Token implements Comparable<Token>{
    @Override
    public int compareTo(@NotNull Token o) {
        return 0;
    }

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

    public String toString() {
        return String.valueOf(value);
    }
}
