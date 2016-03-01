
package edu.birzeit.cs.layaliZidan.parsers;

import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Layali Zidan
 */
public class ParsingXmlWithDom {
    
    public static void main(String[] args) throws Exception {
    
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        
        InputStream xmlStream = ParsingXmlWithDom.class.getResourceAsStream("users.xml");
        
        Document doc = docBuilder.parse(xmlStream);
        
        NodeList list = doc.getElementsByTagName("*");
        
        int usersCount=0;
        ArrayList <User> users = new <User> ArrayList();
        
        for (int i = 0; i < list.getLength(); i++) {
			
	    Element element = (Element) list.item(i);
	    String nodeName = element.getNodeName();
            
            if(nodeName.equals("user")){
                
                User user = new User();
                user.setId(Integer.parseInt(element.getAttribute("ID")));
               // System.out.println(user.getId());
                users.add(usersCount,user);
                usersCount++;
            }else if(nodeName.equals("firstName")){
                
               users.get(usersCount-1).setFirstName( element.getChildNodes().item(0).getNodeValue());
            }else if(nodeName.equals("lastName")){
                
               users.get(usersCount-1).setLastName( element.getChildNodes().item(0).getNodeValue());
            }else if(nodeName.equals("dateOfBirth")){
                
               users.get(usersCount-1).setDateOfBirth( element.getChildNodes().item(0).getNodeValue());
            }else if(nodeName.equals("location")){
                
               users.get(usersCount-1).setLocation(element.getChildNodes().item(0).getNodeValue());
            }else if(nodeName.equals("joinYear")){
                
               users.get(usersCount-1).setJoinYear(Integer.parseInt(element.getChildNodes().item(0).getNodeValue()));
            }else if(nodeName.equals("jobtitle")){
                
               users.get(usersCount-1).setJobTitle(element.getChildNodes().item(0).getNodeValue());
            }else if(nodeName.equals("address")){
                
               users.get(usersCount-1).setAddress(element.getChildNodes().item(0).getNodeValue());
            }else if(nodeName.equals("email")){
                
               users.get(usersCount-1).setEmail(element.getChildNodes().item(0).getNodeValue());
            }else if(nodeName.equals("phone")){
                
               users.get(usersCount-1).setPhone(element.getChildNodes().item(0).getNodeValue());
            }else if(nodeName.equals("gender")){
                
               users.get(usersCount-1).setGender(element.getChildNodes().item(0).getNodeValue());
            }else if(nodeName.equals("nationality")){
                
               users.get(usersCount-1).setNationality(element.getChildNodes().item(0).getNodeValue());
            }
            
        }
        
        /* Relations file parsing :*/
        
        DocumentBuilderFactory factory_2 = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder docBuilder_2 = factory_2.newDocumentBuilder();
        
        InputStream xmlStream_2 = ParsingXmlWithDom.class.getResourceAsStream("relations.xml");
        
        Document doc_2 = docBuilder_2.parse(xmlStream_2);
        
        NodeList list_2 = doc_2.getElementsByTagName("*");
        
        int id =0;
        for (int i = 0; i < list_2.getLength(); i++) {
        
            Element element = (Element) list_2.item(i);
	    String nodeName = element.getNodeName();
            
            if(nodeName.equals("friend1_id")){
                
                id=Integer.parseInt(element.getChildNodes().item(0).getNodeValue());
               // System.out.println(id);
            }else if(nodeName.equals("friend2_id")){
                int id_2;
                id_2= Integer.parseInt(element.getChildNodes().item(0).getNodeValue());
              // System.out.println( " id 1" + id + " id 2 "+id_2);
              //System.out.println(users.get(id_2-1).getId());
               users.get(id_2-1).friends.add(users.get(id-1));
               users.get(id-1).friends.add(users.get(id_2-1));
            }
        }
        
        
        
        for(int i =0; i <users.size(); i++){
            User u = (User) users.get(i);
           System.out.println(" \nUser : "+ u.getId());
           System.out.println(" \t Name : "+ u.getFirstName() +" "+u.getLastName());
           System.out.println(" \t Date of Birth : "+ u.getDateOfBirth());
           System.out.println(" \t Location : "+u.getLocation());
           System.out.println(" \t Join year :" + u.getJoinYear());
           System.out.println(" \t Job title :" +u.getJobTitle());
           System.out.println(" \t Address :"+ u.getAddress());
           System.out.println(" \t E-mail :"+u.getEmail());
           System.out.println(" \t Phone :"+u.getPhone());
           System.out.println(" \t Gender :"+u.getGender());
           System.out.println(" \t Nationality : "+u.getNationality());
           System.out.println(" \tFriend List :");
           for(int m =0 ; m<u.friends.size() ; m++){
               System.out.println(" \t\tFriend "+ (m+1) +": "+u.friends.get(m).getFirstName());
           }
           
           // System.out.println("\n\n");
        }
    }
    
    
}
