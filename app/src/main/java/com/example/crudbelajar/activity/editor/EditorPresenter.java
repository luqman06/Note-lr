package com.example.crudbelajar.activity.editor;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.crudbelajar.api.ApiClient;
import com.example.crudbelajar.api.ApiInterface;
import com.example.crudbelajar.model.Note;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {
    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

 void saveNote(final String title, final String note, final int color) {

view.showProgress();
//        progressDialog.show();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.saveNote(title, note, color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
             view.hideProgress();

                if (response.isSuccessful() && response.body() != null){

                    Boolean success = response.body().getSuccess();
                    if (success){
                        view.onRequestSuccess(response.body().getMessage());

                    }
                    else {
                        view.onRequestError( response.body().getMessage());
                    }
//                        Toast.makeText(EditorActivity.this,
//                                response.body()
//                                        .getMessage(),
//                                Toast.LENGTH_SHORT).show();}

                }

            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
//                progressDialog.dismiss();
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
//                Toast.makeText(EditorActivity.this,
//                        t.getLocalizedMessage(),
//                        Toast.LENGTH_SHORT).show();

            }
        });






    }
    void updateNote(int id, String title,String note, int color){
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Note> call = apiInterface.updateNote(id, title, note, color );
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call,@NonNull Response<Note> response) {
view.hideProgress();
if (response.isSuccessful() && response.body() !=null){

    Boolean success = response.body().getSuccess();
    if (success){
        view.onRequestSuccess(response.body().getMessage());
    }else {
        view.onRequestError(response.body().getMessage());
    }
}
            }

            @Override
            public void onFailure(@NonNull Call<Note> call,@NonNull Throwable t) {
view.hideProgress();
view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deleteNote(int id){

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.deleteNote(id);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call,@NonNull Response<Note> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null){

                    Boolean success = response.body().getSuccess();
                    if (success){
                        view.onRequestSuccess(response.body().getMessage());
                    }else {
                        view.onRequestError(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Note> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());

            }
        });
    }

}
