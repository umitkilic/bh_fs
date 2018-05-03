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
    
    
    public List<StarsPOJO> createStarPopulation(String pathname,double MR,int foldnumber){
        Instances       data;
        StarsPOJO       star;
        List<StarsPOJO> stars=new ArrayList<>();
        data=this.ReadData(pathname);
        int             numofattr=data.numAttributes()-1;
        double          n=0.0;
        
        for (int i = 0; i < numofattr; i++) {
            star=new StarsPOJO();
            star=createOneStar(stars, pathname, MR, foldnumber);
            stars.add(star);
        }
        
        return stars;
    }
    
    public StarsPOJO createOneStar(List<StarsPOJO> stars,String pathname,double MR,int foldnumber){
        Instances       data;
        Random          rand = new Random(1);
        data=this.ReadData(pathname);
        double          n=0.0;
        int             numofattr=data.numAttributes()-1;
        StarsPOJO       star=new StarsPOJO();
        boolean         e=true;
        while(e){
            int star_array[]=new int[numofattr];
            for (int j = 0; j < numofattr; j++) {
                n = rand.nextDouble();
                if (n<MR) {
                    star_array[j]=1;
                }
            }
            e=existence(stars, star_array);

            if (!e) {
                star=new StarsPOJO();
                star.setStar(star_array);
                star.setNumberof1s(numberof1s(star_array));
                getFitnessValue gfv=new getFitnessValue();
                star.setFitnessVal(gfv.getFitnessOneByOne(star_array, foldnumber, pathname));
            }else{

            }
        }
        
        return star;
    }
    
    public Instances ReadData(String pathname){
        ConverterUtils.DataSource   source;
        Instances                   data=null;
        
        try {
            String path=pathname;
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
    
    public int numberof1s(int[] s){
        int t=0;
        for (int i = 0; i < s.length; i++) {
            t=t+s[i];
        }
        return t;
    }
}
