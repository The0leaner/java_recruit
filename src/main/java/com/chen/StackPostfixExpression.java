package com.chen;


import com.chen.parser.Token;
import com.chen.parser.TokenStream;
import com.chen.parser.TokenStreamReader;
import com.chen.stack.Stack;

import java.io.IOException;

public class StackPostfixExpression {

    public static void main(String[] args) throws IOException {
        TokenStream tsReader = new TokenStreamReader();
        Stack<Integer> numbers = new Stack<>(128);
        Stack<Token> operators = new Stack<>(128);
        while(tsReader.getToken().tokenType != Token.TokenType.NONE ) {
            if (tsReader.getToken().tokenType == Token.TokenType.INT) {
                numbers.push((Integer) tsReader.getToken().value);
                tsReader.consumeToken();
            } else {
                if (operators.getTop() == null || preOrder(operators.getTop().tokenType, tsReader.getToken().tokenType) < 0) {
                    operators.push(tsReader.getToken());
                    tsReader.consumeToken();
                } else if (preOrder(operators.getTop().tokenType, tsReader.getToken().tokenType) == 0) {
                    operators.pop();
                    tsReader.consumeToken();
                } else {
                    BinaryCal(numbers, operators);
                    if (tsReader.getToken().tokenType != Token.TokenType.RPAR) {
                        operators.push(tsReader.getToken());
                        tsReader.consumeToken();
                    }
                }


            }
//            else if (tsReader.getToken().tokenType == Token.TokenType.PLUS||tsReader.getToken().tokenType == Token.TokenType.MINUS||tsReader.getToken().tokenType == Token.TokenType.DIV||tsReader.getToken().tokenType == Token.TokenType.MULT){
//                operations.push((Token) tsReader.getToken().value);
//                tsReader.consumeToken();
//            }else if (tsReader.getToken().tokenType == Token.TokenType.LPAR||tsReader.getToken().tokenType == Token.TokenType.RPAR){
//              System.out.println("IllegalException");
//            }
        }
            while(!operators.isEmpty()){
            BinaryCal(numbers , operators);

            }
            System.out.println("the result is" + numbers.getTop());
        }

     //0正常优先度，-1降一个优先度，1优先度更高
        private static int preOrder(Token.TokenType left , Token.TokenType right){

            if (left == Token.TokenType.LPAR && right == Token.TokenType.RPAR){
                return 0;
            }
            if (left == Token.TokenType.LPAR ){
                return -1;
            }else if (right == Token.TokenType.LPAR ){
                return -1;
            }else if(right == Token.TokenType.RPAR){
                return 1;
            }
            if (left == Token.TokenType.PLUS ||  left == Token.TokenType.MINUS ){
                if(right == Token.TokenType.MULT ||right == Token.TokenType.DIV)
                    return -1;
               else
                    return 1;}

                    else
                        return 1;
            }


        private static void BinaryCal(Stack<Integer> numbers , Stack<Token> operators){
        int a = numbers.pop();
        int b = numbers.pop();

        Token tk = operators.pop();
        int c = 0;
        if(tk.tokenType == Token.TokenType.PLUS){
           c = a + b;
        }else if(tk.tokenType == Token.TokenType.DIV){
            c = a - b;
        }else if (tk.tokenType == Token.TokenType.MULT){
            c = a * b;
        }else if (tk.tokenType == Token.TokenType.MINUS){
            c = a / b;
        }
        numbers.push(c);
        }
    }


