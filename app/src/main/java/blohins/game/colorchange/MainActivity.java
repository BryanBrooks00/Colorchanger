package blohins.game.colorchange;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private  long backPressedTime;
    private Toast backToast;
    Context context = this;
   MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView background = (TextView) findViewById(R.id.background);
        mp = MediaPlayer.create(context, R.raw.sound);
        background.setBackgroundColor(Color.GREEN);
    background.setOnClickListener(v -> {
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(context, R.raw.sound);
            }  mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
            Random random = new Random();
            int color = Color.argb(255, random.nextInt(), random.nextInt(256), random.nextInt(256));
            background.setBackgroundColor(color);
        });
    }
    //System button Back START
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    //System button Back END
}