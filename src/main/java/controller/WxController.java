package controller;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.CheckSignature;
import util.MenuAndGuideConfig;
import util.MessageUtil;
import util.XMLUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@WebServlet(urlPatterns = "/wx")
public class WxController extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 处理微信服务器验证请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        PrintWriter out = resp.getWriter();
        if (CheckSignature.checkSignature(signature, timestamp, nonce)) {
            logger.info("验证成功，echostr={}", echostr);
            out.print(echostr);
        } else {
            logger.warn("验证失败，echostr={}", echostr);
        }
        out.close();
    }

    /**
     * 处理微信服务器转发请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 将报文输入流转化为字符串，并设置字符编码 */
        String xml = inputStreamToString(req.getInputStream());
        logger.info("请求xml如下:\n{}", xml);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String message = null;          // resp返回消息

        try {

            /*
             * 将微信消息xml内容存到map中，
             * 并判断消息类型，调用相应的方法
             */
            Map<String, String> map = XMLUtil.xmlToMap(xml);
            if (MessageUtil.MESSAGE_TEXT.equals(map.get("MsgType"))) {
                message = MenuAndGuideConfig.handleText(map);
            } else if(MessageUtil.MESSAGE_LOCATION.equals(map.get("MsgType"))){
                message = MenuAndGuideConfig.handleLocation(map);
            } else if (MessageUtil.MESSAGE_EVNET.equals(map.get("MsgType"))) {
                message = MenuAndGuideConfig.handleEvent(map);
            }
            logger.info("回复：\n{}", message);

            out.print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

    }

    /**
     * 将输入流转化为字符串
     * @param ins
     * @return
     * @throws IOException
     */
    private String inputStreamToString(InputStream ins) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(ins, "utf-8"));
        StringBuilder sb = new StringBuilder();
        String temp;
        while ((temp = bufferedReader.readLine()) != null) {
            sb.append(temp);
        }
        return sb.toString();
    }
}
