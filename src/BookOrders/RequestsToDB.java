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
                "where(orders.seller = shop.id) AND (orders.buyer = buyer.id) order by orders.buyer";
        DataManager.getStringStringData(lastNameShop, "last_name","shop_name");
    }
}
