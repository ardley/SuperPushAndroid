package com.example.superpushv1;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class IntenstigatorActivity extends Activity {
	
	
	private CharSequence pastedText;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CharSequence text = "Hello from the Clipboard!"; //This is the initial input
		clipText(text);									 //This is where the input goes to the clipboard
		pastedText = pasteText();						 //This is where it comes back from the clipboard
		broadcastToastIntent();					     	 //This is where the intent launches a BR
		finish();										 //This closes the initial activity
		
	}
	
	
	
	
	
	private void broadcastToastIntent(){ 					//Sends intent
       Intent intent = new Intent("ToastSenderIntent");
       intent.putExtra("message", pastedText);
       intent.setAction("com.example.superpushV1.TOASTSENDER_INTENT");
       sendBroadcast(intent);
    }
	
	private void clipText(CharSequence toClip) {   			//Sends argument to clipboard... Need better validation or casting
		ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("simple text", toClip);
		clipboard.setPrimaryClip(clip);
	}
	
	private CharSequence pasteText(){						//makes a var from the clipboard contents
		ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		CharSequence pasteData = "";
		ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
		pasteData = item.getText();
		if (pasteData != null) {
		    return pasteData;
		} 
		else {
		    Log.e("Clipboard contains an invalid data type", null);
		    return null;
		}    
	}
	
	
                
	   
	
}