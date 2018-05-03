/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bh_fs;

import java.util.List;

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
    
}
