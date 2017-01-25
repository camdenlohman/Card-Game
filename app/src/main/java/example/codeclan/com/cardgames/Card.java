package example.codeclan.com.cardgames;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

/**
 * Created by user on 20/01/2017.
 */


public class Card {

    Rank name;
    Suit type;
    int num;
    Rect card_rect;
    Drawable card_image;
    ImageView imageView;
    int image_num;

    public Card(Rank message){
        this.name = message;
    }

    public void setType(Suit type){
        this.type = type;
    }

    public Suit getType(){
        return this.type;
    }

    public void setNum(int num){
        this.num = num;
    }

    public String standardGetInfo(){
        return this.name + " of " + this.type;
    }

    public int standardGetNumber(){
        return this.num;
    }

    public void setCard_rect(Rect R_input){
        this.card_rect = R_input;
    }

    public Rect getCard_rect(){
        return this.card_rect;
    }

    public void setCard_image(Drawable I_input){
        this.card_image = I_input;
    }

    public Drawable getCard_image(){
        return this.card_image;
    }

    public ImageView getImageView(){
        return this.imageView;
    }
    public void setImageview(ImageView I_input){
        this.imageView = I_input;
    }


    // which card ImageView should the card use?
    public void setImage_num(int input) {
        if (input == 0) {
            image_num = R.id.ace_of_hearts_IV;
        }
        if (input == 1) {
            image_num = R.id.h2_of_hearts_IV;
        }
        if (input == 2) {
            image_num = R.id.h3_of_hearts_IV;
        }
        if (input == 3) {
            image_num = R.id.h4_of_hearts_IV;
        }
        if (input == 4) {
            image_num = R.id.h5_of_hearts_IV;
        }
        if (input == 5) {
            image_num = R.id.h6_of_hearts_IV;
        }
        if (input == 6) {
            image_num = R.id.h7_of_hearts_IV;
        }
        if (input == 7) {
            image_num = R.id.h8_of_hearts_IV;
        }
        if (input == 8) {
            image_num = R.id.h9_of_hearts_IV;
        }
        if (input == 9) {
            image_num = R.id.h10_of_hearts_IV;
        }
//
    }

    public int getImage_num(){
        return this.image_num;
    }
}
