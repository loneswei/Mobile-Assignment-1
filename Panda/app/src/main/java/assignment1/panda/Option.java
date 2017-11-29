package assignment1.panda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Option extends Activity implements View.OnClickListener {

    private ImageButton button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // this is using layout
        setContentView(R.layout.option);

        // set listener to button
        button_back = (ImageButton)findViewById(R.id.btn_back);
        button_back.setOnClickListener(this);
    }

    @Override
    //invoke a callback on clicked event on a view
    public void onClick(View v)
    {
        //Intent = action to be performed
        Intent intent = new Intent();

        if(v == button_back)
        {
            intent.setClass(this, Mainmenu.class);
        }

        startActivity(intent);
    }
}
