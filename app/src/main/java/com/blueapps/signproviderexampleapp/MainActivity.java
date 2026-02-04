package com.blueapps.signproviderexampleapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.blueapps.signprovider.SignProvider;
import com.blueapps.signproviderexampleapp.databinding.ActivityMainBinding;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.id.addTextChangedListener(this);

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        SignProvider signProvider = new SignProvider(this);
        try {
            binding.Sign.setImageDrawable(signProvider.getSign(charSequence.toString()));
            binding.idText.setText(signProvider.getGardinerFromPhonetic(charSequence.toString()));
            binding.phoneticsText.setText(signProvider.getPhoneticsFromGardiner(signProvider.getGardinerFromPhonetic(charSequence.toString())).toString());
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}