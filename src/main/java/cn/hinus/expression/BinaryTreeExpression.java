package cn.hinus.expression;

import cn.hinus.datastructure.BinarySearchTree;
import cn.hinus.datastructure.Node;
import cn.hinus.parser.Token;
import cn.hinus.parser.TokenStream;

import java.io.BufferedInputStream;
import java.io.IOException;

import static cn.hinus.parser.Token.TokenType.*;
import static cn.hinus.parser.Token.TokenType.MINUS;

/**
 * Created by hinus on 2017/1/14.
 */
public class BinaryTreeExpression {
    public TokenStream ts;

    public static void main(String args[]) {
        BinaryTreeExpression e = new BinaryTreeExpression();
        BinarySearchTree<Token> bst = new BinarySearchTree<Token>();
        bst.root = e.expression();
        bst.postOrder(bst.root);
    }

    public BinaryTreeExpression() {
        ts = new ExpressionTokenStream(new BufferedInputStream(System.in));
    }

    public Node<Token> expression() {
        Node<Token> left = term();

        try {
            if (match(PLUS)) {
                Node<Token> root = new Node<>(new Token(Token.TokenType.PLUS, "+"));
                root.left = left;
                root.right = term();
                return root;
            }
            else if (match(MINUS)) {
                Node<Token> root = new Node<>(new Token(Token.TokenType.MINUS, "-"));
                root.left = left;
                root.right = term();
                return root;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return left;
    }

    private Node<Token> term() {
        Node<Token> left = factor();

        try {
            if (match(MULT)) {
                Node<Token> root = new Node<>(new Token(Token.TokenType.MULT, "*"));
                root.left = left;
                root.right = factor();
                return root;
            }
            else if(match(DIV)){
                Node<Token> root = new Node<>(new Token(Token.TokenType.DIV, "/"));
                root.left = left;
                root.right = factor();
                return root;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return left;
    }

    private Node<Token> factor() {
        Token t = null;
        Node<Token> left;
        try {
            t = ts.getToken();

            if (t.tokenType == INT) {
                ts.consumeToken();
                return new Node<Token>(t);
            }
            else if (match(LPAR)) {
                Node<Token> v = expression();
                if (!match(RPAR))
                    assert false;
                return v;
            }
            else if (match(MINUS)) {
                Node<Token> v = new Node<>(new Token(INT, 0));
                Node<Token> root = new Node<>(new Token(MINUS, "-"));
                root.left = v;
                root.right = factor();
                return root;
            }
            else {
                throw new IOException("Illegal Expression!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // should not reach here
        return null;
    }

    private boolean match(Token.TokenType tt) throws IOException {
        if (ts.getToken().tokenType == tt) {
            ts.consumeToken();
            return true;
        }
        return false;
    }
}
