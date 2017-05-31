package api;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * A utility class to handle rpc related parsing logics. 
 */
public class RpcParser {
    static final String ACCESS_TOKEN ="PkYCL9MhHoXvUoR4nNWs-GNc9iseD9Ev_3EqILCasLtI9b8RwOlwPR8zeVjbVVShPIewJfQ-QZuGVCHR6ytYF_04M6xNTExM2UyNcyKyWXR8Tb7tvG4ay__uwjofWXYx";
    public static JSONObject parseInput(HttpServletRequest request) {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            reader.close();
            System.out.println(jb.toString());
            return new JSONObject(jb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeOutput(HttpServletResponse response, JSONObject obj) {
        try {           
            response.setContentType("application/json");
            response.addHeader("Access-Control-Allow-Origin", "*");
            PrintWriter out = response.getWriter();
            out.print(obj);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    public static void writeOutput(HttpServletResponse response, JSONArray array) {
        try {           
            response.setContentType("application/json");
            response.addHeader("Access-Control-Allow-Origin", "*");
            PrintWriter out = response.getWriter();
            out.print(array);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}