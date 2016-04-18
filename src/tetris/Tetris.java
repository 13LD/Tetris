package tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;




public class Tetris extends JFrame implements ActionListener {

    JLabel statusbar;


    public Tetris() {
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        Board board = new Board(this);
        add(board);
        board.start();

        setSize(400, 800);
        setTitle("Tetris Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();

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

//import java.awt.BorderLayout;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//
//public class Tetris extends JFrame {
//
//    JLabel statusbar;
//
//
//    public Tetris() {
//
//        statusbar = new JLabel(" 0");
//        add(statusbar, BorderLayout.SOUTH);
//        Board board = new Board(this);
//        add(board);
//        board.start();
//
//        setSize(200, 400);
//        setTitle("Tetris Game");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//
//    public JLabel getStatusBar() {
//        return statusbar;
//    }
//
//    public static void main(String[] args) {
//
//        Tetris game = new Tetris();
//        game.setLocationRelativeTo(null);
//        game.setVisible(true);
//
//    }
//}