/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bh_fs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Umit Kilic
 */
public class Main {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        List<StarsPOJO>     stars=new ArrayList<>();
        initial             in=new initial();
        
        
        stars=in.createStarPopulation();
        
        for (int i = 0; i < stars.size(); i++) {
            System.out.println("gelen:"+ Arrays.toString(stars.get(i).getStar()));
        }
    }
    
}
