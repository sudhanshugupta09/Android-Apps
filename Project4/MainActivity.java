package com.gupta.sudhanshu.cs478.project4_sdmp;


// author == Sudhanshu Gupta


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    //Variables

    Map<Integer,Integer> p1CorrectNumAndPos = new HashMap<>();
    Set<Integer> p1CorrectNumbers = new HashSet<>();

    Map<Integer,Integer> p2CorrectNumAndPos = new HashMap<>();
    Set<Integer> p2CorrectNumbers = new HashSet<>();

    ArrayList<Integer> p1DisplayCorrectPosNum = new ArrayList<>();
    ArrayList<Integer> p1DisplayWrongPosNum = new ArrayList<>();

    ArrayList<Integer> p2DisplayCorrectPosNum = new ArrayList<>();
    ArrayList<Integer> p2DisplayWrongPosNum = new ArrayList<>();


//    static ArrayList<Integer> numPool = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    int p1FinalNumberArray[] = new int[]{-1,-1,-1,-1};
    int p1finalNumGuess;
    int p2finalNumGuess;
    static Set<Integer> p1GuessSet= new HashSet<>();
    static Set<Integer> p2GuessSet= new HashSet<>();
    int player1Number;
    int player2Number;
//    int



    // Handlers
    Handler mainHandler;
    Handler p1Handler;
    Handler p2Handler;


    // Threads
    Thread p1Thread;
    Thread p2Thread;

    //Views
    //views
    EditText editTextPlayer1Choice;
    EditText editTextPlayer2Choice;
    EditText editTextCorrectPlaceNumbers;
    EditText editTextWrongPlaceNumbers;

    TextView textViewWinText;

    LinearLayout player1Guesses;
    LinearLayout player2Guesses;
    RelativeLayout player1Container;
    RelativeLayout player2Container;

    Context mContext;


    // Moves
    int player1Moves = 0;
    int player2Moves = 0;

    //Guess
    int player1Guess = 0;
    int player2Guess = 0;



    // Correct Position Guess Player 1


    // Wrong Position Guess PLayer 1


    // Message Arguments
    static final int GAME_START = 1;
    static final int GUESS_NEXT_NUMBER = 2;
    static final int UPDATE_PLAYER1_GUESS = 3;
    static final int UPDATE_PLAYER2_GUESS = 4;


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPlayer1Choice = (EditText) findViewById(R.id.editText_Player1Choice);
        editTextPlayer2Choice = (EditText) findViewById(R.id.editText_Player2Choice);
        editTextCorrectPlaceNumbers = (EditText) findViewById(R.id.editText_CorrectPlaceNumbers);
        editTextWrongPlaceNumbers = (EditText) findViewById(R.id.editText_WrongPlaceNumbers);

        player1Guesses = (LinearLayout) findViewById(R.id.layout_Player1Guesses);
        player2Guesses = (LinearLayout) findViewById(R.id.layout_Player2Guesses);
        player1Container = (RelativeLayout) findViewById(R.id.layout_Player1);
        player2Container = (RelativeLayout) findViewById(R.id.layout_Player2);


        textViewWinText = (TextView) findViewById(R.id.textView_winStatus);


        mContext = this;


        Button btn_start = (Button) findViewById(R.id.button_startGame);


        mainHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                Message message;
                switch (msg.what) {
                    case GAME_START:
                        Log.i("UI HANDLER","GAME STARTED");
                        player1Container.setBackgroundResource(R.drawable.border_blue);
                        message = p1Handler.obtainMessage(GUESS_NEXT_NUMBER);
                        p1Handler.sendMessage(message);
                        break;
                    case UPDATE_PLAYER1_GUESS:

                        int guess1 = msg.arg1;

                        // set views to the number
                        TextView show_guess1 = new TextView(mContext);
                        show_guess1.setText(String.valueOf(guess1));
                        player1Guesses.addView(show_guess1);

                        if (guess1 == player2Number) {
//                            clearAll();
                            // set player 1 won view
                            textViewWinText.setText(R.string.player1_won);
                            textViewWinText.setVisibility(View.VISIBLE);
                            p1Thread.interrupt();
                            p2Thread.interrupt();

                            mainHandler.removeCallbacksAndMessages(null);
                            p1Handler.removeCallbacksAndMessages(null);
                            p2Handler.removeCallbacksAndMessages(null);

                            // handle loopers
                            p1Handler.getLooper().quitSafely();
                            p2Handler.getLooper().quitSafely();

                            // exit
                            break;
                        } else {
                            // update the values in correct positions and wrong positions
                            // update guess set for the player
                            p1GuessSet.add(guess1);
                            getCorrectAndWrongPositionsPlayer1(guess1);
                            editTextCorrectPlaceNumbers.setText(p1DisplayCorrectPosNum.toString());
                            editTextWrongPlaceNumbers.setText(p1DisplayWrongPosNum.toString());

                        }

                        p2Handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        player1Container.setBackgroundResource(R.drawable.border);
                        player2Container.setBackgroundResource(R.drawable.border_blue);

                        message = p2Handler.obtainMessage(GUESS_NEXT_NUMBER);
                        p2Handler.sendMessage(message);

                        break;
                    case UPDATE_PLAYER2_GUESS:

                        int guess2 = msg.arg1;

                        // set views to the number
                        TextView show_guess2 = new TextView(mContext);
                        show_guess2.setText(String.valueOf(guess2));
                        player2Guesses.addView(show_guess2);

                        if (guess2 == player1Number) {
//                            clearAll();
                            // set player 1 won view
                            textViewWinText.setText(R.string.player2_won);
                            textViewWinText.setVisibility(View.VISIBLE);
                            p1Thread.interrupt();
                            p2Thread.interrupt();

                            mainHandler.removeCallbacksAndMessages(null);
                            p1Handler.removeCallbacksAndMessages(null);
                            p2Handler.removeCallbacksAndMessages(null);

                            // handle loopers
                            p1Handler.getLooper().quitSafely();
                            p2Handler.getLooper().quitSafely();
                            // exit
                            break;
                        } else {
                            // update guess set for the player
                            p2GuessSet.add(guess2);
                            // update the values in correct positions and wrong positions
                            getCorrectAndWrongPositionsPlayer2(guess2);
//                            p1DisplayWrongPosNum
                            editTextCorrectPlaceNumbers.setText(p2DisplayCorrectPosNum.toString());
                            editTextWrongPlaceNumbers.setText(p2DisplayWrongPosNum.toString());
                        }

                        p1Handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        player2Container.setBackgroundResource(R.drawable.border);
                        player1Container.setBackgroundResource(R.drawable.border_blue);

                        message = p1Handler.obtainMessage(GUESS_NEXT_NUMBER);
                        p1Handler.sendMessage(message);

                        break;

                    default:
                        break;

                }
            }
        };


