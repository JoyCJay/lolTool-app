package fr.utt.if26.loltool_frontend.ui.matchList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MatchListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MatchListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}