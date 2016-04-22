package tetris;

import java.util.Random;
import java.lang.Math;
/**
 * Created by lysogordima on 17.04.16.
 */
public class ShapeMaker  {
    ZShape zzShape;
    LShape llShape;
    MirroredLShape mirroredllShape;
    SquareShape ssquareShape;
    SShape ssShape;
    TShape ttShape;
    LineShape llineShape;
    
    public Shape.Tetrominoes pieceShape;
    public Shape shape;
    public int coords[][];
    public int[][][] coordsTable;
    public Shape.Tetrominoes getShape()  { return pieceShape; }

    public void setX(int index, int x) { coords[index][0] = x; }
    public void setY(int index, int y) { coords[index][1] = y; }
    public int x(int index) { return coords[index][0]; }
    public int y(int index) { return coords[index][1]; }
    Shape.Tetrominoes[] values = Shape.Tetrominoes.values();
//
//
    public Shape  ShapeMake(){
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        if (x == 1) {
            shape = new ZShape();
        }
        else if (x == 2) {
            shape = new SShape();
        }
        else if (x == 3) {
            shape = new LineShape();
        }
        else if (x == 4) {
            shape = new TShape();
        }
        else if (x == 5) {
            shape = new SquareShape();
        }
        else if (x == 6) {
            shape = new LShape();
        }
        else {
            shape = new MirroredLShape();
        }
        return shape;
    }

}
