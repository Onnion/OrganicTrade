package mpoo.bsi.ufrpe.organictrade.infra.persistencia;

public class ComandosSql {

    static String sqlCreateUserTable() {
        return
                "CREATE TABLE " + DatabaseHelper.getTableUserName() + "( "
                        + DatabaseHelper.getColumnUserId() + " integer primary key autoincrement unique not null , "
                        + DatabaseHelper.getColumnUserUsername() + " text unique not null ,"
                        + DatabaseHelper.getColumnUserPassword() + " text not null , "
                        + DatabaseHelper.getColumnUserEmail() + " text unique not null , "
                        + DatabaseHelper.getColumnUserName() + " text , "
                        + DatabaseHelper.getColumnUserPhone() + " integer , "
                        + DatabaseHelper.getColumnUserImg() + " blob"
                        + ");";
    }

    static String sqlCreateTentItemsTable() {
        return
                "CREATE TABLE " + DatabaseHelper.getTableTentitemsName() + "( "
                        + DatabaseHelper.getColumnTentitemsId() + " integer primary key autoincrement not null , "
                        + DatabaseHelper.getColumnTentitemsAmount() + " integer not null , "
                        + DatabaseHelper.getColumnTentitemsPrice() + " integer not null , "
                        + DatabaseHelper.getColumnTentitemsUnity() + " text no null , "
                        + DatabaseHelper.getColumnTentitemsProductId() + " integer no null , "
                        + DatabaseHelper.getColumnTentitemsTentId() + " integer no null , "
                        + DatabaseHelper.getColumnTentitemsImg() + " blob , "
                        + "foreign key ( " + DatabaseHelper.getColumnTentitemsProductId() + " ) references " + DatabaseHelper.getTableProductName() + " (" + DatabaseHelper.getColumnProductId() + ") , "
                        + "foreign key ( " + DatabaseHelper.getColumnTentitemsTentId() + " ) references " + DatabaseHelper.getTableTentName() + " (" + DatabaseHelper.getColumnTentId() + ")"
                        + ");";
    }

    static String sqlCreateTransactionTable() {
        return
                "CREATE TABLE " + DatabaseHelper.getTableTransactionName() + "( "
                        + DatabaseHelper.getColumnTransactionId() + " integer primary key autoincrement not null , "
                        + DatabaseHelper.getColumnTransactionVote() + " integer , "
                        + DatabaseHelper.getColumnTransactionDate() + " text not null , "
                        + DatabaseHelper.getColumnTransactionUserSellingId() + " integer no null , "
                        + DatabaseHelper.getColumnTransactionUserBuyingId() + " integer no null , "
                        + DatabaseHelper.getColumnTransactionTentitemId() + " integer no null , "
                        + "foreign key ( " + DatabaseHelper.getColumnTransactionUserBuyingId() + " ) references " + DatabaseHelper.getTableUserName() + " (" + DatabaseHelper.getColumnUserId() + ") , "
                        + "foreign key ( " + DatabaseHelper.getColumnTransactionUserSellingId() + " ) references " + DatabaseHelper.getTableUserName() + " (" + DatabaseHelper.getColumnUserId() + ") , "
                        + "foreign key ( " + DatabaseHelper.getColumnTransactionTentitemId() + " ) references " + DatabaseHelper.getTableTentitemsName() + " (" + DatabaseHelper.getColumnTentitemsId() + ")"
                        + ");";
    }

    static String sqlCreateUserLoggedTable() {
        return
                "CREATE TABLE " + DatabaseHelper.getTableUserLoggedName() + "( "
                        + DatabaseHelper.getColumnUserLoggedId() + " integer primary key autoincrement not null , "
                        + "foreign key ( " + DatabaseHelper.getColumnUserLoggedId() + " ) references " + DatabaseHelper.getTableUserName() + " ( " + DatabaseHelper.getColumnUserId() + " )"
                        + ");";
    }

    static String sqlCreateProductTable() {
        return
                "CREATE TABLE " + DatabaseHelper.getTableProductName() + "( "
                        + DatabaseHelper.getColumnProductId() + " integer primary key autoincrement not null , "
                        + DatabaseHelper.getColumnProductName() + " text not null , "
                        + DatabaseHelper.getColumnProductType() + " text not null , "
                        + DatabaseHelper.getColumnProductDescription() + " text , "
                        + DatabaseHelper.getColumnProductImg() + " text"
                        + ");";
    }

