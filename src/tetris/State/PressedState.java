package tetris.State;

import tetris.Tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lysogordima on 18.04.16.
 */
public class PressedState extends State implements ActionListener {
    public  PressedState(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Tetris();
    }
}
