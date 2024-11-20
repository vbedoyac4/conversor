import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean terminar = false;
        Scanner scanner = new Scanner(System.in);

        while (!terminar) {
            System.out.println("_________________CALCULAR CANTIDAD EN OTRA MONEDA __________________");


            System.out.println("Ingrese el país de la moneda original:");
            String de = scanner.nextLine();
            de = darFormato(de);
            String existePais = DiccionarioMonedas.currencyMap.get(de);


            while (existePais == null) {
                System.out.println("El país no existe en la lista disponible. Ingrese otro o escriba X para terminar:");
                de = scanner.nextLine();
                if (de.equalsIgnoreCase("X")) {
                    terminar = true;
                    break;
                }
                de = darFormato(de);
                existePais = DiccionarioMonedas.currencyMap.get(de);
            }

            if (terminar) break;

            System.out.println("Ingrese la cantidad a convertir:");
            double cantidad = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Ingrese el país de la moneda a cambiar en Ingles:");
            String a = scanner.nextLine();
            a = darFormato(a);
            String existePaisA = DiccionarioMonedas.currencyMap.get(a);

            while (existePaisA == null) {
                System.out.println("El país no existe en la lista disponible. Ingrese otro o escriba X para terminar:");
                a = scanner.nextLine();
                if (a.equalsIgnoreCase("X")) {
                    terminar = true;
                    break;
                }
                a = darFormato(a);
                existePaisA = DiccionarioMonedas.currencyMap.get(a);
            }

            if (terminar) break;

            if (existePais != null && existePaisA != null) {
                double tasa = ConsultarTasa.buscarTasadeCambio(existePais, existePaisA);
                double montoNuevaMoneda = tasa * cantidad;
                System.out.println("La cantidad " + cantidad +" " + existePais + " equivale a " + montoNuevaMoneda + " " + existePaisA);
            }
        }
    }

    public static String darFormato(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String[] palabras = str.trim().split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                palabra = palabra.replaceAll("[^a-zA-Z]", "");
                if (!palabra.isEmpty()) {
                    resultado.append(Character.toUpperCase(palabra.charAt(0)))
                            .append(palabra.substring(1).toLowerCase())
                            .append(" ");
                }
            }
        }

        return resultado.toString().trim();
    }
}
