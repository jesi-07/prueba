package game;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.border.LineBorder;


public class interfaz {

	private JFrame frameGame;
	private JTextField textTitle;
	private JButton[][] buttons;
	private int emptyRow, emptyCol;
    private int contadorMovimientos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaz window = new interfaz();
					//window.frameGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameGame = new JFrame("Trabajo Practico N°1");
		frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameGame.setSize(400, 400);
		frameGame.getContentPane().setLayout(new BorderLayout());
		
		textTitle = new JTextField("¡Este es el juego del ROMPECABEZAS DESLIZANTE!");
		textTitle.setHorizontalAlignment(SwingConstants.CENTER);
		textTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		frameGame.getContentPane().add(textTitle, BorderLayout.NORTH);
		textTitle.setColumns(10);

		JPanel gridPanel = new JPanel(new GridLayout(4, 4));
		gridPanel.setBorder(new LineBorder(new Color(102, 102, 0), 8, true));
		gridPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttons = new JButton[4][4];
        
    //    Imagen imagen = new Imagen();
        
     // Generar una lista de números desordenados
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            numbers.add(i);
        }
        numbers.add(0); // 0 representa el espacio vacío
        Collections.shuffle(numbers); // Mezcla aleatoria
        
        // Asignar numeros desordenados a los botones
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            	int value = numbers.get(index++);
                JButton button = new JButton(value == 0 ? "" : String.valueOf(value));
                buttons[i][j] = button;
                gridPanel.add(button);

                if(value == 0) { emptyRow = i ; emptyCol = j; }
                final int row = i;
                final int col = j;
                
                button.addActionListener((ActionListener) new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        moveTile(row, col);
                      /*  JLabel moveLabel = new JLabel("Puntaje = " + contadorMovimientos);
                        String setContadorMovimientos = Integer.toString(contadorMovimientos);
                        moveLabel.setText("Puntaje: " + setContadorMovimientos);       	*/
                    }
                });
            }
        }

        JLabel moveLabel = new JLabel("Movimientos " + contadorMovimientos);
      //  String setContadorMovimientos = Integer.toString(contadorMovimientos);
      //  moveLabel.setText("Puntaje: " + setContadorMovimientos);
        moveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frameGame.getContentPane().add(moveLabel, BorderLayout.SOUTH);
        frameGame.getContentPane().add(gridPanel, BorderLayout.CENTER);

        frameGame.setVisible(true);
         
	}
	
	/* Esta funcion de encarga del moviento del espacio vacio, o
	 * mejor dicho, de los botones (numeros) que los rodea.
	 * Swap entre set texto 
	 * Pasar esta funcion a la logica, por forms and controls */
	 private void moveTile(int row, int col) {
	        if (Math.abs(row - emptyRow) + Math.abs(col - emptyCol) == 1) {
	            buttons[emptyRow][emptyCol].setText(buttons[row][col].getText());
	            buttons[row][col].setText("");
	            emptyRow = row;
	            emptyCol = col;
	            contadorMovimientos++;		// Para la cantidad de movimientos
	            
	            // Actualizar el contador de movimientos en la etiqueta
	        }
	        // Verificar si el rompecabezas está resuelto
	    }


}