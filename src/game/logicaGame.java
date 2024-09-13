package game;

import javax.swing.JButton;

public class logicaGame {
    private JButton[][] buttons;
    private int emptyRow, emptyCol;
    private int moves;

	private interfazGame interfaz; // Referencia a la interfaz

    public logicaGame(JButton[][] buttons, int emptyRow, int emptyCol, interfazGame interfaz) {
        this.buttons = buttons;
        this.emptyRow = emptyRow;
        this.emptyCol = emptyCol;
        this.interfaz = interfaz; // Para actualizar la interfaz
        this.moves = 0; // Inicializar el contador de movimientos
    }

    public void moveTile(int row, int col) {
        if (Math.abs(row - emptyRow) + Math.abs(col - emptyCol) == 1) {
            // Intercambiar el texto de los botones
            buttons[emptyRow][emptyCol].setText(buttons[row][col].getText());
            buttons[row][col].setText("");
            emptyRow = row;
            emptyCol = col;
            
            moves++;            										
            interfaz.updateMove(moves); // Actualizar el contador de movimientos en la interfaz
            
            if(finishGame()) {
            	interfaz.messegeWinner();
            }
        }
        // Aquí puedes añadir la verificación de si el rompecabezas está resuelto
    }
    
    /* 
     * Verifica si el tablero esta ordenado, en caso de ser TRUE,
     * el jugador gano. */
    public boolean finishGame() {
        int expectedValue = 1;

        // Recorre todo el tablero excepto el casillero vacío, si lo encuentra lo ignora
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String text = buttons[i][j].getText();
                
                // Si el casillero es vacío, lo ignoramos
                if (text.isEmpty()) {
                    continue;
                }

                // Verificar que el valor en el botón coincida con el valor esperado
                if (!text.equals(String.valueOf(expectedValue))) {
                    return false;  // Si hay un valor incorrecto, el tablero no está ordenado
                }
                expectedValue++;
            }
        }
        return true;  // Todos los números están en orden
    }
    
    public int getMoves() {
  		return moves;
  	}
    
    public void randomizePuzzle(int moves) {
    	// Arrays para representar los cambios de fila y columna
        int[] dRow = {-1, 1, 0, 0};  // Arriba, abajo
        int[] dCol = {0, 0, -1, 1};  // Izquierda, derecha

        for (int i = 0; i < moves; i++) {
            int randomDirection = (int) (Math.random() * 4);  // Generar una dirección aleatoria

            int newRow = emptyRow + dRow[randomDirection];  // Cambiar la fila de acuerdo con la dirección
            int newCol = emptyCol + dCol[randomDirection];  // Cambiar la columna de acuerdo con la dirección

            // Verificar si la nueva posición es válida
            if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4) {
                // Intercambiar las posiciones
                swapButtons(emptyRow, emptyCol, newRow, newCol);
                emptyRow = newRow;
                emptyCol = newCol;
            }
        }
    }
    
    private void swapButtons(int row1, int col1, int row2, int col2) {
        String tempText = buttons[row1][col1].getText();
        buttons[row1][col1].setText(buttons[row2][col2].getText());
        buttons[row2][col2].setText(tempText);
    }
    
}

