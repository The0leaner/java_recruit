package cn.hinus.expression;

import cn.hinus.parser.Token;
import cn.hinus.parser.TokenStream;
import cn.hinus.datastructure.Stack;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hinus on 2016/12/25.
 */
public class StackExpression {
    public static void main(String args[]) throws IOException {
        TokenStream ts = new ExpressionTokenStream(System.in);
        Stack<Integer> numbers = new Stack<>(100);
        Stack<Token> operators = new Stack<>(100);

        while (ts.getToken().tokenType != Token.TokenType.NONE) {
            if (ts.getToken().tokenType == Token.TokenType.INT) {
                numbers.push((Integer)ts.getToken().value);
                ts.consumeToken();
            }
            else {
                if (operators.getTop() == null || preOrder( operators.getTop().tokenType, ts.getToken().tokenType) < 0) {
                    operators.push(ts.getToken());
                    ts.consumeToken();
                }
                else if (preOrder( operators.getTop().tokenType, ts.getToken().tokenType) == 0) {
                    operators.pop();
                    ts.consumeToken();
                }
                else {
                    binaryCalc(numbers, operators);

                    if (ts.getToken().tokenType != Token.TokenType.RPAR) {
                        operators.push(ts.getToken());
                        ts.consumeToken();
                    }
                }
            }
        }

        while (!operators.isEmpty()) {
            binaryCalc(numbers, operators);
        }

        System.out.println("result is " + numbers.getTop());
    }

    private static void binaryCalc(Stack<Integer> numbers, Stack<Token> operators) {
        int a = numbers.pop();
        int b = numbers.pop();

        Token oprt = operators.pop();
        int d = 0;
        if (oprt.tokenType == Token.TokenType.PLUS)
            d = b + a;
        else if (oprt.tokenType == Token.TokenType.MULT)
            d = a * b;
        else if (oprt.tokenType == Token.TokenType.MINUS)
            d = b - a;
        else if (oprt.tokenType == Token.TokenType.DIV)
            d = b / a;

        numbers.push(d);
    }

    private static int preOrder(Token.TokenType left, Token.TokenType right) {
        if (left == Token.TokenType.LPAR && right == Token.TokenType.RPAR) {
            return 0;
        }

        if (left == Token.TokenType.LPAR) {
            return -1;
        }
        else if (right == Token.TokenType.LPAR) {
            return -1;
        }
        else if (right == Token.TokenType.RPAR) {
            return 1;
        }


        if (left == Token.TokenType.PLUS || left == Token.TokenType.MINUS) {
            if (right == Token.TokenType.MULT || right == Token.TokenType.DIV)
                return -1;
            else
                return 1;
        }
        else
            return 1;
    }
}

