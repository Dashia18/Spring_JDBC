package BookOrders;

import java.sql.*;
import java.util.Map;

import static BookOrders.Main.*;


/**
 * Created by Daria Serebryakova on 29.03.2017.
 */
public class DataManager {

    public static void mainForDataManage() {
        //1 task - add, delete, update, get count, get all rows

        //Books
        String selectBooksSQL = "select * from books";
        String deleteBookSQL = "DELETE FROM books WHERE ID = 21";
        String updateBookSQL = "UPDATE books SET book_name = 'Java 8' WHERE ID = 20";
        String getCountBookSQL = "SELECT COUNT(1) FROM books";
        String insertBookSQL = "INSERT INTO books"
                + "(ID, book_name, price, stock, quantity) " + "VALUES"
                + "( " + (DataManager.getOneValueIntegerData(getCountBookSQL, "COUNT")+1) +",'C++ For C Programmers',1790, 1, 4 )";


//        DataManager.changeData(insertBookSQL); // Options: insertBookSQL, deleteBookSQL, updateBookSQL
//        DataManager.getData(selectBooksSQL, "book"); //Option: selectBooksSQL
//        DataManager.getOneIntegerData(getCountBookSQL, "COUNT");

        //Buyer
        String selectBuyerSQL = "select * from buyer";
        String deleteBuyerSQL = "DELETE FROM buyer WHERE ID = 21";
        String updateBuyerSQL = "UPDATE buyer SET discount = '5' WHERE ID = 21";
        String getCountBuyerSQL = "SELECT COUNT(1) FROM buyer";
        String insertBuyerSQL = "INSERT INTO buyer"
                + "(ID, last_name, buyer_district, discount) " + "VALUES"
                + "( " + (DataManager.getOneValueIntegerData(getCountBuyerSQL, "COUNT")+1) +",'test','test', 4)";

//        DataManager.changeData(deleteBuyerSQL); // Options: insert, delete, update
//        DataManager.getData(selectBuyerSQL, "buyer");

        //Shop
        String selectShopSQL = "select * from shop";
        String deleteShopSQL = "DELETE FROM shop WHERE ID = 12";
        String updateShopSQL = "UPDATE shop SET commission = '5' WHERE ID = 11";
        String getCountShopSQL = "SELECT COUNT(1) FROM shop";
        String insertShopSQL = "INSERT INTO shop"
                + "(ID, shop_name, shop_district, commission) " + "VALUES"
                + "( " + (DataManager.getOneValueIntegerData(getCountShopSQL, "COUNT")+1) +",'test','test', 4)";

//        DataManager.changeData(deleteShopSQL); // Options: insert, delete, update
//        DataManager.getData(selectShopSQL, "shop");

        //Orders
        String selectOrderSQL = "select * from orders";
        String deleteOrderSQL = "DELETE FROM orders WHERE order_number = 2";
        String updateOrderSQL = "UPDATE orders SET seller = '10' WHERE order_number = 24";
        String getCountOrderSQL = "SELECT COUNT(1) FROM orders";
        String insertOrderSQL = getInsertOrderSQL("2017-04-05", 1, 19, 16, 1, getCountOrderSQL);

//        DataManager.changeData(updateOrderSQL); // Options: insert, delete, update
//        DataManager.getData(selectOrderSQL, "orders");

    }




