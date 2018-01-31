package util;

import entity.Music;
import entity.News;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MessageUtilTest {

    @Test
    public void createTextMessage() {
        System.out.println(
                MessageUtil.createImageMessage(
                        "铁柱", "小美", "爱你"));
    }

    @Test
    public void createImageMessage() {
        System.out.println(
                MessageUtil.createImageMessage(
                        "铁柱", "小美", "2250")
        );
    }

    @Test
    public void createNewsMessage() {
        List<News> list = new ArrayList<>();

        News news1 = new News();
        news1.setTitle("慕课网介绍");
        news1.setDescription("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。慕课网课程涵盖前端开发、PHP、Html5、Android、iOS、Swift等IT前沿技术语言，包括基础课程、实用案例、高级分享三大类型，适合不同阶段的学习人群。");
        news1.setPicUrl("http://zapper.tunnel.mobi/Weixin/image/imooc.jpg");
        news1.setUrl("www.imooc.com");
        list.add(news1);

        News news2 = new News();
        news2.setTitle("雷州介绍");
        news2.setDescription("雷州市，建市前称海康县，是广东省湛江市辖县级市，位于雷州半岛中部。属热带季风气候。雷州市东濒雷州湾、南隔琼州海峡与国际旅游岛海南相望、西濒北部湾、北接湛江城区。雷州市境内交通有粤海铁路、国道207、湛徐高速公路贯通全境。");
        news2.setPicUrl("雷州风景.jpg");
        news2.setUrl("leizhou.com");
        list.add(news2);

        System.out.println(
                MessageUtil.createNewsMessage(
                        "铁柱", "小美", list));
    }

    @Test
    public void createMusicMessage() {
        Music music = new Music();
        music.setTitle("等你下课");
        music.setDescription("杰伦新单曲");
        music.setHQMusicUrl("hq.url");
        music.setMusicUrl("url");
        music.setThumbMediaId("thumbMediaId");
        System.out.println(
                MessageUtil.createMusicMessage("铁柱", "小美", music));
    }
}