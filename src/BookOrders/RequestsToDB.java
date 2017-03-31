package BookOrders;

/**
 * Created by Daria Serebryakova on 30.03.2017.
 */
public class RequestsToDB {

    //task - 2
    public static void getDifferentTypes() {

        //a
        System.out.println("All books & price:");
        String diffNamesPrice = "select distinct book_name, price from books order by book_name;";
        DataManager.getStringIntegerData(diffNamesPrice, "book_name","price");

        //b
        System.out.println("All buyer districts:");
        String diffBuyerDistricts = "select distinct buyer_district from buyer;";
        DataManager.getStringRowsData(diffBuyerDistricts, "buyer_district");


        //c
        System.out.println("All orders months:");
        String diffOrderMonth = "select distinct EXTRACT(MONTH FROM ORDER_DATE) mon from orders  order by mon";
        DataManager.getStringRowsData(diffOrderMonth, "mon");



    }

    //task - 3
    public static void getInformation() {
        //a
        System.out.println("Live in Nizhegorodskiy district:");
        String infoDistrict = "select last_name, discount from buyer where buyer_district = 'Nizhegorodskiy' order by last_name;";
        DataManager.getStringIntegerData(infoDistrict, "last_name","discount");

        //b
        System.out.println("Shops in Sormovskiy & Sovetskiy district:");
        String infoShopName = "select shop_name from shop where shop_district = 'Sormovskiy'" +
                " OR shop_district = 'Sovetskiy' order by shop_name;";
        DataManager.getStringRowsData(infoShopName, "shop_name");

        //c
        System.out.println("Books about Windows OR price more then 20000:");
        String infoBook = "select book_name, price  from books where book_name LIKE '%Windows%' OR price > 20000 " +
                "order by price DESC;";
        DataManager.getStringIntegerData(infoBook, "book_name","price");

    }
    //task - 4
    public static void orderData() {
        //a
        System.out.println("Last name & Book shop of order :");
        String lastNameShop = "select  buyer.last_name, shop.shop_name   from buyer, shop, orders " +
                "where(orders.seller = shop.id) AND (orders.buyer = buyer.id) order by orders.buyer;";
        DataManager.getStringStringData(lastNameShop, "last_name","shop_name");

        //b
        System.out.println("Data about order:");
        String orderData = "select  orders.order_date, buyer.last_name, buyer.discount,  books.book_name, orders.count " +
                "from buyer, books,orders " +
                "where(orders.book = books.id) AND (orders.buyer = buyer.id) order by orders.buyer;";
        DataManager.getOrderData(orderData);


    }
    //task - 5
    public static void determineData(){
        //a
        System.out.println("Data about order where sum>60000:");
        String sumRequest = "select  orders.order_number, orders.order_date, buyer.last_name " +
                "from orders, buyer where(orders.sum > 60000) AND (orders.buyer = buyer.id) order by orders.buyer;";
        DataManager.getIntDateString(sumRequest);

        //b
        System.out.println("Data about order where MONTH > 3:");
        String monthRequest =  "select  orders.order_date, buyer.last_name, buyer.buyer_district " +
                "from buyer, orders, shop " +
                "where(EXTRACT(MONTH FROM orders.ORDER_DATE)>3)  " +
                "AND(shop.id = orders.seller) AND(buyer.id = orders.buyer) " +
                "AND (shop.shop_district = buyer.buyer_district) order by orders.buyer;";
        DataManager.getMonth(monthRequest);

        //c
        System.out.println("Data about order where discount>=10 & discount<=15:");
        String shopRequest =  "select distinct shop.shop_name " +
                "from buyer, orders, shop where (shop.id = orders.seller)" +
                " AND(buyer.id = orders.buyer) AND (shop.shop_district != 'Avtozavodskiy') " +
                "AND(buyer.discount>=10)AND(buyer.discount<=15)order by shop.shop_name;";
        DataManager.getStringRowsData(shopRequest,"shop_name");

        //d
        System.out.println("Data about order where district shop = district stock:");
        String stockRequest =  "select  books.book_name, books.stock, books.quantity " +
                "from orders, shop, books where(shop.id = orders.seller) AND(books.id = orders.book) " +
                "AND (books.stock = shop.shop_district)  order by books.book_name;";
        DataManager.getStock(stockRequest);



    }
}
