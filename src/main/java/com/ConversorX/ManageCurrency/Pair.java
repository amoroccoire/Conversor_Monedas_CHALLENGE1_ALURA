package com.ConversorX.ManageCurrency;

public class Pair {
    private String currencyId;
    private String name;

    public Pair(String currencyId, String name) {
        this.currencyId = currencyId;
        this.name = name;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }
    public String getCurrencyId() {
        return this.currencyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
