package com.example.canvasviewapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.colorpicker.ColorPickerDialog;
import com.github.dhaval2404.colorpicker.model.ColorShape;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ColorPickerDialog.Builder;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyCanvasView myCanvasView;
    private int mSelectedColor=R.color.purple_200;
    private int mSelectedStroke=12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCanvasView = findViewById(R.id.canvas_view);

        FloatingActionButton fabColor = findViewById(R.id.fab_color_picker);
        FloatingActionButton fabWidth = findViewById(R.id.fab_width_stroke);
        fabColor.setOnClickListener(this);
        fabWidth.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_color_picker:
                colorPicker();
                break;
            case R.id.fab_width_stroke:
                widthStroke();
            default:
                break;
        }
    }

    private void colorPicker(){
        new Builder(this)
                .setTitle("Pick Color")
                .setColorShape(ColorShape.SQAURE)
                .setDefaultColor(mSelectedColor)
                .setColorListener((color, colorHex) -> {
                    mSelectedColor = color;
                    myCanvasView.setPathColor(mSelectedColor);
                })
                .show();
    }

    private void widthStroke(){
        MaterialNumberPicker mNumberPicker = new MaterialNumberPicker.Builder(this)
                .minValue(1)
                .maxValue(25)
                .defaultValue(mSelectedStroke)
                .backgroundColor(Color.WHITE)
                .separatorColor(Color.TRANSPARENT)
                .textSize(12)
                .enableFocusability(false)
                .wrapSelectorWheel(true)
                .build();

        new AlertDialog.Builder(this)
                .setTitle("Pick Stroke Width")
                .setView(mNumberPicker).setNegativeButton(getString(android.R.string.cancel), null)
                .setPositiveButton(getString(android.R.string.ok), (dialog, which) -> {
                    mSelectedStroke = mNumberPicker.getValue();
                    myCanvasView.setWidthStroke(mSelectedStroke);
                })
                .show();
    }
}