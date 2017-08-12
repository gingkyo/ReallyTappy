package lasoo.reallytappy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by ERN on 23/07/2017.
 */

public class Ship {
    protected int x,y,minY,maxY;
    protected int speed;
    protected Bitmap bitmap;
    protected Rect hitBox;
    public Ship(Context context, int bitmapRes) {

        bitmap = BitmapFactory.decodeResource(context.getResources(),bitmapRes);
        minY = 0;
        speed =1;
    }
    protected Bitmap getBitmap() {
        return bitmap;
    }

    protected void resetX() {
    }
    protected int getX() {
        System.out.println("hit");return x;
    }

    protected int getY() {
        return y;
    }

    protected void update() {
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + bitmap.getWidth();
        hitBox.bottom = y + bitmap.getHeight();
    }
    protected int getSpeed() {
        return speed;
    }
    protected void setBoosting() {
    }
    protected void stopBoosting() {
    }
    public void setHitBox() {
        this.hitBox =new Rect(this.x,this.y,this.bitmap.getWidth(),this.bitmap.getHeight());
    }
    public Rect getHitBox() {
        return hitBox;
    }
}
