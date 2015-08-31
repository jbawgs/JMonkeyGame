/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;
import static mygame.tools.RandomRange.*;

/**
 *
 * @author carri
 */
public class Generator {

    public int roomNum;
    public int minSize;
    public int maxSize;
    public float minX = -15;
    public float maxX = 15;
    public float minY = -15;
    public float maxY = 15;
    public float strength = 4;
    public ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
    public ArrayList<Geometry> boxes = new ArrayList<Geometry>();
    public boolean sorted = false;
    public Node rootNode;
    public AssetManager assetManager;


    public Generator(int roomNum, int minSize, int maxSize, Node rootNode, AssetManager assetmanager) {
        this.roomNum = roomNum;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.rootNode = rootNode;
        this.assetManager = assetManager;
        genRects(roomNum);
        sortRects();
    }

    public void genRects(int roomNum) {
        for (int i = 0; i < roomNum; i++) {
            //width, height, Vec2Pos, ID
            Rectangle r = new Rectangle(
                    RandomRange(minSize, maxSize),
                    RandomRange(minSize, maxSize),
                    new Vector2f(RandomRange(minX, maxX), RandomRange(minY, maxY)), i);
            rects.add(r);
        }
    }

    public void sortRects() {

        int count = 0;
        while (count <= roomNum) {
            for (Rectangle r1 : rects) {
                Vector2f startPos = r1.pos;
                for (Rectangle r2 : rects) {
                    if (r1.intersects(r2) && r1 != r2) {
                        Vector2f direction = r1.pos.subtract(r2.pos);
                        direction.normalizeLocal();
                        direction = direction.mult(strength);
                        r1.pos = r1.pos.add(direction);
                    }
                }
                if (r1.pos.equals(startPos)) {
                    ++count;
                    //System.out.println("PLUS");
                } else {
                    --count;
                   // System.out.println("MINUS");
                }
                //System.out.println(count);
            }
        }
    }
    public ArrayList<Rectangle> getRects(){
        return rects;
    }
}
