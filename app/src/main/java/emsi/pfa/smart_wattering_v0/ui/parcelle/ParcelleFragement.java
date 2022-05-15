package emsi.pfa.smart_wattering_v0.ui.parcelle;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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

import emsi.pfa.smart_wattering_v0.R;
import emsi.pfa.smart_wattering_v0.databinding.FragementParcelleBinding;
import emsi.pfa.smart_wattering_v0.ui.adapter.ParcelleAdapter;
import emsi.pfa.smart_wattering_v0.ui.beans.Ferme;
import emsi.pfa.smart_wattering_v0.ui.beans.Parcelle;
import emsi.pfa.smart_wattering_v0.ui.beans.User;
import emsi.pfa.smart_wattering_v0.ui.service.FermeService;
import emsi.pfa.smart_wattering_v0.ui.service.UserService;
import emsi.pfa.smart_wattering_v0.ui.session.SessionFerme;
import emsi.pfa.smart_wattering_v0.ui.session.SessionManagement;

public class ParcelleFragement extends Fragment {
    private FragementParcelleBinding binding;
    String insertparcelle = "http://10.0.2.2:8090/parcelle/all";
    User cuser;
    Ferme sferm;

    SessionManagement sessionManagement;
    private static AlertDialog alertDialog;

    RequestQueue requestQueue;
    private ArrayList<Parcelle> parcell = new ArrayList<>();
    private FermeService fermeService;
    private Context context;
    UserService userService;
    User u;
    int idF;
    SessionFerme sessionFerme;
    @Override
    public void onStart() {
        userService = new UserService();
       // sferm = sessionFerme.getfermeconnect();
        u = userService.findById(sessionManagement.getuserconnect().getUser_id());
        sessionFerme = new SessionFerme(getContext());
        idF = sessionFerme.getSession();
        parcell.clear();
        getdataparcelle();
        super.onStart();
    }
    private void getdataparcelle() {


        //***************************parcelle**********************************
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET,
                insertparcelle, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("********************", "parcelles");
                Type type = new TypeToken<Collection<Parcelle>>() {
                }.getType();
                Collection<Parcelle> parcelles = new Gson().fromJson(response, type);

                for (Parcelle p : parcelles) {
                    Parcelle s = new Parcelle(p.getId(),p.getSurface(),p.getPhoto(),p.getFerme());

                    if (idF ==p.getFerme().getId()) {
                        Log.d("---->", "Id F : "+idF);
                        parcell.add(s);
                    }

                }
                ParcelleAdapter parcelleAdapter = new ParcelleAdapter(getActivity(),parcell);
                binding.listparcelle.setAdapter(parcelleAdapter);
                binding.listparcelle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("Message click : ", "up");
                        Navigation.findNavController(view).navigate(R.id.navToFerm);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

        });
        requestQueue.add(request);
        //****************************end******************

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fermeService = new FermeService();
        sessionManagement = new SessionManagement(getActivity().getApplicationContext());
        cuser = sessionManagement.getuserconnect();
        binding = FragementParcelleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}
