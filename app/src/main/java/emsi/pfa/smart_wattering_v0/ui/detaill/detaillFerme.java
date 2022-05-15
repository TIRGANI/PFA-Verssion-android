package emsi.pfa.smart_wattering_v0.ui.detaill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import emsi.pfa.smart_wattering_v0.databinding.FragmentDetaillfermeBinding;
import emsi.pfa.smart_wattering_v0.ui.service.UserService;
import emsi.pfa.smart_wattering_v0.ui.session.SessionManagement;

public class detaillFerme extends Fragment {
    private FragmentDetaillfermeBinding binding;
    SessionManagement sessionManagement;
    UserService userService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetaillfermeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sessionManagement = new SessionManagement(getActivity().getApplicationContext());

        final TextView textView = binding.iddeiall;

        //
        userService = new UserService();
     //   User u= userService.findById(sessionManagement.getuserconnect().getUser_id());

        textView.setText("Detaille Ferme**********");
        //
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
