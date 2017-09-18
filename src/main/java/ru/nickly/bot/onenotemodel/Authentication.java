package ru.nickly.bot.onenotemodel;

import lombok.Getter;
import lombok.Setter;

public class Authentication {
    private static Authentication authentication;
    public static Authentication getInstance(){
        if(authentication == null)
            authentication = new Authentication();
        return authentication;
    }

    @Getter @Setter private String code;
    @Getter @Setter private Token token;
}
