package assignment1.panda;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

import java.util.Arrays;
import java.util.List;


public class OptionState extends Activity implements OnClickListener
{
    private ImageButton btn_back;
    private Button btn_fbLogin;
    private Button btn_share;

    boolean LoggedIn = false;
    private CallbackManager _callbackmanager;
    private LoginManager _loginmanager;

    ProfilePictureView _ProfilePic;

    List<String> PERMISSIONS = Arrays.asList("publish_actions");

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Init for Facebook content
        FacebookSdk.setApplicationId(getString(R.string.appID));
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        setContentView(R.layout.option);

        btn_back = (ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        // The long FB button at the bottom of the page.
        btn_fbLogin = (LoginButton)findViewById(R.id.btn_fbLogin);
        btn_fbLogin.setOnClickListener(this);

        // Share Game Button, the small FB Icon.
        btn_share = (Button)findViewById(R.id.btn_share);
        btn_share.setOnClickListener(this);

        // Define Profile Pic to be displayed.
        _ProfilePic = (ProfilePictureView)findViewById(R.id.btn_fbProfilePic);
        _callbackmanager = CallbackManager.Factory.create();

        // Codes to deal with access token of Facebook
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker()
        {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken)
            {
                if(currentAccessToken == null)
                {
                    // User log out
                    _ProfilePic.setProfileId("");
                }
                else
                {
                    _ProfilePic.setProfileId(Profile.getCurrentProfile().getId());
                }
            }
        };

        accessTokenTracker.startTracking();

        // Codes to deal with Login Manager of Facebook
        _loginmanager = LoginManager.getInstance();
        _loginmanager.logInWithPublishPermissions(this, PERMISSIONS);

        _loginmanager.registerCallback(_callbackmanager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                _ProfilePic.setProfileId(Profile.getCurrentProfile().getId());
                ShareGame();
            }

            @Override
            public void onCancel()
            {
                System.out.println("Login attempt cancelled");
            }

            @Override
            public void onError(FacebookException error)
            {
                System.out.println("Login attempt failed.");
            }
        });
    }





    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        if(v == btn_back)
        {
            intent.setClass(this, MainMenuState.class);
            startActivity(intent);
        }
        else if(v == btn_share)
        {
            AlertDialog.Builder _alertBuilder = new AlertDialog.Builder(OptionState.this);
            _alertBuilder.setMessage("Do you want to share this game on Facebook?");
            _alertBuilder.setCancelable(false);

            _alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    // Call methods to share game
                    ShareGame();
                }
            });

            _alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    // Dialog cancel
                    dialog.cancel();
                }
            });

            _alertBuilder.show();
        }
    }

    public void ShareGame()
    {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.mainmenu_background);

        // Indicate what to share
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .setCaption("Thank you for playing Panda.")
                .build();

        // Share it out
        SharePhotoContent content =  new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        ShareApi.share(content, null);
    }

    // FB use callback manager to manage login
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        _callbackmanager.onActivityResult(requestCode, resultCode, data);
    }

    protected void onPause()
    {
        super.onPause();
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }

    protected void onStop()
    {
        super.onStop();
    }
}


/*
public class OptionState implements StateBase
{
    private int ScreenWidth, ScreenHeight;

    // Background
    private Bitmap bmp = null;
    // Back Button
    private Bitmap bmpBackButton = null;

    // Facebook //
    // Login Button
    private Bitmap bmpLoginButton = null;

    @Override
    public String GetName() { return "Options"; }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        // Options Background
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.option_background);
        // Back Button
        bmpBackButton = BitmapFactory.decodeResource(_view.getResources(), R.drawable.back);

        // Display Metrics
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
    }

    @Override
    public void OnExit() { }

    @Override
    public void Update(float _dt)
    {
        float imgBack = bmpBackButton.getWidth() * 0.5f;

        // Checking if there's any touch on any buttons
        if(TouchManager.Instance.isDown())
        {
            // Back Button.
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.03f, ScreenHeight * 0.05f, imgBack))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                StateManager.Instance.ChangeState("MainMenu");
            }
        }
    }

    @Override
    public void Render(Canvas _canvas)
    {
        // Background
        Matrix backgroundTransform = new Matrix();
        backgroundTransform.setTranslate(ScreenWidth * -0.05f, ScreenHeight * -0.09f);
        _canvas.drawBitmap(bmp, backgroundTransform, null);

        // Back Button
        Matrix buttonsTransform = new Matrix();
        buttonsTransform.setTranslate(ScreenWidth * 0.f, ScreenHeight * 0.0f);
        _canvas.drawBitmap(bmpBackButton, buttonsTransform, null);
    }
}
*/