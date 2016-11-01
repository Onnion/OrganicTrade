package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio;

import java.util.ArrayList;
import java.util.List;

public class Tenda {
    private List<ItensDeTenda> itensDeTendas = new ArrayList<ItensDeTenda>();

    public List getItensDeTendas(){
        return this.itensDeTendas;
    }

    public void setItensDeTendas(ItensDeTenda itensDeTendas){
        this.itensDeTendas.add(itensDeTendas);
    }

}
