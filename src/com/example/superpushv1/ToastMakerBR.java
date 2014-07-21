package com.example.superpushv1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;



public class ToastMakerBR extends BroadcastReceiver{

	
	private Context lastActivityContext; //The context of the activity that called this in receiver in the first place)
	private CharSequence intentData;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		// Extract data included in the Intent
		intentData = intent.getCharSequenceExtra("message");
		lastActivityContext = context;
		Toast.makeText(context, "ToastMakerBR received the Intent's message: "+intentData, Toast.LENGTH_LONG).show();
		broadcastHttpPostIntent();
	}
	
	private void broadcastHttpPostIntent(){ 					//Sends intent
	       Intent intent = new Intent("HttpPostSenderIntent");
	       intent.putExtra("message", intentData);
	       intent.setAction("com.example.superpushV1.HTTPPOSTSENDER_INTENT");
	       lastActivityContext.sendBroadcast(intent);
	}

	
}

