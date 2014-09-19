package me.Vortex20000.bitbiz;

import java.util.Observable;
import java.util.Observer;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lazydroid.autoupdateapk.AutoUpdateApk;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParsePush;
import com.parse.PushService;

public class MainActivity extends ActionBarActivity implements Observer{
	
	
	private AutoUpdateApk aua; 

    @SuppressLint("SetJavaScriptEnabled") @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Parse.initialize(this, "EDITED_OUT_FOR_SAFETY", "SAME_THING_THIS_WAS_EDITED");
    	PushService.setDefaultPushCallback(this, MainActivity.class);
    	ParseAnalytics.trackAppOpened(getIntent());
    	PushService.subscribe(getApplicationContext(), "Alpha", MainActivity.class);
    	super.onCreate(savedInstanceState);
    	aua = new AutoUpdateApk(getApplicationContext());
    	aua.addObserver(this);
        WebView webview = new WebView(this);
        webview.setWebViewClient(new WebViewClient());
        setContentView(webview);
        webview.loadUrl("http://bitbiz.io/rf/?c=I4HFP8-F");
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	
        getMenuInflater().inflate(R.menu.main, menu);
        
        return true;
    }
    
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.donate) {
        	showDonations();
            return true;
        }
        if (id == R.id.openinbrowser){
        	Uri uri = Uri.parse("http://bitbiz.io/rf/?c=I4HFP8-F");
        	 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        	 startActivity(intent);
        	 return true;
        }
        if (id == R.id.officepage){
        	WebView webview = new WebView(this);
            webview.setWebViewClient(new WebViewClient());
            setContentView(webview);
            webview.loadUrl("http://bitbiz.io/office/public");
            Context context = getApplicationContext();
            CharSequence text = "Loading Office...";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            
        }
        if (id == R.id.push){
        	setContentView(R.layout.pushsignin);
        	final EditText userText = (EditText)findViewById(R.id.pushUsername);
        	final EditText userPass = (EditText)findViewById(R.id.pushPassword);
        	Button submit = (Button)findViewById(R.id.pushSubmit);
        	submit.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					if(userText.getText().toString().equalsIgnoreCase("Vortex20000") && userPass.getText().toString().equalsIgnoreCase("EDITED_MY_PASSWORD_OUT")){
						Context context = getApplicationContext();
						CharSequence text = "Login success.";
						int duration = Toast.LENGTH_LONG;
						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
						setContentView(R.layout.pushdashboard);
						final EditText pushMessage = (EditText)findViewById(R.id.pushMessage);
						Button launchPush = (Button)findViewById(R.id.launchPush);
						launchPush.setOnClickListener(new OnClickListener(){
							public void onClick(View v){
								ParsePush push = new ParsePush();
								push.setChannel("Alpha");
								push.setMessage(pushMessage.getText().toString());
								push.sendInBackground();
								pushMessage.setText("");
								Context context = getApplicationContext();
								CharSequence text = "Push sent.";
								int duration = Toast.LENGTH_LONG;
								Toast toast = Toast.makeText(context, text, duration);
								toast.show();
								
							}
						});
					} else{
						Context context = getApplicationContext();
						int duration = Toast.LENGTH_LONG;
						CharSequence text = "Login failed.";
						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
					}
					
				}
        	});
        	
        	
        	
        }
        return super.onOptionsItemSelected(item);
    }
    public void showDonations(){
    	DialogFragment frag = new DonateDialog();
    	frag.show(getFragmentManager(), "donations");
    }


    @Override
    public void update(Observable observable, Object data) {
            if( ((String)data).equalsIgnoreCase(AutoUpdateApk.AUTOUPDATE_GOT_UPDATE) ) {
                    android.util.Log.i("MainActivity", "Have just received update!");
            }
            if( ((String)data).equalsIgnoreCase(AutoUpdateApk.AUTOUPDATE_HAVE_UPDATE) ) {
                    android.util.Log.i("MainActivity", "There's an update available!");
            }
    }
}
