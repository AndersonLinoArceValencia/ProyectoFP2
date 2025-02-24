/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package caminito;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author lenovo
 */
public class CaminoGame extends javax.swing.JFrame {
 private JLabel diceResultLabel;
  private JLabel Menu;
      private Timer colorTimer;
    private int colorStep = 0;
    public CaminoGame() {
        setTitle("Juego de Caminito");
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(Color.CYAN);
        
        //FONDO:
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // Crear el panel superior para el botón y el resultado del dado
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        
        JPanel Panel2 = new JPanel();
        Panel2.setLayout(new FlowLayout());
        topPanel.setLayout(new BorderLayout());

        JButton rollDiceButton = new JButton("Lanzar Dado");
        
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        
         Menu = new JLabel("BIENVENIDO AL JUEGO DE LA MUERTE ");
         Menu.setForeground(Color.BLACK);
         Menu.setFont(new Font("Helvetica",Font.BOLD,25));
         Menu.setBackground(Color.red);
         Menu.setHorizontalAlignment(JLabel.CENTER);
            diceResultLabel = new JLabel("MENU COLOR: ");
            diceResultLabel.setFont(new Font("Arial", Font.BOLD, 20));
         rollDiceButton.setOpaque(true);
         rollDiceButton.setBackground(Color.RED);
        topPanel.add(Menu);
        Panel2.add(rollDiceButton);
        Panel2.add(diceResultLabel);
        add(topPanel,BorderLayout.CENTER);
        add(Panel2, BorderLayout.BEFORE_FIRST_LINE);
        add(new CaminoPanel(), BorderLayout.LINE_END);
        //
         colorTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorStep++;
                int red = (int) (Math.sin(0.1 * colorStep) * 127 + 128);
                int green = (int) (Math.sin(0.1 * colorStep + 2) * 127 + 128);
                int blue = (int) (Math.sin(0.1 * colorStep + 4) * 127 + 128);
                Menu.setForeground(new Color(blue, green, red));
                if (colorStep >= 100) {
                    colorTimer.stop();
                }
            }
        });
    }
    

    private void rollDice() {
        Random random = new Random();
        int diceResult = random.nextInt(6) + 1; // Genera un número entre 1 y 6
        colorStep= random.nextInt(40) + 1;
        diceResultLabel.setText("MENU COLOR: " + colorStep);
        CaminoPanel caminoPanel = (CaminoPanel) getContentPane().getComponent(2);
        caminoPanel.movePiece(diceResult);
        colorTimer.start();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
// </editor-fold>                        
// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CaminoGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CaminoGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CaminoGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CaminoGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CaminoGame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    
    class CaminoPanel extends JPanel {
   private final int TILE_SIZE = 82;
        private final int BOARD_SIZE = 8;
        private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

        private final int CENTER_SIZE = 6;
        private final Image redPieceImage;
        private final Image fondo;
        private final Image cola;
        private int pieceRow = 0; // Posición inicial de la pieza
        private int pieceCol = 0;

        public CaminoPanel() {
            redPieceImage = new ImageIcon("C:\\Users\\lenovo\\Downloads\\ficha.jpg").getImage();
            fondo = new ImageIcon("C:\\Users\\lenovo\\Downloads\\Elcaminodelsaber.png").getImage();
            cola= new ImageIcon("C:\\Users\\lenovo\\Downloads\\Diseño sin título.png").getImage();
            setPreferredSize(new Dimension(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE));
             setPreferredSize(new Dimension(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBoard(g);
            drawPieces(g);
        }

        private void drawBoard(Graphics g) {
            int centerStart = (BOARD_SIZE - CENTER_SIZE) / 2;
            int centerEnd = centerStart + CENTER_SIZE;

            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if (row >= centerStart && row < centerEnd && col >= centerStart && col < centerEnd) {
                        g.setColor(Color.RED);
                        board[row][col] = 2;
                    } else {
                        g.setColor(new Color(136, 209, 237));
                        board[row][col] = 0; 
                    }
                    g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    g.setColor(Color.darkGray);
                    g.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
            g.setColor(new Color(136, 209, 237));
            g.fillRect(0 * TILE_SIZE, 0 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            int centerX = centerStart * TILE_SIZE;
            int centerY = centerStart * TILE_SIZE;
            int centerSize = CENTER_SIZE * TILE_SIZE;
            g.drawImage(fondo, centerX, centerY, centerSize, centerSize, this);
        }

        private void movePiece(int steps) {
            for (int i = 0; i < steps; i++) {
                if (isOnEdge(pieceRow, pieceCol)) {
                    // Si está en el borde, moverse en la dirección del borde
                    if (pieceRow == 0 && pieceCol < BOARD_SIZE - 1) { // Arriba
                        pieceCol++;
                    } else if (pieceCol == BOARD_SIZE - 1 && pieceRow < BOARD_SIZE - 1) { // Derecha
                        pieceRow++;
                    } else if (pieceRow == BOARD_SIZE - 1 && pieceCol > 0) { // Abajo
                        pieceCol--;
                    } else if (pieceCol == 0 && pieceRow > 0) { // Izquierda
                        pieceRow--;
                    }
                } else {
                    // Si no está en el borde, mover alrededor del área central
                    if (pieceRow == BOARD_SIZE - CENTER_SIZE - 1 && pieceCol < BOARD_SIZE - CENTER_SIZE) {
                        pieceCol++;
                    } else if (pieceCol == BOARD_SIZE - CENTER_SIZE && pieceRow < BOARD_SIZE - CENTER_SIZE) {
                        pieceRow++;
                    } else if (pieceRow == BOARD_SIZE - CENTER_SIZE && pieceCol > CENTER_SIZE) {
                        pieceCol--;
                    } else if (pieceCol == CENTER_SIZE && pieceRow > CENTER_SIZE) {
                        pieceRow--;
                    }
                }
            }
            
            repaint();
        }

        private boolean isOnEdge(int row, int col) {
            return row == 0 || col == 0 || row == BOARD_SIZE - 1 || col == BOARD_SIZE - 1;
            
        }

        private void drawPieces(Graphics g) {
            g.drawImage(redPieceImage, pieceCol * TILE_SIZE + 5, pieceRow * TILE_SIZE + 5, TILE_SIZE - 10, TILE_SIZE - 10, this);
            
        }
    }
     class BackgroundPanel extends JPanel {
        private final Image backgroundImage;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon("C:\\Users\\lenovo\\Downloads\\CAMINO DE FONDO.jpg").getImage(); // Cambia la ruta de la imagen según sea necesario
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
