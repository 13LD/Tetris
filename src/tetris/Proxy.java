package tetris;

import tetris.State.PressedState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

/**
 * Created by lysogordima on 17.04.16.
 */
public class Proxy extends JFrame implements ActionListener,ItemListener {
    JPanel cards; //a panel that uses CardLayout
    final static String BUTTONPANEL = "Start Menu";
    final static String TEXTPANEL = "Enter your name";

    public JButton btn;
    public JButton btnn;
    public JTextField inputName;

    public void addComponentToPane(Container pane) {
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = {TEXTPANEL, BUTTONPANEL };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        //Create the "cards".
        JPanel card1 = new JPanel();
        btn = new JButton("Start Game");
        btn.addActionListener(this);
        btn.setActionCommand("Open");
        btnn = new JButton("Exit");
        btnn.addActionListener(this);
        btnn.setActionCommand("Return");
        card1.add(btn);
        card1.add(btnn);

        inputName = new JTextField("", 20);
        JPanel card2 = new JPanel();
        card2.add(inputName);


        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card2, TEXTPANEL);
        cards.add(card1, BUTTONPANEL);

        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);

    }
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Start Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Proxy demo = new Proxy();
        demo.addComponentToPane(frame.getContentPane());

        //Display the window.

        frame.pack();
        frame.setVisible(true);
    }
    public static void StartGame(){
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
    public Proxy()
    {
        setSize(500, 500);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();

        if(cmd.equals("Open") && !inputName.getText().equals(""))
        {
            try(FileWriter fw = new FileWriter("myFile.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(inputName.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            dispose();
            new PressedState().actionPerformed(e);
        }
        else if (cmd.equals("Open") && inputName.getText().equals("")){
            dispose();
            new Proxy().StartGame();
        }
        else if (cmd.equals("Return")) {
            System.exit(0);
        }
    }

}