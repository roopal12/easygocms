package com.example.easygocms;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.easygocms.datarecordactivities.BookingCallActivity;
import com.example.easygocms.datarecordactivities.CallReminderActivity;
import com.example.easygocms.datarecordactivities.LostCustomerActivity;
import com.example.easygocms.datarecordactivities.PSFActivity;
import com.example.easygocms.datarecordactivities.SalesEnquiryActivity;
import com.example.easygocms.datarecordactivities.ServiecBookingActivity;
import com.example.easygocms.datarecordactivities.ServiecReminderActivity;
import com.example.easygocms.dataringingcall.RCLostCustActivity;
import com.example.easygocms.dataringingcall.RCPspActivity;
import com.example.easygocms.dataringingcall.RCSalesEnqActivity;
import com.example.easygocms.dataringingcall.RCServicereminderActivity;

public class OutgoingCallBroadcastReceiver extends BroadcastReceiver {
    SharedPreferences sharedPreferenStatus;

    @SuppressLint({"UnsafeProtectedBroadcastReceiver", "ApplySharedPref"})
    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPreferenStatus = context.getSharedPreferences("StatusDetails", Context.MODE_PRIVATE);
        String restoredStatus = sharedPreferenStatus.getString("Status", "");
       switch (restoredStatus)
       {
           case "1":
               Intent i1 = new Intent();
               i1.setClass(context, ServiecReminderActivity.class);
               i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i1);
               break;
           case "2":
               Intent i2 = new Intent();
               i2.setClass(context, LostCustomerActivity.class);
               i2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i2);
               break;
           case "3":
               Intent i3 = new Intent();
               i3.setClass(context, SalesEnquiryActivity.class);
               i3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i3);
               break;
           case "4":
               Intent i4 = new Intent();
               i4.setClass(context, PSFActivity.class);
               i4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i4);
               break;
           case "5":
               Intent i5 = new Intent();
               i5.setClass(context, CallReminderActivity.class);
               i5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i5);
               break;
               case "6":
               Intent i6 = new Intent();
               i6.setClass(context, BookingCallActivity.class);
               i6.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i6);
               break;
           case "7":
               Intent i7 = new Intent();
               i7.setClass(context, ServiecBookingActivity.class);
               i7.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i7);
               break;
           case "8":
               Intent i8 = new Intent();
               i8.setClass(context, RCServicereminderActivity.class);
               i8.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i8);
               break;
           case "9":
               Intent i9 = new Intent();
               i9.setClass(context, RCPspActivity.class);
               i9.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i9);
               break;
           case "10":
               Intent i10 = new Intent();
               i10.setClass(context, RCLostCustActivity.class);
               i10.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i10);
               break;
           case "11":
               Intent i11 = new Intent();
               i11.setClass(context, RCSalesEnqActivity.class);
               i11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i11);
               break;
           case "12":
               Intent i12 = new Intent();
               i12.setClass(context, NotifSerRemindActivity.class);
               i12.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i12);
               break;
           case "13":
               Intent i13 = new Intent();
               i13.setClass(context, NotifPSFActivity.class);
               i13.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i13);
               break;
           case "14":
               Intent i14 = new Intent();
               i14.setClass(context, NotifLostCustomerActivity.class);
               i14.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i14);
               break;
           case "15":
               Intent i15 = new Intent();
               i15.setClass(context, NotifSalesEnqActivity.class);
               i15.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i15);
               break;

           case "404":
               Intent ihelp = new Intent();
               ihelp.setClass(context, HelpScreenActivity.class);
               ihelp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(ihelp);
               break;

           default:
               break;
       }


    }
}
