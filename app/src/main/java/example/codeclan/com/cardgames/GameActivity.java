package example.codeclan.com.cardgames;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Rect;

import android.view.View.OnTouchListener;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by user on 20/01/2017.
 */

public class GameActivity extends AppCompatActivity {

    private ViewGroup rootLayout;
    private int _xDelta;
    private int _yDelta;

    TextView game_text;
    TextView result_text;

    ImageView img;

    SecureRandom rand = new SecureRandom();

    TypedArray imgs;

    Game game;

    Drawable draw_test;

    Rect rec = new Rect(50, 50, 200, 200);

    ArrayList<Card> deck;

    String result;
    String answer3;

    RelativeLayout.LayoutParams layoutParams;

    int radnum;

    Player p1 = new Player("P1");
    Player p2 = new Player("P2");
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        rootLayout = (ViewGroup) findViewById(R.id.activity_home);

        imgs = getResources().obtainTypedArray(R.array.cards_image_path_array);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String gameChoice = extras.getString("gameChoice");

        game = new Game(gameChoice);
        deck = game.getDeck();

        int cards_limit = 0;

        if (game.name.equals("HighCard")){
            cards_limit = 2;
        }
        if (game.name.equals("BlackJack")){
            cards_limit = 8;
        }


        for (int i = 0;i < cards_limit; i++) {

            Drawable draw_test = imgs.getDrawable(i);

            int resourceId = imgs.getResourceId(i, -1);
            deck.get(i).setCard_rect(rec);
            deck.get(i).setCard_image(draw_test);

            deck.get(i).setImage_num(i);

            int cardio;

            layoutParams = new RelativeLayout.LayoutParams(400, 400);

            cardio = deck.get(i).getImage_num();

            img = (ImageView) rootLayout.findViewById(cardio);

            img.setLayoutParams(layoutParams);
            img.setImageResource(resourceId);
            img.setOnTouchListener(new ChoiceTouchListener());

        }
        imgs.recycle();


        game_text = (TextView) findViewById(R.id.game_choice_out);
        result_text = (TextView) findViewById(R.id.result_text);

        radnum = rand.nextInt(game.getDeck().size());

        result = "test";

        if (deck.isEmpty()) {
            answer3 = "No more cards in the deck";
        } else {
            answer3 = deck.get(radnum).standardGetInfo();
        }

        if (game.name.equals("HighCard")) {

            int play_num = 0;

            CardTransfer_DToH(p1);
            CardTransfer_DToH(p2);

            imgs = getResources().obtainTypedArray(R.array.cards_image_path_array);

            for (int i = 0;i < 2; i++) {
                if (i == 0) {
                    play_num = p1.getHand().get(0).standardGetNumber();

                    if (p1.getHand().get(0).getType() == Suit.Hearts) {
                        play_num = play_num + (13 * 1);
                    }
                    if (p1.getHand().get(0).getType() == Suit.Clubs) {
                        play_num = play_num + (13 * 2);
                    }
                    if (p1.getHand().get(0).getType() == Suit.Spades) {
                        play_num = play_num + (13 * 3);
                    }

                    deck.get(i).setImage_num(play_num);
                }

                if (i == 1) {
                    play_num = p2.getHand().get(0).standardGetNumber();

                    if (p2.getHand().get(0).getType() == Suit.Hearts) {
                        play_num = play_num + (13 * 1);
                    }
                    if (p2.getHand().get(0).getType() == Suit.Clubs) {
                        play_num = play_num + (13 * 2);
                    }
                    if (p2.getHand().get(0).getType() == Suit.Spades) {
                        play_num = play_num + (13 * 3);
                    }

                    deck.get(i).setImage_num(play_num);
                }
                draw_test = imgs.getDrawable(play_num);

                int resourceId = imgs.getResourceId(play_num, -1);
                deck.get(i).setCard_rect(rec);
                deck.get(i).setCard_image(draw_test);

                int cardio;

                layoutParams = new RelativeLayout.LayoutParams(400, 400);

                cardio = deck.get(i).getImage_num();

                img = (ImageView) rootLayout.findViewById(cardio);

                img.setLayoutParams(layoutParams);
                img.setImageResource(resourceId);
                DisplayMetrics displaymetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                int height = displaymetrics.heightPixels;
                int width = displaymetrics.widthPixels;
                if (i == 0) {
                    img.setY((height / 4) * 2 - 200);
                }

                if (i == 1) {
                   img.setY((height / 4) * 2 - 200);
                    img.setX(500);
                }
                img.setOnTouchListener(new ChoiceTouchListener());

            }
            imgs.recycle();

            if (p1.getHand().get(0).standardGetNumber() >= p2.getHand().get(0).standardGetNumber()) {
                result = "Player 1 Wins!";
            }

            if (p1.getHand().get(0).standardGetNumber() <= p2.getHand().get(0).standardGetNumber()) {
                result = "Player 2 Wins!";
            }

            if (p1.getHand().get(0).standardGetNumber() == p2.getHand().get(0).standardGetNumber()) {
                result = "It's a draw. :(";
            }

            System.out.println(result);
        }

