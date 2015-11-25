package tsp;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
   
   private void parseDocument(Document dom){
       Element docEle = dom.getDocumentElement();
       
       name = MyXML.getStringContent(docEle, "name");
       source = MyXML.getStringContent(docEle, "source");
       description = MyXML.getStringContent(docEle, "description");
       doublePrecision = Integer.parseInt(MyXML.getStringContent(docEle, "doublePrecision"));
       ignoredDigits = Integer.parseInt(MyXML.getStringContent(docEle, "ignoredDigits"));
       
       Element graphEle = MyXML.getElement(docEle, "graph");
       distanceMatrix = parseGraphElement(graphEle);
   }
   
   private DistanceMatrix parseGraphElement(Element graphEle){
       ArrayList<Element> vertexEleList = MyXML.getNodeList(graphEle, "vertex");
       int vertexNumber = vertexEleList.size();
       
       DistanceMatrix distMat = new DistanceMatrix(vertexNumber);
       for(int i = 0; i < vertexNumber; i++){
           
           ArrayList<Element> edgeEleList = MyXML.getNodeList(vertexEleList.get(i), "edge");
           
           for(Element e : edgeEleList){
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
