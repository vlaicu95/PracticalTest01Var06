package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var03ChooseNumber extends AppCompatActivity {

    private EditText number;
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_choose_number);

        number = (EditText) findViewById(R.id.edit_text);
        play = (Button) findViewById(R.id.button);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number.getText() != null && !number.getText().toString().equals("")) {
                    Intent i = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
                    i.putExtra("number", number.getText().toString());
                    startActivity(i);
                }
            }
        });

    }
}
