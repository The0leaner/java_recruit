package com.chen.parser;

import java.io.IOException;

/**
 * Created by lenovo on 2017/4/5.
 */
public interface TokenStream {
    public Token getToken()  throws IOException;
    public void consumeToken();
}
