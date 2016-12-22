package com.sanchev.accounts;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserProfile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @SuppressWarnings("UnusedDeclaration")
    public UserProfile() {
    }
    
    @SuppressWarnings("UnusedDeclaration")
    public UserProfile(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UserProfile(String login, String password) {
        this.id = -1;
        this.login = login;
        if (password==null)
            password = login;
        this.password = password;
    }

    @SuppressWarnings("UnusedDeclaration")
    public UserProfile(String login) {
        this.id = -1;
        this.login = login;
        this.password = login;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}