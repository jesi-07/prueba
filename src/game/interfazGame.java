package game;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class interfazGame {

	private JFrame frmJuego;
	private JTextField textTitle;
	private JButton[][] buttons;
	private JLabel moveLabel; // Para mostrar el número de movimientos
	private logicaGame logica; // Referencia a la clase de lógica

	// Constructor
	public interfazGame() {
		initialize();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfazGame window = new interfazGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frmJuego = new JFrame("Rompecabezas Deslizante");
	    frmJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frmJuego.setSize(400, 400);
	    frmJuego.getContentPane().setLayout(new BorderLayout());

	    textTitle = new JTextField("¡Este es el juego del ROMPECABEZAS DESLIZANTE!");
	    textTitle.setHorizontalAlignment(SwingConstants.CENTER);
	    textTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
	    frmJuego.getContentPane().add(textTitle, BorderLayout.NORTH);
	    textTitle.setColumns(10);

	    JPanel gridPanel = new JPanel(new GridLayout(4, 4));
	    buttons = new JButton[4][4];

	    // Partir del tablero en la posición ganadora
	    int index = 1;
	    int emptyRow = 3, emptyCol = 3;  // La última posición en un tablero 4x4
	    for (int i = 0; i < 4; i++) {
	        for (int j = 0; j < 4; j++) {
	            if (i == 3 && j == 3) {
	                buttons[i][j] = new JButton(""); // Espacio vacío
	            } else {
	                JButton button = new JButton(String.valueOf(index++));
	                buttons[i][j] = button;
	            }
	            gridPanel.add(buttons[i][j]);

	            final int row = i;
	            final int col = j;

	            buttons[i][j].addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    logica.moveTile(row, col); // Mover el casillero
	                }
	            });
	        }
	    }

	    moveLabel = new JLabel("Movimientos: 0");
	    moveLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    frmJuego.getContentPane().add(moveLabel, BorderLayout.SOUTH);
	    frmJuego.getContentPane().add(gridPanel, BorderLayout.CENTER);

	    // Inicializar la clase de lógica con el tablero en la posición ganadora
	    logica = new logicaGame(buttons, emptyRow, emptyCol, this);

	    // Mezclar el tablero de forma aleatoria con movimientos válidos
	    logica.randomizePuzzle(4);  // Puedes ajustar el número de movimientos aleatorios

	    frmJuego.setVisible(true);  // Mostrar la ventana
	}

	// Método para actualizar el contador de movimientos en la interfaz
	public void updateMove(int movimientos) {
		moveLabel.setText("Movimientos: " + movimientos);
	}
	
	public void messegeWinner() {
		JOptionPane.showMessageDialog(frmJuego, "¡Felicidades! Has completado el rompecabezas. "
				+ "Tus movimientos fueron: " + logica.getMoves());
		
	}
	
}
