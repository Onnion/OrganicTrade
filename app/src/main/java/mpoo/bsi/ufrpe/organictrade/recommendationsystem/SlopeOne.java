package mpoo.bsi.ufrpe.organictrade.recommendationsystem;

import java.util.*;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.controler.item.negocio.ProductNegocio;
import mpoo.bsi.ufrpe.organictrade.controler.item.persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.recommendationsystem.persistencia.LoadData;

public class SlopeOne {
    private static ArrayList<Product> reommendation;
    private static ArrayList<RecommendationItem> recommendationItems;
    private Map<UserId,Map<ItemId,Float>> dataBase;
    private Map<ItemId,Map<ItemId,Float>> matrizDeNotas;

    private SlopeOne(Map<UserId,Map<ItemId,Float>> data) {
        dataBase = data;
        buildDiffMatrix();
    }

    public static ArrayList<Product> getRecomendation(){
        recommendationItems = new ArrayList<>();
        LoadData loadData = new LoadData();
        SlopeOne slopeOne = new SlopeOne(loadData.loadData());
        slopeOne.getProductsForRecomendation(slopeOne.predict(loadData.loadCurrentUser(recommendationItems)));
        return reommendation;
    }

    private Map<ItemId,Float> predict(Map<ItemId,Float> user) {
        HashMap<ItemId,Float> predictions = new HashMap<>();
        for (ItemId j : matrizDeNotas.keySet()) {
            predictions.put(j,0.0f);
        }
        for (ItemId j : user.keySet()) {
            for (ItemId k : matrizDeNotas.keySet()) {
                if(matrizDeNotas.get(k).get(j) != null && user.get(j) != null){
                    float newval = (matrizDeNotas.get(k).get(j) + user.get(j));
                    predictions.put(k, predictions.get(k) + newval);
                }
            }
        }
        for (ItemId j : predictions.keySet()) {
            predictions.put(j, predictions.get(j) / user.size());
        }
        for (ItemId j : user.keySet()) {
            predictions.put(j,user.get(j));
        }
        return predictions;
    }

    private void getProductsForRecomendation(Map<ItemId,Float> user) {
        ArrayList<RecommendationItem> productsRecommendation = new ArrayList<>();
        ProductPersistence productPersistence = new ProductPersistence();
        ProductNegocio productNegocio = new ProductNegocio();
        for (ItemId itemId : user.keySet()) {
            if(!(user.get(itemId).equals(0.0f))) {
                Product product = productPersistence.getProductById(Integer.parseInt(itemId.toString()));
                RecommendationItem recommendationItem = new RecommendationItem( product, user.get(itemId));
                int aux = 0;
                for(RecommendationItem recommendationItem1: recommendationItems){
                    if(recommendationItem.getProduct().getProductId() == recommendationItem1.getProduct().getProductId()) {
                        aux++;
                    }
                }
                if(aux==0){
                    productsRecommendation.add(recommendationItem);
                }
            }
        }
        Collections.sort(productsRecommendation);
        reommendation = new ArrayList<>();
        for (int i = 0; i<3; i++){
            reommendation.add(productNegocio.getProductById(productsRecommendation.get(i).getProduct().getProductId()));
        }
    }

    private void buildDiffMatrix() {
        matrizDeNotas = new HashMap<>();
        Map<ItemId,Map<ItemId,Integer>> mFreqMatrix = new HashMap<>();
        for(Map<ItemId,Float> user : dataBase.values()) {
            for(Map.Entry<ItemId,Float> entry: user.entrySet()) {
                if(!matrizDeNotas.containsKey(entry.getKey())) {
                    matrizDeNotas.put(entry.getKey(), new HashMap<ItemId,Float>());
                    mFreqMatrix.put(entry.getKey(), new HashMap<ItemId,Integer>());
                }

                for(Map.Entry<ItemId,Float> entry2: user.entrySet()) {
                    int oldcount = 0;
                    float olddiff = 0.0f;
                    if(mFreqMatrix.get(entry.getKey()).containsKey(entry2.getKey()))
                        oldcount = mFreqMatrix.get(entry.getKey()).get(entry2.getKey());
                    if(matrizDeNotas.get(entry.getKey()).containsKey(entry2.getKey()))
                        olddiff = matrizDeNotas.get(entry.getKey()).get(entry2.getKey());
                    float observeddiff = entry.getValue() - entry2.getValue();
                    mFreqMatrix.get(entry.getKey()).put(entry2.getKey(),oldcount + 1);
                    matrizDeNotas.get(entry.getKey()).put(entry2.getKey(),olddiff+observeddiff);
                }
            }
        }

        for (ItemId j : matrizDeNotas.keySet()) {
            for (ItemId i : matrizDeNotas.get(j).keySet()) {
                float oldvalue = matrizDeNotas.get(j).get(i);
                int count = mFreqMatrix.get(j).get(i);
                double d = (((oldvalue+count)*count)/(dataBase.size())) / Math.sqrt(( (Math.pow((oldvalue+count),2))-((Math.pow((oldvalue+count),2))/(Math.sqrt(count))) ) * ( (Math.pow(count,2))-((Math.pow(count,2))/(Math.sqrt(count)) )) );
                float f = (float) d;
                matrizDeNotas.get(j).put(i,f);
            }
        }
    }
}