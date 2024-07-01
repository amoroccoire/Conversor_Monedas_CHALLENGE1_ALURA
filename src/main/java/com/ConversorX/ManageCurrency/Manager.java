package com.ConversorX.ManageCurrency;

import com.ConversorX.ClientHttp.Client;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Manager {
    private String baseCurrency = "USD";
    private String baseCurrencyName = "Dolar";
    private Map<Integer, Pair> monedasActuales = null;
    private static Manager instance;
    private Client clienteAPI;

    private Manager() {
        if (monedasActuales == null) {
            monedasActuales = new HashMap<>();
            clienteAPI = new Client();
            loadCurrencies();
        }
    }

    public static Manager getInstanceManager() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    private void loadCurrencies() {
        File file = new File("ActualCurrencies.txt");
        try {
            Scanner fileReader = new Scanner(file);
            String[] arrayKeyValue = new String[2];
            int id = 1;

            while (fileReader.hasNextLine()) {
                String linea = fileReader.nextLine();
                arrayKeyValue = linea.split(",", 2);

                monedasActuales.put(id, new Pair(arrayKeyValue[0], arrayKeyValue[1]));
                id++;
            }
        } catch (IOException e) {
            System.out.println("No se ha podido iniciar las monedas de intercambio");
        }
    }

    public void showOption() {
        System.out.println("*************************************************");
        System.out.println("Sea bienvenido al conversor de monedas =]");
        int opciones = getNumOfOptions() - 1;

        int id2 = 1;
        for (int i = 1; i <= opciones; i += 2) {
            System.out.println(i + ") " + baseCurrencyName + " ===> " + monedasActuales.get(id2).getName());
            System.out.println((i + 1) + ") " + monedasActuales.get(id2).getName() + " ===> " + baseCurrencyName);
            id2++;
        }
        System.out.println((opciones + 1) + ") Salir");
        System.out.println("Eliga una opcion valida:");
        System.out.println("*************************************************");
    }

    public int getNumOfOptions() {
        return monedasActuales.size()*2+1;
    }

    public String makeQuery(int id, boolean direction, double monto) {
        Pair par = monedasActuales.get(id);

        if (!direction) {
            clienteAPI.values(baseCurrency, par.getCurrencyId(), monto);
        }
        else {
            clienteAPI.values(par.getCurrencyId(), baseCurrency, monto);
        }
        clienteAPI.buildUrl();
        Transform transform = clienteAPI.makeQuery();

        return "El valor " + monto + "["+transform.base_code()+"] " +
                "corresponde al valor final de ====> " + transform.conversion_result() +
                "["+transform.target_code()+"]";
    }
}
