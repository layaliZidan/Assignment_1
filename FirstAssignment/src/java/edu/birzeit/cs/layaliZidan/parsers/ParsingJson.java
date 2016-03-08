/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.birzeit.cs.layaliZidan.parsers;

/**
 *
 * @author Layali Zidan
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
//import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ParsingJson {

    private static JSONTokener jsonOut;

    public static void main(String myHelpers[]) throws JSONException, IOException {
                       
                File f = new File("D:\\study\\study\\web services\\FirstAssignment\\src\\java\\edu\\birzeit\\cs\\layaliZidan\\parsers\\friendsJSON.json");

                    String json = readFile(f.getPath());
                    jsonOut = new JSONTokener(json);
                    
         ArrayList <User> users = new <User> ArrayList();
         
         
         try {
        JSONObject output = new JSONObject(jsonOut);
       // JSONObject jsonRoot = new JSONObject(json);
        JSONArray jsonArray = output.getJSONArray("users");
                
        for(int i=0; i < jsonArray.length(); i++){
            JSONObject userObject = jsonArray.getJSONObject(i);
            User user = new User();
            user.setId(Integer.parseInt(userObject.getString("_ID")));
            user.setAddress(userObject.getString("address"));
            user.setDateOfBirth(userObject.getString("dateOfBirth"));
            user.setEmail(userObject.getString("email"));
            user.setFirstName(userObject.getString("firstName"));
            user.setGender(userObject.getString("gender"));
            user.setJobTitle(userObject.getString("jobtitle"));
            user.setJoinYear(Integer.parseInt(userObject.getString("joinYear")));
            user.setLastName(userObject.getString("lastName"));
            user.setLocation(userObject.getString("location"));
            user.setNationality(userObject.getString("nationality"));
            user.setPhone(userObject.getString("phone"));
            users.add(user);
        }

       } catch (JSONException e) {
          e.printStackTrace();
       }
                         
            
           File fr = new File("D:\\study\\study\\web services\\FirstAssignment\\src\\java\\edu\\birzeit\\cs\\layaliZidan\\parsers\\relations.json");
            String jsonRelations = readFile(fr.getPath());
                    jsonOut = new JSONTokener(jsonRelations);
                    
               try {
               JSONObject output = new JSONObject(jsonOut);
           // JSONObject jsonRoot = new JSONObject(json);
               JSONArray jsonArray = output.getJSONArray("relations");
                
                 for(int i=0; i < jsonArray.length(); i++){
                  JSONObject userObject = jsonArray.getJSONObject(i);
                  int first_id, second_id;
                  first_id= Integer.parseInt(userObject.getString("first_id"));
                  second_id= Integer.parseInt(userObject.getString("second_id"));
                  
                  users.get(second_id-1).friends.add(users.get(first_id-1));
                  users.get(first_id-1).friends.add(users.get(second_id-1));
                 }
                     } catch (JSONException e) {
              e.printStackTrace();
              }
               
               for(int i =0; i <users.size(); i++){
            User u = (User) users.get(i);
           System.out.println(" \nUser : "+ u.getId());
           System.out.println(" \t Name : "+ u.getFirstName() +" "+u.getLastName());
           System.out.println(" \t Date of Birth : "+ u.getDateOfBirth());
           System.out.println(" \t Location : "+u.getLocation());
           System.out.println(" \t Address :"+ u.getAddress());
              System.out.println(" \t Join year : " + u.getJoinYear());
           System.out.println(" \t Job title : " +u.getJobTitle());
           System.out.println(" \t E-mail : "+u.getEmail());
           System.out.println(" \t Phone : "+u.getPhone());
           System.out.println(" \t Gender : "+u.getGender());
           System.out.println(" \t Nationality : "+u.getNationality());
           System.out.println("  \tFriend List :");
           for(int m =0 ; m<u.friends.size() ; m++){
               System.out.println(" \t\tFriend "+ (m+1) +": "+u.friends.get(m).getFirstName()+" "+
                                                             u.friends.get(m).getLastName());
           }
           
           // System.out.println("\n\n");
        }
            }
    

    private static String readFile(String file) throws IOException {
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        return stringBuilder.toString();
    }

}
