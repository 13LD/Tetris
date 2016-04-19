package tetris;

import tetris.State.BackPressed;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;




public class Tetris extends JFrame implements ActionListener {

    JLabel statusbar;


    public Tetris() {
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        Board board = new Board(this);
        add(board,BorderLayout.CENTER);
        JButton jButton = new JButton("Start Menu");
        jButton.addActionListener(this);
        jButton.setActionCommand("Back");
        add(jButton, BorderLayout.EAST);
        board.start();

        setSize(400, 800);
        setTitle("Tetris Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
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
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run(){

                new Proxy().StartGame();

            }


        });
    }


}
