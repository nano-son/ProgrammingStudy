package manager;

import model.ReserveForm;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReserveManager {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36";
    private static final String URL = "http://sports.knu.ac.kr/pages/register/facility_reserve.php";
    private static final String REFERER = "https://sports.knu.ac.kr/doc/class_info6_reserve.php";

    public boolean reserve(final HttpEntity httpEntity) throws IOException {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = makeRequest();
            httpPost.setEntity(httpEntity);

            //요청 날림
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if (!checkReserveSuccess(response))
                return false;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
        System.out.println("[reserve success]");
        return true;
    }

    public boolean checkReserveSuccess(CloseableHttpResponse response) throws IOException{
        int responseCode = response.getStatusLine().getStatusCode();
        if(responseCode != 200)
            return false;

        String line=null;
        String result_alert = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        while((line = br.readLine()) != null) {
            if(line.contains("선택된 시설이 없습니다")) {
                System.err.println("선택된 시설이 없습니다");
                return false;
            }
            else if (line.contains("해당시간에 예약이 불가합니다")) {
                System.err.println("해당시간에 예약이 불가합니다");
                return false;
            }
            else if(line.contains("alert("))
                result_alert = line.split("\"")[1];
        }
        System.out.println(result_alert);
        return true;
    }


    public HttpPost makeRequest() {
        HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader("Host","sports.knu.ac.kr");
        httpPost.addHeader("Connection","keep-alive");
        httpPost.addHeader("Cache-Control","max-age=0");
        httpPost.addHeader("Origin","https://sports.knu.ac.kr");
        httpPost.addHeader("Upgrade-Insecure-Requests","1");
        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
        httpPost.addHeader("User-Agent",USER_AGENT);
        httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpPost.addHeader("Referer",REFERER);
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Accept-Language","ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        httpPost.addHeader("Cookie",CookieManager.getCookie("PHPSESSID"));

        return httpPost;
    }
}

