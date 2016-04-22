package tetris;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

import javax.swing.*;



//Contoller part
public class Tetris extends JFrame implements ActionListener, Game {

    JLabel statusbar;



    public Tetris() {

    }
    @Override
    public void actionPerformed(ActionEvent j)
    {
        String cmd = j.getActionCommand();
        if (cmd.equals("Back")){
            new BackPressed().actionPerformed(j);
            dispose();
        }

    }


    public JLabel getStatusBar() {
        return statusbar;
    }

    public static void main(String[] args) {
        Factory factory = new Factory();
        Game game = factory.getMenu("MainMenu");
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run(){

                game.StartGame();

            }


        });
    }


    @Override
    public void StartGame() {
        statusbar = new JLabel("Score : 0");
        add(statusbar, BorderLayout.SOUTH);
        Board board = new Board(this);
        board.setBackground(Color.lightGray);
        add(board,BorderLayout.CENTER);
        JPanel jPanel = new JPanel();
        JButton jButton = new JButton("Menu");


        jButton.setBackground(Color.ORANGE);
        jButton.setContentAreaFilled(false);
        jButton.setPreferredSize(new Dimension(80, 50));
        jButton.setOpaque(true);
        jButton.addActionListener(this);
        jButton.setActionCommand("Back");


        jPanel.add(jButton, BorderLayout.PAGE_START);
        add(jPanel, BorderLayout.EAST);

        board.start();

        setSize(400, 800);
        setTitle("Tetris Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
