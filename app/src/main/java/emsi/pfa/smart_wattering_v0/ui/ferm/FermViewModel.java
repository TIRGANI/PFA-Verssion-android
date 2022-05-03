package emsi.pfa.smart_wattering_v0.ui.ferm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FermViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FermViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ferm fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}