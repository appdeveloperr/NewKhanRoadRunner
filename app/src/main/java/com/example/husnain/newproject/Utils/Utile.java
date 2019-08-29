package com.example.husnain.newproject.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.husnain.newproject.Global;
import com.example.husnain.newproject.PrintPakage.PrintTicketActivity;
import com.example.husnain.newproject.SeatsInfo;
import com.example.husnain.newproject.entities.TicketingSchedule;
import com.example.husnain.newproject.entities.TicketingSeat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Utile {
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final String TAG = "Utile";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void changeImageViewBg(final Context context, final ImageView imageView, final int n) {
        if (Build.VERSION.SDK_INT < 16) {
            imageView.setBackgroundDrawable(ContextCompat.getDrawable(context, n));
            return;
        }
        imageView.setBackground(ContextCompat.getDrawable(context, n));
    }

    public static void closeKeyboard(final View view, final Context context) {
        if (view == null) {
            return;
        }
        try {
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception ex) {
        }
    }

    public static byte[] compressGZIP(final String s) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(s.length());
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(s.getBytes());
        gzipOutputStream.close();
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }

    public static int convertDpToPixel(final float n, final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final float n2 = n / (displayMetrics.densityDpi / 160.0f);
        Log.e("Utile", "metrics.densityDpi = " + displayMetrics.densityDpi);
        Log.e("Utile", "Pixels = " + n + "    DP   = " + n2);
        return (int) n2;
    }

    public static int convertPixelsToDp(final float n, final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Log.e("Utile", "metrics.densityDpi = " + displayMetrics.densityDpi);
        final float n2 = n * (displayMetrics.densityDpi / 160.0f);
        Log.e("Utile", "Pixels = " + n2 + "    DP   = " + n);
        return (int) n2;
    }

    public static Bitmap decodeBase64(final String s) {
        try {
            final byte[] decode = Base64.decode(s, 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception ex) {
            Log.e("Utile", "decodeBase64 e.getMessage() = " + ex.getMessage());
            final byte[] decode = null;
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        }
    }

    public static String decompressGZIP(final byte[] array) throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream, 32);
        final StringBuilder sb = new StringBuilder();
        final byte[] array2 = new byte[32];
        while (true) {
            final int read = gzipInputStream.read(array2);
            if (read == -1) {
                break;
            }
            sb.append(new String(array2, 0, read));
        }
        gzipInputStream.close();
        byteArrayInputStream.close();
        return sb.toString();
    }

    public static String encodeTobase64(final Bitmap bitmap, final int n) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream) byteArrayOutputStream);
        final String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        Log.e("COUNT", "COUNT = " + encodeToString.length());
        Log.e("LOOK", encodeToString);
        return encodeToString;
    }

    public static String getCountString(final int n) {
        return "(" + n + ")";
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void getDisplay(final Activity activity) {
        final Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        defaultDisplay.getName();
        final Point point = new Point();
        defaultDisplay.getSize(point);
        final int x = point.x;
        final int y = point.y;
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int heightPixels = displayMetrics.heightPixels;
        final int widthPixels = displayMetrics.widthPixels;
        final int densityDpi = displayMetrics.densityDpi;
        final float xdpi = displayMetrics.xdpi;
        final float ydpi = displayMetrics.ydpi;
    }


    private double[] getGPSLocation(final Context context) {
        final double[] array = {0.0, 0.0};
        final LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        final String bestProvider = locationManager.getBestProvider(new Criteria(), false);
        try {
            final Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
            if (lastKnownLocation != null) {
                array[0] = lastKnownLocation.getLatitude();
                array[1] = lastKnownLocation.getLongitude();
            }
            return array;
        } catch (SecurityException ex) {
            Log.e("exception: ", ex.getMessage());
            return array;
        } catch (Exception ex2) {
            Log.e("exception: ", ex2.getMessage());
            return array;
        }
    }

    public static String getLocalIpAddress() {
        try {
            final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress inetAddress = null;
            Block_4:
            {
                while (networkInterfaces.hasMoreElements()) {
                    final Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        inetAddress = inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            break Block_4;
                        }
                    }
                }
                return null;
            }
            final String formatIpAddress = Formatter.formatIpAddress(inetAddress.hashCode());
            Log.i("Utile", "***** IP=" + formatIpAddress);
            return formatIpAddress;
        } catch (SocketException ex) {
            Log.e("Utile", ex.toString());
        }
        return null;
    }

    public static String getLocalIpAddress1() {
        try {
            final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                final Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    final InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
            return null;
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static double[] getLocation(final Context context) {
        final double[] array = {0.0, 0.0};
        try {
            final LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation("gps");
            if (location == null) {
                location = locationManager.getLastKnownLocation("network");
            }
            if (location != null) {
                array[0] = location.getLatitude();
                array[1] = location.getLongitude();
            }
            return array;
        } catch (SecurityException ex) {
            Log.e("exception: ", ex.getMessage());
            return array;
        } catch (Exception ex2) {
            Log.e("exception: ", ex2.getMessage());
            return array;
        }
    }

    public static File getOutputMediaFile(final int n) {
        final File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Police Uploaded Files");
        if (!file.exists() && !file.mkdirs()) {
            Log.d("Utile", "Oops! Failed create Police Uploaded Files directory");
        } else {
            final String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            if (n == 1) {
                return new File(file.getPath() + File.separator + "IMG_" + format + ".jpg");
            }
            if (n == 2) {
                return new File(file.getPath() + File.separator + "VID_" + format + ".mp4");
            }
        }
        return null;
    }

    public static boolean isNetConnected(final Context context) {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String parseData(final String s) {
        try {
            if (s.contains(";")) {
                return s.split(";")[0].replace("-", "");
            }
            return s.substring(12, 25);
        } catch (Exception ex) {
            Log.e("Utile", " parseData error in parsing data =" + s);
            Log.e("Utile", " parseData error in parsing r =" + "");
            return "";
        }
    }

    public static void dialogEnableLocation(String title, final Context context) {
        final android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
        alertDialogBuilder.setTitle((CharSequence) title);
        alertDialogBuilder.setMessage((CharSequence) "GPS or Network location is not enabled. Do you want to go to settings menu?");
        alertDialogBuilder.setPositiveButton((CharSequence) "Settings", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                context.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        });
        alertDialogBuilder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        alertDialogBuilder.create().show();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static String getIMEI(Context context) {

        String devcieId;
        TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.

        }
        devcieId = mTelephony.getDeviceId();

        return devcieId;
    }
    

    public static Dialog showAlertDialog(String message, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public static boolean gpsturnon(Context context){
        try{
            final LocationManager manager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE );

            if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                return false;
            }
            else{
                return true;
            }
        }catch (Exception ee){
            return  false;
        }
    }

    public static void DisplayShortToast(Context context, String Message){
        Toast.makeText(context,Message,Toast.LENGTH_SHORT).show();
    }

    public static void DisplayLongToast(Context context, String Message){
        Toast.makeText(context,Message,Toast.LENGTH_LONG).show();
    }

    public static String Formatdmytoymd(String s)
    {
        try{
            final String OLD_FORMAT="dd/MM/yyyy";
            final String NEW_FORMAT="yyyy-MM-dd";
            String oldDateString = s;
            //String newDateString;
            SimpleDateFormat sdf=new SimpleDateFormat(OLD_FORMAT);
            Date d= sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            s=sdf.format(d);
        }
        catch(Exception e){
        }
        return s;
    }

    public static String Fromatmdytoymd(String s)
    {
        try{
            final String OLD_FORMAT="MM/dd/yyyy";
            final String NEW_FORMAT="yyyy/MM/dd";
            String oldDateString = s;
            //String newDateString;
            SimpleDateFormat sdf=new SimpleDateFormat(OLD_FORMAT);
            Date d= sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            s=sdf.format(d);
        }
        catch(Exception e){
        }
        return s;
    }

    public static JSONObject GenerateSeatsInfoJSONObject(SeatsInfo seatsInfo){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.OPERATOR_ID,
                    Global.User_ID);
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.BORDING_TERMINAL_ID,
                    30151 );
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.MASK_DATE,
                    Global.VoucherDate);
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.MASK_ROUTE,
                    Global.VoucherServiceTypeId );
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.MASK_TERMINAL_ID,
                    30151);
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.MASK_DEP_TIME,
                    Global.VoucherTime);
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.PASSENGER_CNIC,
                    "00000-0000000-0");
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.PASSENGER_NAME,
                    "OnTheWay");
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.CONTACT_NO,
                    "0333-7777777");
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.INVOICE_ID,
                    0);
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.SEAT_ID,
                    seatsInfo.getSeat_id());
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.SEAT_NO,
                    seatsInfo.getSeat_No());
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.GENDER,
                    seatsInfo.getGender());
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.SOURCE_ID,
                    PrintTicketActivity.Source.getCity_ID());
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.DESTINATION_ID,
                    PrintTicketActivity.Destination.getCity_ID());
            jsonObject.put(Constants.UPDATE_SEATS_INFO_COL.PASSENGER_FARE,
                    seatsInfo.getFare());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject GenerateTicketScheduleJSONObject(TicketingSchedule ticketingSchedule){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.TICKETING_SCHEDULE_ID,
                    ticketingSchedule.getTicketing_Schedule_ID());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.SR_NO,
                    ticketingSchedule.getSr_No());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.TS_DATE,
                    ticketingSchedule.getTS_Date());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.SCHEDULE_ID,
                    ticketingSchedule.getSchedule_ID());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VOUCHER_NO,
                    ticketingSchedule.getVoucher_No());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VOUCHER_STATUS,
                    ticketingSchedule.getVoucher_Status());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VOUCHER_OPENED_BY,
                    ticketingSchedule.getVoucher_Opened_By());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VOUCHER_CLOSED_BY,
                    ticketingSchedule.getVoucher_Closed_By());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VOUCHER_CLOSED_DATE,
                    ticketingSchedule.getVoucher_Closed_Date());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.DEPARTURE_TIME,
                    ticketingSchedule.getDeparture_Time());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VEHICLE_ID,
                    ticketingSchedule.getVehicle_ID());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.DRIVER_NAME,
                    ticketingSchedule.getDriver_Name());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.HOSTESS_NAME,
                    ticketingSchedule.getHostess_Name());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.USER_ID,
                    ticketingSchedule.getUser_Id());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.ACCESS_DATETIME,
                    ticketingSchedule.getAccess_DateTime());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.ACCESS_SYS_NAME,
                    ticketingSchedule.getAccess_Sys_Name());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.ACCESS_TERMINAL_ID,
                    ticketingSchedule.getAccess_Terminal_Id());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.ACTUAL_DEPARTURE_TIME,
                    ticketingSchedule.getActual_Departure_Time());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.GUARD,
                    ticketingSchedule.getGuard());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.IS_CLOSED,
                    ticketingSchedule.getIs_Closed());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.BOOK_ID,
                    ticketingSchedule.getBook_Id());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.IS_PULLED,
                    ticketingSchedule.getIs_Pulled());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.SERVICETYPE_ID,
                    ticketingSchedule.getServiceType_Id());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject GenerateTicketSeatJSONObject(TicketingSeat ticketingSeat){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.TICKETING_SEAT_COL.TICKETING_SEAT_ID,
                    ticketingSeat.getTicketing_Seat_ID());
            jsonObject.put(Constants.TICKETING_SEAT_COL.TICKETING_SCHEDULE_ID,
                    ticketingSeat.getTicketing_Schedule_ID());
            jsonObject.put(Constants.TICKETING_SEAT_COL.SEAT_NO,
                    ticketingSeat.getSeat_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.STATUS,
                    ticketingSeat.getStatus());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ISSUE_DATE,
                    ticketingSeat.getIssue_Date());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ISSUE_TERMINAL,
                    ticketingSeat.getIssue_Terminal());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ISSUED_BY,
                    ticketingSeat.getIssued_By());
            jsonObject.put(Constants.TICKETING_SEAT_COL.SOURCE_ID,
                    ticketingSeat.getSource_ID());
            jsonObject.put(Constants.TICKETING_SEAT_COL.DESTINATION_ID,
                    ticketingSeat.getDestination_ID());
            jsonObject.put(Constants.TICKETING_SEAT_COL.PASSENGER_NAME,
                    ticketingSeat.getPassenger_Name());
            jsonObject.put(Constants.TICKETING_SEAT_COL.CONTACT_NO,
                    ticketingSeat.getContact_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.FARE,
                    ticketingSeat.getFare());
            jsonObject.put(Constants.TICKETING_SEAT_COL.DISCOUNT,
                    ticketingSeat.getDiscount());
            jsonObject.put(Constants.TICKETING_SEAT_COL.TICKET_SR_NO,
                    ticketingSeat.getTicket_Sr_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.IS_BOOKEDSOLD,
                    ticketingSeat.getIs_bookedSold());
            jsonObject.put(Constants.TICKETING_SEAT_COL.BOOKED_USER,
                    ticketingSeat.getBooked_User());
            jsonObject.put(Constants.TICKETING_SEAT_COL.IS_SMS_SENT,
                    ticketingSeat.getIs_SMS_Sent());
            jsonObject.put(Constants.TICKETING_SEAT_COL.CNIC,
                    ticketingSeat.getCNIC());
            jsonObject.put(Constants.TICKETING_SEAT_COL.GENDER,
                    ticketingSeat.getGender());
            jsonObject.put(Constants.TICKETING_SEAT_COL.IS_TRANSIT,
                    ticketingSeat.getIs_Transit());
            jsonObject.put(Constants.TICKETING_SEAT_COL.SHIFT_USER_ID,
                    ticketingSeat.getShift_User_Id());
            jsonObject.put(Constants.TICKETING_SEAT_COL.PNR_NO,
                    ticketingSeat.getPNR_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.TELENOR,
                    ticketingSeat.getTelenor());
            jsonObject.put(Constants.TICKETING_SEAT_COL.PAYMENTDATE,
                    ticketingSeat.getPaymentDate());
            jsonObject.put(Constants.TICKETING_SEAT_COL.TELENOR,
                    ticketingSeat.getTelenor());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ISMISSED,
                    ticketingSeat.getIsMissed());
            jsonObject.put(Constants.TICKETING_SEAT_COL.CHANGETICKET_NO,
                    ticketingSeat.getChangeTicket_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.COLLECTIONPOINT,
                    ticketingSeat.getCollectionPoint());
            jsonObject.put(Constants.TICKETING_SEAT_COL.CHANGETYPE,
                    ticketingSeat.getChangeType());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ROUTE_SR_NO,
                    ticketingSeat.getRoute_Sr_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.OPERATOR_ID,
                    ticketingSeat.getOperator_Id());
            jsonObject.put(Constants.TICKETING_SEAT_COL.CUSTOMER_ID,
                    ticketingSeat.getCustomer_Id());
            jsonObject.put(Constants.TICKETING_SEAT_COL.POINTS,
                    ticketingSeat.getPoints());
            jsonObject.put(Constants.TICKETING_SEAT_COL.IS_ONLINEPRINTED,
                    ticketingSeat.getIs_OnlinePrinted());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ONLINEPRINTER_USERID,
                    ticketingSeat.getOnlinePrinter_UserId());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ONLINEPRINT_TERMINAL_ID,
                    ticketingSeat.getOnlinePrint_Terminal_Id());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ONLINEPRINT_DATE,
                    ticketingSeat.getOnlinePrint_Date());
            jsonObject.put(Constants.TICKETING_SEAT_COL.INVOICE_ID,
                    ticketingSeat.getInvoice_Id());
            jsonObject.put(Constants.TICKETING_SEAT_COL.IS_ONLINE,
                    ticketingSeat.getIs_Online());
            jsonObject.put(Constants.TICKETING_SEAT_COL.LATITUDE,
                    ticketingSeat.getLatitude());
            jsonObject.put(Constants.TICKETING_SEAT_COL.LONGITUDE,
                    ticketingSeat.getLongitude());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
