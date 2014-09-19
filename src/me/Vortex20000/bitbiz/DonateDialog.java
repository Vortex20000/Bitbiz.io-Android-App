package me.Vortex20000.bitbiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DonateDialog extends DialogFragment{
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder dbuilder = new AlertDialog.Builder(getActivity());
		dbuilder.setTitle(R.string.donate);
		dbuilder.setMessage(R.string.donations_body);
		dbuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		dbuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		return dbuilder.create();
	}
	
	
	

}
