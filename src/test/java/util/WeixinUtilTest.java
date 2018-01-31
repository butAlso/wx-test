package util;

import entity.AccessToken;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static util.MenuAndGuideConfig.initMenu;

public class WeixinUtilTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private AccessToken accessToken;

    @Before
    public void setUp() throws Exception {
        accessToken = WeixinUtil.getAccessToken();
    }

    @Test
    public void createMenu() throws IOException {
        String menu = JSONObject.fromObject(initMenu()).toString();
        logger.info("menu={}", menu);
        int result = WeixinUtil.createMenu(accessToken.getToken(), menu);
        if (result == 0) {
            logger.info("创建菜单成功！");
        } else {
            logger.info("创建菜单失败，errcode={}", result);
        }
    }

    @Test
    public void queryMenu() throws IOException {
        JSONObject jsonObject = WeixinUtil.queryMenu(accessToken.getToken());
        logger.info("jsonObject={}", jsonObject);
    }

    @Test
    public void deleteMenu() throws IOException {
        int errcode = WeixinUtil.deleteMenu(accessToken.getToken());
        Assert.assertEquals(0, errcode);
    }

    /**
     * 一次成功测试结果
     * imoocMediaId = jyMZCE1yKYTyy0YuRIxdtGaIAM7ymgE5L3cgWIJRvEZ-5kNJDTrXh-FCxsT5ToET
     * leizhouMediaId = iCNsDAKtpBEy9UH_cbHFbHtp5yAFnYYvk1ZCdLHbTgOXTABqHkCjetnhMncoOyir
     * @throws Exception
     */
    @Test
    public void upload() throws Exception {
        /* 上传两个图片 */
        String imoocMediaId = null;
        String leizhouMediaId = null;
        String basePath = WeixinUtil.class.getResource("/").getPath().replace("test-classes", "wx-test");
        logger.info("imooc.jpg文件存在={}", new File(basePath + "/image/imooc.jpg").exists());
        logger.info("leizhou.jpg文件存在={}", new File(basePath + "/image/leizhou.jpg").exists());
        imoocMediaId = WeixinUtil.upload(
                basePath + "/image/imooc.jpg", accessToken.getToken(), "image");
        leizhouMediaId = WeixinUtil.upload(
                basePath + "/image/leizhou.jpg", accessToken.getToken(), "image");
        logger.info("imoocMediaId={}", imoocMediaId);
        logger.info("leizhouMediaId={}", leizhouMediaId);

    }

    @Test
    public void getAccessToken() throws IOException {
        Assert.assertNotNull(WeixinUtil.getAccessToken());
    }
}