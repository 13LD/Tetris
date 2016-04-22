package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lysogordima on 18.04.16.
 */
public class PressedState extends Strategy implements ActionListener {
    public  PressedState(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Tetris();
    }


}
