package com.just.han.activity;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.just.han.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SmsReadActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_read);
        textView = (TextView) findViewById(R.id.textView2);
        textView.setText(getSmsInPhone());
    }

    private String getSmsInPhone() {
        final String SMS_URI_ALL = "content://sms/";            // 所有短信
        final String SMS_URI_INBOX = "content://sms/inbox";     //收件箱
        final String SMS_URI_SEND = "content://sms/sent";       // 已发送
        final String SMS_URI_DRAFT = "content://sms/draft";     // 草稿
        final String SMS_URI_OUTBOX = "content://sms/outbox";   // 发件箱
        final String SMS_URI_FAILED = "content://sms/failed";   // 发送失败
        final String SMS_URI_QUEUED = "content://sms/queued";   //待发送短信

        StringBuilder smsBuilder = new StringBuilder();
        Uri uri = Uri.parse(SMS_URI_ALL);
        String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, "date desc");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int index_Address = cursor.getColumnIndex("address");
                int index_Person = cursor.getColumnIndex("person");
                int index_Body = cursor.getColumnIndex("body");
                int index_Date = cursor.getColumnIndex("date");
                int index_Type = cursor.getColumnIndex("type");
                do {
                    String strAddress = cursor.getString(index_Address);
                    int intPerson = cursor.getInt(index_Person);
                    String strbody = cursor.getString(index_Body);
                    long longDate = cursor.getLong(index_Date);
                    int intType = cursor.getInt(index_Type);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
                    Date d = new Date(longDate);
                    String strDate = dateFormat.format(d);

                    String strType = "";
                    if (intType == 1) {
                        strType = "接收";
                    } else if (intType == 2) {
                        strType = "发送";
                    } else {
                        strType = "null";
                    }

                    smsBuilder.append("[ ");
                    smsBuilder.append(strAddress + ", ");
                    smsBuilder.append(intPerson + ", ");
                    smsBuilder.append(strbody + ", ");
                    smsBuilder.append(strDate + ", ");
                    smsBuilder.append(strType);
                    smsBuilder.append(" ]\n\n");
                } while (cursor.moveToNext());

                if (!cursor.isClosed()) {
                    cursor.close();
                }
            } else {
                smsBuilder.append("no result!");
            }
        }
        return smsBuilder.toString();
    }
}
