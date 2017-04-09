package com.chen.parser;


import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

public class TokenStreamReader implements TokenStream{

    private InputStream in;
    private byte[] buffer;
    private int tpes = 0;
    private int length = 0;
    private Token currentToken;
    public Token getToken() throws IOException {
        return currentToken;
    }


    public void consumeToken() {

        try{
            currentToken = getNextToken();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Token getNextToken() throws IOException{
//        if(length == 0){
//            length = in.read(buffer);
//        }
//        if(length == 0){
//            return null;
//        }
        length = in.read(buffer);
        if(length == 0){
            return null;
        }
        while(tpes < length){
            if(buffer[tpes] == '\n'){
                tpes++;
                return new Token(Token.TokenType.NONE, "");
            }
            if(isSpace((char)buffer[tpes]))
            if(buffer[tpes] == '('){
                tpes++;
                return new Token(Token.TokenType.LPAR , "(");
            }
            if(buffer[tpes] == ')'){
                tpes++;
                return new Token(Token.TokenType.RPAR , ")");
            }

            if(buffer[tpes] == '+'){
                tpes++;
                return new Token(Token.TokenType.PLUS , "+");
            }
            if(buffer[tpes] == '-'){
                tpes++;
                return new Token(Token.TokenType.MINUS , "-");
            }
            if(buffer[tpes] == '+'){
                tpes++;
                return new Token(Token.TokenType.PLUS , "+");
            }
            if(buffer[tpes] == '*'){
                tpes++;
                return new Token(Token.TokenType.MULT , "*");
            }
            if(buffer[tpes] == '/'){
                tpes++;
                return new Token(Token.TokenType.DIV , "/");
            }

            if(isNumber(buffer[tpes])) {
                int t = 0;
                while(isNumber(buffer[tpes])){
                    t = t * 10 + (buffer[tpes] - '0');
                    tpes++;
                }
                return new Token(Token.TokenType.INT , Integer.valueOf(t));
            }






        }
        return null;
    }
    public boolean isSpace(char c){
        return c == ' '|| c == '\t' || c =='\r';
    }

    public boolean isNumber(byte b){
        return b <= '9'&& b >='0';
    }
}
