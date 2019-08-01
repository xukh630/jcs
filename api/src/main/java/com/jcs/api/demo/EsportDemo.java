package com.jcs.api.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Locale;

/**
 * @auther xukh
 * @date 2019/8/1 14:09
 */
public class EsportDemo {

    private static final String accessKey = "JibasdasdQW3t4ancdsaEh6upUlIcboZ8Euq";    // 【替换成自己的access key】
    private static final String secretKey = "cbfWCLgzzxcxaEmSqvckFoO676UHDse";    // 【替换成自己的secret key】
    private static final String baseUrl = "esportsapi.feijing88.com";  // 【替换成请求对应数据的api接口地址】
    private static String path = " /data-service/dota/league/list?offset=0&limit=10";                              // api path 例如：/data-service/lol/raw/hero
    private static String apiTime = System.currentTimeMillis()+"";                           // api 请求时间戳

    public static void main(String[] args) {
        // 初始化请求接口参数
        apiTime = getNowTime();
        String url = baseUrl + path;

        // 调用电竞数据 API 接口获取格式为 JSON 字符串
        String charset = "UTF-8";
        String jsonResult = get(url, charset);
        // 打印请求结果
        System.out.println(jsonResult);
    }

    /**
     * @param url:请求接口
     * @param charset:字符编码
     * @return 返回json字符串
     */
    public static String get(String url, String charset) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

        try {
            URL newUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)newUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(30000);
            connection.setConnectTimeout(30000);
            connection.setRequestProperty("User-agent", userAgent);
            // 设置请求头
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestProperty("Accept-ApiAccess", accessKey);
            connection.setRequestProperty("Accept-ClientTime", apiTime);
            connection.setRequestProperty("Accept-ApiSign", getSign());

            System.out.println(getSign());

            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, charset));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
                reader.close();
                result = sbf.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取当前系统的时间戳
     */
    private static String getNowTime() {
        return System.currentTimeMillis()+ "";
    }

    /**
     * 获取Api 加密签名 算法： md5(secretKey|nowTime()|path)
     *
     * @return Api Sign
     */
    private static String getSign() {
        // 拼接请求加密签名
        StringBuffer tempStr = new StringBuffer("");
        tempStr.append(secretKey);
        tempStr.append("|");
        tempStr.append(apiTime);
        tempStr.append("|");
        tempStr.append(path);
        return encryption(tempStr.toString());
    }

    /**
     * md5加密，可以替换成你自己的
     * @param plainText 明文
     * @return 32位密文（大写）
     */
    private static String encryption(String plainText) {
        String resultMd5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            resultMd5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return resultMd5.toUpperCase(Locale.ENGLISH);
    }

}
