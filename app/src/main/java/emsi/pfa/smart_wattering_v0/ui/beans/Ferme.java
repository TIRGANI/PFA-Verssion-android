package emsi.pfa.smart_wattering_v0.ui.beans;

public class Ferme {
    private int id;
    private int numParcel;
    private String photo;
    private static int cpt=0;
    private User user;

    public int getNumParcel() {
        return numParcel;
    }

    public void setNumParcel(int numParcel) {
        this.numParcel = numParcel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ferme(int id, String photo, User user, int nbrParcel) {
        this.id = id;
        this.numParcel = nbrParcel;
        this.photo = photo;
        this.user=user;
    }
    public Ferme( int nbrParcel, String photo) {
        this.id = ++cpt;
        this.numParcel = nbrParcel;
        this.photo = photo;
    }
    public Ferme() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
