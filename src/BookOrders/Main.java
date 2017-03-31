package BookOrders;

/**
 * Created by Daria Serebryakova on 29.03.2017.
 */
public class Main {
    public static  final String JDBC_DRIVER = "org.postgresql.Driver";
    public static  final String DB_URL = "jdbc:postgresql://localhost:5432/shop";
    public static  final String USER = "postgres";
    public static  final String PASSWORD = "postgres";


    public static void main(String[] args) {

        //task 1 - add, delete, update, get count, get all rows
        DataManager.mainForDataManage();

        //task 2
//        RequestsToDB.getDifferentTypes();

        //task 3
//        RequestsToDB.getInformation();

        //task 4
//        RequestsToDB.orderData();

        //task 5
        RequestsToDB.determineData();


    }



}

