import com.google.gson.JsonObject;
import java.util.Scanner;

public class Gui {
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        JsonObject conversionRates = Controller.obtenerTasas();

        if (conversionRates == null) {
            System.out.println("Error al obtener tasas de cambio. Verifica tu API key.");
            return;
        }

        while (true) {
            System.out.println("==== Conversor de Monedas ====");
            System.out.println("1. Soles (PEN)");
            System.out.println("2. Euros (EUR)");
            System.out.println("3. Libras (GBP)");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            String moneda = "";

            switch (opcion) {
                case 1 -> moneda = "PEN";
                case 2 -> moneda = "EUR";
                case 3 -> moneda = "GBP";
                case 4 -> {
                    System.out.println("Gracias por usar el conversor.");
                    return;
                }
                default -> {
                    System.out.println("Opción inválida.");
                    continue;
                }
            }

            System.out.print("Ingresa la cantidad en " + moneda + ": ");
            double cantidad = scanner.nextDouble();

            double tasa = conversionRates.get(moneda).getAsDouble();
            double resultado = cantidad / tasa;

            System.out.printf("Equivale a %.2f USD\n", resultado);
        }
    }
}