//        onClick listener for start button
        btn_start.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (p1Thread != null && p2Thread != null) {

                    p1Thread.interrupt();
                    p2Thread.interrupt();

                    mainHandler.removeCallbacksAndMessages(null);
                    p1Handler.removeCallbacksAndMessages(null);
                    p2Handler.removeCallbacksAndMessages(null);

                    // handle loopers
                    p1Handler.getLooper().quitSafely();
                    p2Handler.getLooper().quitSafely();
                    clearAll();
                }

                player1Number = getRandomNumber();
                Log.i("OnClick PLayer 1 Number : ",""+player1Number);
                player2Number = getRandomNumber();
                Log.i("OnClick PLayer 2 Number : ",""+player2Number);

                // set text to these random generated numbers
                editTextPlayer1Choice.setText(""+player1Number);
                editTextPlayer2Choice.setText(""+player2Number);


                p1Thread = new Thread(new Runnable() {
                    @SuppressLint("HandlerLeak")
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Looper.prepare();

                        p1Handler = new Handler() {

                            Message message;

                            @Override
                            public void handleMessage(Message msg) {
                                switch (msg.what) {
                                    case GUESS_NEXT_NUMBER:
                                        message = mainHandler.obtainMessage(UPDATE_PLAYER1_GUESS);
                                        message.arg1 = player1NumberGuess();
                                        mainHandler.sendMessage(message);
                                        break;

                                    default:
                                        break;


                                }
                            }
                        };
                        // handler ends

                        // Start of the game, initial pass
                        Message message = mainHandler.obtainMessage(GAME_START);
                        mainHandler.sendMessage(message);

                        Looper.loop();
                    }


                });
                p1Thread.start();

                p2Thread = new Thread(new Runnable() {
                    @SuppressLint("HandlerLeak")
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Looper.prepare();

                        p2Handler = new Handler() {

                            Message message;

                            @Override
                            public void handleMessage(Message msg) {
                                switch (msg.what) {
                                    case GUESS_NEXT_NUMBER:
                                        message = mainHandler.obtainMessage(UPDATE_PLAYER2_GUESS);
                                        message.arg1 = player2NumberGuess();
                                        mainHandler.sendMessage(message);
                                        break;

                                    default:
                                        break;


                                }
                            }
                        };
                        // handler ends

                        Looper.loop();
                    }


                });
                p2Thread.start();
                Log.i("Thread2"," Player 2 thread started");
            }
        });


    }


    // Functions

    public void clearAll(){
        p1Thread.interrupt();
        p2Thread.interrupt();

        mainHandler.removeCallbacksAndMessages(null);
        p1Handler.removeCallbacksAndMessages(null);
        p2Handler.removeCallbacksAndMessages(null);

        // handle loopers
        p1Handler.getLooper().quitSafely();
        p2Handler.getLooper().quitSafely();


        editTextPlayer1Choice.setText("");
        editTextPlayer2Choice.setText("");
        editTextCorrectPlaceNumbers.setText("");
        editTextWrongPlaceNumbers.setText("");

        textViewWinText.setText("");

        player1Guesses.removeAllViews();
        player2Guesses.removeAllViews();

        p1CorrectNumAndPos.clear();
        p2CorrectNumAndPos.clear();

        p2CorrectNumbers.clear();
        p1CorrectNumbers.clear();

        p1DisplayCorrectPosNum.clear();
        p1DisplayWrongPosNum.clear();
        p2DisplayCorrectPosNum.clear();
        p2DisplayWrongPosNum.clear();
        for(int i=0; i<4; i++){
            p1FinalNumberArray[i] = -1;
        }
        player2Moves = 0;
        player1Moves = 0;
    }

