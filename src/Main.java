import java.util.Scanner;

public class Main {

    static char[][] letras = {
            {'A','B','C','D','E','F','G'},
            {'H','I','J','K','L','M','N'},
            {'Ñ','O','P','Q','R','S','T'},
            {'U','V','W','X','Y','Z',' '}
    };

    static final int filas = 4;
    static final int columnas = 7;
    static final int total = filas * columnas;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        System.out.println("CIFRADO CESAR");
        System.out.print("Ingresa una frase: ");
        String frase = sc.next().toUpperCase();

        int opcion;

        do {

            System.out.println("\n--- MENU ---");
            System.out.println("1. Cifrar");
            System.out.println("2. Descifrar");
            System.out.println("3. Descifrar por fuerza bruta");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opcion: ");
            opcion = sc.nextInt();

            switch(opcion) {

                case 1:
                    System.out.print("Ingresa la llave para cifrar: ");
                    int llaveCifrar = sc.nextInt();
                    frase = procesar(frase, llaveCifrar, true);
                    System.out.println("Texto cifrado: " + frase);
                    break;

                case 2:
                    System.out.print("Ingresa la llave para descifrar: ");
                    int llaveDescifrar = sc.nextInt();
                    frase = procesar(frase, llaveDescifrar, false);
                    System.out.println("Texto descifrado: " + frase);
                    break;

                case 3:
                    System.out.println("\n--- FUERZA BRUTA ---");
                    System.out.println("Probando todas las llaves posibles:\n");
                    fuerzaBruta(frase);
                    break;

                case 4:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while(opcion != 4);

        sc.close();
    }

    public static String procesar(String texto, int llave, boolean cifrar){

        StringBuilder resultado = new StringBuilder();
        llave = llave % total;

        for(int i = 0; i < texto.length(); i++){

            char caracter = texto.charAt(i);
            int pos = buscarPosicion(caracter);

            if(pos == -1){
                resultado.append(caracter);
            } else {

                if(cifrar){
                    pos = (pos + llave + total) % total;
                } else {
                    pos = (pos - llave + total) % total;
                }
                resultado.append(obtenerCaracter(pos));
            }
        }

        return resultado.toString();
    }

    public static int buscarPosicion(char c){
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                if(letras[i][j] == c){
                    return i * columnas + j;
                }
            }
        }
        return -1;
    }

    public static char obtenerCaracter(int pos){

        int fila = pos / columnas;
        int columna = pos % columnas;

        return letras[fila][columna];
    }

    public static void fuerzaBruta(String textoCifrado){
        for(int llave = 0; llave < total; llave++){
            String descifrado = procesar(textoCifrado, llave, false);
            System.out.println("Llave " + llave + ": " + descifrado);
        }
    }
}