package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio;

import java.util.ArrayList;
import java.util.List;

public class Tent {
    private List<TentItems> tent = new ArrayList<TentItems>();

    public List getTent(){
        return this.tent;
    }

    public void setTent(TentItems tentItems){
        this.tent.add(tentItems);
    }

}
