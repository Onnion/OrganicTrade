package mpoo.bsi.ufrpe.organictrade.recomendationSystem;

import java.util.*;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.controler.item.persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.recomendationSystem.persistencia.LoadData;

public class SlopeOne {

    private Map<UserId,Map<ItemId,Float>> mData;
    private Map<ItemId,Map<ItemId,Float>> mDiffMatrix;
    private Map<ItemId,Map<ItemId,Integer>> mFreqMatrix;

    public SlopeOne() {
        LoadData loadData = new LoadData();
        mData = loadData.loadData();
        buildDiffMatrix();
    }

    private void buildDiffMatrix() {
        mDiffMatrix = new HashMap<>();
        mFreqMatrix = new HashMap<>();
        for(Map<ItemId,Float> user : mData.values()) {
            for(Map.Entry<ItemId,Float> entry: user.entrySet()) {
                if(!mDiffMatrix.containsKey(entry.getKey())) {
                    mDiffMatrix.put(entry.getKey(), new HashMap<ItemId,Float>());
                    mFreqMatrix.put(entry.getKey(), new HashMap<ItemId,Integer>());
                }
                for(Map.Entry<ItemId,Float> entry2: user.entrySet()) {
                    int oldcount = 0;
                    if(mFreqMatrix.get(entry.getKey()).containsKey(entry2.getKey()))
                        oldcount = mFreqMatrix.get(entry.getKey()).get(entry2.getKey());
                    float olddiff = 0.0f;
                    if(mDiffMatrix.get(entry.getKey()).containsKey(entry2.getKey()))
                        olddiff = mDiffMatrix.get(entry.getKey()).get(entry2.getKey());
                    float observeddiff = entry.getValue() - entry2.getValue();
                    mFreqMatrix.get(entry.getKey()).put(entry2.getKey(),oldcount + 1);
                    mDiffMatrix.get(entry.getKey()).put(entry2.getKey(),olddiff+observeddiff);
                }
            }
        }
        for (ItemId j : mDiffMatrix.keySet()) {
            for (ItemId i : mDiffMatrix.get(j).keySet()) {
                float oldvalue = mDiffMatrix.get(j).get(i);
                int count = mFreqMatrix.get(j).get(i);
                mDiffMatrix.get(j).put(i,oldvalue/count);
            }
        }
    }

    private Map<ItemId,Float> predict(Map<ItemId,Float> user) {
        HashMap<ItemId,Float> predictions = new HashMap<>();
        HashMap<ItemId,Integer> frequencies = new HashMap<>();
        for (ItemId j : mDiffMatrix.keySet()) {
            frequencies.put(j,0);
            predictions.put(j,0.0f);
        }
        for (ItemId j : user.keySet()) {
            for (ItemId k : mDiffMatrix.keySet()) {
                try {
                    float newval = ( mDiffMatrix.get(k).get(j) + user.get(j)) * mFreqMatrix.get(k).get(j);
                    predictions.put(k, predictions.get(k)+newval);
                    frequencies.put(k, frequencies.get(k)+mFreqMatrix.get(k).get(j));
                } catch(NullPointerException e) {}
            }
        }
        HashMap<ItemId,Float> cleanpredictions = new HashMap<>();
        for (ItemId j : predictions.keySet()) {
            if (frequencies.get(j)>0) {
                cleanpredictions.put(j, predictions.get(j)/frequencies.get(j));
            }
        }
        for (ItemId j : user.keySet()) {
            cleanpredictions.put(j,user.get(j));
        }
        return cleanpredictions;
    }

    public ArrayList<Product> getRecomendation(){
        ProductPersistence productPersistence = new ProductPersistence();
        LoadData loadData = new LoadData();
        ArrayList<Product> productsForRecomendation = new ArrayList<>();
        ArrayList<String> idsForRecomendation = new ArrayList<>();

        HashMap<ItemId,Float> currentUser = loadData.loadCurrentUser();

        for (ItemId j : predict(currentUser).keySet()) {
            idsForRecomendation.add(j.toString());
        }

        for (String id: idsForRecomendation){
            productsForRecomendation.add(productPersistence.getProductById(Integer.parseInt(id)));
        }
        return productsForRecomendation;
    }
}


