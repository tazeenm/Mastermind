package com.example.anusha.mastermind_cs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ImageView boardView;

    ImageView answerView;
    Bitmap bitmap;
    Bitmap bitmap1;
    Bitmap bitmap2;
    public int[] answer=new int[4];
    public int[][] board=new int[8][4];
    public static boolean flag=true;
    int countblack=0;
    int countwhite=0;

    Canvas canvas;
    Canvas canvas1;
    Canvas canvas2;

    Paint paint;
    static int r=1;
    static int c=1;
    Random random;
    int randomNumber;
    int randomColor;

    int CanvasRadius;
    int CanvasRadius2;
    Button redButton;
    Button blueButton;
    Button pinkButton;
    Button orangeButton;
    Button purpleButton;
    Button greenButton;
    Button playButton;
    Button resetButton;
    private TextView row8TextView;
    private TextView row7TextView;
    private TextView row6TextView;
    private TextView row5TextView;
    private TextView row4TextView;
    private TextView row3TextView;
    private TextView row2TextView;
    private TextView row1TextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bitmap myBitmap = Bitmap.createBitmap((int)Width, (int)Height, Bitmap.Config.RGB_565);
        //Canvas canvas = new Canvas(myBitmap);
        boardView = (ImageView) findViewById(R.id.boardId);
        //clueView =(ImageView) findViewById(R.id.clueView);
        answerView =(ImageView) findViewById(R.id.answerView);

        random = new Random();

        // myHandler=(View)findViewById(R.id.wholeid);

        redButton = (Button) findViewById(R.id.buttonRed);
        blueButton = (Button) findViewById(R.id.buttonBlue);
        pinkButton = (Button) findViewById(R.id.buttonPink);
        orangeButton = (Button) findViewById(R.id.buttonOrange);
        purpleButton = (Button) findViewById(R.id.buttonPurple);
        greenButton = (Button) findViewById(R.id.buttonGreen);
        playButton = (Button) findViewById(R.id.playButton);
        resetButton = (Button) findViewById(R.id.resetButton);


        redButton.setOnClickListener(myHandler);
        blueButton.setOnClickListener(myHandler);
        pinkButton.setOnClickListener(myHandler);
        orangeButton.setOnClickListener(myHandler);
        purpleButton.setOnClickListener(myHandler);
        greenButton.setOnClickListener(myHandler);

        row8TextView = (TextView)findViewById(R.id.row8);
        row7TextView = (TextView)findViewById(R.id.row7);
        row6TextView = (TextView)findViewById(R.id.row6);
        row5TextView = (TextView)findViewById(R.id.row5);
        row4TextView = (TextView)findViewById(R.id.row4);
        row3TextView = (TextView)findViewById(R.id.row3);
        row2TextView = (TextView)findViewById(R.id.row2);
        row1TextView = (TextView)findViewById(R.id.row1);


        redButton.setEnabled(false);
        blueButton.setEnabled(false);
        pinkButton.setEnabled(false);
        orangeButton.setEnabled(false);
        purpleButton.setEnabled(false);
        greenButton.setEnabled(false);

        row1TextView.setText(" ");
        row2TextView.setText(" ");
        row3TextView.setText(" ");
        row4TextView.setText(" ");
        row5TextView.setText(" ");
        row6TextView.setText(" ");
        row7TextView.setText(" ");
        row8TextView.setText(" ");




        CreateBitmap();

        CreateCanvas();

        CanvasRadius = Math.min(canvas.getWidth(), canvas.getHeight());
        CanvasRadius2 = Math.min(canvas2.getWidth(), canvas2.getHeight());

        CreatePaint(Color.WHITE);

        canvas.drawColor(Color.LTGRAY);

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 8; j++) {

                canvas.drawCircle(
                        (i * (canvas.getWidth() / 5)),
                        (j * (canvas.getHeight() / 9)),
                        CanvasRadius / 14,
                        paint);
                boardView.setImageBitmap(bitmap);
            }

        }




        canvas2.drawColor(Color.GRAY);

        for (int i = 1; i <= 4; i++){
                canvas2.drawCircle(
                        (i * (canvas2.getWidth() / 5)),
                        ((canvas2.getHeight())/2),
                        CanvasRadius2 / 3,
                        paint);
                answerView.setImageBitmap(bitmap2);
            }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerView.setAlpha(0);
                for (int i = 1; i <=4; i++){
                    randomColor=RandomGenerator(i);
                    CreatePaint(randomColor);
                    canvas2.drawCircle(
                            (i * (canvas2.getWidth() / 5)),
                            ((canvas2.getHeight())/2),
                            CanvasRadius2 / 3,
                            paint);
                    answerView.setImageBitmap(bitmap2);
                }
                Log.d("this is my deep array", "deep arr: " + Arrays.toString(answer));
                /*for(int i = 0; i<8; i++)
                {
                    for(int j = 0; j<4; j++)
                    {
                        System.out.print(board[i][j]);
                    }
                    System.out.println("\t");
                }*/
                //Log.d("this is my deep array", "deep arr: " + Arrays.toString(board));

                row1TextView.setText(" ");
                row2TextView.setText(" ");
                row3TextView.setText(" ");
                row4TextView.setText(" ");
                row5TextView.setText(" ");
                row6TextView.setText(" ");
                row7TextView.setText(" ");
                row8TextView.setText(" ");


                redButton.setEnabled(true);
                blueButton.setEnabled(true);
                pinkButton.setEnabled(true);
                orangeButton.setEnabled(true);
                purpleButton.setEnabled(true);
                greenButton.setEnabled(true);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playButton.setEnabled(true);
                row1TextView.setText(" ");
                row2TextView.setText(" ");
                row3TextView.setText(" ");
                row4TextView.setText(" ");
                row5TextView.setText(" ");
                row6TextView.setText(" ");
                row7TextView.setText(" ");
                row8TextView.setText(" ");
                CreateBitmap();

                CreateCanvas();

                CanvasRadius = Math.min(canvas.getWidth(), canvas.getHeight());
                CanvasRadius2 = Math.min(canvas2.getWidth(), canvas2.getHeight());

                CreatePaint(Color.WHITE);

                canvas.drawColor(Color.LTGRAY);

                for (int i = 1; i <= 4; i++) {
                    for (int j = 1; j <= 8; j++) {

                        canvas.drawCircle(
                                (i * (canvas.getWidth() / 5)),
                                (j * (canvas.getHeight() / 9)),
                                CanvasRadius / 14,
                                paint);
                        boardView.setImageBitmap(bitmap);
                    }

                }

                canvas2.drawColor(Color.GRAY);

                for (int i = 1; i <= 4; i++){
                    canvas2.drawCircle(
                            (i * (canvas2.getWidth() / 5)),
                            ((canvas2.getHeight())/2),
                            CanvasRadius2 / 3,
                            paint);
                    answerView.setImageBitmap(bitmap2);
                }
                answerView.setAlpha(255);
                r=1;
                c=1;
                redButton.setEnabled(false);
                blueButton.setEnabled(false);
                pinkButton.setEnabled(false);
                orangeButton.setEnabled(false);
                purpleButton.setEnabled(false);
                greenButton.setEnabled(false);


            }
        });


    }



    public int RandomGenerator(int i){
        randomNumber = random.nextInt(6)+1;
        switch(randomNumber){
            case 1: randomColor=Color.RED;
                        answer[i-1]=1;
                break;
            case 2: randomColor=Color.CYAN;
                answer[i-1]=2;
                break;
            case 3: randomColor=Color.MAGENTA;
                answer[i-1]=3;
                break;
            case 4: randomColor=Color.YELLOW;
                answer[i-1]=4;
                break;
            case 5: randomColor=Color.BLUE;
                answer[i-1]=5;
                break;
            case 6: randomColor=Color.GREEN;
                answer[i-1]=6;
                break;
            default:randomColor=Color.WHITE;

        }
        return randomColor;
    }



                View.OnClickListener myHandler = new View.OnClickListener() {
                    public void onClick(View view) {
                            playButton.setEnabled(false);
                            switch (view.getId()) {
                                case R.id.buttonRed:
                                    board[8-c][r-1]=1;
                                    AddColor( Color.RED);

                                    break;
                                case R.id.buttonBlue:
                                    board[8-c][r-1]=2;
                                    AddColor( Color.CYAN);

                                    break;
                                case R.id.buttonPink:
                                    board[8-c][r-1]=3;
                                    AddColor(Color.MAGENTA);

                                    break;
                                case R.id.buttonOrange:
                                    board[8-c][r-1]=4;
                                    AddColor(Color.YELLOW);

                                    break;
                                case R.id.buttonPurple:
                                    board[8-c][r-1]=5;
                                    AddColor(Color.BLUE);

                                    break;
                                case R.id.buttonGreen:
                                    board[8-c][r-1]=6;
                                    AddColor(Color.GREEN);

                                    break;
                                default:
                                    AddColor( Color.WHITE);
                            }

                    }

                };





    private void AddColor(int colo){

        CreatePaint(colo);
        canvas.drawCircle(
                (r++ * (canvas.getWidth() / 5)),
                ((9-c) * (canvas.getHeight() / 9)),
                CanvasRadius / 14,
                paint);
        boardView.setImageBitmap(bitmap);

        if(r%5==0)
        {
            for(int i = 0; i<8; i++)
            {
                for(int j = 0; j<4; j++)
                {
                    System.out.print(board[i][j]);
                }
                System.out.println("\t");
            }
            flag=true;
            countblack=0;
            countwhite=0;
            checkForAnswer(c);
            r=1;
            c++;
        }
        if(c==9){
            System.out.println("correct");
            Context context=getApplicationContext();
            CharSequence text = "GAME OVER";
            int duration=Toast.LENGTH_LONG;
            Toast toast=Toast.makeText(context,text,duration);
            toast.show();
            answerView.setAlpha(255);
            redButton.setEnabled(false);
            blueButton.setEnabled(false);
            pinkButton.setEnabled(false);
            orangeButton.setEnabled(false);
            purpleButton.setEnabled(false);
            greenButton.setEnabled(false);
            playButton.setEnabled(false
            );
        }
    }









    public void CreateBitmap() {

        bitmap = Bitmap.createBitmap(
                113,
                160,
                Bitmap.Config.RGB_565
        );
        bitmap1 = Bitmap.createBitmap(
                35,
                160,
                Bitmap.Config.RGB_565
        );
        bitmap2 = Bitmap.createBitmap(
                128,
                20,
                Bitmap.Config.RGB_565
        );


    }

    public void CreateCanvas() {

        canvas = new Canvas(bitmap);
        canvas1 = new Canvas(bitmap1);
        canvas2 = new Canvas(bitmap2);


        //canvas.drawColor(Color.CYAN);

    }

    public void CreatePaint(int c) {

        paint = new Paint();

        paint.setStyle(Paint.Style.FILL);

        paint.setColor(c);

        paint.setAntiAlias(true);

    }

    public void checkForAnswer(int x){
        for (int i=0;i<4;i++){
                if(answer[i]!=board[8-x][i]){

                    flag=false;
                }
        }
        for (int i=0;i<4;i++) {
            if (answer[i] == board[8 - x][i]) {
                countblack++;
            }
        }
            ArrayList<Integer> alreadyProcessed= new ArrayList<Integer>();

            for(int color: answer)
                for(int j=0;j<4;j++)
                    if((color == board[8-x][j])&& !alreadyProcessed.contains(j)){
                        alreadyProcessed.add(j);
                        countwhite++;
                        break;
                    }
        countwhite-=countblack;

            if(x==0){
                row1TextView.setText("W:" + countwhite +" " +"B:" + countblack);
            }
            else if(x==1){
                row2TextView.setText("W:" + countwhite +" " +"B:" + countblack);
            }
            else if(x==2){
                row3TextView.setText("W:" + countwhite +" " +"B:" + countblack);
            }
            else if(x==3){
                row4TextView.setText("W:" + countwhite +" " +"B:" + countblack);
            }
            else if(x==4){
                row5TextView.setText("W:" + countwhite +" " +"B:" + countblack);
            }
            else if(x==5){
                row6TextView.setText("W:" + countwhite +" " +"B:" + countblack);
            }
            else if(x==6){
                row7TextView.setText("W:" + countwhite +" " +"B:" + countblack);
            }
            else if(x==7){
                row8TextView.setText("W:" + countwhite +" " +"B:" + countblack);
            }




        Log.i("h","Black" + countblack);
        Log.i("h","White" + countwhite);

        if(flag==true)
        {
            answerView.setAlpha(255);
            System.out.println("correct");
            Context context=getApplicationContext();
            CharSequence text = "YOU WIN!!";
            int duration=Toast.LENGTH_LONG;
            Toast toast=Toast.makeText(context,text,duration);
            toast.show();
            redButton.setEnabled(false);
            blueButton.setEnabled(false);
            pinkButton.setEnabled(false);
            orangeButton.setEnabled(false);
            purpleButton.setEnabled(false);
            greenButton.setEnabled(false);
        }
    }








}

