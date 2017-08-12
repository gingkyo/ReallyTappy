package lasoo.reallytappy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;


/**
 * Created by ERN on 23/07/2017.
 */

public class TappyView extends SurfaceView implements Runnable {

    volatile boolean bPlaying;
    Thread gameThread=null;

    private Ship playerShip;
    public Ship enemyOne;
    public Ship enemyTwo;
    public Ship enemyThree;
    public ArrayList <SpaceDust> dustList = new ArrayList<SpaceDust>();
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;


    public TappyView(Context context,int x,int y) {
        super(context);
        surfaceHolder =getHolder();
        paint = new Paint();
        playerShip = new PlayerShip(context,y);
        playerShip.setHitBox();
        enemyOne = new EnemyShip(context,x,y,R.drawable.enemy,playerShip);
        enemyOne.setHitBox();
        enemyTwo = new EnemyShip(context,x,y,R.drawable.enemy2,playerShip);
        enemyTwo.setHitBox();
        enemyThree = new EnemyShip(context,x,y,R.drawable.enemy3,playerShip);
        enemyThree.setHitBox();

        int numSpecs =40;

        for(int i=0;i<numSpecs;i++) {
            SpaceDust spec =new SpaceDust(x,y);
            dustList.add(spec);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        // There are many different events in MotionEvent
        // We care about just 2 - for now.
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Has the player lifted their finger up?
            case MotionEvent.ACTION_UP:
                playerShip.stopBoosting();
                break;

            // Has the player touched the screen?
            case MotionEvent.ACTION_DOWN:
                playerShip.setBoosting();
                break;
        }
        return true;
    }

    @Override
    public void run() {
        while(bPlaying){
            if(!surfaceHolder.getSurface().isValid()) {
                continue;
            }
            tappyUpdate();
            tappyDraw();
            tappyControl();
        }
        
    }
    public void pause() {
        bPlaying = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {

        }
    }
    public void resume() {
        bPlaying =true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void tappyControl() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {

        }
    }

    private void tappyUpdate() {
        if(Rect.intersects
                (playerShip.getHitBox(), enemyOne.getHitBox())){
            enemyOne.resetX();
        }

        if(Rect.intersects
                (playerShip.getHitBox(), enemyTwo.getHitBox())){
            enemyTwo.resetX();
        }

        if(Rect.intersects
                (playerShip.getHitBox(), enemyThree.getHitBox())){
            enemyThree.resetX();
        }
        playerShip.update();
        enemyOne.update();
        enemyTwo.update();
        enemyThree.update();
        for(SpaceDust sd :dustList) {
            sd.update(playerShip.getSpeed());
        }
    }

    private void tappyDraw() {
        canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.argb(255,0,0,0));
        // White specs of dust
        paint.setColor(Color.argb(255, 255, 255, 255));

        for (SpaceDust sd : dustList) {
            canvas.drawPoint(sd.getX(), sd.getY(), paint);
        }
        canvas.drawBitmap(playerShip.getBitmap(),playerShip.getX(),playerShip.getY(),paint);
        canvas.drawBitmap(enemyOne.getBitmap(),enemyOne.getX(),enemyOne.getY(), paint);
        canvas.drawBitmap(enemyTwo.getBitmap(),enemyTwo.getX(),enemyTwo.getY(), paint);
        canvas.drawBitmap(enemyThree.getBitmap(),enemyThree.getX(),enemyThree.getY(), paint);

        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}
