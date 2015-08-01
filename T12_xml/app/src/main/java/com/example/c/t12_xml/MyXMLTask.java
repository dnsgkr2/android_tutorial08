package com.example.c.t12_xml;

import android.os.AsyncTask;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by c on 2015-08-01.
 */
class MyXMLTask extends AsyncTask<String,Void,Document> {

    MyXMLTask(TextView textview){
        this.textview = textview;
    }

    private TextView textview;
    private String str = "";

    public String getStr(){
        return str;
    }
    @Override
    protected Document doInBackground(String... params) {

        URL url;
        Document doc = null;

        try {
            url = new URL(params[0]);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(url.openStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    private String getTagValue(Element dataElement, String tagName){
        NodeList NodeList = dataElement.getElementsByTagName(tagName);
        Element element = (Element)NodeList.item(0); // <day>0</day>

        NodeList TextNodeList = element.getChildNodes();
        return TextNodeList.item(0).getNodeValue(); //0
    }

    @Override
    protected void onPostExecute(Document document) {
        super.onPostExecute(document);

        str = "";
        NodeList nodeList = document.getElementsByTagName("data");
        for(int i=0; i<nodeList.getLength(); i++){
            Element DataElement = (Element)nodeList.item(i);  // <data>...</data>
            str += "data : "+getTagValue(DataElement, "day");
            str += " 시간 : "+getTagValue(DataElement, "hour");
            str += " 온도 : "+getTagValue(DataElement, "temp");
            str += " 날씨 : "+getTagValue(DataElement, "wfKor");



            //NodeList dayNodeList = DataElement.getElementsByTagName("day");
            //Element dayElement = (Element)dayNodeList.item(0); // <day>0</day>

            //NodeList dayTextNodeList = dayElement.getChildNodes();
            //str += "day : "+dayTextNodeList.item(0).getNodeValue(); //0

            // NodeList hourNodeList = DataElement.getElementsByTagName("day");
            //Element hourElement = (Element)dayNodeList.item(0); // <hour>0</hour>

            //NodeList hourTextNodeList = dayElement.getChildNodes();
            //str += "hour : "+hourTextNodeList.item(0).getNodeValue(); //0
            //
            //
            //

            str += "\n";
        }

        textview.setText(str);

    }
}
