package com.ConversorX.MainLoop;

import com.ConversorX.ManageCurrency.Manager;
public class Principal {
    public static void main(String[] args) {
        Manager managerCurrency = Manager.getInstanceManager();
        KeyPad keypad = new KeyPad();

        int id;
        boolean flag;
        while (true) {
            managerCurrency.showOption();
            int option = keypad.getInputInt();

            //verifica si el numero es la ultima opcion
            //getNumOfOptions devuelve la cantidad de opciones, icnluidas las de tipo USD -> MONEDA
            if (option == managerCurrency.getNumOfOptions())
                break;
            if (option < 1 || option > managerCurrency.getNumOfOptions())
                System.out.println("Ingrese una opcion valida");
            else { //los calculos de abajo convierten la opcion en una que sea de [1-Num de valores en el Map de monedas]
                //el flag indica si se trata de USD -> MONEDA o de MONEDA -> USD
                if (option % 2 == 0) {
                    id = option / 2;
                    flag = true;
                } else {
                    id = (option + 1) / 2;
                    flag = false;
                }

                System.out.print("Ingrese el monto a convertir: ");
                double monto = keypad.getInputDouble(); //obtiene el monto
                String resultado = managerCurrency.makeQuery(id, flag, monto);
                System.out.println(resultado);
            }
        }
    }
}
