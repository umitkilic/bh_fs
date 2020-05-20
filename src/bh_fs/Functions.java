/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bh_fs;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author samsung1
 */
public class Functions {
    
    public int findBlackHole(List<StarsPOJO> stars){
        double bestFitness=0.0;
        int bestIndex=0;
        int bestNumof1=0;
        for (int i = 0; i < stars.size(); i++) {
            if (stars.get(i).getFitnessVal()>bestFitness) {
                bestFitness=stars.get(i).getFitnessVal();
                bestIndex=i;
                bestNumof1=stars.get(i).getNumberof1s();
            }else if(stars.get(i).getFitnessVal()==bestFitness){
                if (stars.get(i).getNumberof1s()>bestNumof1) {
                    bestFitness=stars.get(i).getFitnessVal();
                    bestIndex=i;
                    bestNumof1=stars.get(i).getNumberof1s();
                }
            }
        }
        return bestIndex;
    }
    
    public double getTotalFitness(List<StarsPOJO> stars){
        double totalFitness=0.0;
        for (int i = 0; i < stars.size(); i++) {
            totalFitness+=stars.get(i).getFitnessVal();
        }
        
        return totalFitness;
    }
    
    public int[] changePosition(int BH[],int star[]){
        double              n=0.0;
        Random              rand=new Random(1);
        
        double starNew[]=new double[star.length];
        int star1[]=star.clone();
        int starBH[]=BH.clone();
        int starToBeReturned[]=new int[star.length];
                
                for (int j = 0; j < star.length; j++) {
                    n=rand.nextDouble();
                    starNew[j]=star1[j]+n*(starBH[j]-star1[j]);
                    if (Math.abs(Math.tanh(starNew[j]))>0.6) {
                        starToBeReturned[j]=1;
                    }
                }
                System.out.println("döndürülen:"+Arrays.toString(starToBeReturned));
        return starToBeReturned;
    }
    
    public int findFinalBest(List<StarsPOJO> stars){
        double maxfit=0.0;
        int finalbestindex=0;
        
        for (int i = 0; i < stars.size(); i++) {
            if (stars.get(i).getFitnessVal()>maxfit) {
                maxfit=stars.get(i).getFitnessVal();
                finalbestindex=i;
            }else if(stars.get(i).getFitnessVal()==maxfit){
                if (stars.get(finalbestindex).getNumberof1s()<stars.get(i).getNumberof1s()) {
                    maxfit=stars.get(i).getFitnessVal();
                    finalbestindex=i;
                }
            }
        }
        
        return finalbestindex;
    }

    public double findSimilarity(int[] star1,int[] star2){
        int a=0,b=0,c=0,d=0;
        double sim=0.0;
        
        for (int i = 0; i < star1.length; i++) {
            if (star1[i]==1 && star2[i]==1) {
                a++;
            }else if (star1[i]==0 && star2[i]==1) {
                b++;
            }else if (star1[i]==1 && star2[i]==0) {
                c++;
            }else if (star1[i]==0 && star2[i]==0) {
                d++;
            }
        }
        
        sim=(double) 3*a/(3*a+b+c);
        
        return sim;
    }
}
