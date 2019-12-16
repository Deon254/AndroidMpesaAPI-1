package com.ngangavictor.mpesa.api;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.ngangavictor.mpesa.api.C2BSettings.getBillRef;
import static com.ngangavictor.mpesa.api.C2BSettings.getC2bUrl;
import static com.ngangavictor.mpesa.api.C2BSettings.getCommandId;
import static com.ngangavictor.mpesa.api.C2BSettings.getMSISDN;
import static com.ngangavictor.mpesa.api.C2BSettings.getShortCode;
import static com.ngangavictor.mpesa.api.Network.sendRequest;
import static com.ngangavictor.mpesa.api.LNMPSettings.getAccount_reference;
import static com.ngangavictor.mpesa.api.LNMPSettings.getAmount;
import static com.ngangavictor.mpesa.api.LNMPSettings.getBusiness_short_code;
import static com.ngangavictor.mpesa.api.LNMPSettings.getCallback_url;
import static com.ngangavictor.mpesa.api.LNMPSettings.getDate;
import static com.ngangavictor.mpesa.api.LNMPSettings.getPassword;
import static com.ngangavictor.mpesa.api.LNMPSettings.getPhone;
import static com.ngangavictor.mpesa.api.LNMPSettings.getStk_push_url;
import static com.ngangavictor.mpesa.api.LNMPSettings.getTimeout_url;
import static com.ngangavictor.mpesa.api.LNMPSettings.getTransaction_desc;
import static com.ngangavictor.mpesa.api.LNMPSettings.getTransaction_type;

public class Mpesa {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sktPush()throws JSONException,IOException{
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("BusinessShortCode", getBusiness_short_code());
        jsonObject.put("Password", getPassword());
        jsonObject.put("Timestamp", getDate());
        jsonObject.put("TransactionType", getTransaction_type());
        jsonObject.put("Amount", getAmount());
        jsonObject.put("PhoneNumber", getPhone());
        jsonObject.put("PartyA", getPhone());
        jsonObject.put("PartyB", getBusiness_short_code());
        jsonObject.put("CallBackURL", getCallback_url());
        jsonObject.put("AccountReference", getAccount_reference());
        jsonObject.put("QueueTimeOutURL", getTimeout_url());
        jsonObject.put("TransactionDesc", getTransaction_desc());

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        return sendRequest(requestJson, getStk_push_url());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String c2bSimulation()throws JSONException,IOException{
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("ShortCode", getShortCode());
        jsonObject.put("CommandID", getCommandId());
        jsonObject.put("Amount", C2BSettings.getAmount());
        jsonObject.put("Msisdn", getMSISDN());
        jsonObject.put("BillRefNumber", getBillRef());

        jsonArray.put(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
        return sendRequest(requestJson, getC2bUrl());
    }


}