    static String sqlCreateTentTable() {
        return
                "CREATE TABLE " + DatabaseHelper.getTableTentName() + "( "
                        + DatabaseHelper.getColumnTentId() + " integer primary key autoincrement not null , "
                        + DatabaseHelper.getColumnTentLongi() + " integer not null , "
                        + DatabaseHelper.getColumnTentLagi() + " integer not null , "
                        + DatabaseHelper.getColumnTentUserId() + " integer not null , "
                        + DatabaseHelper.getColumnTentName() + " text , "
                        + DatabaseHelper.getColumnTentImg() + " blob , "
                        + DatabaseHelper.getColumnTentNote() + " integer , "
                        + DatabaseHelper.getColumnTentNumberofvotes() + " integer , "
                        + "foreign key ( " + DatabaseHelper.getColumnTentUserId() + " ) references " + DatabaseHelper.getTableUserName() + " (" + DatabaseHelper.getColumnUserId() + ")"
                        + ");";
    }

    static String sqlCreateUserProductTable() {
        return
                "CREATE TABLE " + DatabaseHelper.getTableUserproductName() + "( "
                        + DatabaseHelper.getColumnUserproductUserId() + " integer not null  , "
                        + DatabaseHelper.getColumnUserproductProductId() + " integer not null  , "
                        + "foreign key ( " + DatabaseHelper.getColumnUserproductUserId() + " ) references " + DatabaseHelper.getTableUserName() + " ( " + DatabaseHelper.getColumnUserId() + " ) , "
                        + "foreign key ( " + DatabaseHelper.getColumnUserproductProductId() + " ) references " + DatabaseHelper.getTableProductName() + " ( " + DatabaseHelper.getColumnProductId() + " )"
                        + ");";
    }

    static String sqlDropTableUsuarioLogado() {
        return
                "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserLoggedName();
    }

    static String sqlDropTableUsuario() {
        return
                "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserName();
    }

    static String sqlDropTableTent() {
        return
                "DROP TABLE IF EXISTS " + DatabaseHelper.getTableTentName();
    }

    static String sqlDropTableUserProduct() {
        return
                "DROP TABLE IF EXISTS " + DatabaseHelper.getTableUserproductName();
    }

    static String sqlDropTableItensDeTenda() {
        return
                "DROP TABLE IF EXISTS " + DatabaseHelper.getTableTentitemsName();
    }

    static String sqlDropTableProduct() {
        return
                "DROP TABLE IF EXISTS " + DatabaseHelper.getTableProductName();
    }

    static String sqlDropTableTransaction() {
        return
                "DROP TABLE IF EXISTS " + DatabaseHelper.getTableTransactionName();
    }

