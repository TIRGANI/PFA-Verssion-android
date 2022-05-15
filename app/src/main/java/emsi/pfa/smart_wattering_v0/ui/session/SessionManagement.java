package emsi.pfa.smart_wattering_v0.ui.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import emsi.pfa.smart_wattering_v0.ui.beans.Ferme;
import emsi.pfa.smart_wattering_v0.ui.beans.Role;
import emsi.pfa.smart_wattering_v0.ui.beans.User;
import emsi.pfa.smart_wattering_v0.ui.service.FermeService;
import emsi.pfa.smart_wattering_v0.ui.service.RoleService;
import emsi.pfa.smart_wattering_v0.ui.service.UserService;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";


    private UserService service;
    private FermeService fermeService;
    private RoleService roleService;


    public SessionManagement(Context context) {
        service = new UserService();

        fermeService = new FermeService();
        roleService = new RoleService();
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSessione(User user) {

        //save session of user whenever user is loggied in
        int id = user.getUser_id();
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();



        Role role = user.getRole();
        editor.putInt("id", 1).commit();
        editor.putString("username", username).commit();
        editor.putString("password", password).commit();
        editor.putString("email", email).commit();
        //

        Gson gson = new Gson();

        String use = gson.toJson(user);
        editor.putString("user", use).commit();
        String rol = gson.toJson(role);
        editor.putString("role", rol).commit();
        //String ferm = gson.toJson(ferme);
        //editor.putString("ferme", ferm).commit();

        //

    }

    public int getSession() {
        return sharedPreferences.getInt("id", -1);
    }
    public User getuserconnect() {
        //return user whose session is saved
        Gson gson = new Gson();
        int id = sharedPreferences.getInt("id", -1);
        String username = sharedPreferences.getString("username", "");
        String password =sharedPreferences.getString("password", "");
        String email = sharedPreferences.getString("email", "");
        //--ferm
        String json = sharedPreferences.getString("ferme", "");
        Ferme ferm = gson.fromJson(json, Ferme.class);
        //--role
        String rol = sharedPreferences.getString("role", "");
        Role role = gson.fromJson(rol, Role.class);
        User user = new User(id,email,password,username,role);

        return user;
    }


    public void removeSession() {
        //return user whose session is saved
        editor.putInt("id", -1).commit();
    }
}
