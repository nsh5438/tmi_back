package kr.hs.dgsw.backend;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;

@RestController
@CrossOrigin(origins = "*")
public class Test {

    @GetMapping("/selectdata")
    public JSONArray selectData(){

        JSONArray object_list = new JSONArray();
        for (int i = 0 ; i < 4 ; i++ ){
            JSONArray object = (JSONArray) getData(String.valueOf(i+1));
            for (Object data : object) {
                JSONObject objdata = (JSONObject) data;
                object_list.add(objdata);
            }
        }

        return object_list;

    }

    public Object getData(String page){
        String header = "KakaoAK cffaac58188e67d58b65cef63d5c7066";
        String result= "";
        try{
            URL obj = new URL("https://dapi.kakao.com/v2/local/search/keyword.json?query=%EB%96%A1%EB%B3%B6%EC%9D%B4&page=" + page);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            con.setRequestProperty("charset","UTF-8");

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine + "\n");
            }
            br.close();
            result =  response.toString();

            JSONParser parser = new JSONParser();
            Object object = parser.parse(result);
            JSONObject jsonObject = (JSONObject)object;
            JSONArray list = (JSONArray) jsonObject.get("documents");

            return list;

        }catch (Exception e){
            e.printStackTrace();
        }


        return null;

    }
}

