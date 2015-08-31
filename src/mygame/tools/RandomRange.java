/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.tools;

import java.util.Random;

/**
 *
 * @author carri
 */
public  class RandomRange {
    public static int RandomRange(int min, int max){
          Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    public static float RandomRange(float min, float max){
       Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }
}
