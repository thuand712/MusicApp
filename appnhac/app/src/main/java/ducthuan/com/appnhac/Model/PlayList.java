package ducthuan.com.appnhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayList implements Serializable {

@SerializedName("id")
@Expose
private String id;
@SerializedName("ten")
@Expose
private String ten;
@SerializedName("hinhnen")
@Expose
private String hinhnen;
@SerializedName("hinhicon")
@Expose
private String hinhicon;

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

public String getHinhnen() {
return hinhnen;
}

public void setHinhnen(String hinhnen) {
this.hinhnen = hinhnen;
}

public String getHinhicon() {
return hinhicon;
}

public void setHinhicon(String hinhicon) {
this.hinhicon = hinhicon;
}

}