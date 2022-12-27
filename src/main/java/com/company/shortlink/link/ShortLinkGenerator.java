package com.company.shortlink.link;

import java.util.Random;

public class ShortLinkGenerator {
    public String generate() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random r = new Random();
        String generatedLink = String.valueOf(alphabet.charAt(r.nextInt(alphabet.length())));
        for(int i=1;i<5;i++){
            generatedLink=generatedLink+alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return generatedLink;
    }

    public static void main(String[] args) {

        System.out.println(new ShortLinkGenerator().generate());
    }

}
