package com.centrumhr.desktop.data;

import com.centrumhr.data.core.DatabaseException;
import com.centrumhr.data.core.xml.IXMLDataBase;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 24.05.2017.
 */
public class XMLDatabase implements IXMLDataBase {

    private File dbPath;
    private Document transactionDocument;

    public XMLDatabase(File dbPath) {
        this.dbPath = dbPath;
    }

    public void onCreateDataBase(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            transactionDocument = builder.newDocument();
            Element root = transactionDocument.createElement("items");
            transactionDocument.appendChild(root);
            endTransaction();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Document loadDocument(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream input = new FileInputStream(dbPath);
            return builder.parse(input);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        } catch (SAXException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        }
    }

    @Override
    public void beginTransaction() {
        transactionDocument = loadDocument();
    }

    @Override
    public void endTransaction() {
        try {
            OutputFormat format = new OutputFormat(transactionDocument);
            if (transactionDocument.getXmlEncoding() != null) {
                format.setEncoding(transactionDocument.getXmlEncoding());
            }
            format.setLineWidth(100);
            format.setIndenting(true);
            format.setIndent(5);
            Writer out = new FileWriter(dbPath);
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(transactionDocument);
            out.close();
            transactionDocument = null;
        } catch (IOException e) {
            e.printStackTrace();
            transactionDocument = null;
            throw new DatabaseException(e);
        }
    }

    @Override
    public Element create(String tag) {
        try {
            return transactionDocument.createElement(tag);
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        }
    }

    @Override
    public void insert(Element item) {
        transactionDocument.getFirstChild().appendChild(item);
    }

    @Override
    public Node find( String nodeType , UUID uuid) {
        try {
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//attendancePlan[@uniqueId='"+uuid.toString()+"']");
            NodeList nl = (NodeList) expr.evaluate(transactionDocument, XPathConstants.NODESET);
            if(nl.getLength() > 0){
                return nl.item(0);
            }
            return null;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Element> list() {
        try {
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//attendancePlan");
            NodeList nl = (NodeList) expr.evaluate(transactionDocument, XPathConstants.NODESET);

            List<Element> elements = new ArrayList<>();
            for(int i = 0 ; i < nl.getLength() ; i++){
                elements.add((Element)nl.item(i));
            }

            return elements;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(UUID uuid) {
        Node node = find("attendancePlan",uuid);
        if(node !=null){
            transactionDocument.getFirstChild().removeChild( node );
        }
    }
}
