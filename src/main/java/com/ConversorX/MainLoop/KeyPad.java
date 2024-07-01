package com.ConversorX.MainLoop;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class KeyPad {
    private Scanner input;

    public KeyPad() {
        input = new Scanner(System.in);
    }
    public int getInputInt() {
        try {
            return input.nextInt();
        } catch (NoSuchElementException e) {
            System.out.println("La entrada debe ser un entero valido");
            System.out.println("Vuelva a intentarlo");
            input.nextLine();
            return -1;
        }
    }

    public double getInputDouble() {
        try {
            return input.nextDouble();
        } catch (NoSuchElementException e) {
            System.out.println("La entrada debe ser un entero valido");
            System.out.println("Vuelva a intentarlo");
            input.nextLine();
            return -1;
        }
    }
}
