package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.negocio;


import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia.TentPersistence;


public class TentNegocio {

    private static TentPersistence tentPersistence = new TentPersistence();

    public static TentPersistence tentPersistence(){
        return tentPersistence;
    }

}
