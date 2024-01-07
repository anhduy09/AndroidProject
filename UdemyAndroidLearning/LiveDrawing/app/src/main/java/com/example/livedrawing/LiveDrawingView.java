package com.example.livedrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;

public class LiveDrawingView extends SurfaceView implements Runnable{
    //Are we debugging???
    private final boolean DEBUGGING = true;

    //these objects are needed to do the drawing
    private SurfaceHolder mOurHolder;
    private Canvas mCanvas;
    private Paint mPaint;

    //how many frames per second did we get??
    private long mFPS;
    //the number of millisecons in a second
    private final int MILLIS_IN_SECOND = 1000;

    // Holds the resolution of the screen
    private int mScreenX;
    private int mScreenY;

    //How big will the text be???
    private int mFontSize;
    private int mFontMargin;

    //The particle system will be declared here later

    //these will be used to make simple buttons
    private RectF mResetButton;
    private RectF mTogglePauseButton;

    //here is the Thread and two control variables
    private Thread mThread = null;
    //this volatile variable can be accessed from inside and outside the thread
    private volatile boolean mDrawing;
    private boolean mPaused = true;

    //
    private ArrayList<ParticleSystem> mParticleSystems = new ArrayList<>();
    private int mNextSystem = 0;
    private final int MAX_SYSTEM = 1000;
    private int mParticlePerSystem = 100;

    public LiveDrawingView(Context context, int x, int y) {
        super(context);
        mScreenX = x;
        mScreenY = y;

        //Font is 5% (1/20) of screen width
        mFontSize = mScreenX / 20;
        mFontMargin = mScreenX / 75;

        //getHolder is a method of SurfaveView
        mOurHolder = getHolder();
        mPaint = new Paint();

        //Init the two buttons
        mResetButton = new RectF(0,0,100,100);
        mTogglePauseButton = new RectF(0,150,100,250);

        for (int i = 0; i < MAX_SYSTEM; ++i) {
            mParticleSystems.add(new ParticleSystem());
            mParticleSystems.get(i).init(mParticlePerSystem);
        }

    }

    private void draw() {
        if (mOurHolder.getSurface().isValid()) {
            //lock the canvas (graphic memory) ready to draw
            mCanvas = mOurHolder.lockCanvas();
            //Fill the screen with a solod color
            mCanvas.drawColor(Color.argb(255,0,0,0));

            //Choose a color to paint with
            mPaint.setColor(Color.argb(255,255,255,255));

            //choose the font size
            mPaint.setTextSize(mFontSize);

            //draw particle system
            for (int i = 0; i < mNextSystem; ++i) {
                mParticleSystems.get(i).draw(mCanvas, mPaint);
            }

            //draw the button
            mCanvas.drawRect(mResetButton, mPaint);
            mCanvas.drawRect(mTogglePauseButton, mPaint);
            //draw HUD

            if (DEBUGGING) {
                printDebuggingText();
            }

            //display the drawing on screen
            // unlockCanvasAndPost is a method of SurfaceHolder
            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public void run() {
        while(mDrawing) {
            // what time is it now at the start of the loop?
            long frameStartTime = System.currentTimeMillis();

            //provide the app isn't paused
            // call the update method
            if(!mPaused) {
                update();
                //now the particles are in thei new positions
            }

            //the movement has been handled and now we can draw the scene
            draw();

            //how long did this frame/loop take?
            //store the answer in the timeThisFrame
            long timeThisFrame = System.currentTimeMillis() - frameStartTime;

            //make sure timeThisFrame is at least 1
            // millisecond because accidentally dividing by zero crashes the app
            if (timeThisFrame > 0) {
                //store the current frame rate in mFPS
                // ready to pass to the update methods of out parricles in the next frame/loop
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }
    }

    private void update() {
        //update particle
        for (int i = 0; i < mParticleSystems.size(); ++i) {
            if (mParticleSystems.get(i).mIsRunning) {
                mParticleSystems.get(i).update(mFPS);
            }
        }
    }

    //this method is called by LiveDrawingActivity when user quits app
    public void pause() {
        //set mDrawing to false, stop the thread isn't always instant
        mDrawing = false;
        try {
            mThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "Joining thread");
        }
    }

    // this method is called by LiveDrawingActivity when the player starts the app
    public  void resume() {
        mDrawing = true;
        mThread.start();
    }

    private void printDebuggingText() {
        int debugSize = mFontSize / 2;
        int debugStart = 150;
        mPaint.setTextSize(debugSize);
        mCanvas.drawText("FPS: " + mFPS, 10, debugStart + debugSize, mPaint);

        //we will add more code here in the next

        mCanvas.drawText("Particles: " + mNextSystem*mParticlePerSystem, 10, mFontMargin
        +debugStart + debugSize*3, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        if (((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_MOVE))
        {
            mParticleSystems.get(mNextSystem).emitParticles(new PointF(event.getX(),
                    event.getY()));
            mNextSystem++;
            if(mNextSystem == MAX_SYSTEM) {
                mNextSystem = 0;
            }
        }

        // did the user touch the screeen
        if ((event.getAction() & MotionEvent.ACTION_MASK) == event.ACTION_DOWN) {
            //user pressed the screen see if it was in a button
            if (mResetButton.contains(event.getX(), event.getY())) {
                //clear the screen of all particles
                mNextSystem = 0;
            }

            //user pressed the screen see if it was in a button
            if (mTogglePauseButton.contains(event.getX(), event.getY())) {
                mPaused = !mPaused;
            }
        }
        return true;
    }
}
