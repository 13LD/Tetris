package tetris;

import tetris.Facede.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lysogordima on 17.04.16.
 */
public class Proxy extends JFrame implements ActionListener
{
    private JButton btn;
    private JButton btnn;
    JPanel panel;
    JPanel red = new ColorPanel(Color.red,300,300);
    JPanel grn = new ColorPanel(Color.green,100,300);

    class ColorPanel extends JPanel
    {
        public ColorPanel(Color color, int W, int H)
        {
            this.setBackground(color);
            this.setPreferredSize(new Dimension(W,H));
        }
    }

    public void addComponentToPane(Container pane) {


        btn = new JButton("Start Game");
        btn.addActionListener(this);
        btn.setActionCommand("Open");

        btnn = new JButton("Exit");
        btnn.addActionListener(this);
        btnn.setActionCommand("Return");
        setSize(200, 200);
        panel = new JPanel(new CardLayout());
        panel.add(btn);
        panel.add(btnn, BorderLayout.EAST);

        pane.add(panel, BorderLayout.CENTER);
    }

    public Proxy()
    {
        JFrame frame = new JFrame("Start Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addComponentToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();

        if(cmd.equals("Open"))
        {
            dispose();
            new Tetris();
        }
        if (cmd.equals("Return"))
            dispose();
    }

}