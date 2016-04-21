package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Created by lysogordima on 17.04.16.
 */


//Controller part
public class Proxy extends JFrame implements ActionListener, ItemListener, Game {
    JPanel cards; //a panel that uses CardLayout
//    final static String BUTTONPANEL = "Start Menu";
    final static String TEXTPANEL = "Enter your name";

    public JButton btn;
    public JButton btnn;
    public JTextField inputName;

    public void addComponentToPane(Container pane) {
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = {TEXTPANEL};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.setBackground(Color.ORANGE);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        comboBoxPane.setBackground(Color.RED);

        //Create the "cards".
        JPanel card1 = new JPanel();
        card1.setBackground(Color.RED);
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
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    private  void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Start Menu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        Proxy demo = new Proxy();
        demo.addComponentToPane(frame.getContentPane());

        //Display the window.
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

    public Proxy()
    {
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
            dispose();
            dispose();
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