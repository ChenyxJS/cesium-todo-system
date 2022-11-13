package com.chenyx.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

/**
 * @author CBeann
 * @create 2020-04-13 1:31
 */
public class IPUtils {



    public static String getInterIP1() throws Exception {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static String getInterIP2() throws SocketException {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        Enumeration<NetworkInterface> netInterfaces;
        netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

    /**
     * 获得外网ip
     * @return
     */
    public static String getOutIPV4() {
        String ip = "";
//        String chinaz = "http://ip.chinaz.com";
//
//        StringBuilder inputLine = new StringBuilder();
//        String read = "";
//        URL url = null;
//        HttpURLConnection urlConnection = null;
//        BufferedReader in = null;
//        try {
//            url = new URL(chinaz);
//            urlConnection = (HttpURLConnection) url.openConnection();
//            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
//            while ((read = in.readLine()) != null) {
//                inputLine.append(read + "\r\n");
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
//        Matcher m = p.matcher(inputLine.toString());
//        if (m.find()) {
//            String ipstr = m.group(1);
//            ip = ipstr;
//        }
        return ip;
    }


    public static String[] getIp(){
        String[] obj = new String[3];
        StringBuffer strForeignIP = new StringBuffer("");
        StringBuffer strLocation = new StringBuffer("");
        StringBuffer strOperatorMessageation = new StringBuffer("");
        String ipAddress = "";
        String ipLocation = "";
        String ipSystem = "";
        StringBuffer strUrl =new StringBuffer("http://www.cz88.net/ip/viewip778.aspx");
        try{
            URL url = new URL(strUrl.toString());
            URLConnection context = url.openConnection();
            InputStream in = context.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "gb2312"));//防止读出来的是乱码

            String s = "";
            StringBuffer sb = new StringBuffer("");
            while ((s = br.readLine()) != null) {
                sb.append(s + "\r\n");
            }
            br.close();
            String webContent = sb.toString();
            if (null!=webContent && webContent.trim().length()>0){
                String flagofForeignIPString = "IPMessage";
                int startIP = webContent.indexOf(flagofForeignIPString) + flagofForeignIPString.length() + 2;
                int endIP = webContent.indexOf("</span>", startIP);
                strForeignIP.delete(0, webContent.length());
                strForeignIP.append(webContent.substring(startIP, endIP));

                String flagofLocationString = "AddrMessage";
                int startLoc = webContent.indexOf(flagofLocationString)+ flagofLocationString.length() + 2;
                int endLoc = webContent.indexOf("</span>", startLoc);
                strLocation.delete(0, webContent.length());
                strLocation.append(webContent.substring(startLoc, endLoc));

                String flagoOperatorMessage = "OperatorMessage";
                int startOpera = webContent.indexOf(flagoOperatorMessage)+ flagoOperatorMessage.length() + 2;
                int endOpera = webContent.indexOf("</span>", startOpera);
                strOperatorMessageation.delete(0, webContent.length());
                strOperatorMessageation.append(webContent.substring(startOpera, endOpera));
            }
        }catch(Exception e){
            System.out.println("IpUtil:get ip is failed:"+e.getMessage());
            e.printStackTrace();
        }
        if(strForeignIP.toString().trim().length()<0){
            ipAddress = "未知";
        }else{
            ipAddress = strForeignIP.toString();
        }

        if(strForeignIP.toString().trim().length()<0){
            ipLocation = "未知";
        }else{
            ipLocation = strLocation.toString();
        }

        if(strForeignIP.toString().trim().length()<0){
            ipSystem = "未知";
        }else{
            ipSystem = strOperatorMessageation.toString();
        }
        obj[0]=ipAddress;
        obj[1]=ipLocation;
        obj[2]=ipSystem;
        return obj;
    }
}