//    public void checkGuess(int playerGuess, int currentGuess){
//        if(currentGuess == playerGuess){
////            break;
//        }
//    }

    // smart player
    public int player1NumberGuess(){

        boolean pos_flag[] = new boolean[]{false, false, false, false};
        Set<Integer> correctUnique = new HashSet<>();
        if (player1Moves == 0){
            player1Guess = 1234;
            player1Moves +=1;
            return player1Guess;
        }
        else if(player1Moves == 1){
            player1Guess = 5678;
            player1Moves +=1;
            return player1Guess;
        }
        else if(player1Moves == 2){
            player1Guess = 9012;
            player1Moves +=1;
            return player1Guess;
        }
        else{
            int count = 0;
            if( !p1CorrectNumAndPos.isEmpty()){

                System.out.println(p1CorrectNumAndPos);

                for(Map.Entry<Integer, Integer> entry: p1CorrectNumAndPos.entrySet()){
                    int guessNum = entry.getKey();
                    int  position = entry.getValue();
//                    Log.i("posititon",""+position);
//                    Log.i("guessNum",""+guessNum);

                    p1FinalNumberArray[position] = guessNum;
                    pos_flag[position]= true;
                    count +=1;
                }
            }
            if(count == 4){
                return getNumberFromArray(p1FinalNumberArray);
            }

            ArrayList<Integer> listCorrect = getArrayfromSet();
            Collections.shuffle(listCorrect);
            int j=0;
            for(int i=0; i<4; i++){
//                System.out.println(listCorrect);
//                System.out.println(j);
                if(!pos_flag[i]){
                    p1FinalNumberArray[i] = listCorrect.get(j);
                    j+=1;
                }
            }

            if(checkRedundantNumbers()){
                player1NumberGuess();
            }
            p1finalNumGuess = getNumberFromArray(p1FinalNumberArray);



            if (p1GuessSet.contains(p1finalNumGuess)){
                p1finalNumGuess = player1NumberGuess();
            }

        }
        player1Moves +=1;
        return p1finalNumGuess;
    }

    // dumb player
    public int player2NumberGuess(){

        p2finalNumGuess = getRandomNumber();
        while(p2GuessSet.contains(p2finalNumGuess)){
            p2finalNumGuess = getRandomNumber();
        }
        player2Moves +=1;
        return p2finalNumGuess;
    }

    public boolean checkRedundantNumbers(){

      Set<Integer> lump = new HashSet<Integer>();
      for (int i : p1FinalNumberArray)
      {
        if (lump.contains(i)) return true;
        lump.add(i);
      }
      return false;
    }
    public ArrayList<Integer> getArrayfromSet(){

        ArrayList<Integer> num = new ArrayList<>();
//        int num[] = new int[correctSet.size()];
        num.addAll(p1CorrectNumbers);

        return num;
    }
