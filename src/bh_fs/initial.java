/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bh_fs;

import java.util.Arrays;
import java.util.Random;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author Umit Kilic
 */
public class initial {
    
    public void createStarPopulation(){
        Instances data;
        Random  rand = new Random();
        data=this.ReadData();
        int numofattr=data.numAttributes()-1;
        int starPopulation[][]=new int[numofattr][numofattr];
        double  n=0.0;
        double MR=0.5;
        
        
        for (int i = 0; i < numofattr; i++) {
            n = rand.nextDouble();
            if (n>MR) {
                
            }
        }
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
    
    public boolean existence(int[][] pop,int[] star){
        boolean exist=false;
        int a[]=new int[pop[0].length];
        
        for (int i = 0; i < pop.length; i++) {
            for (int j = 0; j < pop[0].length; j++) {
                a[j]=pop[i][j];
            }
            
            exist=Arrays.equals(a, star);
            if (exist) {
                break;
            }
        }
        
        return exist;
    }
}
