package me.Vortex20000.bitbiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class UpdateDialog extends DialogFragment{
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder dbuilder = new AlertDialog.Builder(getActivity());
		dbuilder.setTitle(R.string.update);
		dbuilder.setMessage(R.string.update_body);
		dbuilder.setPositiveButton(R.string.download, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Uri uri = Uri.parse("http://auto-update-apk.com/download/me.Vortex20000.bitbiz");
	        	 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	        	 startActivity(intent);
				
			}
		});
		dbuilder.setNegativeButton(R.string.goon, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		return dbuilder.create();
	}
	
	
	

}
