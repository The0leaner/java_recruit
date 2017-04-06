package com.chen.expression;



import com.chen.parser.Token;
import com.chen.parser.TokenStream;

import java.io.*;

import static com.chen.parser.Token.TokenType.*;

/**
 * Created by hinus on 2016/12/19.
 */
public class Expression {
    public TokenStream ts;

    public static void main(String args[]) {
        Expression e = new Expression();
        System.out.println(e.evalue());
    }

    public Expression() {
        ts = new ExpressionTokenStream(new BufferedInputStream(System.in));
    }

    public int evalue() {
        int t = term();
        Token op = null;

        try {
            op = ts.getToken();
            while (op.tokenType == PLUS || op.tokenType == MINUS) {
                ts.consumeToken();
                int t2 = term();
                if (op.tokenType == PLUS) {
                    t += t2;
                } else {
                    t -= t2;
                }

                op = ts.getToken();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    private int term() {
        int t = factor();

        Token op = null;
        try {
            op = ts.getToken();

            while (op.tokenType == MULT || op.tokenType == DIV) {
                ts.consumeToken();
                int t2 = factor();
                if (op.tokenType == MULT) {
                    t *= t2;
                } else {
                    t /= t2;
                }
                op = ts.getToken();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    private int factor() {
        Token t = null;
        try {
            t = ts.getToken();

            if (t.tokenType == INT) {
                ts.consumeToken();
                return (((Integer) t.value).intValue());
            } else if (t.tokenType == LPAR) {
                ts.consumeToken();
                int v = evalue();
                match(ts.getToken(), RPAR);
                return v;
            } else {
                throw new IOException("Illegal Expression!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // should not reach here
        return 0;
    }

    private void match(Token t, Token.TokenType tt) {
        assert t.tokenType == tt;
        ts.consumeToken();
    }
}

class ExpressionTokenStream implements TokenStream {
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

                return new Token(INT, Integer.valueOf(t));
            }
        }

        return null;
    }

    private boolean isSpace(char c) {
        return (c == ' ' || c == '\t');
    }

    private boolean isNumber(byte c) {
        return (c <= '9' && c >= '0');
    }
}


