package tetris;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by lysogordima on 17.04.16.
 */


//Controller part
public class Proxy extends JFrame implements ActionListener, ItemListener, Game {
    JPanel cards;
    final static String TEXTPANEL = "Enter your name";

    public JButton btn;
    public JButton btnn;
    public JTextField inputName;
    JPanel card1 = new JPanel();
    private CardLayout cardLayout = new CardLayout();


    public void addComponentToPane(Container pane) {
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = {TEXTPANEL};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.setBackground(Color.ORANGE);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        comboBoxPane.setBackground(Color.WHITE);


        card1.setOpaque(true);

        btn = new JButton("Start Game");
        btn.addActionListener(this);
        btn.setActionCommand("Open");

        btn.setBackground(Color.ORANGE);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);

        btnn = new JButton("Exit");
        btnn.addActionListener(this);

        btnn.setBackground(Color.ORANGE);
        btnn.setContentAreaFilled(false);
        btnn.setOpaque(true);

        btnn.setActionCommand("Return");
        inputName = new JTextField("", 20);

        card1.add(inputName);
        card1.add(btn);
        card1.add(btnn);


        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());

        cards.add(card1, TEXTPANEL);
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);


    }
    public void itemStateChanged(ItemEvent evt) {

    }

    private  void createAndShowGUI() {
        JFrame frame = new JFrame("Start Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Proxy demo = new Proxy();
        demo.addComponentToPane(frame.getContentPane());

        frame.setLocation(550, 200);
        frame.setSize(300, 400);
        frame.setVisible(true);
    }
    public void StartGame(){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();

        if(cmd.equals("Open") && !inputName.getText().equals("") &&
                !inputName.getText().equals("Input your name")) {
            try(FileWriter fw = new FileWriter("myFile.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(inputName.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            new PressedState().actionPerformed(e);

        }
        else if (cmd.equals("Open") && inputName.getText().equals("")){
            inputName.setText("Input your name");
            inputName.setBackground(Color.lightGray);
        }
        else if (cmd.equals("Return")) {
            System.exit(0);
        }
    }

}