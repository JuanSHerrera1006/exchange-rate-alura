package com.alura.exchangerate.challenge.api.util.console;

import com.alura.exchangerate.challenge.api.client.ExchangeRateService;
import com.alura.exchangerate.challenge.api.model.ExchangeRateResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsolePrinter {
    private static final Scanner sc = new Scanner(System.in);
    private final ExchangeRateService exchangeRateService;
    private ArrayList<ArrayList<String>> listCodes;

    public ConsolePrinter(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
        this.listCodes = null;
    }


    public void exchangeRate() throws IOException, InterruptedException {
        boolean exit = false;
        String rate1 = "", rate2 = "";
        double value = 0;
        listRequest();
        while (!exit) {
            System.out.print("Codigo de la moneda (De): ");
            rate1 = sc.nextLine().toUpperCase();
            System.out.print("Codigo de la moneda (A): ");
            rate2 = sc.nextLine().toUpperCase();

            System.out.print("Ingrese el valor a convertir: ");
            value = Double.parseDouble(sc.nextLine());
            if (!codeExists(rate1).isEmpty() && !codeExists(rate2).isEmpty()) {
                exit = true;
            } else if (value <= 0) {
                System.out.println();
                System.out.println("Haz ingresado un valor no valido.");
                System.out.println();
            } else {
                System.out.println();
                System.out.println("Haz ingresado codigos de monedas no validos.");
                System.out.println();
            }
        }
        ExchangeRateResponse exchangeRateResponse = exchangeRateService.getExchangeRate(rate1, rate2);
        System.out.println();
        System.out.println("+======================+");
        System.out.println("| Resultado conversion |");
        System.out.println("+======================+");
        System.out.println();
        System.out.println(value + " " + rate1 + " = " + value * exchangeRateResponse.conversionRate() + " " + rate2);
        System.out.println("1.0 " + rate1 + " = " + exchangeRateResponse.conversionRate() + " " + rate2);
        System.out.println();
        System.out.println("Presiona cualquier tecla para continuar...");
        sc.nextLine();


    }
    private void listRequest() throws IOException, InterruptedException {
        if (listCodes == null){
            listCodes = exchangeRateService.getSupportCodes().supportCodes();
        }
    }
    public void menu() throws IOException, InterruptedException {
        boolean exit = false;

        while (!exit) {
            System.out.println("+=======================+");
            System.out.println("| Conversor de monedas  |");
            System.out.println("+=======================+");
            System.out.println();

            System.out.println("1. Hacer conversion de monedas");
            System.out.println("2. Lista de monedas disponibles");
            System.out.println("3. Salir");
            System.out.println();
            System.out.print("Haz seleccionado la opcion: ");
            char choice = sc.nextLine().toLowerCase().charAt(0);
            switch (choice) {
                case '1' -> exchangeRate();
                case '2' -> showListElements(10);
                case '3' -> exit = true;
                default -> System.out.println("Opcion invalida.");
            }
        }
    }
    public ArrayList<ArrayList<String>> codeExists(String code) throws IOException, InterruptedException {
        listRequest();
        return listCodes
                .stream()
                .filter(ele -> ele.get(0).equals(code.toUpperCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public void searchElement(String code) throws IOException, InterruptedException {
        ArrayList<ArrayList<String>> filterList = codeExists(code);
        if (!filterList.isEmpty()){
            ArrayList<String> foundCoin = filterList.get(0);
            System.out.println("Se ha encontrado la siguiente moneda de acuerdo a tu busqueda: ");
            System.out.println(foundCoin.get(1) + "(" + foundCoin.get(0) + ")");
        } else {
            System.out.println("No se ha encontrado ningun elemento a partir de la busqueda.");
        }
        System.out.println("Presiona cualquier tecla para continuar...");
        sc.nextLine();
    }
    public void showListElements(Integer pageLimit) throws IOException, InterruptedException {
        listRequest();
        int totalPages = (int) Math.ceil((double) listCodes.size() / pageLimit);
        int currentPage = 1;
        boolean exit = false;
        while (!exit) {
            int start = (currentPage - 1) * pageLimit;
            int end = Math.min(start + pageLimit, listCodes.size());

            System.out.println("+==============================+");
            System.out.println("| Lista de monedas disponibles |");
            System.out.println("+==============================+");
            System.out.println();

            for (int i = start; i < end; i++) {
                String code = listCodes.get(i).get(0);
                String name = listCodes.get(i).get(1);
                System.out.println((i + 1) + ". " + name + "(" + code + ")");
            }
            System.out.println();
            System.out.print("Presiona 'n' para siguiente página, "
                    + "'p' para página anterior, "
                    + "'b' para buscar una moneda, "
                    + "o 'q' para salir: ");

            char choice = sc.nextLine().toLowerCase().charAt(0);
            switch (choice) {
                case 'n' -> {
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println();
                        System.out.println("Ya te encuentras en la ultima pagina.");
                        System.out.println();
                    }
                }
                case 'p' -> {
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println();
                        System.out.println("Ya te encuentras en la primera pagina.");
                        System.out.println();
                    }
                }
                case 'b' -> {
                    System.out.print("Ingrese el valor del codigo de la moneda que desea buscar: ");
                    System.out.println();
                    searchElement(sc.nextLine());
                    System.out.println();
                }
                case 'q' -> exit = true;
                default -> System.out.println("Opcion invalida.");
            }
        }
    }
}
