package com.example.checkzip;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed = "";
    String zipurl="";
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            URL url = new URL(zipurl);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray jsonArray = new JSONArray(data);
            for(int i =0; i<jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                singleParsed = "Zipcode: "+jsonObject.get("AreaCode")+"\n"+
                        "County: "+jsonObject.get("County")+"\n"+
                        "Latitude:"+jsonObject.get("Latitude")+"\n"+
                        "Longitude:"+jsonObject.get("Longitude")+"\n"+
                        "Population Urban Area:"+jsonObject.get("PopulationUrbanArea")+"\n"+
                        "SquareMiles:"+jsonObject.get("SquareMiles")+"\n"+
                        "State:"+jsonObject.get("State")+"\n";
                dataParsed = dataParsed+ singleParsed+"\n";

            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        zipcheck.age.setText(this.data);
    }
    public void setZipcode(String zipcode){
        zipurl = "https://api.datafinder.com/v2/qdf.php?service=zip5&k2=1fmnw5ifn7c0nrxdl3ua28nz&service=zip5&output=json&d_zip="+zipcode;
    }
}
