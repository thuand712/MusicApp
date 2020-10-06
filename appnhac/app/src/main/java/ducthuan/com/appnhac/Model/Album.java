package ducthuan.com.appnhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

@SerializedName("id")
@Expose
private String id;
@SerializedName("ten")
@Expose
private String ten;
@SerializedName("tencasi")
@Expose
private String tencasi;
@SerializedName("hinh")
@Expose
private String hinh;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getTen() {
return ten;
}

public void setTen(String ten) {
this.ten = ten;
}

public String getTencasi() {
return tencasi;
}

public void setTencasi(String tencasi) {
this.tencasi = tencasi;
}

public String getHinh() {
return hinh;
}

public void setHinh(String hinh) {
this.hinh = hinh;
}

}