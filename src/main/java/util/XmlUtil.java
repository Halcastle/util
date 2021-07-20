package util;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName XmlUtil
 * @Description TODO
 * @Author pwang6
 * @Date 2021/7/20 15:32
 * @Version 1.0
 **/
public class XmlUtil {

    public static void main(String[] args) throws DocumentException {

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<document>\n" +
                "    <request>\n" +
                "        <head>\n" +
                "            <version>1.0.0</version>\n" +
                "            <appId>ALIPAY</appId>\n" +
                "            <function>ant.credit.platform.agreement.ask</function>\n" +
                "            <reqTime>20190927023015</reqTime>\n" +
                "            <reqTimeZone>UTC+8</reqTimeZone>\n" +
                "            <reqMsgId>20190927110400030003700020971765</reqMsgId>\n" +
                "            <reserve></reserve>\n" +
                "            <signType>RSA</signType>\n" +
                "            <inputCharset>UTF-8</inputCharset>\n" +
                "        </head>\n" +
                "        <body>\n" +
                "            <applyNo>2019092700901001012502006897002A</applyNo>\n" +
                "            <certType>01</certType>\n" +
                "            <certNo>440705194504303997</certNo>\n" +
                "            <name>蓬蓬</name>\n" +
                "            <productCode>JB</productCode>\n" +
                "            <bizMode>PLATFORM_3</bizMode>\n" +
                "            <loanMode>UNION</loanMode>\n" +
                "            <applyType>ADMIT_APPLY</applyType>\n" +
                "            <bizType>ADMIT_APPLY</bizType>\n" +
                "            <participant>F51,F53</participant>\n" +
                "            <agreement><![CDATA[[{\"name\": \"《申请协议》\"},\"version\": \"V1\"},{\"name\": \"《征信查询授权书》\"},\"version\": \"V1\"}]]]></agreement>\n" +
                "            <extInfo><![CDATA[{\"key1\":\"value1\"}]]></extInfo>\n" +
                "        </body>\n" +
                "    </request>\n" +
                " <signature>N7hC9YyEzKcd5fj9aBmJRqGvZGQHy6y8k6nTw1PpV/2LAekFs0IwUqvqMAmazWLHGgm4cozGBfDpKYHr9f95TJbwgl4dy//NFoc0K8N11xXbiuOW2EWx7XSix8djep+ejqvhxNhHURH0cLR7acEpEBij31OxrCu7Mq09S7qyWgzZYuOtJU03SJ4Ar8VpwDTNBt67KtW3/1q4iBgJ2Qqa9TR+9m6/r6HhhIOAcch6e243LJWptaCLa8ARk6aqmP+BbLIaIPjrzfinABnWjySSaAunqW9Y+c2q/zqt3TffUUKaPxzm3JjTn/ArHds5tnez6NdjYL7cspdvZ9J/CwYT6Q==</signature>\n" +
                "</document>";

        long begin = System.currentTimeMillis();
        readStringXmlOut(xml);
        System.out.println("耗时：   " + String.valueOf(System.currentTimeMillis() - begin));
    }

    /**
     * @param xml
     * @return Map
     * @description 将xml字符串转换成map
     */
    public static Map readStringXmlOut(String xml) throws DocumentException {
        Map map = new HashMap();
        Document doc = null;
        // 将字符串转为XML
        doc = DocumentHelper.parseText(xml);
        // 获取根节点
        Element rootElt = doc.getRootElement();
        // 拿到根节点的名称
        System.out.println("根节点：" + rootElt.getName());

        // 获取根节点下的子节点head
        Iterator iter_head = rootElt.element("request").elementIterator("head");
        Iterator iter_body = rootElt.element("request").elementIterator("body");
        System.out.println("iter_head:" + iter_head);
        System.out.println("iter_body:" + iter_body);
        // 遍历head节点
        while (iter_head.hasNext()) {

            Element recordEle = (Element) iter_head.next();
            // 拿到head节点下的子节点version值
            String version = recordEle.elementTextTrim("version");
            String appId = recordEle.elementTextTrim("appId");
            String function = recordEle.elementTextTrim("function");
            String reqTime = recordEle.elementTextTrim("reqTime");
            String reqTimeZone = recordEle.elementTextTrim("reqTimeZone");
            String reqMsgId = recordEle.elementTextTrim("reqMsgId");
            System.out.println("appId:" + appId);
            System.out.println("function:" + function);
            System.out.println("reqTime:" + reqTime);
            System.out.println("reqTimeZone:" + reqTimeZone);
            System.out.println("reqMsgId:" + reqMsgId);
            map.put("version", version);

        }
        while (iter_body.hasNext()) {

            Element recordEle = (Element) iter_body.next();
            // 拿到head节点下的子节点version值
            String applyNo = recordEle.elementTextTrim("applyNo");
            String certType = recordEle.elementTextTrim("certType");
            String agreement = recordEle.elementTextTrim("agreement");
            String extInfo = recordEle.elementTextTrim("extInfo");
            System.out.println("applyNo:" + applyNo);
            System.out.println("certType:" + certType);
            System.out.println("agreement:" + agreement);
            System.out.println("extInfo:" + extInfo);
        }


        //获取根节点下的子节点body
        Iterator iterss = rootElt.elementIterator("signature");
        // 遍历body节点
        while (iterss.hasNext()) {
            Element recordEless = (Element) iterss.next();
            // 拿到body节点下的子节点result值
            String result = recordEless.elementTextTrim("result");
            System.out.println("result:" + result);
            // 获取子节点body下的子节点form
            Iterator itersElIterator = recordEless.elementIterator("form");
            // 遍历Header节点下的Response节点
            while (itersElIterator.hasNext()) {
                Element itemEle = (Element) itersElIterator.next();
                // 拿到body下的子节点form下的字节点banlce的值
                String banlce = itemEle.elementTextTrim("banlce");
                String subID = itemEle.elementTextTrim("subID");

                System.out.println("banlce:" + banlce);
                System.out.println("subID:" + subID);
                map.put("result", result);
                map.put("banlce", banlce);
                map.put("subID", subID);
            }
        }
        return map;
    }
}
