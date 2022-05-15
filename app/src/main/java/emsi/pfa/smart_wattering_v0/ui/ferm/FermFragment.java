package emsi.pfa.smart_wattering_v0.ui.ferm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

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
import emsi.pfa.smart_wattering_v0.databinding.FragmentFermBinding;
import emsi.pfa.smart_wattering_v0.ui.adapter.FermeAdapter;
import emsi.pfa.smart_wattering_v0.ui.beans.Ferme;
import emsi.pfa.smart_wattering_v0.ui.beans.User;
import emsi.pfa.smart_wattering_v0.ui.communicator.Communicator;
import emsi.pfa.smart_wattering_v0.ui.service.FermeService;
import emsi.pfa.smart_wattering_v0.ui.service.UserService;
import emsi.pfa.smart_wattering_v0.ui.session.SessionFerme;
import emsi.pfa.smart_wattering_v0.ui.session.SessionManagement;

public class FermFragment extends Fragment {

    private FragmentFermBinding binding;
    String insertferm = "http://10.0.2.2:8090/ferme/all";
    User cuser;
    private static AlertDialog alertDialog;

    RequestQueue requestQueue;
    private ArrayList<Ferme> ferm = new ArrayList<>();
    private FermeService fermeService;
    private Context context;
    UserService userService;
    User u;
    SessionManagement sessionManagement;
    SessionFerme sessionFerme;

    @Override
    public void onStart() {
        sessionFerme = new SessionFerme(getActivity().getApplicationContext());
        userService = new UserService();
        u = userService.findById(sessionManagement.getuserconnect().getUser_id());
        ferm.clear();
        getdataferm();
        super.onStart();
    }

    private Communicator communicator;

    private void getdataferm() {


        //***************************ferm**********************************
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET,
                insertferm, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("********************", "ferms");
                Type type = new TypeToken<Collection<Ferme>>() {
                }.getType();
                Collection<Ferme> fermes = new Gson().fromJson(response, type);

                for (Ferme f : fermes) {
                    Ferme s = new Ferme(f.getId(), f.getPhoto(), f.getUser(), f.getNumParcel());

                    if (s.getUser().getUser_id() == cuser.getRole().getId()) {
                        Log.d("---->", "current user id : " + cuser.getRole().getId() + " | id of list : " + s.getUser().getUser_id() + " | ferm :" + s.getNumParcel());
                        ferm.add(s);
                    }

                }
                FermeAdapter fermeAdapter = new FermeAdapter(getActivity(), ferm);
                binding.listferme.setAdapter(fermeAdapter);
                binding.listferme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("Message click : ", "up");
                        //Toast.makeText(getActivity().getApplicationContext(),"click",Toast.LENGTH_SHORT).show();
                        //

                            // Use the Builder class for convenient dialog construction
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("Ferme : ")
                                    .setPositiveButton("Detail", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                          //  sessionFerme.saveSessione(fermeAdapter.getItem(i));
                                            Navigation.findNavController(view).navigate(R.id.navigTodetaillFerm);
                                        }
                                    })
                                    .setNegativeButton("List Parcelle", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            sessionFerme.saveSessione(fermeAdapter.getItem(i));
                                            Navigation.findNavController(view).navigate(R.id.navigToParcell);
                                        }
                                    });
                            // Create the AlertDialog object and return it
                             builder.create().show();
                        //

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
        binding = FragmentFermBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    //Function to create checkBox.
    private void createCheckBox() {
        Toast.makeText(getActivity(), "Text!", Toast.LENGTH_SHORT).show();
    }
    //---------------------------------------------------------

    //---------------------------------------------------------

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}