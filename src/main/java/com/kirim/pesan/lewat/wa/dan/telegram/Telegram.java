/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kirim.pesan.lewat.wa.dan.telegram;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

/**
 *
 * @author Hanif
 */
public class Telegram implements Serializable{
    private String token;
    private HashMap<String, Integer> daftar = new HashMap<>();
    
    public Telegram() {
    }
    
    public Telegram(String token,  HashMap<String, Integer> daftar) {
        this.token = token;
        this.daftar = daftar;
    }

    public String getToken() {
        return token;
    }

    public HashMap<String, Integer> getDaftar() {
        return daftar;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public void KirimPesan(String username, String pesan) throws Exception {
        String pesanterkode = URLEncoder.encode(pesan, "UTF-8");
        
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = "https://api.telegram.org/bot" + token + "/sendMessage?chat_id=" + daftar.get(username) + "&text=" + pesanterkode;
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
    
    public static void UpdateChatID(Telegram tele) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = "https://api.telegram.org/bot" + tele.getToken() + "/getUpdates";
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(httpResponse.body());

        JsonNode resultNode = jsonNode.get("result");
        if (resultNode.isArray()) {
            for (JsonNode messageNode : resultNode) {
                JsonNode fromNode = messageNode.get("message").get("from");
                Integer id = fromNode.get("id").asInt();
                String username = fromNode.get("username").asText();
                tele.daftar.put(username, id);
            }
        }
    }
    
    public static Telegram MuatPengaturan() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("chat-id.apk");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Telegram tele = (Telegram) objectInputStream.readObject();
        
        UpdateChatID(tele);
        
        return tele;
    }
    
    public static void SimpanPengaturan(Telegram tele) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("chat-id.apk");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(tele);

        objectOutputStream.close();
        fileOutputStream.close();
    }
}