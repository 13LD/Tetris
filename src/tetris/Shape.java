package tetris;


public abstract class Shape {

    public enum Tetrominoes { NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape;
    };
    public Shape.Tetrominoes pieceShape;
    public int coords[][];
    public int[][][] coordsTable;
    abstract Tetrominoes getShape();
    abstract void setShape(Tetrominoes shape);
    abstract void setX(int index, int x);
    abstract void setY(int index, int y);


    abstract void setRandomShape();
    abstract int minX();
    abstract int minY();
    abstract Shape rotateLeft();
    abstract Shape rotateRight();


}

