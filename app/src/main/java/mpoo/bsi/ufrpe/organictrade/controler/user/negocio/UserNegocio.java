package mpoo.bsi.ufrpe.organictrade.controler.user.negocio;

import java.util.List;

import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.TentItem;
import mpoo.bsi.ufrpe.organictrade.controler.user.dominio.User;
import mpoo.bsi.ufrpe.organictrade.controler.user.persistencia.UserPersistence;

public class UserNegocio{
    private UserPersistence userPersistence = new UserPersistence();

    public boolean validateRegister(String userId){
        return userPersistence.userNotRegistered(userId);
    }

    public boolean login(User user) {
        String password = Md5.encrypt(user.getPassword());
        return userPersistence.searchAndLoginUser(user.getUserName(),password);
    }

    public void edit(User user){
        userPersistence.userEdit(user);
    }

    public void editImg(User userLocal) {
        userPersistence.setImageUser(userLocal.getImage());
    }

    public void logout() {
        userPersistence.userLogoff();
    }

    public boolean getUserLogged() {
        return userPersistence.userLogged();
    }

    public void searchFromUserLogged() {
        userPersistence.searchFromUserLogged();
    }

    public void register(User userLocal) {
        userPersistence.registerUser(userLocal);
    }

    public List<Product> getFavorites() {
        return userPersistence.getFavorites();
    }

    public List<TentItem> getBuyingHistoryc() {
        return userPersistence.getBuyingHistoryc();
    }

    public List<TentItem> getSellingHistoryc() {
        return userPersistence.getSellingHistoryc();
    }

    public void registerFavorites(List<Product> selecionados) {
        userPersistence.registerFavorites(selecionados);
    }

    public User searchFromId(int idUser) {
        return userPersistence.searchFromId(idUser);
    }
}