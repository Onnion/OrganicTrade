package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.negocio;

import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia.ProductPersistence;

public class ProductNegocio {

    private static ProductPersistence productPersistence = new ProductPersistence();

    public static ProductPersistence productPersistence(){
        return productPersistence;
    }

}