        if (game.name.equals("BlackJack")) {

            int play_num = 0;

            CardTransfer_DToH(p1);
            CardTransfer_DToH(p1);

            CardTransfer_DToH(p2);
            CardTransfer_DToH(p2);

            int p1_total = 0;
            int p2_total = 0;

            for (int i = 0; i < p1.getHand().size(); i++) {
                p1_total += p1.getHand().get(i).standardGetNumber();
            }

            for (int i = 0; i < p2.getHand().size(); i++) {
                p2_total += p2.getHand().get(i).standardGetNumber();
            }

            while (!p1.getStop()) {

                p1.setTotal(p1_total);

                System.out.println(p1.getName() + " make your choice.");
                System.out.println("Hit or Stay?");

                boolean hit = NewRandP(p1);

                if (hit) {
                    CardTransfer_DToH(p1);
                    p1_total += p1.getHand().get(p1.getHand().size() - 1).standardGetNumber();
                }

            }

            while (!p2.getStop()) {

                p2.setTotal(p2_total);

                System.out.println(p2.getName() + " make your choice.");
                System.out.println("Hit or Stay?");

                boolean hit = NewRandP(p2);

                if (hit) {
                    CardTransfer_DToH(p2);
                    p2_total += p2.getHand().get(p2.getHand().size() - 1).standardGetNumber();
                }

            }

            imgs = getResources().obtainTypedArray(R.array.cards_image_path_array);

            for (int i = 0;i < p1.handSize(); i++) {

                play_num = p1.getHand().get(i).standardGetNumber();

                if (p1.getHand().get(i).getType() == Suit.Hearts) {
                    play_num = play_num + (13 * 1);
                }
                if (p1.getHand().get(i).getType() == Suit.Clubs) {
                    play_num = play_num + (13 * 2);
                }
                if (p1.getHand().get(i).getType() == Suit.Spades) {
                    play_num = play_num + (13 * 3);
                }

                p1.getHand().get(i).setImage_num(play_num);


                draw_test = imgs.getDrawable(play_num);

                int resourceId = imgs.getResourceId(play_num, -1);
                deck.get(i).setCard_rect(rec);
                deck.get(i).setCard_image(draw_test);

                int cardio;

                layoutParams = new RelativeLayout.LayoutParams(400, 400);

                p1.getHand().get(i).setImage_num(i);

                cardio = p1.getHand().get(i).getImage_num();

                img = (ImageView) rootLayout.findViewById(cardio);

                img.setLayoutParams(layoutParams);
                img.setImageResource(resourceId);
                DisplayMetrics displaymetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                int height = displaymetrics.heightPixels;
                int width = displaymetrics.widthPixels;
                if (i == 0) {
                    img.setY((height / 4) * 2 - 200);
                }

                if (i == 1) {
                    img.setY((height / 4) * 2 - 150);
                }
                if (i == 2) {
                    img.setY((height / 4) * 2 - 100);
                }
                if (i == 3) {
                    img.setY((height / 4) * 2 - 50);
                }
                if (i == 4) {
                    img.setY((height / 4) * 2);
                }
                if (i == 5) {
                    img.setY((height / 4) * 2 - 100);
                    img.setX(300);
                }
                if (i == 6) {
                    img.setY((height / 4) * 2 + 50);
                }
                if (i == 7) {
                    img.setY((height / 4) * 2 + 100);
                }
                if (i == 8) {
                    img.setY((height / 4) * 2 + 150);
                }
                if (i == 9) {
                    img.setY((height / 4) * 2 + 200);
                }
                img.setOnTouchListener(new ChoiceTouchListener());

            }

            int loop_2_total = p2.handSize() + p1.handSize();

            for (int i = p1.handSize();i < loop_2_total; i++) {

                play_num = p2.getHand().get(i - p1.getHand().size()).standardGetNumber();

                if (p2.getHand().get(i - p1.getHand().size()).getType() == Suit.Hearts) {
                    play_num = play_num + (13 * 1);
                }
                if (p2.getHand().get(i - p1.getHand().size()).getType() == Suit.Clubs) {
                    play_num = play_num + (13 * 2);
                }
                if (p2.getHand().get(i - p1.getHand().size()).getType() == Suit.Spades) {
                    play_num = play_num + (13 * 3);
                }

                p2.getHand().get(i - p1.getHand().size()).setImage_num(play_num);


                draw_test = imgs.getDrawable(play_num);

                int resourceId = imgs.getResourceId(play_num, -1);
                deck.get(i).setCard_rect(rec);
                deck.get(i).setCard_image(draw_test);

                int cardio;

                layoutParams = new RelativeLayout.LayoutParams(400, 400);

                p2.getHand().get(i - p1.getHand().size()).setImage_num(i);

                cardio = p2.getHand().get(i - p1.getHand().size()).getImage_num();

                img = (ImageView) rootLayout.findViewById(cardio);

                img.setLayoutParams(layoutParams);
                img.setImageResource(resourceId);
                DisplayMetrics displaymetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                int height = displaymetrics.heightPixels;
                int width = displaymetrics.widthPixels;
                if (i == 0) {
                    img.setY((height / 4) * 2 - 200);
                    img.setX(300);
                }

                if (i == 1) {
                    img.setY((height / 4) * 2 - 200);
                    img.setX(300);
                }
                if (i == 2) {
                    img.setY((height / 4) * 2 - 200);
                    img.setX(300);
                }
                if (i == 3) {
                    img.setY((height / 4) * 2 - 200);
                    img.setX(300);
                }
                if (i == 4) {
                    img.setY((height / 4) * 2 - 200);
                    img.setX(300);
                }
                if (i == 5) {
                    img.setY((height / 4) * 2 - 200);
                    img.setX(300);
                }
                if (i == 6) {
                    img.setY((height / 4) * 2 - 200);
                    img.setX(300);
                }
                if (i == 7) {
                    img.setY((height / 4) * 2 - 200);
                    img.setX(300);
                }
                if (i == 8) {
                    img.setY((height / 4) * 2 - 200);
                    img.setX(300);
                }
                if (i == 9) {
                    img.setY((height / 4) * 2 - 200);
                    img.setX(300);
                }
                img.setOnTouchListener(new ChoiceTouchListener());

            }
            imgs.recycle();

            if (p1_total >= p2_total) {
                result = "Player 1 Wins BlackJack!";
            }

            if (p1_total <= p2_total) {
                result = "Player 2 Wins BlackJack!";
            }

            if (p1_total == p2_total) {
                result = "It's a draw. :(";
            }
            System.out.println("Player 1 total:" + p1_total);
            System.out.println("Player 2 total:" + p2_total);
            System.out.println(result);
        }

        result_text.setText(result);
        System.out.println(result_text);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Game Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private final class ChoiceTouchListener implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams.leftMargin;
                    _yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                            .getLayoutParams();
                    layoutParams.leftMargin = X - _xDelta;
                    layoutParams.topMargin = Y - _yDelta;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    view.setLayoutParams(layoutParams);
                    break;
            }
            rootLayout.invalidate();
            return true;
        }
    }

    public int NewRand() {
        if (deck.isEmpty()) {
            radnum = 0;
        } else {
            radnum = rand.nextInt(game.getDeck().size());
        }

        return radnum;
    }

    public boolean NewRandP(Player player) {

        boolean hit = false;

        radnum = rand.nextInt(21);

        if (radnum > player.getTotal()) {
            hit = true;
        } else {
            player.setStop(true);
        }

        System.out.println(radnum);
        System.out.println(player.getTotal());

        return hit;
    }

    public void CardTransfer_DToH(Player current_player) {

        int num = NewRand();

        Card c_card = deck.get(num);

        current_player.intoHand(c_card);

        game.outOfDeck(num);

        deck = game.getDeck();
    }


}

