package com.pandacreep.onlineshop.util;

import lombok.Data;

import java.util.Random;

@Data
public class Captcha {
    private Random r = new Random();
    private int number1 = r.nextInt(9) + 1;
    private int number2 = r.nextInt(9) + 1;
    private int result = number1 * number2;
}