    public static String sqlProductIdByName() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableProductName() + " WHERE "
                        + DatabaseHelper.getColumnProductName() + " =?;";

    }

    public static String sqlTentFromUser() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableTentName()
                        + " WHERE "
                        + DatabaseHelper.getColumnTentUserId()
                        + " =?;";
    }

    public static String sqlUserFromLoginAndPass() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableUserName() + " WHERE "
                        + DatabaseHelper.getColumnUserUsername() + " =? AND "
                        + DatabaseHelper.getColumnUserPassword() + " =?;";
    }

    public static String sqlUserFromLogin() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableUserName() + " WHERE "
                        + DatabaseHelper.getColumnUserUsername() + " =?;";
    }

    public static String sqlUserFromId() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableUserName() + " WHERE "
                        + DatabaseHelper.getColumnUserId() + " =?;";
    }

    public static String sqlLimparTabela() {
        return
                "DELETE FROM " + DatabaseHelper.getTableUserLoggedName();
    }

    public static String sqlSearchInBaseFromLoggedUser() {
        return
                "SELECT " + DatabaseHelper.getColumnUserLoggedId() + " FROM " + DatabaseHelper.getTableUserLoggedName() + ";";
    }

    public static String sqlUserLogged() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableUserLoggedName() + ";";
    }

    public static String sqlUserLogoff() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableUserLoggedName() + " WHERE "
                        + DatabaseHelper.getColumnUserLoggedId() + " =?;";
    }

    public static String sqlProductNameById() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableProductName() + " WHERE "
                        + DatabaseHelper.getColumnProductId() + " =?;";
    }

    public static String sqlGetAllItems() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableTentitemsName() + " WHERE "
                        + DatabaseHelper.getColumnTentitemsTentId()
                        + " IN ( SELECT " + DatabaseHelper.getColumnTentId() + " FROM " + DatabaseHelper.getTableTentName() + " WHERE NOT "
                        + DatabaseHelper.getColumnTentUserId() + " = ?) AND "
                        + DatabaseHelper.getColumnTentitemsProductId()
                        + " IN ( SELECT " + DatabaseHelper.getColumnUserproductProductId() + " FROM " + DatabaseHelper.getTableUserproductName() + " WHERE "
                        + DatabaseHelper.getColumnUserproductUserId() + " = ?);";
    }

    public static String sqlGetAllItemsOfTent() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableTentitemsName() + " WHERE "
                        + DatabaseHelper.getColumnTentitemsTentId() + " =?;";
    }

    public static String sqlSearchTentById() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableTentName() + " WHERE "
                        + DatabaseHelper.getColumnTentId() + " =?;";
    }

    public static String sqlSearchTentItemById() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableTentitemsName() + " WHERE "
                        + DatabaseHelper.getColumnTentitemsId() + " =?;";
    }

    public static String sqlGetAllProducts() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableProductName() + ";";
    }

    public static String sqlGetFavorites() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableUserproductName() + " WHERE "
                        + DatabaseHelper.getColumnUserproductUserId() + " =?;";
    }

    public static String sqlGetSellingHistorycOfUser() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableTransactionName() + " WHERE "
                        + DatabaseHelper.getColumnTransactionUserSellingId() + " =?;";
    }

    public static String sqlGetBuyingHistorycOfUser() {
        return
                "SELECT * FROM " + DatabaseHelper.getTableTransactionName() + " WHERE "
                        + DatabaseHelper.getColumnTransactionUserBuyingId() + " =?;";
    }

    //----------------------------------RecommendationSystem---------------------------------------//
    public static String sqlLoadUsersToRecomendationSystem(){
        return
                "SELECT " + DatabaseHelper.getColumnTransactionUserBuyingId() + " FROM " + DatabaseHelper.getTableTransactionName()
                        + " WHERE " + DatabaseHelper.getColumnTransactionUserBuyingId() + "<>?;";
    }

    public static String sqlLoadProductsOfUserToRecomendationSystem() {
        return
                "SELECT " + DatabaseHelper.getColumnTentitemsProductId() + " FROM " + DatabaseHelper.getTableTentitemsName()
                        + " WHERE " + DatabaseHelper.getColumnTentitemsId()
                        + " IN ( SELECT " + DatabaseHelper.getColumnTransactionTentitemId() + " FROM " + DatabaseHelper.getTableTransactionName() + " WHERE "
                        + DatabaseHelper.getColumnTransactionUserBuyingId() + "=? AND "
                        + DatabaseHelper.getColumnTransactionUserBuyingId() + "<>? " + ");";
    }

    public static String sqlLoadProductsOfCurrentUserToRecomendationSystem() {
        return
                "SELECT " + DatabaseHelper.getColumnTentitemsProductId() + " FROM " + DatabaseHelper.getTableTentitemsName() + " WHERE "
                        + DatabaseHelper.getColumnTentitemsId()
                        + " IN ( SELECT " + DatabaseHelper.getColumnTransactionTentitemId() + " FROM " + DatabaseHelper.getTableTransactionName() + " WHERE "
                        + DatabaseHelper.getColumnTransactionUserBuyingId() + "=?);";
    }

    public static String sqlGetAllItensOfTransitions(){
        return
                "SELECT " + DatabaseHelper.getColumnTentitemsProductId() + " FROM " + DatabaseHelper.getTableTentitemsName()
                        + " WHERE " + DatabaseHelper.getColumnTentitemsId()
                        + " IN ( SELECT " + DatabaseHelper.getColumnTransactionTentitemId() + " FROM " + DatabaseHelper.getTableTransactionName()+");";
    }
}