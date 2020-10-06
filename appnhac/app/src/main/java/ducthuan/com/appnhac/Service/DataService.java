package ducthuan.com.appnhac.Service;

import java.util.List;

import ducthuan.com.appnhac.Model.Album;
import ducthuan.com.appnhac.Model.BaiHat;
import ducthuan.com.appnhac.Model.ChuDe;
import ducthuan.com.appnhac.Model.PlayList;
import ducthuan.com.appnhac.Model.QuangCao;
import ducthuan.com.appnhac.Model.TheLoai;
import ducthuan.com.appnhac.Model.TheLoaiTrongNgay;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    //gui phuong thuc len server, server tra du lieu ve
    @GET("songbanner.php")
    //nhan du lieu ve Call(Neu o ngoai chi co json object chi de Call<QuangCao>
    Call<List<QuangCao>> GetDataQuangCao();

    //Lay PlayListforToday
    @GET("playlistforcurrentday.php")
    Call<List<PlayList>>getDataPlayListForToday();

    //Lay ChuDe The Loai Today
    @GET("chudevatheloaitrongngay.php")
    Call<TheLoaiTrongNgay>getDataChuDeTheLoaiToDay();

    //
    @GET("albumhot.php")
    Call<List<Album>>getDataAlbumToday();
    //
    @GET("baihatduocthich.php")
    Call<List<BaiHat>>getDataBaiHatHot();

    //de su dung pt POST, gui len idquangcao de so sanh lay bai hat ve theo quang cao
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>>getDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    //lay danh sach bai hat theo idplaylist
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>>getDanhSachBaiHatTheoPlayList(@Field("idplaylist") String idplaylist);

    //PlayLists
    @GET("danhsachplaylist.php")
    Call<List<PlayList>>getDataPlayLists();

    //lay danh sach bai hat theo idtheloai
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>>getDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    //Lay danh sach tat ca cac ChuDe
    @GET("danhsachchude.php")
    Call<List<ChuDe>>getDataChuDes();

    //Lay danh sach the loai theo chu de
    @FormUrlEncoded
    @POST("danhsachtheloaitheochude.php")
    Call<List<TheLoai>>getTheLoaisTheoChuDe(@Field("idchude") String idchude);

    //Lay danh sach tat ca album
    @GET("danhsachalbum.php")
    Call<List<Album>>getDanhSachAlbum();

    //Lay danh sach bai hat theo idalbum
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>>getDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    //Update luot thich
    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String>UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    //Search
    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>>getSearchBaiHat(@Field("tukhoa") String tukhoa);
}
