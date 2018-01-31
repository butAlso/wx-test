package util;

import entity.*;

import java.util.*;

public class MessageUtil {

    /**
     * 微信消息类型
     */
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_MUSIC = "music";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVNET = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";
    public static final String MESSAGE_SCANCODE= "scancode_push";

    /**
     * 组装文本消息
     * @param fromUserName
     * @param toUserName
     * @param Content
     * @return
     */
    public static String createTextMessage(String fromUserName, String toUserName,
                                         String Content) {
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(fromUserName);
        textMessage.setToUserName(toUserName);
        textMessage.setMsgType(MESSAGE_TEXT);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setContent(Content);
        return XMLUtil.textMessageToXml(textMessage);
    }

    /**
     * 组装图片消息
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String createImageMessage(String toUserName,String fromUserName, String mediaId){
        Image image = new Image();
        image.setMediaId(mediaId);
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MESSAGE_IMAGE);
        imageMessage.setCreateTime(new Date().getTime());
        imageMessage.setImage(image);
        return XMLUtil.imageMessageToXml(imageMessage);
    }

    /**
     * 图文消息的组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String createNewsMessage(String toUserName,String fromUserName, List<News> newsList){
        NewsMessage newsMessage = new NewsMessage();

        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        return XMLUtil.newsMessageToXml(newsMessage);
    }
    /**
     * 组装音乐消息
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String createMusicMessage(String toUserName,String fromUserName,
                                          Music music){
        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setFromUserName(toUserName);
        musicMessage.setToUserName(fromUserName);
        musicMessage.setMsgType(MESSAGE_MUSIC);
        musicMessage.setCreateTime(new Date().getTime());
        musicMessage.setMusic(music);

        return XMLUtil.musicMessageToXml(musicMessage);
    }

}
