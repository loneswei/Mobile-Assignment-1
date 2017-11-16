package assignment1.panda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Mainmenu extends Activity implements OnClickListener{

    //define button as an object
    private Button button_start;
    //private Button button_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // this is using layout
        setContentView(R.layout.mainmenu);

        // set listener to button
        button_start = (Button)findViewById(R.id.button_start);
        button_start.setOnClickListener(this);
        //button_help = (Button)findViewById(R.id.button_help);
        //button_help.setOnClickListener(this);

    }
    @Override
    //invoke a callback on clicked event on a view
    public void onClick(View v)
    {
        //Intent = action to be performed
        Intent intent = new Intent();

        if(v == button_start)
        {
            intent.setClass(this, Splashscreen.class);
        }
        //else if(v == button_help)
        //{
        //intent.setClass(this, SampleHelp.class);
        //}
        startActivity(intent);
    }
}