    private static String getInsertOrderSQL(String data, int seller, int buyer,int book,
                                            int count, String getCountOrderSQL){


        String bookSQL = "SELECT price FROM books WHERE ID="+book;
        int bookPrice = DataManager.getOneValueIntegerData(bookSQL,"price");
        String buyerSQL = "SELECT discount FROM buyer WHERE ID="+buyer;
        int buyerDiscount = DataManager.getOneValueIntegerData(buyerSQL,"discount");
        int sum = bookPrice*count   -  bookPrice*count * buyerDiscount/100;


        return "INSERT INTO orders"
                + "(order_number, order_date, seller, buyer, book,count,sum) " + "VALUES"
                + "( " + (DataManager.getOneValueIntegerData(getCountOrderSQL, "COUNT")+1) +
                ", '"+data+"',"+seller+", "+buyer+","+ book +","+count+","+sum+")";

    }
    public static void changeData(String sql){
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            statement.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getData(String sql, String type) {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            switch (type) {
                case "book":
                    while (resultSet.next()) {

                        int id = resultSet.getInt("Id");
                        String book_name = resultSet.getString("book_name");
                        int price = resultSet.getInt("price");
                        int stock = resultSet.getInt("stock");
                        int quantity = resultSet.getInt("quantity");

                        System.out.println(id + " " + book_name + " " + price
                                + " " + stock + " " + quantity);
                    }
                    break;
                case "buyer":
                    while (resultSet.next()) {

                        int id = resultSet.getInt("Id");
                        String last_name = resultSet.getString("last_name");
                        String buyer_district = resultSet.getString("buyer_district");
                        int discount = resultSet.getInt("discount");

                        System.out.println(id + " " + last_name + " " + buyer_district
                                + " " + discount);
                    }
                    break;
                case "shop":
                    while (resultSet.next()) {

                        int id = resultSet.getInt("Id");
                        String shop_name = resultSet.getString("shop_name");
                        String shop_district = resultSet.getString("shop_district");
                        int commission = resultSet.getInt("commission");

                        System.out.println(id + " " + shop_name + " " + shop_district
                                + " " + commission);
                    }
                    break;
                case "orders":
                    while (resultSet.next()) {

                        int id = resultSet.getInt("order_number");
                        Date order_date = resultSet.getDate("order_date");
                        int seller = resultSet.getInt("seller");
                        int buyer = resultSet.getInt("buyer");
                        int book = resultSet.getInt("book");
                        int count = resultSet.getInt("count");
                        int sum = resultSet.getInt("sum");

                        System.out.println(id + " " + order_date + " " + seller
                                + " " + buyer + " " + book + " " + count + " " + sum);
                    }
                    break;
            }


            statement.close();
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getOneValueIntegerData(String sql, String column) {
        Connection connection;
        int result = 0;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                result = resultSet.getInt(column);
//                System.out.println(column+" "+result);
            }

            statement.close();
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void getStringRowsData(String sql, String column) {
        Connection connection;
        String result = "";
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                result = resultSet.getString(column);
                System.out.println(result);

            }
            System.out.println();
            statement.close();
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }

    }
    public static void getStringIntegerData(String sql, String str, String num) {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String string = resultSet.getString(str);
                int number = resultSet.getInt(num);
                System.out.println(string+ " " + number);
            }
            System.out.println();








            statement.close();
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getStringStringData(String sql, String str1, String str2) {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String string1 = resultSet.getString(str1);
                String string2 = resultSet.getString(str2);
                System.out.println(string1+ " " + string2);
            }
            System.out.println();

            statement.close();
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getOrderData(String sql) {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Date date = resultSet.getDate("order_date");
                String last_name = resultSet.getString("last_name");
                int discount = resultSet.getInt("discount");
                String book_name = resultSet.getString("book_name");
                int count = resultSet.getInt("count");
                System.out.println(date+ " " + last_name+ " " +discount+ " " +book_name+ " " +count);
            }
            System.out.println();

            statement.close();
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getIntDateString(String sql) {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Date order_date = resultSet.getDate("order_date");
                String last_name = resultSet.getString("last_name");
                int order_number = resultSet.getInt("order_number");

                System.out.println(order_number+ " " + order_date+ " " +last_name);
            }
            System.out.println();

            statement.close();
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getMonth(String sql) {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Date order_date = resultSet.getDate("order_date");
                String last_name = resultSet.getString("last_name");
                String buyer_district = resultSet.getString("buyer_district");

                System.out.println(order_date+ " " + last_name+ " " +buyer_district);
            }
            System.out.println();

            statement.close();
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getStock(String sql) {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                String book_name = resultSet.getString("book_name");
                String stock = resultSet.getString("stock");
                int quantity = resultSet.getInt("quantity");

                System.out.println(book_name+ " " + stock+ " " +quantity);
            }
            System.out.println();

            statement.close();
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }






}
