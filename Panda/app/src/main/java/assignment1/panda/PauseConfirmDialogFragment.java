package assignment1.panda;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class PauseConfirmDialogFragment extends DialogFragment
{
    public static boolean IsShown = false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        IsShown = true;

        // use the builder class to create our confirmation dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confirm Pause?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                        IsShown = false;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        IsShown = false;
                    }
                });
        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog)
    {
        IsShown = false;
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        IsShown = false;
    }
}
