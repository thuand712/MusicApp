package ducthuan.com.appnhac.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaiHat implements Parcelable {

@SerializedName("idbaihat")
@Expose
private String idbaihat;
@SerializedName("tenbaihat")
@Expose
private String tenbaihat;
@SerializedName("hinhbaihat")
@Expose
private String hinhbaihat;
@SerializedName("casi")
@Expose
private String casi;
@SerializedName("linkbaihat")
@Expose
private String linkbaihat;
@SerializedName("luotthich")
@Expose
private String luotthich;

    protected BaiHat(Parcel in) {
        idbaihat = in.readString();
        tenbaihat = in.readString();
        hinhbaihat = in.readString();
        casi = in.readString();
        linkbaihat = in.readString();
        luotthich = in.readString();
    }

    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };

    public String getIdbaihat() {
return idbaihat;
}

public void setIdbaihat(String idbaihat) {
this.idbaihat = idbaihat;
}

public String getTenbaihat() {
return tenbaihat;
}

public void setTenbaihat(String tenbaihat) {
this.tenbaihat = tenbaihat;
}

public String getHinhbaihat() {
return hinhbaihat;
}

public void setHinhbaihat(String hinhbaihat) {
this.hinhbaihat = hinhbaihat;
}

public String getCasi() {
return casi;
}

public void setCasi(String casi) {
this.casi = casi;
}

public String getLinkbaihat() {
return linkbaihat;
}

public void setLinkbaihat(String linkbaihat) {
this.linkbaihat = linkbaihat;
}

public String getLuotthich() {
return luotthich;
}

public void setLuotthich(String luotthich) {
this.luotthich = luotthich;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idbaihat);
        parcel.writeString(tenbaihat);
        parcel.writeString(hinhbaihat);
        parcel.writeString(casi);
        parcel.writeString(linkbaihat);
        parcel.writeString(luotthich);
    }
}