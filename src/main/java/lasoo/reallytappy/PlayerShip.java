package lasoo.reallytappy;

import android.content.Context;

/**
 * Created by ERN on 23/07/2017.
 */

public class PlayerShip extends Ship {

    private boolean bBoosting;
    private final int MIN_SPEED= 1;
    private final int MAX_SPEED = 20;
    private final int GRAVITY = -12;

    public PlayerShip(Context context,int screenY) {
        super(context,R.drawable.ship);
        bBoosting = false;
        x = 50;
        y= 50;
        maxY = screenY - bitmap.getHeight();

    }
    protected void update() {
        super.update();
        if(bBoosting) {
            speed += 2;
        } else {
            speed -= 5;
        }
        // Constrain top speed
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }

        // Never stop completely
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        // move the ship up or down
        y -= speed + GRAVITY;

        // But don't let ship stray off screen
        if (y < minY) {
            y = minY;
        }

        if (y > maxY) {
            y = maxY;
        }
    }


    protected int getSpeed() {
        return speed;
    }
    protected void setBoosting() {
        bBoosting = true;
    }
    protected void stopBoosting() {
        bBoosting = false;
    }
}
