package com.ConversorX.ClientHttp;

import com.ConversorX.ManageCurrency.Transform;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Client {

    private String url = "https://v6.exchangerate-api.com/v6/";
    private String apiKey = "819c2e1efbc23f6278abb2d6";
    private URI uri;

    private String base;
    private String target;
    private double monto;
    private Gson gson;

    public Client() {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting().create();
    }
    public void values(String base, String target, double monto) {
        this.base = base;
        this.target = target;
        this.monto = monto;
    }
    public void buildUrl() {
        String fullURL = url+apiKey+"/pair/"+this.base+"/"+this.target+"/"+this.monto;
        uri = URI.create(fullURL);
    }

    public Transform makeQuery() {
        Transform result = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            result = gson.fromJson(json, Transform.class);
            if (result.result().equalsIgnoreCase("error"))
                throw new Exception("No existe alguno de los tipos de cambio dados");
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Algo salio mal, intentelo mas tarde");
        }
        return result;
    }

}
