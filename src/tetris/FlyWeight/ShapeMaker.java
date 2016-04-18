package tetris.FlyWeight;

import java.util.Random;
import java.lang.Math;
/**
 * Created by lysogordima on 17.04.16.
 */
public class ShapeMaker extends ZShape implements Shape{
    ZShape zzShape;
    LShape llShape;
    MirroredLShape mirroredllShape;
    SquareShape ssquareShape;
    SShape ssShape;
    TShape ttShape;
    LineShape llineShape;
    
    public Tetrominoes pieceShape;
    public int coords[][];
    public int[][][] coordsTable;
    public Tetrominoes getShape()  { return pieceShape; }

    public void setX(int index, int x) { coords[index][0] = x; }
    public void setY(int index, int y) { coords[index][1] = y; }
    public int x(int index) { return coords[index][0]; }
    public int y(int index) { return coords[index][1]; }
    Tetrominoes[] values = Tetrominoes.values();
//
//
    public ShapeMaker(){
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        if (x == 1) {
            zzShape = new ZShape();
        }
        else if (x == 2) {
            ssShape = new SShape();
        }
        else if (x == 3) {
            llineShape = new LineShape();
        }
        else if (x == 4) {
            ttShape = new TShape();
        }
        else if (x == 5) {
            ssquareShape = new SquareShape();
        }
        else if (x == 6) {
            llShape = new LShape();
        }
        else {
            mirroredllShape = new MirroredLShape();
        }
    }
    public void setShape(Tetrominoes shape) {
        pieceShape = shape;
    }
    public ShapeMaker rotateLeft()
    {
        if (pieceShape == Tetrominoes.SquareShape)
            return this;

        ShapeMaker result = new ShapeMaker();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }

    public ShapeMaker rotateRight()
    {
        if (pieceShape == Tetrominoes.SquareShape)
            return this;

        ShapeMaker result = new ShapeMaker();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }
    public void setRandomShape()
    {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        setShape(values[x]);
    }
//
    public int minX()
    {
        int m = coords[0][0];
        for (int i=0; i < 4; i++) {
            m = Math.min(m, coords[i][0]);
        }
        return m;
    }


    public int minY()
    {
        int m = coords[0][1];
        for (int i=0; i < 4; i++) {
            m = Math.min(m, coords[i][1]);
        }
        return m;
    }

}
