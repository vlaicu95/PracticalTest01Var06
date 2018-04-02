package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {

    private Button generate;
    private TextView guess;
    private TextView score;
    private Button back;
    private Button check;

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private IntentFilter intentFilter = new IntentFilter();
    int number = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        generate = (Button) findViewById(R.id.button2);
        guess =  findViewById(R.id.textView3);
        back = findViewById(R.id.button4);

        check = findViewById(R.id.button3);
        score = findViewById(R.id.textView4);
        score.setText("0");

        Intent i = getIntent();
        String n = i.getStringExtra("number");
        number = Integer.parseInt(n);
        intentFilter.addAction("actiune");

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (guess.getText() != null) {
                    int aux = Integer.parseInt(guess.getText().toString());

                    if (aux == number) {
                        int x = Integer.parseInt(score.getText().toString());
                        x++;
                        score.setText(String.valueOf(x));
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PracticalTest01Var03ChooseNumber.class);
                startActivity(i);
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                int aux = r.nextInt(9);
                guess.setText(String.valueOf(aux));

            }
        });

        Intent intent = new Intent(getApplicationContext(), PracticalTest01Service.class);
        getApplicationContext().startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("guess", guess.getText().toString());
        savedInstanceState.putString("score", score.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("guess")) {
            guess.setText(savedInstanceState.getString("guess"));
        } else {
            //guess.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("score")) {
            score.setText(savedInstanceState.getString("score"));
        } else {
            score.setText(String.valueOf(0));
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Service.class);
        stopService(intent);
        super.onDestroy();
    }

    class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
            guess.setText(intent.getStringExtra("message"));
        }
    }
}



