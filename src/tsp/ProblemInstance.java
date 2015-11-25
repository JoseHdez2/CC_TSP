package tsp;

import java.io.File;

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
   DistanceMatrix distanceMatrix = null;

   public ProblemInstance(File file) throws Exception{
       Document dom = this.parseXML(file);
       parseDocument(dom);
   }
   
   private Document parseXML(File file){
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
   
   private void parseDocument(Document dom) throws Exception{
       Element docEle = dom.getDocumentElement();
       
       name = MyXML.getStringContent(docEle, "name");
       source = MyXML.getStringContent(docEle, "source");
       description = MyXML.getStringContent(docEle, "description");
       doublePrecision = Integer.parseInt(MyXML.getStringContent(docEle, "doublePrecision"));
       ignoredDigits = Integer.parseInt(MyXML.getStringContent(docEle, "ignoredDigits"));
       
       Element graphEle = (Element) MyXML.getSubNode(docEle, "graph");
       distanceMatrix = parseGraphElement(graphEle);
   }
   
   private DistanceMatrix parseGraphElement(Element graphEle) throws Exception{
       NodeList vertexEleList = MyXML.getSubNodeList(graphEle, "vertex");
       
       DistanceMatrix distMat = new DistanceMatrix(vertexEleList.getLength());
       for(int i = 0; i < vertexEleList.getLength(); i++){
           
           NodeList edgeEleList = MyXML.getSubNodeList((Element)vertexEleList.item(i), "edge");
           
           for (int j = 0; j < edgeEleList.getLength(); j++){
               Element e = (Element)edgeEleList.item(j);
               double cost = Double.parseDouble(e.getAttribute("cost"));
               int endNode = Integer.parseInt(e.getTextContent());
               distMat.set(i, endNode, cost);
           }
           
           // Whatever the value, exist or not: set self distance to zero.
           distMat.set(i, i, 0);
           
       }
       return distMat;
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
