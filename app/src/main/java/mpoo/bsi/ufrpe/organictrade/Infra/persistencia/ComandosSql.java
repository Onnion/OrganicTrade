package mpoo.bsi.ufrpe.organictrade.Infra.persistencia;

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
                        + DatabaseHelper.getColumnTentitemsTentId()+ " integer , "
                        + DatabaseHelper.getColumnTentitemsImg() + " text , "
                        + "foreign key ( "+DatabaseHelper.getColumnTentitemsProductId()+" ) references "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductId()+") , "
                        + "foreign key ( "+DatabaseHelper.getColumnTentitemsTentId()+" ) references "+DatabaseHelper.getTableTentName()+" ("+DatabaseHelper.getColumnTentId()+")"
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
                        + DatabaseHelper.getColumnProductType()+" text"
                        + DatabaseHelper.getColumnProductDescription()+" text"
                        + DatabaseHelper.getColumnProductImg()+" text"
                        + ");";
        return(sqlCreateProductTable);
    }

    public static String sqlCreateTentTable() {
        String sqlCreateTentTable =
                "CREATE TABLE "+ DatabaseHelper.getTableTentName() + "( "
                        + DatabaseHelper.getColumnTentId()+" integer primary key autoincrement not null , "
                        + DatabaseHelper.getColumnTentLongi()+" text , "
                        + DatabaseHelper.getColumnTentLagi()+ " text , "
                        + DatabaseHelper.getColumnTentUserId()+" text , "
                        + DatabaseHelper.getColumnTentName()+" text , "
                        + DatabaseHelper.getColumnTentImg()+" text , "
                        + DatabaseHelper.getColumnTentNote()+" text , "
                        + DatabaseHelper.getColumnTentNumberofvotes()+" text , "
                        + "foreign key ( "+DatabaseHelper.getColumnTentUserId()+" ) references "+DatabaseHelper.getTableUserName()+" ("+DatabaseHelper.getColumnUserId()+")"
                        + ");";
        return(sqlCreateTentTable);
    }

    public static String sqlCreateUserProductTable(){
        String sqlCreateUserProductTable =
                "CREATE TABLE "+ DatabaseHelper.getTableUserUserproductName() + "( "
                        + DatabaseHelper.getColumnUserproductUserId()+" integer , "
                        + DatabaseHelper.getColumnUserproductProductId()+" inteer , "
                        + "foreign key ( "+DatabaseHelper.getColumnUserproductUserId()+" ) references "+DatabaseHelper.getTableUserName()+" ( "+DatabaseHelper.getColumnUserId()+" ) , "
                        + "foreign key ( "+DatabaseHelper.getColumnUserproductProductId()+" ) references "+DatabaseHelper.getTableProductName()+" ( "+DatabaseHelper.getColumnProductId()+" )"
                        + ");";
        return(sqlCreateUserProductTable);
    }

    public static String sqlDropTableUsuarioLogado(){
        String sqlDropTableUsuarioLogado = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserLoggedName();
        return (sqlDropTableUsuarioLogado);
    }

    public static String sqlDropTableUsuario() {
        String sqlDropTableUsuario = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserName();
        return(sqlDropTableUsuario);
    }

    public static String sqlDropTableTent() {
        String sqlDropTableTent = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableTentName();
        return(sqlDropTableTent);
    }

    public static String sqlDropTableUserProduct() {
        String sqlDropTableUserProduct = "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserUserproductName();
        return(sqlDropTableUserProduct);
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

    public static String sqlTentFromUser(){
        String sqlItemFromUser =
                "SELECT * FROM " + DatabaseHelper.getTableTentName()
                        + " WHERE "
                        + DatabaseHelper.getColumnTentUserId()
                        + " =?;";
        ;
        return (sqlItemFromUser);
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

    public static String sqlAllItems(){
        String sqlAllItems =
                "SELECT * FROM "+DatabaseHelper.getTableTentitemsName() +" WHERE "
                        +DatabaseHelper.getColumnTentitemsTentId()
                        +" IN ( SELECT "+DatabaseHelper.getColumnTentId()+" FROM "+DatabaseHelper.getTableTentName()+" WHERE NOT "
                        +DatabaseHelper.getColumnTentUserId()+" = ?);";
        return (sqlAllItems);
    }

    public static String sqlAllItemsOfTent(){
        String sqlAllItemsOfTent =
                "SELECT * FROM "+DatabaseHelper.getTableTentitemsName() +" WHERE "
                        +DatabaseHelper.getColumnTentitemsTentId()+" =?;";
        return (sqlAllItemsOfTent);
    }

    public static String sqlTentById(){
        String sqlTentById =
                "SELECT * FROM "+DatabaseHelper.getTableTentName() +" WHERE "
                        +DatabaseHelper.getColumnTentId()+" =?;";
        return (sqlTentById);
    }

    public static String sqlAllProducts(){
        String sqlAllProducts =
                "SELECT * FROM "+DatabaseHelper.getTableProductName()+";";
        return (sqlAllProducts);
    }
}