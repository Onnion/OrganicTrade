package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio;

import java.util.ArrayList;
import java.util.List;

public class Tent {
    private List<TentItems> tentItems = new ArrayList<TentItems>();

    public List<TentItems> getTentItems(){
        return this.tentItems;
    }

    public void addTentItem(TentItems tentItems){
        this.tentItems.add(tentItems);
    }

}