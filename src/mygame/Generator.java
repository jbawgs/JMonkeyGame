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
    public float gridExtentX;
    public float gridExtentY;
    public ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
    public ArrayList<Geometry> boxes = new ArrayList<Geometry>();
    public boolean sorted = false;
    public Node rootNode;
    public AssetManager assetManager;
    public int[][] grid;


    public Generator(int roomNum, int minSize, int maxSize, Node rootNode, AssetManager assetmanager) {
        this.roomNum = roomNum;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.rootNode = rootNode;
        this.assetManager = assetManager;
        genRects(roomNum);
        sortRects();
        rectPosToInt();
        pruneRects();
        createGrid();
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
        while (count <= roomNum*roomNum) {
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
    
    public void rectPosToInt(){
        for(Rectangle r : rects){
            r.pos = new Vector2f((int)r.pos.x, (int)r.pos.y);
            //System.out.println(r.pos);
        }
    }
    
    public void pruneRects(){
        for (int j = rects.size() - 1; j >= 0; j--) {
            Rectangle r = rects.get(j);
            if (r.width * r.height < 50 || r.width * r.height > 150) {
                rects.remove(r);
            }
        }
    }
    
    public void sortMST(){
        int i = rects.size();
        ArrayList<Rectangle> mstRects = new ArrayList<Rectangle>();
        Vector2f pos = new Vector2f(-42, -42);
        Rectangle r1 = new Rectangle();
        for(Rectangle r2 : rects){
            if(pos.equals(new Vector2f(-42, -42))){
                pos = r2.pos;
                r1 = r2;
            }
            if(r2.pos.x < pos.x){
                pos = r2.pos;
                r1 = r2;
            }
        }
        mstRects.add(r1);
        rects.remove(r1);
    }
    
    public void createGrid(){
        int minX = 0;
        int minY = 0;
        int maxX = 0;
        int maxY = 0;
        for(Rectangle r : rects){
            r.or = new Vector2f(r.pos.x-(r.width/2), r.pos.y - (r.height/2));
            r.tr = new Vector2f(r.pos.x+(r.width/2), r.pos.y+(r.height/2));
            if(r.or.x < minX){
                minX = (int)r.or.x;
            }
            if(r.or.y < minY){
                minY = (int)r.or.y;
            }
            if(r.tr.x > maxX ){
                maxX = (int)r.tr.x;
            }
            if(r.tr.y > maxY){
                maxY = (int)r.tr.y;
            }
        }
        for(Rectangle r : rects){
            r.pos = new Vector2f(r.pos.x + Math.abs(minX), r.pos.y + Math.abs(minY));
            System.out.println(r.pos + " " + minX + " " + maxX + " " + minY + " " + maxY);
        }
    }
    
    public ArrayList<Rectangle> getRects(){
        return rects;
    }
}
