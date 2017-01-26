package com.sanchev.base;

public interface AccountService {
    void signUp(String login, String password);
    boolean signIn(String login, String password);
}