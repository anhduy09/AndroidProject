package com.example.livedrawing;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Random;

public class ParticleSystem {
    private float mDuration;

    private ArrayList<Particle> mParticles;
    private Random random = new Random();
    boolean mIsRunning = false;

    void init(int numParticles) {
        for (int i = 0; i < numParticles; ++i) {
            float angle = (random.nextInt(350));
            angle = angle*3.14f/180.f;

            //option1 : slow particles
            //float speed = (random.nextFloat()/10);
            //option2: fast particles;
            float speed = (random.nextInt(10)+1);
            PointF direction;
            direction = new PointF((float)Math.cos(angle)*speed, (float)Math.sin(angle)*speed);
            mParticles.add(new Particle(direction));
        }
    }

    void update(long fps) {
        mDuration -= (1f/fps);

        for(Particle p : mParticles) {
            p.update(fps);
        }
        if (mDuration < 0) {
            mIsRunning = false;
        }
    }

    void emitParticles(PointF startDuration) {
        mIsRunning = true;

        //option 1 - system lasts for half a minute
        //mDuration  = 30f;

        //option 2 - system lasts for 2 seconds

        for(Particle p : mParticles) {
            p.setPosition(startDuration);
        }
    }

    void draw(Canvas canvas, Paint paint) {
        for (Particle p : mParticles) {
            //option1 - Coloured particles
            //paint.setARGB(255, random.nextInt(256),
            //random.nextInt(256),
            //random.nextInt(256);

            //option 2 - White particles
            paint.setColor(Color.argb(255,255,255,255));

            //how big is each particle?
            float sizeX = 0;
            float sizeY = 0;

            //option 1 - Big particles
            //sizeX = 25;
            //sizeY = 25;

            //opttion 2 - Medium particles
            sizeX = 10;
            sizeY = 10;

            //draw the particle
            //option1 - square particles
            //canvas.drawRect(p.getPosition().x, p.getPosition().y, p.getPosition().x +sizeX,
            //p.getPosition().y + sizeY, paint);

            //option 2 - circle particles
            canvas.drawCircle(p.getPosition().x, p.getPosition().y,
                    sizeX, paint);

        }
    }
}
