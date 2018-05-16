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
        String              pathname="glass.arff";
        int                 foldnumber=10;
        int                 iteration=2;
        double              MR=0.3;
        List<StarsPOJO>     stars=new ArrayList<>();
        initial             in=new initial();
        Functions           functions=new Functions();
        StarsPOJO           star;
        
        
        stars=in.createStarPopulation(pathname,MR,foldnumber); // starlar oluşturuluyor
        int blackholeindex=0;
       
        for (int i = 0; i < stars.size(); i++) {
            System.out.println("Başlangıç:"+ Arrays.toString(stars.get(i).getStar())+" fitness:"+stars.get(i).getFitnessVal()+" numof1:"+stars.get(i).getNumberof1s());
        }
        
        int it_counter=0;
        double R;
        while(it_counter<iteration){
            blackholeindex=functions.findBlackHole(stars);
            System.out.println("Seçilen BlackHole index:"+blackholeindex);
        
            for (int i = 0; i < stars.size(); i++) {
                if (i==blackholeindex) {
                    continue;
                }
                if (stars.get(i).getFitnessVal()>stars.get(blackholeindex).getFitnessVal()) {
                    blackholeindex=i;
                }else if((stars.get(i).getFitnessVal()==stars.get(blackholeindex).getFitnessVal()) && (stars.get(i).getNumberof1s()<stars.get(blackholeindex).getNumberof1s())){
                    blackholeindex=i;
                }
                R=stars.get(blackholeindex).getFitnessVal()/functions.getTotalFitness(stars);
                System.out.println("R:"+R);
                
                if (Math.sqrt(Math.pow(stars.get(blackholeindex).getFitnessVal()-stars.get(i).getFitnessVal(), 2))<R && i!=blackholeindex) {
                    star=new StarsPOJO();
                    star=in.createOneStar(stars, pathname, MR,foldnumber);
                    stars.remove(i);
                    stars.add(i, star);
                    System.out.println(i+". kaldırıldı. Yeni olusturulup eklenen:"+Arrays.toString(star.getStar())+" fit:"+star.getFitnessVal()+" num1:"+star.getNumberof1s());
                }
                
            }
            
            for (int k = 0; k < stars.size(); k++) {
                System.out.println("Stars:"+ Arrays.toString(stars.get(k).getStar())+" fitness:"+stars.get(k).getFitnessVal()+" numof1:"+stars.get(k).getNumberof1s());
            }
            
            for (int i = 0; i < stars.size(); i++) {
                
                double Xnew[]=new double[stars.size()];
                int Xnew1[]=new int[stars.size()];
                int Xold[]=stars.get(i).getStar().clone();
                int Xbh[]=stars.get(blackholeindex).getStar().clone();
                for (int j = 0; j < stars.size(); j++) {
                    Xnew[j]=Xold[j]+0.6*(Xbh[j]-Xold[j]);
                    
                    if (Math.abs(Math.tanh(Xnew[j]))>0.6) {
                        Xnew1[j]=1;
                    }
                }
                star=new StarsPOJO();
                star=in.createOneStarWithArray(stars, pathname, MR, foldnumber, Xnew1);
                if (star.getFitnessVal()!=0.0) {
                    stars.remove(i);
                    stars.add(i,star);
                }
                
            }
            
            for (int k = 0; k < stars.size(); k++) {
                System.out.println("Starss:"+ Arrays.toString(stars.get(k).getStar())+" fitness:"+stars.get(k).getFitnessVal()+" numof1:"+stars.get(k).getNumberof1s());
            }
            it_counter++;
        }
    }
    
}
