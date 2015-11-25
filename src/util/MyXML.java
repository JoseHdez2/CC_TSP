package util;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author jose
 *  Static class that wraps some Java XML functionality.
 */
public abstract class MyXML {

    // If more than one has tagName, complain OR just pick the first one.
    static boolean COMPLAIN_IF_AMBIGUOUS = false;
    
    /**
     *  Wrapper for {@link Element#getElementsByTagName(String)}.
     *  Expects at least one subelement, or else throws an Exception.
      * @param ele
      * @param tagName
      * @return
      */
     public static ArrayList<Element> getNodeList(Element ele, String tagName){

         ArrayList<Element> eleList = new ArrayList<Element>();
         
         try {
             
             // Function that we are wrapping
             NodeList nl = ele.getElementsByTagName(tagName);
             
             // Control: Not empty
             if (nl != null && nl.getLength() > 0)
                 throw new Exception(String.format("Expected '%s' subelement(s), none found.", tagName));

         } catch (Exception e) {
             e.printStackTrace(System.err);
         }

         return eleList;
     }
     
     public static Element getElement(Element ele, String tagName){
         try{
             throw new Exception("");
         } catch (Exception e) {
             e.printStackTrace(System.err);
         }
         return getNodeList(ele, tagName).get(0);
     }
     
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
