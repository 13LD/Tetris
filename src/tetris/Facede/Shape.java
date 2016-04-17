package tetris.Facede;

import java.util.Random;
import java.lang.Math;


public interface Shape {

    public enum Tetrominoes { NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape;
    };
    Tetrominoes getShape();
    void setShape(Tetrominoes shape);
    void setX(int index, int x);
    void setY(int index, int y);


    void setRandomShape();
    int minX();
    int minY();
    Shape rotateLeft();
    Shape rotateRight();

}

