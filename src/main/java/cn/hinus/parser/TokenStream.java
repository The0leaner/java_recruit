package cn.hinus.parser;

import java.io.IOException;

/**
 * Created by hinus on 2016/12/19.
 */
public interface TokenStream {
    public Token getToken()  throws IOException;
    public void consumeToken();
}