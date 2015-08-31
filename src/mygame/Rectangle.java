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
import com.jme3.scene.shape.Quad;

/**
 *
 * @author carri
 */
public class Rectangle {
    public int width;
    public int height;
    public int id;
    public Vector2f pos;
    public float left;
    public float right;
    public float top;
    public float bottom;
    
    public Rectangle(int width, int height, Vector2f pos, int id){
        this.width = width;
        this.height = height;
        this.pos = pos;
        this.left = pos.x - (width/2);
        this.right = pos.x + (width/2);
        this.top = pos.y + (height/2);
        this.bottom = pos.y - (height/2);
        this.id = id;
    }
    
    public boolean intersects(Rectangle rect) {
        //System.out.println("INTERSECTED ID: " + this.id + " " + this.pos + " ID " + rect.id + " " + rect.pos);
        return (Math.abs(this.pos.x - rect.pos.x) * 1 < (this.width + rect.width))
                && (Math.abs(this.pos.y - rect.pos.y) * 1 < (this.height + rect.height));
    }
}
