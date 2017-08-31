package com.example.bandofbrotherszts.androidphoneinfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

/**
 * Created by ${zhaojz} on 2017/8/31.
 */
//权限
//<uses-permission android:name="android.permission.READ_PHONE_STATE"/>

/**  throw new UnsupportedOperationException("you can't instantiate me...");
 *   可以代替return null  来提示你自己
 */
public class AndroidDeviceInfo {
    public static String androidID = null;//androidID
    public static String mSerial = null;//SIM卡的序列号
    public static String deviceID = null;//设备ID
    public static String androidUuidID = null;//Uuid加密的android设备唯一标识
    public static String phoneProducer = null;//手机厂商
    public static String IMEI = null;//手机IM
    public static String phoneModel = null;//手机型号
    public static String systemVersion = null;//手机系统版本号
    public static String SDKVersion = null;//SDK版本
    public static String versionName = null;//软件版本

    public static Context context;

    /**
     *   本工具类初始化
     * @param context
     */
    public static void  init(Context context){
        AndroidDeviceInfo.context= context;
    }
    /**
     * 未加密
     * @return 设备ID
     */
    public static String getAndroidID(){
        androidID = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        return androidID;
    }
    @SuppressLint("ByteOrderMark")
    public static String getAndroidID2(){
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        deviceID = "" + tm.getDeviceId();
        mSerial = "" + tm.getSimSerialNumber();
//        getSimOperatorName() ﻿Returns the Service Provider Name (SPN).
//                               获取服务提供商名字，比如电信，联通，移动
//        String imsi = telManager.getSubscriberId();
//        if(imsi!=null){ if(imsi.startsWith(“46000″) || imsi.startsWith(“46002″))
//        {因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，
//           134/159号段使用了此编号 //中国移动
//        }else if(imsi.startsWith(“46001″)){
//         中国联通
//        }else if(imsi.startsWith(“46003″)){
//        中国电信
//        } }
//        getCellLocation（） 返回的单元格位置的装置 ACCESS_COARSE_LOCATION或ACCESS_FINE_LOCATION
//        getLine1Number（） 返回设备的电话号码（MSISDN号码） READ_PHONE_STATE
//        getNetworkOperatorName（） 返回注册的网络运营商的名字
//        getNetworkOperator（） 返回的MCC +跨国公司的注册网络运营商
//        getNetworkCountryIso（） 返回注册的网络运营商的国家代码
//        getSimCountryIso（） 返回SIM卡运营商的国家代码 READ_PHONE_STATE
//        getSimOperator（） 返回SIM卡运营商的单个核细胞数+冶 READ_PHONE_STATE
//        getSimOperatorName（） 返回SIM卡运营商的名字 READ_PHONE_STATE
//        getSimSerialNumber（） 返回SIM卡的序列号 READ_PHONE_STATE
//        getNetworkType（） 返回网络设备可用的类型。
        return deviceID ;
    }

    /**
     * UUID 加密过后
     * @return  android唯一标识
     */
    public static String getUUIDAndroidID(String androidID,String deviceID,String tmSerial){
        UUID deviceUuid = new UUID(androidID.hashCode(), ((long)deviceID.hashCode() << 32) | tmSerial.hashCode());
        androidUuidID = deviceUuid.toString();
        return androidUuidID;
    }

    /**
     * 手机厂商
     * @return
     */
    public static String getPhoneProducer() {
        phoneProducer = android.os.Build.BRAND;
        return phoneProducer;
    }
    /**
     * 手机IM
     * @return
     */
    public static String getIMEI(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            IMEI = tm.getDeviceId();
            return IMEI;
        }

        return null;
    }

    /**
     *   获取手机型号
     * @return
     */
    public  static  String  getPhoneModel()  {
        phoneModel = android.os.Build.MODEL;
        return  phoneModel;
    }

    /**
     * 获取系统版本号
     * @return
     */
    public static String getSystemVersion() {
        systemVersion = android.os.Build.VERSION.RELEASE;
        return  systemVersion;
    }
    /**
     * SDK 版本
     * @return
     */
    public static String getSDKVersion() {
        SDKVersion = android.os.Build.VERSION.SDK ;
        return  SDKVersion;
    }

    /**
     * 当前软件版本
     * @return
     */
    private  String getAppVersionName() {
        try {
            PackageManager packageManager = context.getPackageManager();
//            context.getPackageName() 当前软件包名
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
//            packageInfo.versionCode
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }


}
