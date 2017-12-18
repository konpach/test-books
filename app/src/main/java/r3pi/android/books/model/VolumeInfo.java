package r3pi.android.books.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by konpach on 15/12/2017.
 */

public class VolumeInfo implements Parcelable {

    @SerializedName("title")
    private String title;

    @SerializedName("subtitle")
    private String subtitle;

    @SerializedName("description")
    private String description;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("authors")
    @Expose
    private List<String> authors = null;

    @SerializedName("pageCount")
    @Expose
    private int pageCount;

    @SerializedName("imageLinks")
    @Expose
    private ImageLinks imageLinks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.subtitle);
        dest.writeString(this.description);
        dest.writeString(this.publisher);
        dest.writeStringList(this.authors);
        dest.writeInt(this.pageCount);
        dest.writeParcelable(this.imageLinks, flags);
    }

    public VolumeInfo() {
    }

    protected VolumeInfo(Parcel in) {
        this.title = in.readString();
        this.subtitle = in.readString();
        this.description = in.readString();
        this.publisher = in.readString();
        this.authors = in.createStringArrayList();
        this.pageCount = in.readInt();
        this.imageLinks = in.readParcelable(ImageLinks.class.getClassLoader());
    }

    public static final Creator<VolumeInfo> CREATOR = new Creator<VolumeInfo>() {
        @Override
        public VolumeInfo createFromParcel(Parcel source) {
            return new VolumeInfo(source);
        }

        @Override
        public VolumeInfo[] newArray(int size) {
            return new VolumeInfo[size];
        }
    };

    @Override
    public String toString() {
        return "VolumeInfo{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", description='" + description + '\'' +
                ", publisher='" + publisher + '\'' +
                ", authors=" + authors +
                ", pageCount=" + pageCount +
                ", imageLinks=" + imageLinks +
                '}';
    }
}
