package util;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author jose
 *  Static class that wraps some Java XML functionality.
 */
public abstract class MyXML {
    
    /**
     *  Wrapper for {@link Element#getElementsByTagName(String)}.
     *  Expects at least one subelement, or else throws an Exception.
      * @param ele  Root DOM element to search subnodes in.
      * @param tagName
      * @param expectUnique Throw an exception if more than one subelement is obtained.
      * @return
      * @throws Exception Expects at least one subnode. Expects exactly one if expectUnique == true.
      */
     private static NodeList getNodeList(Element ele, String tagName, boolean expectUnique) throws Exception{
             
         // Function that we are wrapping, to control its output.
         NodeList nl = ele.getElementsByTagName(tagName);
         
         // Control: Not empty
         if (nl == null || nl.getLength() == 0)
             Sys.exception("Expected '%s' subelement(s), none found.", tagName);

         // Control: There is exactly one result (optional)
         if (nl.getLength() != 1 && expectUnique)
             Sys.exception("Expected 1 '%s' subelement, more found.", tagName, nl.getLength());

         return nl;
     }
    
    /**
     * Wraps {@link MyXML#getNodeList(Element, String, boolean)}.
     * @return  List of all subnodes in ele, with id = tagName.
     * @throws Exception    Expects at least one subnode.
     */
    public static NodeList getSubNodeList(Element ele, String tagName) throws Exception{
         return getNodeList(ele, tagName, false);
    }
    
    /**
    * Wraps {@link MyXML#getNodeList(Element, String, boolean)}.
    * @return  List of all subnodes in ele, with id = tagName.
    * @throws Exception    Expects exactly one subnode.
    */
    public static Node getSubNode(Element ele, String tagName) throws Exception{
        return getNodeList(ele, tagName, true).item(0);
    }
     
    public static String getStringContent(Element ele, String tagName) throws DOMException, Exception{
        return getSubNode(ele, tagName).getTextContent();
     }
}
