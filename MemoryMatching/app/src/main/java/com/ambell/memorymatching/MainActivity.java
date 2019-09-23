package com.ambell.memorymatching;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Dialog endDialog;
    GridView gridView;
    ImageView currentImageViewObject = null;
    TextView scoreView;
    private int scoreCounter = 0;
    final int[] drawable = new int[]{R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,
            R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10};
    int[] ArrayPos = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9};
    int currentPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridv);
        scoreView = findViewById(R.id.score);
        scoreView.setText("Score : "+ scoreCounter);
        final GridAdapter gridAdapter = new GridAdapter(this);
        gridView.setAdapter(gridAdapter);



        ArrayPos = RandomizeArray(ArrayPos);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(currentPos < 0 ){

                    currentImageViewObject = (ImageView)((LinearLayout)gridView.getChildAt(position)).getChildAt(0);
                    if(currentImageViewObject.isEnabled()){
                        currentPos = position;
                        currentImageViewObject.setImageResource(drawable[ArrayPos[position]]);}


                }else{
                    final ImageView thisImage = (ImageView)((LinearLayout)gridView.getChildAt(position)).getChildAt(0);
                    if(currentPos == position){
                        thisImage.setImageResource(R.drawable.blank);
                    }else if(ArrayPos[currentPos] != ArrayPos[position]){
                        if(currentImageViewObject.isEnabled()){
                            thisImage.setImageResource(drawable[ArrayPos[position]]);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    thisImage.setImageResource(R.drawable.blank);
                                    currentImageViewObject.setImageResource(R.drawable.blank);
                                }
                            },100);
                        }
                    }else{
                        thisImage.setImageResource(drawable[ArrayPos[position]]);
                        thisImage.setEnabled(false);
                        currentImageViewObject.setEnabled(false);
                        scoreCounter++;
                        scoreView.setText("Score : "+ scoreCounter);
                        if(scoreCounter == 10){
                            //end game
                            createDialog();
                        }
                    }
                    currentPos = -1;
                }
            }
        });
    }

    public static int[] RandomizeArray(int[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    private void createDialog(){

        endDialog = new Dialog(this);

        endDialog.setContentView(R.layout.endgame_dialog);
        Button dialogClose = endDialog.findViewById(R.id.dclose);

        dialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show ads if show ad is true
                Intent intent = new Intent(MainActivity.this, Splash_Simulator.class);
                startActivity(intent);
                endDialog.dismiss();
                finish();
            }
        });

        endDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        endDialog.setCancelable(false);
        endDialog.setCanceledOnTouchOutside(false);
        endDialog.show();

    }


}
