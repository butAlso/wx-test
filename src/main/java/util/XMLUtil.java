package util;

import com.thoughtworks.xstream.XStream;
import entity.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class XMLUtil {

    /**
     * 微信xml消息转为map集合
     * @param xml
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(String xml) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        System.out.println("xmlToMap: \n" + xml);
        Document doc = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));

        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for(Element e : list){
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    /**
     * 将文本消息对象转为xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        captialXmlLable(xStream, TextMessage.class);
        return xStream.toXML(textMessage);
    }

    /**
     * 将图片消息转为xml
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", imageMessage.getClass());
        captialXmlLable(xStream, ImageMessage.class);
        xStream.aliasField("MediaId", Image.class, "mediaId");
        return xStream.toXML(imageMessage);
    }

    /**
     * 图文消息转为xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", newsMessage.getClass());
        xStream.alias("item", new News().getClass());
        captialXmlLable(xStream, NewsMessage.class);
        captialXmlLable(xStream, News.class);
        return xStream.toXML(newsMessage);
    }

    /**
     * 音乐消息转为xml
     * @param musicMessage
     * @return
     */
    public static String musicMessageToXml(MusicMessage musicMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", musicMessage.getClass());
        captialXmlLable(xStream, MusicMessage.class);
        captialXmlLable(xStream, Music.class);
        return xStream.toXML(musicMessage);
    }

    private static void captialXmlLable(XStream xStream, Class<?> classType) {
        List<String> fields = fieldsName(classType);
        for(String field : fields) {
            xStream.aliasField(capitalStr(field), classType, field);
        }
    }

    private static String capitalStr(String s) {
        String firstLetter = s.substring(0, 1).toUpperCase();
        return firstLetter + s.substring(1);
    }

    private static List<String> fieldsName(Class<?> classType) {
        List<String> list = new ArrayList<>();
        for (; classType != Object.class; classType = classType.getSuperclass()) {
            Field[] fields = classType.getDeclaredFields();
            for(Field field : fields) {
                list.add(field.getName());
            }
        }
        return list;
    }

}
