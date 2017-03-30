package cn.hinus.expression;

import cn.hinus.parser.Token;
import cn.hinus.parser.TokenStream;

import java.io.IOException;
import java.io.InputStream;

import static cn.hinus.parser.Token.TokenType.INT;
import static cn.hinus.parser.Token.TokenType.NONE;

/**
 * Created by hinus on 2016/12/25.
 */
public class ExpressionTokenStream implements TokenStream {
    private byte[] buffer;
    private int tpos = 0;
    private int length = 0;
    private InputStream in;
    private Token currentToken;

    public ExpressionTokenStream(InputStream in) {
        buffer = new byte[1024];
        this.in = in;
        consumeToken();
    }

    public void consumeToken() {
        try {
            currentToken = getNextToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Token getToken() throws IOException {
        return currentToken;
    }


    public Token getNextToken() throws IOException {
        if (length == 0) {
            length = in.read(buffer);
        }

        if (length == 0)
            return null;

        while (tpos < length) {
            if (buffer[tpos] == '\n')
                return new Token(NONE, "");

            while (isSpace((char) buffer[tpos])) {
                tpos++;
            }

            if (buffer[tpos] == '(') {
                tpos++;
                return new Token(Token.TokenType.LPAR, "(");
            }

            if (buffer[tpos] == ')') {
                tpos++;
                return new Token(Token.TokenType.RPAR, ")");
            }

            if (buffer[tpos] == '+') {
                tpos++;
                return new Token(Token.TokenType.PLUS, "+");
            }

            if (buffer[tpos] == '-') {
                tpos++;
                return new Token(Token.TokenType.MINUS, "-");
            }

            if (buffer[tpos] == '*') {
                tpos++;
                return new Token(Token.TokenType.MULT, "*");
            }

            if (buffer[tpos] == '/') {
                tpos++;
                return new Token(Token.TokenType.DIV, "/");
            }

            if (isNumber(buffer[tpos])) {
                int t = 0;
                while (isNumber(buffer[tpos])) {
                    t = t * 10 + (buffer[tpos] - '0');
                    tpos++;
                }

                return new Token(INT, t);
            }
        }

        return null;
    }

    private boolean isSpace(char c) {
        return (c == ' ' || c == '\t' || c == '\r');
    }

    private boolean isNumber(byte c) {
        return (c <= '9' && c >= '0');
    }
}



