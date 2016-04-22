package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lysogordima on 18.04.16.
 */
public class BackPressed extends Strategy implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        new Proxy().StartGame();
    }

}
