package emsi.pfa.smart_wattering_v0.ui.session;

import android.content.Context;
import android.content.SharedPreferences;

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
        Ferme ferm = user.getFerme();
        Role role = user.getRole();
        editor.putInt("id", 1).commit();
        editor.putString("username", username).commit();
        editor.putString("password", password).commit();
        editor.putString("email", email).commit();
        editor.putInt("ferm_id", ferm.getId()).commit();
        editor.putInt("role_id", role.getId()).commit();

    }

    public int getSession() {
        return sharedPreferences.getInt("id", -1);
    }
    public User getuserconnect() {
        //return user whose session is saved

        int id = sharedPreferences.getInt("id", -1);
        String username = sharedPreferences.getString("username", "");
        String password =sharedPreferences.getString("password", "");
        String email = sharedPreferences.getString("email", "");
        Ferme ferm = fermeService.findById(sharedPreferences.getInt("ferm_id", -1));
        Role role = roleService.findById(sharedPreferences.getInt("role_id", -1));
        User user = new User(id,email,password,username,ferm,role);

        return user;
    }


    public void removeSession() {
        //return user whose session is saved
        editor.putInt("id", -1).commit();
    }
}
