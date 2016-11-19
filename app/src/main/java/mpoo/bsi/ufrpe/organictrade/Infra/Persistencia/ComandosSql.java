package mpoo.bsi.ufrpe.organictrade.Infra.Persistencia;

import mpoo.bsi.ufrpe.organictrade.Infra.Session;

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
                        + DatabaseHelper.getColumnUserAdress() + " text"
                        + ");";
        return(sqlCreateUserTable);
    }

    public static String sqlCreateItensdetendaTable(){
        String sqlCreateItensdetendaTable =
                "CREATE TABLE "+ DatabaseHelper.getTableTentitemsName() + "( "
                        + DatabaseHelper.getColumnTentitemsId()+" integer primary key autoincrement not null , "
                        + DatabaseHelper.getColumnTentitemsAmount() +" text not null , "
                        + DatabaseHelper.getColumnTentitemsPrice() + " text not null , "
                        + DatabaseHelper.getColumnTentitemsUnity() + " text no null , "
                        + DatabaseHelper.getColumnTentitemsProductId()+ " integer , "
                        + DatabaseHelper.getColumnTentitemsUserId()+ " integer , "
                        + "foreign key ( "+DatabaseHelper.getColumnTentitemsProductId()+" ) references "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductId()+") , "
                        + "foreign key ( "+DatabaseHelper.getColumnTentitemsUserId()+" ) references "+DatabaseHelper.getTableUserName()+" ("+DatabaseHelper.getColumnUserId()+")"
                        + ");";
        return(sqlCreateItensdetendaTable);

    }

    public static String sqlCreateUsuarioLogadoTable(){
        String sqlCreateUsuarioLogadoTable =
                "CREATE TABLE "+ DatabaseHelper.getTableUserLoggedName() + "( "
                        + DatabaseHelper.getColumnUserLoggedId()+" integer , "
                        + "foreign key ( "+DatabaseHelper.getColumnUserLoggedId()+" ) references "+DatabaseHelper.getTableUserName()+" ( "+DatabaseHelper.getColumnUserId()+" )"
                        + ");";
        return(sqlCreateUsuarioLogadoTable);
    }

    public static String sqlCreateProductTable(){
        String sqlCreateProductTable =
                "CREATE TABLE "+ DatabaseHelper.getTableProductName() + "( "
                        + DatabaseHelper.getColumnProductId()+" integer primary key autoincrement not null , "
                        + DatabaseHelper.getColumnProductName()+" text"
                        + ");";
        return(sqlCreateProductTable);
    }

    public static String sqlCreateSearchTable(){
        String sqlCreateSearchTable =
                "CREATE VIRTUAL TABLE " + DatabaseHelper.getTableSearchName() +
                        " USING fts3 (" + DatabaseHelper.getColumnSearchProductName() + " text"
                        + ");";
        return (sqlCreateSearchTable);
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

    public static String sqlUsuarioQueTemOItem(){
        String sqlUsuarioQueTemOItem =
                "SELECT * FROM " + DatabaseHelper.getTableTentitemsName() + " WHERE "
                        + DatabaseHelper.getColumnTentitemsUserId() + " =?;";
        return (sqlUsuarioQueTemOItem);
    }

    public static String sqlTodosOsItens(){
        String sqlUTodosOsItens =
                "SELECT * FROM " + DatabaseHelper.getTableTentitemsName() + " WHERE NOT "
                        + DatabaseHelper.getColumnTentitemsUserId() + " =?;";
        return (sqlUTodosOsItens);
    }

    public static String sqlUsuarioApartirDoLoginESenha(){
        String sqlUsuarioApartirDoLoginESenha =
                "SELECT * FROM "+DatabaseHelper.getTableUserName() +" WHERE "
                        +DatabaseHelper.getColumnUserUsername() +" =? AND "
                        +DatabaseHelper.getColumnUserPassword() + " =?;";
        return (sqlUsuarioApartirDoLoginESenha);
    }

    public static String sqlUsuarioApartirDoLogin(){
        String sqlUsuarioApartirDoLogin =
                "SELECT * FROM "+DatabaseHelper.getTableUserName() +" WHERE "
                        +DatabaseHelper.getColumnUserUsername() +" =?;";
        return (sqlUsuarioApartirDoLogin);
    }

    public static String sqlUsuarioApartirDoId(){
        String sqlUsuarioApartirDoId=
                "SELECT * FROM "+DatabaseHelper.getTableUserName() +" WHERE "
                        +DatabaseHelper.getColumnUserId() +" =?;";
        return (sqlUsuarioApartirDoId);
    }

    public static String sqlLimparTabela(){
        String sqlLimparTabela = "DELETE FROM " + DatabaseHelper.getTableUserLoggedName();
        return (sqlLimparTabela);
    }

    public static String sqlBuscarNoBancoDeUsuarioLogado(){
        String sqlUsuarioApartirDoBancoUsuarioLogado =
                "SELECT "+ DatabaseHelper.getColumnUserLoggedId()+" FROM "+DatabaseHelper.getTableUserLoggedName() +";";
        return (sqlUsuarioApartirDoBancoUsuarioLogado);
    }

    public static String sqlUsuarioLogado(){
        String sqlUsuarioLogado =
                "SELECT * FROM " + DatabaseHelper.getTableUserLoggedName()+";";
        return (sqlUsuarioLogado);
    }

    public static String sqlDeslogarUsuario(){
        String sqlDeslogarUsuario =
                "SELECT * FROM "+DatabaseHelper.getTableUserLoggedName() +" WHERE "
                        +DatabaseHelper.getColumnUserLoggedId()+" =?;";
        return (sqlDeslogarUsuario);
    }

    public static String sqlProductNameById() {
        String sqlProductNameById=
                "SELECT * FROM "+DatabaseHelper.getTableProductName() +" WHERE "
                        +DatabaseHelper.getColumnProductId() +" =?;";
        return (sqlProductNameById);
    }
}