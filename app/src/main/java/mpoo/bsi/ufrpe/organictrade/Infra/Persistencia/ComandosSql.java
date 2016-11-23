package mpoo.bsi.ufrpe.organictrade.Infra.Persistencia;

public class ComandosSql {

    public static String sqlCreateUserTable(){
        String sqlCreateUserTable =
                "CREATE TABLE " +DatabaseHelper.getTableUserName()+ "( "
                        + DatabaseHelper.getColumnUserId()+" integer primary key autoincrement not null , "
                        + DatabaseHelper.getColumnUserUsername() + " text unique not null ,"
                        + DatabaseHelper.getColumnUserPassword() + " text not null , "
                        + DatabaseHelper.getColumnUserEmail() + " text unique not null , "
                        + DatabaseHelper.getColumnUserName() + " text , "
                        + DatabaseHelper.getColumnUserPhone() + " text , "
                        + DatabaseHelper.getColumnUserAdress() + " text , "
                        + DatabaseHelper.getColumnUserImg() + " text"
                        + ");";
        return(sqlCreateUserTable);
    }

    public static String sqlCreateTentItemsTable(){
        String sqlCreateTentItemsTable =
                "CREATE TABLE "+ DatabaseHelper.getTableTentitemsName() + "( "
                        + DatabaseHelper.getColumnTentitemsId()+" integer primary key autoincrement not null , "
                        + DatabaseHelper.getColumnTentitemsAmount() +" text not null , "
                        + DatabaseHelper.getColumnTentitemsPrice() + " text not null , "
                        + DatabaseHelper.getColumnTentitemsUnity() + " text no null , "
                        + DatabaseHelper.getColumnTentitemsProductId()+ " integer , "
                        + DatabaseHelper.getColumnTentitemsUserId()+ " integer , "
                        + DatabaseHelper.getColumnTentitemsImg() + " text , "
                        + "foreign key ( "+DatabaseHelper.getColumnTentitemsProductId()+" ) references "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductId()+") , "
                        + "foreign key ( "+DatabaseHelper.getColumnTentitemsUserId()+" ) references "+DatabaseHelper.getTableUserName()+" ("+DatabaseHelper.getColumnUserId()+")"
                        + ");";
        return(sqlCreateTentItemsTable);

    }

    public static String sqlCreateUserLoggedTable(){
        String sqlCreateUserLoggedTable =
                "CREATE TABLE "+ DatabaseHelper.getTableUserLoggedName() + "( "
                        + DatabaseHelper.getColumnUserLoggedId()+" integer , "
                        + "foreign key ( "+DatabaseHelper.getColumnUserLoggedId()+" ) references "+DatabaseHelper.getTableUserName()+" ( "+DatabaseHelper.getColumnUserId()+" )"
                        + ");";
        return(sqlCreateUserLoggedTable);
    }

    public static String sqlCreateProductTable(){
        String sqlCreateProductTable =
                "CREATE TABLE "+ DatabaseHelper.getTableProductName() + "( "
                        + DatabaseHelper.getColumnProductId()+" integer primary key autoincrement not null , "
                        + DatabaseHelper.getColumnProductName()+" text"
                        + ");";
        return(sqlCreateProductTable);
    }

    public static String sqlDropTableUsuarioLogado(){
        String sqlDropTableUsuarioLogado = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserLoggedName();
        return (sqlDropTableUsuarioLogado);
    }

    public static String sqlDropTableUsuario() {
        String sqlDropTableUsuario = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserName();
        return(sqlDropTableUsuario);
    }

    public static String sqlDropTableItensDeTenda() {
        String sqlDropTableItensDeTenda = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableTentitemsName();
        return (sqlDropTableItensDeTenda);
    }

    public static String sqlDropTableProduct() {
        String sqlDropTableProduct = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableProductName();
        return (sqlDropTableProduct);
    }

    public static String sqlProductIdByName(){
        String sqlProductIdByName=
                "SELECT * FROM "+DatabaseHelper.getTableProductName() +" WHERE "
                        +DatabaseHelper.getColumnProductName() +" =?;";
        return (sqlProductIdByName);
    }

    public static String productTableIsEmpty(){
        String productTableIsEmpty=
                "SELECT * FROM "+DatabaseHelper.getTableProductName()+";";
        return (productTableIsEmpty);
    }

    public static String sqlItemFromUser(){
        String sqlItemFromUser =
                "SELECT * FROM " + DatabaseHelper.getTableTentitemsName() + " WHERE "
                        + DatabaseHelper.getColumnTentitemsUserId() + " =?;";
        return (sqlItemFromUser);
    }

    public static String sqlAllItems(){
        String sqlAllItems =
                "SELECT * FROM " + DatabaseHelper.getTableTentitemsName() + " WHERE NOT "
                        + DatabaseHelper.getColumnTentitemsUserId() + " =?;";
        return (sqlAllItems);
    }

    public static String sqlUserFromLoginAndPass(){
        String sqlUserFromLoginAndPass =
                "SELECT * FROM "+DatabaseHelper.getTableUserName() +" WHERE "
                        +DatabaseHelper.getColumnUserUsername() +" =? AND "
                        +DatabaseHelper.getColumnUserPassword() + " =?;";
        return (sqlUserFromLoginAndPass);
    }

    public static String sqlUserFromLogin(){
        String sqlUserFromLogin =
                "SELECT * FROM "+DatabaseHelper.getTableUserName() +" WHERE "
                        +DatabaseHelper.getColumnUserUsername() +" =?;";
        return (sqlUserFromLogin);
    }

    public static String sqlUserFromId(){
        String sqlUserFromId =
                "SELECT * FROM "+DatabaseHelper.getTableUserName() +" WHERE "
                        +DatabaseHelper.getColumnUserId() +" =?;";
        return (sqlUserFromId);
    }

    public static String sqlLimparTabela(){
        String sqlClearDisplay = "DELETE FROM " + DatabaseHelper.getTableUserLoggedName();
        return (sqlClearDisplay);
    }

    public static String sqlSearchInBaseFromLoggedUser(){
        String sqlSearchInBaseFromLoggedUser =
                "SELECT "+ DatabaseHelper.getColumnUserLoggedId()+" FROM "+DatabaseHelper.getTableUserLoggedName() +";";
        return (sqlSearchInBaseFromLoggedUser);
    }

    public static String sqlUserLogged(){
        String sqlUserLogged =
                "SELECT * FROM " + DatabaseHelper.getTableUserLoggedName()+";";
        return (sqlUserLogged);
    }

    public static String sqlUserLogoff(){
        String sqlUserLogoff =
                "SELECT * FROM "+DatabaseHelper.getTableUserLoggedName() +" WHERE "
                        +DatabaseHelper.getColumnUserLoggedId()+" =?;";
        return (sqlUserLogoff);
    }

    public static String sqlProductNameById() {
        String sqlProductNameById=
                "SELECT * FROM "+DatabaseHelper.getTableProductName() +" WHERE "
                        +DatabaseHelper.getColumnProductId() +" =?;";
        return (sqlProductNameById);
    }
}