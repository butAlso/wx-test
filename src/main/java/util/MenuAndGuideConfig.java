package util;

import entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuAndGuideConfig {

    /**
     * 组装菜单
     * @return
     */
    public static Menu initMenu(){
        Menu menu = new Menu();
        ClickButton button11 = new ClickButton();
        button11.setName("click菜单");
        button11.setType("click");
        button11.setKey("11");

        ViewButton button21 = new ViewButton();
        button21.setName("view菜单");
        button21.setType("view");
        button21.setUrl("http://www.imooc.com");

        ClickButton button31 = new ClickButton();
        button31.setName("扫码事件");
        button31.setType("scancode_push");
        button31.setKey("31");

        ClickButton button32 = new ClickButton();
        button32.setName("地理位置");
        button32.setType("location_select");
        button32.setKey("32");

        Button button = new Button();
        button.setName("菜单");
        button.setSub_button(new Button[]{button31,button32});

        menu.setButton(new Button[]{button11,button21,button});
        return menu;
    }

    /**
     *  处理用户输入文本消息
     * @param eventMap
     * @return
     */
    public static String handleText(Map<String, String> eventMap) {
        String content = eventMap.get("Content");
        String fromUserName = eventMap.get("FromUserName");
        String toUserName = eventMap.get("ToUserName");
        String result = null;           // 返回结果
        /*
         * 如果用户输入文本消息为1或2，则回复相应的消息，
         * 输入其他文本消息回复菜单消息设置消息
         */
        if (content.equals("1")) {
            result = MessageUtil.createTextMessage(toUserName, fromUserName, guide1());
        } else if (content.equals("2")) {
            result = MessageUtil.createNewsMessage(toUserName, fromUserName, guide2());
        } else if (content.equals("3")) {
            result = MessageUtil.createMusicMessage(toUserName, fromUserName, guide3());
        } else {
            result = MessageUtil.createTextMessage(toUserName, fromUserName, guides());
        }
        return result;
    }

    /**
     * 处理用户位置消息
     * @param locationMap
     * @return
     */
    public static String handleLocation(Map<String, String> locationMap) {
        String fromUserName = locationMap.get("FromUserName");
        String toUserName = locationMap.get("ToUserName");
        String label = locationMap.get("Label");
        String message = MessageUtil.createTextMessage(toUserName, fromUserName, label);
        return message;
    }
    /**
     * 处理用户操作事件消息
     * @param eventMap
     * @return
     */
    public static String handleEvent(Map<String, String> eventMap) {
        String fromUserName = eventMap.get("FromUserName");
        String toUserName = eventMap.get("ToUserName");
        String eventType = eventMap.get("Event");
        String result = null;       // 返回结果
        if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
            result = MessageUtil.createTextMessage(toUserName, fromUserName, guides());
        }else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
            result = MessageUtil.createTextMessage(toUserName, fromUserName, guides());
        }else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
            String url = eventMap.get("EventKey");
            result = MessageUtil.createTextMessage(toUserName, fromUserName, url);
        }else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
            String key = eventMap.get("EventKey");
            result = MessageUtil.createImageMessage(toUserName, fromUserName, key);
        }

        return result;
    }

    /**
     * 菜单消息设置
     * @return
     */
    private static String guides(){
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注，请按照菜单提示进行操作：\n\n");
        sb.append("1、课程介绍\n");
        sb.append("2、慕课网介绍\n");
        sb.append("3、See you again\n");
        sb.append("回复？调出此菜单。");
        return sb.toString();
    }

    /**
     * 处理引导的消息1
     * @return
     */
    private static String guide1(){
        StringBuffer sb = new StringBuffer();
        sb.append("本套课程介绍微信公众号开发，主要涉及公众号介绍、编辑模式介绍、开发模式介绍等");
        return sb.toString();
    }

    /**
     * 处理引导消息2
     * @return
     */
    private static List<News> guide2(){
        List<News> list = new ArrayList<>();

        News news1 = new News();
        news1.setTitle("慕课网介绍");
        news1.setDescription("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。慕课网课程涵盖前端开发、PHP、Html5、Android、iOS、Swift等IT前沿技术语言，包括基础课程、实用案例、高级分享三大类型，适合不同阶段的学习人群。");
        news1.setPicUrl("http://120.79.94.32/wx-test/image/imooc.jpg");
        news1.setUrl("www.imooc.com");
        list.add(news1);

        News news2 = new News();
        news2.setTitle("雷州介绍");
        news2.setDescription("雷州市，建市前称海康县，是广东省湛江市辖县级市，位于雷州半岛中部。属热带季风气候。雷州市东濒雷州湾、南隔琼州海峡与国际旅游岛海南相望、西濒北部湾、北接湛江城区。雷州市境内交通有粤海铁路、国道207、湛徐高速公路贯通全境。");

        news2.setPicUrl("http://120.79.94.32/wx-test/image/leizhou.jpg");
        news2.setUrl("http://www.leizhou.gov.cn/");
        list.add(news2);
        return list;
    }

    public static Music guide3() {
        Music music = new Music();
        music.setTitle("see you again");
        music.setDescription("速度与激情");
        music.setHQMusicUrl("http://120.79.94.32/wx-test/mp3/See You Again.mp3");
        music.setMusicUrl("http://120.79.94.32/wx-test/mp3/See You Again.mp3");
        music.setThumbMediaId("jyMZCE1yKYTyy0YuRIxdtGaIAM7ymgE5L3cgWIJRvEZ-5kNJDTrXh-FCxsT5ToET");
        return music;
    }

}
