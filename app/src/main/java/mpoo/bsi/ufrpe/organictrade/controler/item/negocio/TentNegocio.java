package mpoo.bsi.ufrpe.organictrade.controler.item.negocio;


import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.controler.item.persistencia.TentPersistence;


public class TentNegocio {

    private static TentPersistence tentPersistence = new TentPersistence();

    public void deleteTent(int tentId) {
        tentPersistence.deleteTent(tentId);
    }

    public ArrayList<Tent> getTentOfUser(int idUser) {
        return tentPersistence.getTentOfUser(idUser);
    }

    public void confirmTransaction() {
        tentPersistence.confirmTransaction();
    }

    public void registerTent(Tent tent) {
        tentPersistence.registerTent(tent);
    }
}
