package emsi.pfa.smart_wattering_v0.ui.ferm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import emsi.pfa.smart_wattering_v0.databinding.FragmentFermBinding;

public class FermFragment extends Fragment {

    private FragmentFermBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FermViewModel galleryViewModel =
                new ViewModelProvider(this).get(FermViewModel.class);

        binding = FragmentFermBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}