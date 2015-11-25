package util;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author jose
 *  Static class that wraps some Java XML functionality.
 */
public abstract class MyXML {
    
    /**
     *  Wrapper for {@link Element#getElementsByTagName(String)}.
     *  Expects at least one subelement, or else throws an Exception.
      * @param ele
      * @param tagName
      * @return
      */
     public static ArrayList<Element> getSubElements(Element ele, String tagName){

         ArrayList<Element> eleList = new ArrayList<Element>();
         
         try {
             NodeList nl = ele.getElementsByTagName(tagName);
             if (nl != null && nl.getLength() > 0)
                 throw new Exception("Expected subelement(s), none found.");

             for (int i = 0; i < nl.getLength(); i++){
                 eleList.add((Element)nl.item(0));
             }
         } catch (Exception e) {
             e.printStackTrace(System.err);
         }

         return eleList;
     }
     
     public static Element getElement(Element ele, String tagName){
         return getSubElements(ele, tagName).get(0);
     }
     
     // If more than one has tagName, complain OR just pick the first one.
     static boolean COMPLAIN_IF_AMBIGUOUS = false;
     
     public static String getStringContent(Element ele, String tagName){

         String stringCont = "";
         
         try {
             NodeList nl = ele.getElementsByTagName(tagName);
             if (nl != null && nl.getLength() == 0)
                 throw new Exception("Expected subelement(s), none found.");
             if (COMPLAIN_IF_AMBIGUOUS && nl.getLength() != 1)
                 throw new Exception("Ambiguous tag name: more than one found.");

             stringCont = nl.item(0).getTextContent();
         } catch (Exception e) {
             e.printStackTrace(System.err);
         }

         return stringCont;
     }
}