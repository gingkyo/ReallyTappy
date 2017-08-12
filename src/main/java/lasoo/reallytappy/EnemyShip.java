package lasoo.reallytappy;

import android.content.Context;

import java.util.Random;

/**
 * Created by ERN on 23/07/2017.
 */

public class EnemyShip extends Ship {
    // Detect enemies leaving the screen
    private int maxX, minX;
    private Ship playerShip;

    public EnemyShip(Context context,int screenX,int screenY,int bitmapRes,Ship playerShip) {
        super(context,bitmapRes);
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        this.playerShip = playerShip;

        Random generator = new Random();
        speed = generator.nextInt(6)+10;
        x = screenX;
        y = generator.nextInt(maxY) - bitmap.getHeight();
    }
    public void resetX() {
        this.x = this.maxX;
    }
    protected void update() {
        super.update();
        x-=playerShip.getSpeed();
        x-=speed;
        if(x<minX - bitmap.getHeight()) {
            Random generator = new Random();
            speed = generator.nextInt(10)+10;
            x = maxX;
            y = generator.nextInt(maxY) -bitmap.getHeight();
        }
    }


}
