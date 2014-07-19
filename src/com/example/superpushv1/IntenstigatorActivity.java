package com.example.superpushv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

public class IntenstigatorActivity extends Activity {
	
	private CharSequence pastedText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(isConnected()){
			Log.w("1 ","You are connected -InstActiv");
		}
		else Log.w("1 ", "You are NOT connected -InstActiv");
		CharSequence text = "Hello from the Clipboard!"; //This is the initial input
		clipText(text);									 //This is where the input goes to the clipboard
		pastedText = pasteText();						 //This is where it comes back from the clipboard
		broadcastToastIntent();					     	 //This is where the intent launches a BR
		finish();										 //This closes the initial activity
		
	}
	
	public static String POST(String url, Pushablemsg pushable){//Changed 2nd arg from Person person
        InputStream inputStream = null;
        String result = "";
        try {
        	// 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);
            String json = "";
            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("message", pushable.getMessage());
            jsonObject.accumulate("acctId", pushable.getAcctId());
            jsonObject.accumulate("extra", pushable.getExtraVar());            
            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();
            // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(pushable); 
            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);
            
            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content   
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
         // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null) result = convertInputStreamToString(inputStream);
            else result = "Did not work!";
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
      }
	
	public boolean isConnected(){						//Checks if connected to internet
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;    
    }
	
	public void broadcastToastIntent(){ 					//Sends intent
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
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }   
	
}
