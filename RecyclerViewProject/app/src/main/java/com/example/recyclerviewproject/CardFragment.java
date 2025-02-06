package com.example.recyclerviewproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CardFragment extends RecyclerView.ViewHolder {
    private TextView animalTitleView;
    private TextView animalTextView;
    private ImageView animalImgView;
    private Button seeAnimalButton;
    private TextView animalCheckView;

    public CardFragment(View view) {
        super(view);
        animalTitleView = view.findViewById(R.id.animal_title_view);
        animalTextView = view.findViewById(R.id.animal_txt_view);
        animalImgView = view.findViewById(R.id.animal_img_view);
        seeAnimalButton = view.findViewById(R.id.see_animal_button);
        animalCheckView = view.findViewById(R.id.animal_txt_check);
    }

    public TextView getAnimalTitleView() {
        return animalTitleView;
    }

    public void setAnimalTitleView(TextView animalTitleView) {
        this.animalTitleView = animalTitleView;
    }

    public TextView getAnimalTextView() {
        return animalTextView;
    }

    public void setAnimalTextView(TextView animalTextView) {
        this.animalTextView = animalTextView;
    }

    public ImageView getAnimalImgView() {
        return animalImgView;
    }

    public void setAnimalImgView(ImageView animalImgView) {
        this.animalImgView = animalImgView;
    }

    public Button getSeeAnimalButton() {
        return seeAnimalButton;
    }

    public void setSeeAnimalButton(Button seeAnimalButton) {
        this.seeAnimalButton = seeAnimalButton;
    }

    public TextView getAnimalCheckView() {
        return animalCheckView;
    }

    public void setAnimalCheckView(TextView animalCheckView) {
        this.animalCheckView = animalCheckView;
    }
}