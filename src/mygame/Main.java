package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;
import java.util.Random;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    Generator generator;
    int roomNum = 1200;
    int minSize = 2;
    int maxSize = 10;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        generator = new Generator(roomNum, minSize, maxSize, rootNode, assetManager); 
        debugDrawRects(generator.getRects());
    }

    @Override
    public void simpleUpdate(float tpf) {

    }

     public void debugDrawRects(ArrayList<Rectangle> rects){
        for (Rectangle r : rects) {
            Box b = new Box(r.width, r.height, 0.2f); // create cube shape
            Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
            mat.setColor("Color", ColorRGBA.randomColor());   // set color of material to blue
            geom.setMaterial(mat);                   // set the cube's material
            geom.setLocalTranslation(r.pos.x, r.pos.y, -80f);
            rootNode.attachChild(geom);            
        }        
    }
    public void sortBoxes() {
//        int count = 0;
//        while (count < roomNum) {
//            for (Geometry b1 : boxes) {
//                for (Geometry b2 : boxes) {
//                    Vector2f startPos = new Vector2f(b1.getLocalTranslation().x, b1.getLocalTranslation().y);
//                    Vector2f b1Pos = new Vector2f(b1.getLocalTranslation().x, b1.getLocalTranslation().y);
//                    Vector2f b2Pos = new Vector2f(b2.getLocalTranslation().x, b2.getLocalTranslation().y);
//                    Vector2f direction = new Vector2f();
//                    if (rectCollide(b1, b2) && b1 != b2) {
//
//                        direction = b1Pos.subtract(b2Pos);
//                        direction.normalizeLocal();
//                        System.out.println("BOX INTERSECT " + b1.getLocalTranslation() + " " + b2.getLocalTranslation());
//                        b1Pos = b1Pos.add(direction);
//                        b1.setLocalTranslation(b1Pos.x, b1Pos.y, -60);
//
//                    }
//                    System.out.println(count);
//                    if (b1Pos.equals(startPos)) {
//                        ++count;
//                        System.out.println("PLUS");
//                    } else {
//                        System.out.println("Start: " + startPos + " Current " + b1Pos);
//                        count = count - roomNum;
//                    }
//                }
//            }
//        }
//        sorted = true;
    }
}
