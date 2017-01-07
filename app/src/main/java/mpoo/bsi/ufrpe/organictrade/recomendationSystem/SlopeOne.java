package mpoo.bsi.ufrpe.organictrade.recomendationSystem;

import java.util.*;

import mpoo.bsi.ufrpe.organictrade.recomendationSystem.persistencia.LoadData;

public class SlopeOne {
    private static LoadData loadData = new LoadData();
    private Map<UserId,Map<ItemId,Float>> mData;
    private Map<ItemId,Map<ItemId,Float>> mDiffMatrix;
    private Map<ItemId,Map<ItemId,Integer>> mFreqMatrix;

    public static void getRecomendation(){
        Map<UserId,Map<ItemId,Float>> data = new HashMap<>();
        ItemId item13 = new ItemId("13");
        ItemId item1 = new ItemId("3");
        ItemId item2 = new ItemId("2");
        HashMap<ItemId,Float> user = new HashMap<>();
        user.put(item13,1.0f);
        user.put(item1,1.0f);
        user.put(item2,1.0f);
        data.put(new UserId("3"),user);
        SlopeOne so = new SlopeOne(data);
        //HashMap<ItemId,Float> user1 = loadData.loadCurrentUser();

        HashMap<ItemId,Float> user1 = new HashMap<>();
        user1.put(item2,1.0f);
        System.out.println(user1);
        SlopeOne.print(so.predict(user1));
    }

    public SlopeOne(Map<UserId,Map<ItemId,Float>> data) {
        mData = data;
        for(Map.Entry<UserId,Map<ItemId,Float>> itemIdFloatEntry: mData.entrySet()){
            System.out.println(itemIdFloatEntry);
        }
        buildDiffMatrix();
    }


    public Map<ItemId,Float> predict(Map<ItemId,Float> user) {
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


    public static void print(Map<ItemId,Float> user) {
        for (ItemId j : user.keySet()) {
            System.out.println(" "+ j+ " --> "+user.get(j));
        }
    }

    public void buildDiffMatrix() {
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
}


