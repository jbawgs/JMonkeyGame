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
    int roomNum = 100;
    int minSize = 4;
    int maxSize = 20;
    
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
            geom.setLocalTranslation(r.pos.x, r.pos.y, -180f);
            rootNode.attachChild(geom);            
        }        
    }
}
