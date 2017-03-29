package BookOrders;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daria Serebryakova on 29.03.2017.
 */
public class Main {
    public static  final String JDBC_DRIVER = "org.postgresql.Driver";
    public static  final String DB_URL = "jdbc:postgresql://localhost:5432/shop";
    public static  final String USER = "postgres";
    public static  final String PASSWORD = "postgres";


    public static void main(String[] args) {


    //Books
//        String selectBooksSQL = "select * from books";
//        String deleteBookSQL = "DELETE FROM books WHERE ID = 20";
//        String updateBookSQL = "UPDATE books SET price = '1950' WHERE ID = 6";
//        String getCountBookSQL = "SELECT COUNT(1) FROM books";
//        String insertBookSQL = "INSERT INTO books"
//                + "(ID, book_name, price, stock, quantity) " + "VALUES"
//                + "( " + (DataManager.getCountData(getCountBookSQL)+1) +",'test',2300, 4, 12 )";
//
//        DataManager.getData(selectBooksSQL, "book"); //Option: selectBooksSQL
//        DataManager.changeData(insertBookSQL); // Options: insertBookSQL, deleteBookSQL, updateBookSQL
//        DataManager.getCountData(getCountBookSQL);

    //Buyer
//        String selectBuyerSQL = "select * from buyer";
//        String deleteBuyerSQL = "DELETE FROM buyer WHERE ID = 22";
//        String updateBuyerSQL = "UPDATE buyer SET discount = '5' WHERE ID = 21";
//        String getCountBuyerSQL = "SELECT COUNT(1) FROM buyer";
//        String insertBuyerSQL = "INSERT INTO buyer"
//                + "(ID, last_name, buyer_district, discount) " + "VALUES"
//                + "( " + (DataManager.getCountData(getCountBuyerSQL)+1) +",'test','test', 4)";
//
//        DataManager.getData(selectBuyerSQL, "buyer");
//        DataManager.changeData(updateBuyerSQL); // Options: insertBookSQL, deleteBookSQL, updateBookSQL
//        DataManager.getData(selectBuyerSQL, "buyer");

    //Shop
//        String selectShopSQL = "select * from shop";
//        String deleteShopSQL = "DELETE FROM shop WHERE ID = 14";
//        String updateShopSQL = "UPDATE shop SET commission = '5' WHERE ID = 11";
//        String getCountShopSQL = "SELECT COUNT(1) FROM shop";
//        String insertShopSQL = "INSERT INTO shop"
//                + "(ID, shop_name, shop_district, commission) " + "VALUES"
//                + "( " + (DataManager.getCountData(getCountShopSQL)+1) +",'test','test', 4)";
//
//        DataManager.getData(selectShopSQL, "shop");
//        DataManager.changeData(insertShopSQL); // Options: insertBookSQL, deleteBookSQL, updateBookSQL
//        DataManager.getData(selectShopSQL, "shop");

        //Orders
        String selectOrderSQL = "select * from orders";
        String deleteOrderSQL = "DELETE FROM orders WHERE ID = 14";
        String updateOrderSQL = "UPDATE orders SET commission = '5' WHERE ID = 11";
        String getCountOrderSQL = "SELECT COUNT(1) FROM orders";
        String insertShopSQL = "INSERT INTO orders"
                + "(ID, shop_name, shop_district, commission) " + "VALUES"
                + "( " + (DataManager.getCountData(getCountOrderSQL)+1) +",'test','test', 4)";

        DataManager.getData(selectOrderSQL, "orders");
//        DataManager.changeData(insertShopSQL); // Options: insertBookSQL, deleteBookSQL, updateBookSQL
//        DataManager.getData(selectShopSQL, "shop");


    }


}


