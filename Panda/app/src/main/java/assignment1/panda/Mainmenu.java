package assignment1.panda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Mainmenu extends Activity implements View.OnClickListener
{
    private Button button_start;
    private Button button_option;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // this is using layout
        setContentView(R.layout.mainmenu);

        // set listener to button
        button_start = (Button)findViewById(R.id.btn_start);
        button_start.setOnClickListener(this);
        button_option = (Button)findViewById(R.id.btn_option);
        button_option.setOnClickListener(this);
    }
    @Override
    //invoke a callback on clicked event on a view
    public void onClick(View v)
    {
        //Intent = action to be performed
        Intent intent = new Intent();

        if(v == button_start)
            intent.setClass(this, LevelSelect.class);
        // After create relevant classes then uncomment these
        else if(v == button_option)
            intent.setClass(this, Option.class);
        startActivity(intent);
    }
}