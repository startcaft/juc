/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/19 10:18
 * Description:
 */
package jdqs.startcaft.cn;

import com.mxixm.fastboot.weixin.annotation.*;
import com.mxixm.fastboot.weixin.module.event.WxEvent;
import com.mxixm.fastboot.weixin.module.message.WxMessage;
import com.mxixm.fastboot.weixin.module.message.WxMessageBody;
import com.mxixm.fastboot.weixin.module.user.WxUser;
import com.mxixm.fastboot.weixin.module.web.WxRequest;
import com.mxixm.fastboot.weixin.module.web.WxRequestBody;
import com.mxixm.fastboot.weixin.module.web.session.WxSession;
import org.springframework.boot.SpringApplication;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author StartCaft
 * @create 2018/4/19
 * @since 1.0.0
 */
@WxApplication
@WxController
public class WeiXinApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WeiXinApp.class, args);
    }

    /**
     * 定义微信菜单
     */
    @WxButton(group = WxButton.Group.LEFT, main = true, name = "刺激战场")
    public void left() {
    }

    /**
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.CLICK,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.FIRST,
            name = "吃鸡技巧")
    public String leftFirst(WxRequest wxRequest, WxUser wxUser) {
        return "吃鸡技巧";
    }
    @WxButton(type = WxButton.Type.CLICK,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.SECOND,
            name = "战术打法")
    public String leftSecond(WxRequest wxRequest, WxUser wxUser) {
        return "战术打法";
    }
    @WxButton(type = WxButton.Type.CLICK,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.THIRD,
            name = "武器知识")
    public String leftThird(WxRequest wxRequest, WxUser wxUser) {
        return "武器知识";
    }

//    /**
//     * 定义微信菜单，并接受事件
//     */
//    @WxButton(type = WxButton.Type.VIEW,
//            group = WxButton.Group.LEFT,
//            order = WxButton.Order.SECOND,
//            url = "http://baidu.com",
//            name = "点击链接")
//    @WxAsyncMessage
//    public WxMessage link() {
//        return WxMessage.newsBuilder().addItem("吃鸡第一战 合理规避《刺激战场》中的毒", "在“吃鸡”这种射击竞技类游戏中，玩家需要面对“毒圈”，其具有一定的杀伤力，且伤害随着安全区不断缩小逐渐递增，那么如何避免受到毒圈的危害呢？", "http://img1.gamersky.com/image2018/02/20180213_ZYJ_404_7/image003_S.jpg", "http://shouyou.gamersky.com/gl/201802/1014962.shtml").build();
//    }

    /**
     * 接受微信事件
     * @param wxRequest
     * @param wxUser
     */
    @WxEventMapping(type = WxEvent.Type.UNSUBSCRIBE)
    public void unsubscribe(WxRequest wxRequest, WxUser wxUser) {
        System.out.println(wxUser.getNickName() + "，退订了公众号");
    }

    /**
     * 接受用户文本消息，异步返回文本消息
     * @param content
     * @return dummy
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT)
    @WxAsyncMessage
    public String text(WxRequest wxRequest, String content) {
        WxSession wxSession = wxRequest.getWxSession();
        if (wxSession != null && wxSession.getAttribute("last") != null) {
            return "上次收到消息内容为" + wxSession.getAttribute("last");
        }
        return "收到消息内容为" + content;
    }

    /**
     * 接受用户文本消息，同步返回图文消息
     * @param content
     * @return dummy
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT, wildcard = "1*")
    public WxMessage message(WxSession wxSession, String content) {
        wxSession.setAttribute("last", content);
        return WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title(content).description("吃鸡第一战 合理规避《刺激战场》中的毒")
                        .picUrl("http://img1.gamersky.com/image2018/02/20180213_ZYJ_404_7/image003_S.jpg")
                        .url("http://shouyou.gamersky.com/gl/201802/1014962.shtml").build())
                .addItem(WxMessageBody.News.Item.builder().title("第二条").description("随便二点")
                        .picUrl("http://k2.jsqq.net/uploads/allimg/1702/7_170225142233_1.png")
                        .url("http://baidu.com").build())
                .build();
    }

    /**
     * 接受用户文本消息，异步返回文本消息
     * @param content
     * @return dummy
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT, wildcard = "2*")
    @WxAsyncMessage
    public String text2(WxRequestBody.Text text, String content) {
        boolean match = text.getContent().equals(content);
        return "收到消息内容为" + content + "!结果匹配！" + match;
    }
}
