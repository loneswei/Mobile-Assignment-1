package assignment1.panda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class LevelSelect extends Activity implements View.OnClickListener {

    //define button as an object
    private Button button_level,
            button_level2, button_level4, button_level6, button_level8;
    private ImageButton button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // this is using layout
        setContentView(R.layout.levelselect);

        // set listener to button
        button_level = (Button)findViewById(R.id.btn_level);
        button_level.setOnClickListener(this);
        button_back = (ImageButton)findViewById(R.id.btn_back);
        button_back.setOnClickListener(this);

        // Non - Tutorial
        button_level2 = (Button)findViewById(R.id.btn_level2);
        button_level2.setOnClickListener(this);
        button_level4 = (Button)findViewById(R.id.btn_level4);
        button_level4.setOnClickListener(this);
        button_level6 = (Button)findViewById(R.id.btn_level6);
        button_level6.setOnClickListener(this);
        button_level8 = (Button)findViewById(R.id.btn_level8);
        button_level8.setOnClickListener(this);
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
        else
        {
            if(v == button_level)
                LevelManager.Instance.SetSelectedLevel(1);

            // Non - Tutorial
            else if(v == button_level2)
                LevelManager.Instance.SetSelectedLevel(2);
            else if(v == button_level4)
                LevelManager.Instance.SetSelectedLevel(4);
            else if(v == button_level6)
                LevelManager.Instance.SetSelectedLevel(6);
            else if(v == button_level8)
                LevelManager.Instance.SetSelectedLevel(8);

            intent.setClass(this, GameActivity.class);
        }

        startActivity(intent);
    }
}
