package emsi.pfa.smart_wattering_v0.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import emsi.pfa.smart_wattering_v0.MainActivity;
import emsi.pfa.smart_wattering_v0.R;
import emsi.pfa.smart_wattering_v0.ui.beans.Ferme;
import emsi.pfa.smart_wattering_v0.ui.beans.Role;
import emsi.pfa.smart_wattering_v0.ui.beans.User;
import emsi.pfa.smart_wattering_v0.ui.service.FermeService;
import emsi.pfa.smart_wattering_v0.ui.service.RoleService;
import emsi.pfa.smart_wattering_v0.ui.service.UserService;
import emsi.pfa.smart_wattering_v0.ui.session.SessionManagement;


public class Auth extends AppCompatActivity {
    Button b1, b2;
    EditText ed1, ed2;
    String insertUrllo = "http://10.0.2.2:8090/users/all";
    String insertferm = "http://10.0.2.2:8090/ferme/all";
    String insertrole = "http://10.0.2.2:8090/role/all";
    RequestQueue requestQueue;
    RequestQueue requestQueue2;
    RequestQueue requestQueue3;
    TextView tx1;
    SessionManagement sessionManagement;
    int counter = 3;
    private List<User> users;
    private UserService service;
    private FermeService fermeService;
    private RoleService roleService;

    @Override
    protected void onStart() {

        super.onStart();
        //check if user in logged in
        //if user is logged in move to main activity
        sessionManagement = new SessionManagement(Auth.this);
        int id = sessionManagement.getSession();

        if (id != -1) {
            moveToMainActivity();
        } else {
            connection();
        }

    }

    private void moveToMainActivity() {
        Intent intent = new Intent(Auth.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void connection() {
        if (service.findByName(ed1.getText().toString(), ed2.getText().toString())) {
            //session
            User user = service.findByNamee(ed1.getText().toString(), ed2.getText().toString());
            Log.d("userssssssssssss",user.getRole().getNome());
            sessionManagement.saveSessione(user);
            moveToMainActivity();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Account !!!"
                    , Toast.LENGTH_SHORT).show();

            //   tx1.setVisibility(View.VISIBLE);
            // tx1.setBackgroundColor(Color.RED);
            counter--;
            // tx1.setText(Integer.toString(counter));

            if (counter == 0) {
                b1.setEnabled(false);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);

        b2 = (Button) findViewById(R.id.button2);
        //tx1 = (TextView)findViewById(R.id.textView3);
        //   tx1.setVisibility(View.GONE);
        users = new ArrayList<>();
        service = new UserService();
        fermeService = new FermeService();
        roleService = new RoleService();
        sessionManagement = new SessionManagement(Auth.this);

        getdatauser();
       // getdataferm();
       // getdatarole();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                connection();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getdatarole() {
        //***************************role**********************************
        requestQueue3 = Volley.newRequestQueue(getApplicationContext());
        StringRequest requestrole = new StringRequest(Request.Method.GET,
                insertrole, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("***********************","role");
                Type type = new TypeToken<Collection<Role>>() {
                }.getType();
                Collection<Role> roles = new Gson().fromJson(response, type);

                for (Role e : roles) {

                    Role role = new Role(e.getId(), e.getNome());
                    roleService.create(role);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue3.add(requestrole);
        // ************************************************************
    }

    private void getdataferm() {
        //***************************ferm**********************************
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET,
                insertferm, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("********************", "ferms");
                Type type = new TypeToken<Collection<Ferme>>() {
                }.getType();
                Collection<Ferme> fermes = new Gson().fromJson(response, type);

                for (Ferme f : fermes) {
                    Ferme s = new Ferme(f.getId(),f.getPhoto(),f.getUser(),f.getNumParcel());

                    fermeService.create(s);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);
        //****************************end******************

    }

    private void getdatauser() {
        //***************************user**********************************
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET,
                insertUrllo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("********************", "user");
                Type type = new TypeToken<Collection<User>>() {
                }.getType();
                Collection<User> user = new Gson().fromJson(response, type);

                for (User e : user) {
                    User s = new User(e.getUser_id(), e.getUsername()+ "", e.getPassword() + "", e.getEmail() + "", e.getRole());
                    service.create(s);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);

    }

}