package BookOrders;

import java.sql.*;
import java.util.Map;

import static BookOrders.Main.*;


/**
 * Created by Daria Serebryakova on 29.03.2017.
 */
public class DataManager {
    static int rowCount;

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
    public static int getCountData(String sql) {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            rowCount = 0;
            while (resultSet.next()){
                rowCount = resultSet.getInt("COUNT");
                System.out.println(resultSet.getInt("COUNT"));
            }

            statement.close();
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        return rowCount;
    }


    public static void getData(String sql, String type) {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (type.equals("book")){
                while (resultSet.next()){

                    int id =resultSet.getInt("Id");
                    String book_name = resultSet.getString("book_name");
                    int price = resultSet.getInt("price");
                    int stock = resultSet.getInt("stock");
                    int quantity = resultSet.getInt("quantity");

                    System.out.println(id + " " + book_name + " " + price
                            + " " + stock+ " " +quantity);
                }
            }
            else if (type.equals("buyer")){
                while (resultSet.next()){

                    int id =resultSet.getInt("Id");
                    String last_name = resultSet.getString("last_name");
                    String buyer_district = resultSet.getString("buyer_district");
                    int discount = resultSet.getInt("discount");

                    System.out.println(id + " " + last_name + " " + buyer_district
                            + " " + discount);
                }
            }
            else if (type.equals("shop")){
                while (resultSet.next()){

                    int id =resultSet.getInt("Id");
                    String shop_name = resultSet.getString("shop_name");
                    String shop_district = resultSet.getString("shop_district");
                    int commission = resultSet.getInt("commission");

                    System.out.println(id + " " + shop_name + " " + shop_district
                            + " " + commission);
                }
            }
            else if (type.equals("orders")){
                while (resultSet.next()){

                    int id =resultSet.getInt("order_number");
                    Date order_date = resultSet.getDate("order_date");
                    int seller = resultSet.getInt("seller");
                    int buyer = resultSet.getInt("buyer");
                    int book = resultSet.getInt("book");
                    int count= resultSet.getInt("count");
                    int sum= resultSet.getInt("sum");

                    System.out.println(id + " " + order_date + " " + seller
                            + " " + buyer+ " " + book + " " + count + " " +sum);
                }
            }


            statement.close();
            resultSet.close();
            connection.close();

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }






}
