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

        System.out.println("DESCIFRADO CESAR POR FUERZA BRUTA");
        System.out.print("Ingresa el mensaje cifrado: ");
        String textoCifrado = sc.next().toUpperCase();

        System.out.println("\n--- FUERZA BRUTA ---");
        System.out.println("Probando todas las llaves posibles:\n");
        fuerzaBruta(textoCifrado);

        sc.close();
    }

    public static String descifrar(String texto, int llave){

        StringBuilder resultado = new StringBuilder();
        llave = llave % total;

        for(int i = 0; i < texto.length(); i++){

            char caracter = texto.charAt(i);
            int pos = buscarPosicion(caracter);

            if(pos == -1){
                resultado.append(caracter);
            } else {
                pos = (pos - llave + total) % total;
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
            String descifrado = descifrar(textoCifrado, llave);
            System.out.println("Llave " + llave + " -> " + descifrado);
        }
    }
}