/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bh_fs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

/**
 *
 * @author samsung1
 */
public class getFitnessValue {
    public double getFitnessOneByOne(int[] star,int foldnumber,String pathname){
        Classifier classifier;
        Evaluation eval;
        initial init=new initial();
        Instances data=init.ReadData(pathname);
        int N=data.numAttributes();
        double fitness=0.0;
        Instances data1=data;
        
        try {
            //classifier=new NaiveBayes();
            classifier=new IBk(3); // sınıflandırıcı oluşturuldu
            //classifier=new RandomForest();
            data=this.deleteZeros(star, N, data);
            eval=new Evaluation(data); // degerlendirici olusturuldu
            eval.crossValidateModel(classifier, data1, foldnumber, new Random(1));
            fitness=eval.weightedFMeasure();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return fitness;
    }
    
    public Instances deleteZeros(int[] star,int N,Instances data2){
        List<Integer> deleteIndexes=new ArrayList<Integer>();
        
        for(int i=0;i<star.length;i++){
            if (star[i]==0) {
                deleteIndexes.add(i);
            }
        }
        
        int girildi=0;
        
        Instances dataset=data2;
        for (int i = 0; i < deleteIndexes.size(); i++) {
            dataset.deleteAttributeAt(deleteIndexes.get(i)-girildi);
            girildi++;
        }
        
        return dataset;
        
    }
}
