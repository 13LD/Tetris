package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tetris.FlyWeight.Adapter.KeyAdapter;
import tetris.FlyWeight.Shape.Tetrominoes;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import tetris.FlyWeight.ZShape;
import tetris.State.State;

//View part
public class Board extends JPanel implements ActionListener {


    final int BoardWidth = 10;
    final int BoardHeight = 22;

    Timer timer;
    boolean isFallingFinished = false;
    boolean isStarted = false;
    boolean isPaused = false;
    int numLinesRemoved = 0;
    int curX = 0;
    int curY = 0;
    JLabel statusbar;
    ZShape curPiece;
    Tetrominoes[] board;
    Tetrominoes[] values = Tetrominoes.values();



    public Board(Tetris parent) {

        setFocusable(true);
        curPiece = new ZShape();
        timer = new Timer(400, this);
        timer.start();

        statusbar =  parent.getStatusBar();
        board = new Tetrominoes[BoardWidth * BoardHeight];
        addKeyListener(new Adaptee());
        clearBoard();
    }

    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }


    int squareWidth() { return (int) getSize().getWidth() / BoardWidth; }
    int squareHeight() { return (int) getSize().getHeight() / BoardHeight; }
    Tetrominoes shapeAt(int x, int y) { return board[(y * BoardWidth) + x]; }


    public void start()
    {
        if (isPaused)
            return;

        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        clearBoard();

        newPiece();
        timer.start();
    }

    public void pause()
    {
        if (!isStarted)
            return;

        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
            statusbar.setText("paused");
        } else {
            timer.start();
            statusbar.setText(String.valueOf(numLinesRemoved));
        }
        repaint();
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();


        for (int i = 0; i < BoardHeight; ++i) {
            for (int j = 0; j < BoardWidth; ++j) {
                Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
                if (shape != Tetrominoes.NoShape)
                    drawSquare(g, j * squareWidth(),
                            boardTop + i * squareHeight(), Tetrominoes.ZShape);
            }
        }

        if (curPiece.getShape() != Tetrominoes.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                drawSquare(g, x * squareWidth(),
                        boardTop + (BoardHeight - y - 1) * squareHeight(),
                        curPiece.getShape());
            }
        }
    }

    public void dropDown()
    {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    public void oneLineDown()
    {
        if (!tryMove(curPiece, curX, curY - 1))
            pieceDropped();
    }


    public void clearBoard()
    {
        for (int i = 0; i < BoardHeight * BoardWidth; ++i)
            board[i] = Tetrominoes.NoShape;
    }

    public void pieceDropped()
    {
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BoardWidth) + x] = Tetrominoes.ZShape;
        }

        removeFullLines();

        if (!isFallingFinished)
            newPiece();
    }

    public void newPiece()
    {
        curPiece.setRandomShape();
        curX = BoardWidth / 2 + 1;
        curY = BoardHeight - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(Tetrominoes.NoShape);
            timer.stop();
            isStarted = false;
            try(FileWriter fw = new FileWriter("myFile.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(statusbar.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            statusbar.setText("game over");
        }
    }

    public boolean tryMove(ZShape newPiece, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight)
                return false;
            if (shapeAt(x, y) != Tetrominoes.NoShape)
                return false;
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        return true;
    }

    public void removeFullLines()
    {
        int numFullLines = 0;

        for (int i = BoardHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < BoardWidth; ++j) {
                if (shapeAt(j, i) == Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BoardHeight - 1; ++k) {
                    for (int j = 0; j < BoardWidth; ++j)
                        board[(k * BoardWidth) + j] = shapeAt(j, k + 1);
                }
            }
        }

        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            statusbar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(Tetrominoes.NoShape);
            repaint();
        }
    }

    public void drawSquare(Graphics g, int x, int y, Tetrominoes shape)
    {
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }







//Command
    public interface Executor {
         void Execute();
    }
    class  CommandAction1 implements Executor {
        private User _executor;
        public CommandAction1 (User _executor)
        {
            this._executor = _executor;
        }
        public void Execute()
        {
            _executor.Action1();
        }
    }
    class  CommandAction2 implements Executor {
        private User _executor;
        public CommandAction2 (User _executor)
        {
            this._executor = _executor;
        }
        public void Execute()
        {
            _executor.Action2();
        }
    }
    class  CommandAction3 implements Executor {
        private User _executor;
        public CommandAction3 (User _executor)
        {
            this._executor = _executor;
        }
        public void Execute()
        {
            _executor.Action3();
        }
    }
    class  CommandAction4 implements Executor {
        private User _executor;
        public CommandAction4 (User _executor)
        {
            this._executor = _executor;
        }
        public void Execute()
        {
            _executor.Action4();
        }
    }
    class  CommandAction5 implements Executor {
        private User _executor;
        public CommandAction5 (User _executor)
        {
            this._executor = _executor;
        }
        public void Execute()
        {
            _executor.Action5();
        }
    }
    class  CommandAction6 implements Executor {
        private User _executor;
        public CommandAction6 (User _executor)
        {
            this._executor = _executor;
        }
        public void Execute()
        {
            _executor.Action6();
        }
    }

    public class User
    {

        public void Action1()
        {
            tryMove(curPiece, curX - 1, curY);
        }


        public void Action2() {
            tryMove(curPiece, curX + 1, curY);
        }


        public void Action3() {
            tryMove(curPiece.rotateRight(), curX, curY);
        }


        public void Action4() {
            tryMove(curPiece.rotateLeft(), curX, curY);
        }


        public void Action5() {
            dropDown();
        }


        public void Action6() {
            oneLineDown();
        }
    }

    class Invoker
    {
        private List<Executor> executorsList = new ArrayList<Executor>();
        public void takeExecutor(Executor executor){
            executorsList.add(executor);
        }
        public void placeExecutor(Executor executor){
            executor.Execute();
        }

    }



//State for pause
    class PauseOff extends State  {
        public PauseOff(){
           pause();
        }

    }




//Adaptee
    class Adaptee extends KeyAdapter implements KeyListener {
        public void keyPressed(KeyEvent e) {
            User user = new User();
            CommandAction1 c1 = new CommandAction1(user);
            CommandAction2 c2 = new CommandAction2(user);
            CommandAction3 c3 = new CommandAction3(user);
            CommandAction4 c4 = new CommandAction4(user);
            CommandAction5 c5 = new CommandAction5(user);
            CommandAction6 c6 = new CommandAction6(user);

            Invoker invoker = new Invoker();
            invoker.takeExecutor(c1);
            invoker.takeExecutor(c2);
            invoker.takeExecutor(c3);
            invoker.takeExecutor(c4);
            invoker.takeExecutor(c5);
            invoker.takeExecutor(c6);

            if (!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {
                return;
            }

            int keycode = e.getKeyCode();

            if (keycode == 'p' || keycode == 'P') {
                new PauseOff();
                return;
            }

            if (isPaused)
                return;

            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    invoker.placeExecutor(c1);
//                    tryMove(curPiece, curX - 1, curY);
                    break;
                case KeyEvent.VK_RIGHT:
                    invoker.placeExecutor(c2);
                    break;
                case KeyEvent.VK_DOWN:
                    invoker.placeExecutor(c3);
                    break;
                case KeyEvent.VK_UP:
                    invoker.placeExecutor(c4);
                    break;
                case KeyEvent.VK_SPACE:
                    invoker.placeExecutor(c5);
                    break;
                case 'd':
                    invoker.placeExecutor(c6);
                    break;
                case 'D':
                    invoker.placeExecutor(c6);
                    break;
            }

        }
    }
}