//    public int generateOneNumber(){
//        int num;
//
//        return num;
//    }
    // generalized generateRandomNumbers()
    public int getRandomNumber(){
        int result;

        int res[] = new int[4];
        int i=0;
        int j = 0;
        ArrayList<Integer> numPoolwZero = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Set<Integer> num = new HashSet<>();

        Collections.shuffle(numPoolwZero);
        while(numPoolwZero.get(0) == 0) {
            Collections.shuffle(numPoolwZero);
        }

        while (j<4) {
            if(!num.contains(numPoolwZero.get(i))){
                res[j] = numPoolwZero.get(i);
                num.add(res[j]);
                j+=1;
            }
            i += 1;
        }

        result = getNumberFromArray(res);
        return result;
    }

//    public int generateRandomNumber(){
//
//        Collections.shuffle(numPool);
//        int res[] = new int[4];
//        for(int i=0; i<4; i++){
//            res[i] = numPool.get(i);
//        }
//
//        p2finalNumGuess = getNumberFromArray(res);
//
//        if (p2GuessSet.contains(p2finalNumGuess)){
//            p2finalNumGuess = generateRandomNumber();
//        }
//
//        return p2finalNumGuess;
//    }
//
//
//    public void p1GetCorrectNumRightPos(){
//
//    }
//    public void p1GetCorrectNumWrongPos(){
//
//    }
//    public void p2GetCorrectNumRightPos(){
//
//    }
//    public void p2GetCorrectNumWrongPos(){
//
//    }

    public void getCorrectAndWrongPositionsPlayer1(int guess1){


        int[] guess_arr = convertIntToIntArray(guess1);
        int[] pnum_arr = convertIntToIntArray(player2Number);
        p1DisplayCorrectPosNum.clear();
        p1DisplayWrongPosNum.clear();
//        Log.i("guess_arr",""+guess_arr[0]);
//        Log.i("guess_arr",""+guess_arr[1]);
//        Log.i("guess_arr",""+guess_arr[2]);
//        Log.i("guess_arr",""+guess_arr[3]);
//        Log.i("pnum_arr",""+pnum_arr[0]);
//        Log.i("pnum_arr",""+pnum_arr[1]);
//        Log.i("pnum_arr",""+pnum_arr[2]);
//        Log.i("pnum_arr",""+pnum_arr[3]);

        for(int guess_i=0; guess_i<4; guess_i++){
            for(int p2num_j=0; p2num_j<4; p2num_j++){
                if(guess_i == p2num_j && guess_arr[guess_i] == pnum_arr[p2num_j]){
                    // <number , position>
//                    Log.i("","");
//                    Log.i("","");
//                    Log.i("","");
//                    Log.i("","");
                    p1CorrectNumAndPos.put(guess_arr[guess_i], guess_i);
                    p1DisplayCorrectPosNum.add(guess_arr[guess_i]);
                }
                else if(guess_arr[guess_i] == pnum_arr[p2num_j]){
//                    Log.i("","");
//                    Log.i("","");
                    p1CorrectNumbers.add(guess_arr[guess_i]);
                    p1DisplayWrongPosNum.add(guess_arr[guess_i]);
                }
            }
        }


    }

    public void getCorrectAndWrongPositionsPlayer2(int guess2){

        int[] guess_arr = convertIntToIntArray(guess2);
        int[] pnum_arr = convertIntToIntArray(player1Number);
        p2DisplayCorrectPosNum.clear();
        p2DisplayWrongPosNum.clear();


        for(int guess_i=0; guess_i<4; guess_i++){
            for(int p1num_j=0; p1num_j<4; p1num_j++){
                if(guess_i == p1num_j && guess_arr[guess_i] == pnum_arr[p1num_j]){
                    // <number , position>
                    p2CorrectNumAndPos.put(guess_arr[guess_i], guess_i);
                    p2DisplayCorrectPosNum.add(guess_arr[guess_i]);
                }
                else if(guess_arr[guess_i] == pnum_arr[p1num_j]){
                    p2CorrectNumbers.add(guess_arr[guess_i]);
                    p2DisplayWrongPosNum.add(guess_arr[guess_i]);
                }
            }
        }


    }

    public int[] convertIntToIntArray(int number){

//        int number = 1234567890;
        int len = Integer.toString(number).length();
        int[] iarray = new int[len];
        for (int index = 0; index < len; index++) {
            iarray[index] = number % 10;
            number /= 10;
        }

        for(int i=0; i<2; i++){
            int temp = iarray[i];
            iarray[i] = iarray[3-i];
            iarray[3-i] = temp;

        }
        return iarray;
    }

    public int getNumberFromArray(int[] p1FinalNumberArray){

        StringBuilder s = new StringBuilder();

        for(int num: p1FinalNumberArray){
            s.append(num);
        }

        p1finalNumGuess  = Integer.parseInt(s.toString());

        return p1finalNumGuess;
    }

}



