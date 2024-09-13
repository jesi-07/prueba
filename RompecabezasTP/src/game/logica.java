package game;

import javax.swing.JButton;

public class logica {

	private static final int DIM = 4;
	final String[] completado = new String[SIZE - 1];
	private static final int SIZE = getDim() * getDim();
	private JButton[][] tablero = new JButton[getDim()][getDim()];
	
	private boolean estaCompleto() {
	    int referencia = 1;  // Comenzamos con el numero 1

	    for (int fila = 0; fila < DIM; fila++) {
	        for (int columna = 0; columna < DIM; columna++) {
	            // Si estamos en la última posición, esta debe ser el espacio vacío
	            if (fila == DIM - 1 && columna == DIM - 1) {
	                if (tablero[fila][columna].isVisible()) {
	                    return false; // Si el último casillero no está vacío devuelve falso
	                }
	            } else {
	                // Verificamos si los valores en el tablero están en orden ascendente
	                if (!tablero[fila][columna].getText().equals(String.valueOf(referencia))) {
	                    return false; // Devuelve falso si hay una pieza fuera de lugar
	                }
	                referencia++;
	            }
	        }
	    }

	    return true;  // Devuelve verdadero si el orden es correcto
	}
	
	public static int getDim() {
        return DIM;
    }
}
