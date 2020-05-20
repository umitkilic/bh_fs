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
        String              pathname="C://Absenteeism_at_work_3class_eqf.arff";
        int                 foldnumber=10;
        int                 iteration=100;
        double              MR=0.3;
        double              n=0.0;
        Random              rand=new Random(1);
        List<StarsPOJO>     stars=new ArrayList<>();
        initial             in=new initial();
        Functions           functions=new Functions();
        StarsPOJO           star;
        int                 BHindex=0;
        int                 it_counter=0;
        double              R;
        
        
        
        stars=in.createStarPopulation(pathname,MR,foldnumber); // create stars
        
       
        for (int i = 0; i < stars.size(); i++) {
            System.out.println("Başlangıç:"+ Arrays.toString(stars.get(i).getStar())+" fitness:"+stars.get(i).getFitnessVal()+" numof1:"+stars.get(i).getNumberof1s());
        }
        
        
        while(it_counter<iteration){
            System.out.println("ITERATION:"+it_counter);
            BHindex=functions.findBlackHole(stars); // find BH
            System.out.println("Seçilen BlackHole index:"+BHindex);
        
            for (int i = 0; i < stars.size(); i++) {
                if (i==BHindex)
                    continue;
                int changedStar[]=functions.changePosition(stars.get(BHindex).getStar(), stars.get(i).getStar()).clone();
                System.out.println("Degismis star:"+Arrays.toString(changedStar));
                star=new StarsPOJO();
                star=in.createOneStarWithArray(stars, pathname, MR, foldnumber, changedStar);
                
                if (star.getFitnessVal()!=0.0) {
                    stars.remove(i);
                    stars.add(i,star);
                }
                System.out.println("yeni olusturulan:"+Arrays.toString(stars.get(i).getStar())+" fitness:"+stars.get(i).getFitnessVal()+" numof1:"+stars.get(i).getNumberof1s());                
            }
            for (int i = 0; i < stars.size(); i++) {
            System.out.println("after change:"+ Arrays.toString(stars.get(i).getStar())+" fitness:"+stars.get(i).getFitnessVal()+" numof1:"+stars.get(i).getNumberof1s());
            }
            BHindex=functions.findBlackHole(stars); // find horizon
            System.out.println("New Seçilen BlackHole index:"+BHindex);
            
            R=stars.get(BHindex).getFitnessVal()/functions.getTotalFitness(stars); // find horizon
            System.out.println("R value:"+R);
            
            
            for (int k = 0; k < stars.size(); k++) {
                System.out.println("Stars:"+ Arrays.toString(stars.get(k).getStar())+" \tfitness:"+stars.get(k).getFitnessVal()+" \tnumof1:"+stars.get(k).getNumberof1s());
            }
            
            for (int i = 0; i < stars.size(); i++) {
                if (i==BHindex) 
                    continue;
                
                //if (Math.sqrt(Math.pow(stars.get(BHindex).getFitnessVal()-stars.get(i).getFitnessVal(), 2))<R) {
                double sim=functions.findSimilarity(stars.get(i).getStar(), stars.get(BHindex).getStar());
                System.out.println("sim:"+sim+" R:"+R);
                if (sim<R) {
                    star=new StarsPOJO();
                    star=in.createOneStar(stars, pathname, MR, foldnumber);
                    /*if (star.getFitnessVal()<stars.get(i).getFitnessVal()) {
                        System.out.println("yeni olusturulan daha kucuk.Değişim yok. "+i);
                    }else{*/
                    stars.remove(i);
                    stars.add(i,star);
                    System.out.println(i+". kaldırıldı. Yeni oluşturulup eklenen:"+Arrays.toString(star.getStar())+" fitness:"+star.getFitnessVal()+" numof1:"+star.getNumberof1s());
                //}
                    
                }
                
            }
            
            it_counter++;
        }
        int finalbest=functions.findFinalBest(stars);
        System.out.println("Seçilen:"+ Arrays.toString(stars.get(finalbest).getStar())+" fitness:"+stars.get(finalbest).getFitnessVal()+" num of 1:"+stars.get(finalbest).getNumberof1s());
    }
    
}
