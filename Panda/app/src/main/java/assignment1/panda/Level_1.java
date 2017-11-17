package assignment1.panda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class Level_1  extends Activity implements OnClickListener{

    private ImageButton button_pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // this is using layout
        setContentView(R.layout.level_1);

        // set listener to button
        button_pause = (ImageButton)findViewById(R.id.btn_pause);
        button_pause.setOnClickListener(this);

    }
    @Override
    //invoke a callback on clicked event on a view
    public void onClick(View v)
    {
        //Intent = action to be performed
        Intent intent = new Intent();

       if(v == button_pause)
       {
           intent.setClass(this, Mainmenu.class);
       }

        // After create relevant classes then uncomment these
        //else if(v == button_options)
        //{
        //intent.setClass(this, Options.class);
        //}

        startActivity(intent);
    }
}
