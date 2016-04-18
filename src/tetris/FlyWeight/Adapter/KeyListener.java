package tetris.FlyWeight.Adapter;


import java.awt.event.KeyEvent;
import java.util.EventListener;

/**
 * Created by lysogordima on 17.04.16.
 */
public interface KeyListener extends EventListener {

    public void keyTyped(KeyEvent e);

    public void keyPressed(KeyEvent e);

    public void keyReleased(KeyEvent e);
}
