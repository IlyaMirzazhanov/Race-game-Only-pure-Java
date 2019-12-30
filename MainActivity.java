package com.example.ilya.racegame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MainActivity extends Activity {
    private static MediaPlayer mp;

    private class myView extends View {
        int BONUS_COUNT = 0;
        int HEALTH = HEALTH_MAX;
        int HEALTH_MAX = 3;
        int H_B_C_E;
        int H_B_C_H;
        int H_B_C_M;
        int LVL_1 = 20;
        int LVL_2 = 40;
        int LVL_3 = 80;
        int LVL_4 = 160;
        int bh = 80;
        private ArrayList<Bonus> bonuses;
        int btnx = ((disp.getWidth() / 2) - (bw / 2));
        int bw = 200;
        int by1 = (disp.getHeight() / 8);
        int by2 = (by1 + (disp.getHeight() / 3));
        int by3 = (by2 + (disp.getHeight() / 3));
        Display disp = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        private ArrayList<Fat> fats;
        private String level = ".     .     .     .     .      .     .     .     .     .     .     o      .     .     .     o     .     w      .     .      o     .       .       .       i\n.     .     .     .     .      .     .     w     o     .     .     .      .     .     .     .     .     .      .     .     .\n.     .     .     .     .      .     o     .     .     o     o     o      .     w     o     .     .     o      .     .     . w w w w w\n.     .     .     .     .      .     o     .     .     o     .     o      .     w     o     .     .     o      .     .     . w w w w w\n.     .     .     .     .      .     .     .     .     .     o     .      .     .     .     .     .     .      .     .\n";
        Paint myPaint = new Paint();
        int n;
        Rect rb = new Rect(btnx, by1, btnx + bw, by1 + bh);
        Rect rb2 = new Rect(btnx, by2, btnx + bw, by2 + bh);
        Rect rb3 = new Rect(btnx, by3, btnx + bw, by3 + bh);
        int rw = 20;
        SharedPreferences sPref;
        private ArrayList<Some> somes;
        int speed = 13;
        private int w = 0;
        int x = 200;
        int y = 600;

        public class Chek {
            protected boolean vis = true;
            protected int x;
            protected int y;

            public Chek(int x, int y) {
                x = x;
                y = y;
            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }

            public boolean isVisible() {
                return vis;
            }
        }

        public class Bonus extends Chek {
            public Bonus(int x, int y) {
                super(x, y);
            }

            public void move(int w, int speed) {
                if (y > w) {
                    Random rand2 = new Random();
                    int f = rand2.nextInt(3000) + 1;
                    x = rand2.nextInt(1500) + 1;
                    y = -f;
                }
                y += speed;
            }
        }

        public class Fat extends Chek {
            public Fat(int x, int y) {
                super(x, y);
            }

            public void move(int w, int speed) {
                if (y > w) {
                    Random rand2 = new Random();
                    int f = rand2.nextInt(3000) + 1;
                    x = rand2.nextInt(1500) + 1;
                    y = -f;
                }
                y += speed;
            }
        }

        public class Some extends Chek {
            public Some(int x, int y) {
                super(x, y);
            }

            public void move(int w, int speed) {
                if (y > w) {
                    Random rand2 = new Random();
                    int f = rand2.nextInt(3000) + 1;
                    x = rand2.nextInt(1500) + 1;
                    y = -f;
                }
                y += speed;
            }
        }

        public myView(Context context) {
            super(context);
            initSome();
            MainActivity.mp = MediaPlayer.create(MainActivity.getApplicationContext(), R.raw.dance);
            MainActivity.mp.setVolume(100.0f, 100.0f);
            MainActivity.mp.setLooping(true);
            MainActivity.mp.start();
        }

        public void initSome() {
            sPref = MainActivity.getSharedPreferences("MyPref", 0);
            H_B_C_E = sPref.getInt("load", H_B_C_E);
            H_B_C_M = sPref.getInt("load2", H_B_C_M);
            H_B_C_H = sPref.getInt("load3", H_B_C_H);
            somes = new ArrayList();
            bonuses = new ArrayList();
            fats = new ArrayList();
            int x = 30;
            int y = 30;
            for (int i = 0; i < level.length(); i++) {
                char item = level.charAt(i);
                if (item == 10) {
                    y += 20;
                    if (w < x) {
                        w = x;
                    }
                    x = 30;
                } else if (item == '.') {
                    somes.add(new Some(x, y - 400));
                    x += 20;
                } else if (item == 'o') {
                    bonuses.add(new Bonus(x, y - 400));
                    x += 20;
                } else if (item == 'w') {
                    fats.add(new Fat(x, y - 400));
                    x += 20;
                } else if (item == ' ') {
                    x += 20;
                }
            }
        }

        /* Access modifiers changed, original: protected */
        public void onDraw(Canvas canvas) {
            canvas.drawColor(-16777216);
            Rect btn = new Rect();
            btn.left = btnx;
            btn.top = by1;
            btn.right = btnx + bw;
            btn.bottom = by1 + bh;
            Rect btn2 = new Rect();
            btn2.left = btnx;
            btn2.top = by2;
            btn2.right = btnx + bw;
            btn2.bottom = by2 + bh;
            Rect btn3 = new Rect();
            btn3.left = btnx;
            btn3.top = by3;
            btn3.right = btnx + bw;
            btn3.bottom = by3 + bh;
            myPaint.setColor(Color.rgb(255, 255, R.styleable.AppCompatTheme_buttonStyle));
            canvas.drawRect(btn, myPaint);
            myPaint.setColor(Color.rgb(140, 255, R.styleable.AppCompatTheme_buttonStyle));
            canvas.drawRect(btn2, myPaint);
            myPaint.setColor(Color.rgb(255, 51, 51));
            canvas.drawRect(btn3, myPaint);
            myPaint.setColor(-16777216);
            myPaint.setTextSize(45.0f);
            myPaint.setAntiAlias(true);
            canvas.drawText("EASY", (float) (btnx + 45), (float) (by1 + 55), myPaint);
            canvas.drawText("MEDIUM", (float) (btnx + 15), (float) (by2 + 55), myPaint);
            canvas.drawText("HARD", (float) (btnx + 45), (float) (by3 + 55), myPaint);
            if (n == 1) {
                speed = 10;
            }
            if (n == 2) {
                speed = 13;
            }
            if (n == 3) {
                speed = 18;
            }
            if (n == 1 || n == 2 || n == 3) {
                Random rand2;
                int k;
                canvas.drawColor(Color.rgb(77, 77, 255));
                Rect rect = new Rect();
                rect.left = x;
                rect.top = y;
                rect.right = x + rw;
                rect.bottom = y + 60;
                myPaint.setColor(-16711936);
                canvas.drawRect(rect, myPaint);
                if (BONUS_COUNT >= LVL_1 && BONUS_COUNT < LVL_2) {
                    canvas.drawColor(-16777216);
                    myPaint.setColor(-1);
                    canvas.drawRect(rect, myPaint);
                }
                if (BONUS_COUNT >= LVL_2 && BONUS_COUNT < LVL_3) {
                    canvas.drawColor(Color.rgb(255, 153, R.styleable.AppCompatTheme_buttonStyle));
                    myPaint.setColor(Color.rgb(153, 0, 51));
                    canvas.drawRect(rect, myPaint);
                }
                if (BONUS_COUNT >= LVL_3 && BONUS_COUNT < LVL_4) {
                    canvas.drawColor(-1);
                    myPaint.setColor(-16777216);
                    canvas.drawRect(rect, myPaint);
                }
                Iterator it = bonuses.iterator();
                while (it.hasNext()) {
                    Bonus b = (Bonus) it.next();
                    b.move(canvas.getHeight(), speed);
                    invalidate();
                    if (b.isVisible()) {
                        Rect urect = new Rect();
                        urect.left = b.getX();
                        urect.top = b.getY();
                        urect.right = b.getX() + 20;
                        urect.bottom = b.getY() + 20;
                        myPaint.setStyle(Style.FILL);
                        myPaint.setColor(-65536);
                        canvas.drawRect(urect, myPaint);
                        if (rect.intersect(urect)) {
                            rand2 = new Random();
                            int f = rand2.nextInt(3000) + 1;
                            b.x = rand2.nextInt(1500) + 1;
                            b.y = -f;
                            BONUS_COUNT++;
                            if (rw != 20) {
                                rw -= 5;
                            }
                        }
                        if (BONUS_COUNT >= LVL_1 && BONUS_COUNT < LVL_2) {
                            myPaint.setColor(-16711681);
                            canvas.drawRect(urect, myPaint);
                        }
                        if (BONUS_COUNT >= LVL_2 && BONUS_COUNT < LVL_3) {
                            myPaint.setColor(Color.rgb(R.styleable.AppCompatTheme_buttonStyle, 51, 0));
                            canvas.drawRect(urect, myPaint);
                        }
                        if (BONUS_COUNT >= LVL_3 && BONUS_COUNT < LVL_4) {
                            myPaint.setColor(-16777216);
                            canvas.drawRect(urect, myPaint);
                        }
                    }
                }
                it = fats.iterator();
                while (it.hasNext()) {
                    Fat f2 = (Fat) it.next();
                    f2.move(canvas.getHeight(), speed);
                    invalidate();
                    if (f2.isVisible()) {
                        Rect frect = new Rect();
                        frect.left = f2.getX();
                        frect.top = f2.getY();
                        frect.right = f2.getX() + 30;
                        frect.bottom = f2.getY() + 40;
                        myPaint.setStyle(Style.FILL);
                        myPaint.setColor(Color.rgb(0, 204, 153));
                        canvas.drawRect(frect, myPaint);
                        if (rect.intersect(frect)) {
                            rand2 = new Random();
                            k = rand2.nextInt(3000) + 1;
                            f2.x = rand2.nextInt(1500) + 1;
                            f2.y = -k;
                            rw += 10;
                        }
                        if (BONUS_COUNT >= LVL_1 && BONUS_COUNT < LVL_2) {
                            myPaint.setColor(Color.rgb(214, 214, 194));
                            canvas.drawRect(frect, myPaint);
                        }
                        if (BONUS_COUNT >= LVL_2 && BONUS_COUNT < LVL_3) {
                            myPaint.setColor(Color.rgb(255, 0, R.styleable.AppCompatTheme_buttonStyle));
                            canvas.drawRect(frect, myPaint);
                        }
                        if (BONUS_COUNT >= LVL_3 && BONUS_COUNT < LVL_4) {
                            myPaint.setColor(-16777216);
                            canvas.drawRect(frect, myPaint);
                        }
                    }
                }
                it = somes.iterator();
                while (it.hasNext()) {
                    Some s = (Some) it.next();
                    s.move(canvas.getHeight(), speed);
                    invalidate();
                    if (s.isVisible()) {
                        Rect brect = new Rect();
                        brect.left = s.getX();
                        brect.top = s.getY();
                        brect.right = s.getX() + 20;
                        brect.bottom = s.getY() + 40;
                        myPaint.setColor(-256);
                        canvas.drawRect(brect, myPaint);
                        if (rect.intersect(brect)) {
                            canvas.drawColor(-16711936);
                            rand2 = new Random();
                            k = rand2.nextInt(3000) + 1;
                            s.x = rand2.nextInt(1500) + 1;
                            s.y = -k;
                            HEALTH--;
                            if (HEALTH == 0) {
                                canvas.drawColor(-65536);
                                initSome();
                                HEALTH = 3;
                                if (speed == 10 && BONUS_COUNT > H_B_C_E) {
                                    H_B_C_E = BONUS_COUNT;
                                } else if (speed == 13 && BONUS_COUNT > H_B_C_M) {
                                    H_B_C_M = BONUS_COUNT;
                                } else if (speed == 18 && BONUS_COUNT > H_B_C_H) {
                                    H_B_C_H = BONUS_COUNT;
                                }
                                sPref = MainActivity.getSharedPreferences("MyPref", 0);
                                Editor ed = sPref.edit();
                                ed.putInt("load", H_B_C_E);
                                ed.putInt("load2", H_B_C_M);
                                ed.putInt("load3", H_B_C_H);
                                ed.apply();
                                BONUS_COUNT = 0;
                            }
                            rw = 20;
                        }
                        if (BONUS_COUNT >= LVL_1 && BONUS_COUNT < LVL_2) {
                            myPaint.setColor(-65281);
                            canvas.drawRect(brect, myPaint);
                        }
                        if (BONUS_COUNT >= LVL_2 && BONUS_COUNT < LVL_3) {
                            myPaint.setColor(-65536);
                            canvas.drawRect(brect, myPaint);
                        }
                        if (BONUS_COUNT >= LVL_3 && BONUS_COUNT < LVL_4) {
                            myPaint.setColor(-16777216);
                            canvas.drawRect(brect, myPaint);
                        }
                    }
                }
                myPaint.setTextSize(50.0f);
                myPaint.setAntiAlias(true);
                if (BONUS_COUNT < LVL_3 || BONUS_COUNT >= LVL_4) {
                    if (BONUS_COUNT < LVL_2 || BONUS_COUNT >= LVL_3) {
                        myPaint.setColor(-1);
                    } else {
                        myPaint.setColor(-65536);
                    }
                } else {
                    myPaint.setColor(-16777216);
                }
                canvas.drawText("Bonus:" + BONUS_COUNT, 0.0f, 50.0f, myPaint);
                if (speed == 10) {
                    canvas.drawText("H.s.e:" + H_B_C_E, 0.0f, 100.0f, myPaint);
                }
                if (speed == 13) {
                    canvas.drawText("H.s.m:" + H_B_C_M, 0.0f, 100.0f, myPaint);
                }
                if (speed == 18) {
                    canvas.drawText("H.s.h:" + H_B_C_H, 0.0f, 100.0f, myPaint);
                }
                canvas.drawText("Health:" + HEALTH, (float) (canvas.getWidth() - 190), 50.0f, myPaint);
            }
        }

        public boolean onTouchEvent(MotionEvent event) {
            float xt = event.getX();
            float yt = event.getY();
            switch (event.getAction()) {
                case 0:
                    if (rb.contains((int) event.getX(), (int) event.getY()) && n == 0) {
                        n = 1;
                    }
                    if (rb2.contains((int) event.getX(), (int) event.getY()) && n == 0) {
                        n = 2;
                    }
                    if (rb3.contains((int) event.getX(), (int) event.getY()) && n == 0) {
                        n = 3;
                    }
                    invalidate();
                    break;
                case 1:
                    if (rb.contains((int) event.getX(), (int) event.getY()) && n == 0) {
                        n = 1;
                    }
                    if (rb2.contains((int) event.getX(), (int) event.getY()) && n == 0) {
                        n = 2;
                    }
                    if (rb3.contains((int) event.getX(), (int) event.getY()) && n == 0) {
                        n = 3;
                    }
                    invalidate();
                    break;
                case 2:
                    x = (int) xt;
                    invalidate();
                    break;
            }
            return true;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(new myView(this));
    }

    public void onPause() {
        mp.pause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        mp.seekTo(0);
        mp.start();
    }
}
