
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
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;


public class HttpPostSender extends BroadcastReceiver{

	private String sampleMsg = "Sample Message One";
	private String sampleAcctId = "Sample AcctId One";
	private String sampleExtVar = "Sample ExtraVar One";
	private Pushablemsg pmsg;
	private Context toastmakersContext;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//if(isConnected()){ // Check connection and output it to the console
			Log.w("1 ","You are connected -InstActiv");
		//}
		//else Log.w("1 ", "You are NOT connected -InstActiv");
		toastmakersContext = context;
		//new HttpAsyncTask().execute("http://hmkcode.appspot.com/jsonservlet");
		// Extract data included in the Intent
		//CharSequence intentData = intent.getCharSequenceExtra("message");	
	}
/*	
	
	
	
	
	
	
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            pmsg = new Pushablemsg();
            pmsg.setMessage(sampleMsg);
            pmsg.setAcctId(sampleAcctId);
            pmsg.setExtraVar(sampleExtVar);

            return POST(urls[0],pmsg);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Log.w("2","data sent! Hoorah");
       }
    }
	
	
	
	public static String POST(String url, Pushablemsg pushable){//Changed 2nd arg from Person person
        InputStream inputStream = null;
        String result = "";
        try {
        	HttpClient httpclient = new DefaultHttpClient(); 	// 1. create HttpClient
            HttpPost httpPost = new HttpPost(url);  			// 2. make POST request to the given URL
            String json = "";
            JSONObject jsonObject = new JSONObject(); 			// 3. build jsonObject
            jsonObject.accumulate("message", pushable.getMessage());
            jsonObject.accumulate("acctId", pushable.getAcctId());
            jsonObject.accumulate("extra", pushable.getExtraVar());            
            json = jsonObject.toString();						// 4. convert JSONObject to JSON to String
            // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(pushable); 
            StringEntity se = new StringEntity(json);			// 5. set json to StringEntity
            httpPost.setEntity(se);								// 6. set httpPost Entity
            httpPost.setHeader("Accept", "application/json");	// 7. Set some headers to inform server about the type of the content   
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpclient.execute(httpPost); // 8. Execute POST request to the given URL
            inputStream = httpResponse.getEntity().getContent();// 9. receive response as inputStream
            if(inputStream != null) result = convertInputStreamToString(inputStream); // 10. convert inputstream to string
            else result = "Did not work!";
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        // 11. return result
        return result;
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
	
	public boolean isConnected(){						//Checks if connected to internet
        ConnectivityManager connMgr = (ConnectivityManager) toastmakersContext.getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;    
    }
	
	
*/	
}