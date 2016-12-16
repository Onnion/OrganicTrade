package mpoo.bsi.ufrpe.organictrade.controler.item.negocio;

import mpoo.bsi.ufrpe.organictrade.controler.item.persistencia.ProductPersistence;

public class ProductNegocio {

    private static ProductPersistence productPersistence = new ProductPersistence();

    public static ProductPersistence productPersistence(){
        return productPersistence;
    }

}
