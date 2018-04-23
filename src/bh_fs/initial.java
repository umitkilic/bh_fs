/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bh_fs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author Umit Kilic
 */
public class initial {
    
    public List<StarsPOJO> createStarPopulation(){
        Instances       data;
        Random          rand = new Random(1);
        data=this.ReadData();
        int             numofattr=data.numAttributes()-1;
        StarsPOJO       star=new StarsPOJO();
        int             star_array[]=new int[numofattr];
        double          n=0.0;
        double          MR=0.5;
        List<StarsPOJO> stars=new ArrayList<>();
        
        
        for (int i = 0; i < numofattr; i++) {
            for (int j = 0; j < numofattr; j++) {
                n = rand.nextDouble();
                if (n<MR) {
                    star_array[j]=1;
                }
            }
            boolean e=this.existence(stars, star_array);
            System.out.println("e:"+e);
            if (!e) {
                star.setStar(star_array);
                stars.add(star);
            }else{
                i--;
            }
            
        }
        
        return stars;
    }
    
    public Instances ReadData(){
        ConverterUtils.DataSource   source;
        Instances                   data=null;
        
        try {
            String path="glass.arff";
            source=new ConverterUtils.DataSource(path);
            data=source.getDataSet();
            data.setClassIndex(data.numAttributes()-1); // class indexi belirleniyor
            
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
          
        return data;
    }
    
    public boolean existence(List<StarsPOJO> stars,int[] star){
        boolean exist=false;
        for (int i = 0; i < stars.size(); i++) {
            exist=Arrays.equals(stars.get(i).getStar(), star);
            if (exist) {
                break;
            }
        }
        
        return exist;
    }
}
