package ducthuan.com.appnhac.Service;

public class APIService {

    private static String base_url = "https://ducthuan712.000webhostapp.com/server/";

    public static DataService getService(){
        //create: khoi tao nhung phuong thuc ben dataservice de gui len server
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }

}