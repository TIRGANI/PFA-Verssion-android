package emsi.pfa.smart_wattering_v0.ui.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import emsi.pfa.smart_wattering_v0.ui.beans.Ferme;
import emsi.pfa.smart_wattering_v0.ui.service.FermeService;

public class SessionFerme {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "Sferm";


    private FermeService service;


    public SessionFerme(Context context) {
        service = new FermeService();

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSessione(Ferme ferme) {

        //save session of user whenever user is loggied in
        int id = ferme.getId();
        String photo = ferme.getPhoto();
        int nbrparcelle = ferme.getNumParcel();
        int userid = ferme.getUser().getUser_id();



        editor.putInt("id", id).commit();
        editor.putString("photo", photo).commit();
        editor.putString("nbrparcelle", nbrparcelle+"").commit();
        editor.putString("userid", userid+"").commit();
        //

        Gson gson = new Gson();

        String use = gson.toJson(ferme);
        editor.putString("ferme", use).commit();
       // String rol = gson.toJson(role);
       // editor.putString("role", rol).commit();
        //String ferm = gson.toJson(ferme);
        //editor.putString("ferme", ferm).commit();

        //

    }

    public int getSession() {
        return sharedPreferences.getInt("id", -1);
    }
    public Ferme getfermeconnect() {
        //return user whose session is saved
        Gson gson = new Gson();
        int id = sharedPreferences.getInt("id", -1);
        String nbr_parcel = sharedPreferences.getString("nbr_parcel", "");
        String photo =sharedPreferences.getString("photo", "");
        String user = sharedPreferences.getString("user_id", "");
        //--ferm
        String json = sharedPreferences.getString("ferme", "");
        Ferme ferm = gson.fromJson(json, Ferme.class);
        //--role
        String us = sharedPreferences.getString("user", "");
    //    Role usere = gson.fromJson(us, User.class);
     //   Ferme ferm = new Ferme(id,nbr_parcel,user,nbr_parcel);

        return ferm;
    }


    public void removeSession() {
        //return user whose session is saved
        editor.putInt("id", -1).commit();
    }
}
