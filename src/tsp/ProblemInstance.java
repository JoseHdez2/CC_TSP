package tsp;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import util.MyXML;

public class ProblemInstance {
   
   boolean ERROR_IF_UNDEFINED_DISTANCE = false; 
   
   String name, source, description;
   int doublePrecision, ignoredDigits;
   DistanceMatrix distanceMatrix;

   public ProblemInstance(File file){
       Document dom = this.parseXML(file);
       parseDocument(dom);
   }
   
   public Document parseXML(File file){
       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
       Document dom = null;
       
       try {
           DocumentBuilder db = dbf.newDocumentBuilder();
           dom = db.parse(file);
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return dom;
   }
   
   private void parseDocument(Document dom){
       Element docEle = dom.getDocumentElement();
       
       NodeList nl;
       nl= docEle.getElementsByTagName("name");
       if (nl != null && nl.getLength() > 0){
           name = nl.item(0).getTextContent();
       }
       
       nl= docEle.getElementsByTagName("source");
       if (nl != null && nl.getLength() > 0){
           source = nl.item(0).getTextContent();
       }
       
       nl= docEle.getElementsByTagName("description");
       if (nl != null && nl.getLength() > 0){
           description = nl.item(0).getTextContent();
       }
       
       nl= docEle.getElementsByTagName("doublePrecision");
       if (nl != null && nl.getLength() > 0){
           doublePrecision = Integer.parseInt(nl.item(0).getTextContent());
       }
       
       nl= docEle.getElementsByTagName("ignoredDigits");
       if (nl != null && nl.getLength() > 0){
           ignoredDigits = Integer.parseInt(nl.item(0).getTextContent());
       }
       
       nl = docEle.getElementsByTagName("graph");
       if (nl != null && nl.getLength() > 0){
           Element el = (Element)nl.item(0);
           distanceMatrix = parseGraph(el);
       }
   }
   
   private DistanceMatrix parseGraph(Element graphEle){
       ArrayList<Element> eleVertices = MyXML.getSubElements(graphEle, "vertex");
       for(int i = 0; i < eleVertices.size(); i++){
           Element el = eleVertices.get(i);
           distanceMatrix = parseGraph(el);
       }
       return new DistanceMatrix(1);
   }
   
   public String toString(){
       String str = "";
       str += String.format("name: %s %n", this.name);
       str += String.format("source: %s %n", this.source);
       str += String.format("description: %s %n", this.description);
       str += String.format("doublePrecision: %d %n", this.doublePrecision);
       str += String.format("ignoredDigits: %d %n", this.ignoredDigits);
       str += String.format("distanceMatrix: %n %s", this.distanceMatrix.toString());
       return str;
   }
}
