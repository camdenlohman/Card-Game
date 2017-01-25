package example.codeclan.com.cardgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button HighCard_button;

    String gameChoice = "";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HighCard_button = (Button)findViewById(R.id.HighCard_button);

        intent = new Intent(HomeActivity.this, GameActivity.class);
    }

    public void onHighCardButtonPressed(View Button){


        gameChoice = "HighCard";
        intent.putExtra("gameChoice", gameChoice);
        startActivity(intent);
    }

    public void onBlackJackButtonPressed(View Button){


        gameChoice = "BlackJack";
        intent.putExtra("gameChoice", gameChoice);
        startActivity(intent);
    }
}
