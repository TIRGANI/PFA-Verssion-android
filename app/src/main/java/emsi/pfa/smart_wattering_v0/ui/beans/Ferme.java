package emsi.pfa.smart_wattering_v0.ui.beans;

public class Ferme {
    private int id;
    private int num_parcel;
    private String photo;
    private static int cpt=0;

    public Ferme(int id, int num_parcel, String photo) {
        this.id = id;
        this.num_parcel = num_parcel;
        this.photo = photo;
    }
    public Ferme( int num_parcel, String photo) {
        this.id = ++cpt;
        this.num_parcel = num_parcel;
        this.photo = photo;
    }
    public Ferme() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_parcel() {
        return num_parcel;
    }

    public void setNum_parcel(int num_parcel) {
        this.num_parcel = num_parcel;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
