package com.pruebatecnica.w2m.hero;

public class HeroException extends Exception{

    public static final String ERROR_HERO_NOT_FOUND = "ERROR_HERO_NOT_FOUND";

    public HeroException(String code, String message){
        super(code+ ":" + message);
    }
}
