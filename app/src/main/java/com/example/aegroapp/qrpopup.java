package com.example.aegroapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import androidmads.library.qrgenearator.QRGEncoder;

public class qrpopup extends DialogFragment {
    private ImageView qrCodeIV;
    private String dataEdt;
    //    private ImageButton ;
    Button qr;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    SharedPreferences sp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qrcode,container,false);

        qrCodeIV = view.findViewById(R.id.idIVQrcode);

        sp = getActivity().getSharedPreferences("", Context.MODE_PRIVATE);
        String id = sp.getString("username",null);

//        Toast.makeText(getActivity(), id, Toast.LENGTH_LONG).show();

        Bitmap bitmap = null;
        try {
            bitmap = textToImage(id.toString(), 500, 500);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        qrCodeIV.setImageBitmap(bitmap);

        return view;
    }

    private Bitmap textToImage(String text, int width, int height) throws WriterException, NullPointerException {
//        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.DATA_MATRIX.QR_CODE,
                    width, height, null);
        } catch (IllegalArgumentException Illegalargumentexception) {
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        int colorWhite = 0xFFFFFFFF;
        int colorBlack = 0xFF000000;

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? colorBlack : colorWhite;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, width, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
