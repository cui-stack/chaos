package com.firepongo.chaos.web.service.tencent;

import com.github.qcloudsms.*;
import com.github.qcloudsms.httpclient.ProxyHTTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author G.G
 * @date 2020/2/25 13:55
 */
@Component
public class TencentSmsService {
    @Value("${app.tencent.sms.appid:42}")
    int appid;
    @Value("${app.tencent.sms.appkey:}")
    String appkey;
    @Value("${app.tencent.sms.smsSign:}")
    String smsSign;

    public boolean send(String phoneNumber, int templateId, String[] params) {
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    templateId, params, smsSign, "", "");
            return result.result == 0;
        } catch (HTTPException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendAll(String[] phoneNumbers, int templateId, String[] params) {
        try {
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult result = msender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");
            return result.result == 0;
        } catch (HTTPException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendVoiceCode(String phoneNumber, String msg) {
        try {
            SmsVoiceVerifyCodeSender vvcsender = new SmsVoiceVerifyCodeSender(appid, appkey);
            SmsVoiceVerifyCodeSenderResult result = vvcsender.send("86", phoneNumber,
                    msg, 2, "");
            return result.result == 0;
        } catch (HTTPException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendVoiceNotice(String phoneNumber, String msg) {
        try {
            SmsVoicePromptSender vpsender = new SmsVoicePromptSender(appid, appkey);
            SmsVoicePromptSenderResult result = vpsender.send("86", phoneNumber,
                    2, 2, msg, "");
            return result.result == 0;
        } catch (HTTPException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean pullReply() {
        try {
            // Note: ?????????????????????????????????????????????????????????(QQ:3012203387)????????????
            int maxNum = 10;  // ?????????????????????
            SmsStatusPuller spuller = new SmsStatusPuller(appid, appkey);

            // ??????????????????
            SmsStatusPullCallbackResult callbackResult = spuller.pullCallback(maxNum);
            System.out.println(callbackResult);

            // ????????????
            SmsStatusPullReplyResult replyResult = spuller.pullReply(maxNum);
            System.out.println(replyResult);
        } catch (HTTPException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void pullPhoneStatus(String phoneNumber) {
        try {
            int beginTime = 1511125600;
            int endTime = 1511841600;
            int maxNum = 10;
            SmsMobileStatusPuller mspuller = new SmsMobileStatusPuller(appid, appkey);

            // ??????????????????
            SmsStatusPullCallbackResult callbackResult = mspuller.pullCallback("86",
                    phoneNumber, beginTime, endTime, maxNum);
            System.out.println(callbackResult);

            // ????????????
            SmsStatusPullReplyResult replyResult = mspuller.pullReply("86",
                    phoneNumber, beginTime, endTime, maxNum);
            System.out.println(replyResult);
        } catch (HTTPException e) {
            // HTTP???????????????
            e.printStackTrace();
        } catch (JSONException e) {
            // json????????????
            e.printStackTrace();
        } catch (IOException e) {
            // ??????IO??????
            e.printStackTrace();
        }
    }

    public void uploadVoiceFile() {
        try {
            // Note: ??????????????????????????????400K??????
            String filePath = "path/to/example.mp3";
            byte[] content = Files.readAllBytes(Paths.get(filePath));
            VoiceFileUploader uploader = new VoiceFileUploader(appid, appkey);
            VoiceFileUploaderResult result = uploader.upload(content, VoiceFileUploader.ContentType.MP3);
            // ??????????????????result???????????????????????????fid
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP???????????????
            e.printStackTrace();
        } catch (JSONException e) {
            // json????????????
            e.printStackTrace();
        } catch (IOException e) {
            // ??????IO??????
            e.printStackTrace();
        }
    }

    public void checkVoiceFile() {
        try {
            // Note: ??????fid??????`??????????????????`????????????????????????????????????
            //       ??????fid??????????????????????????????????????????????????????fid
            String fid = "c799d10a43ec109f02f2288ca3c85b79e7700c98.mp3";
            VoiceFileStatus vfstatus = new VoiceFileStatus(appid, appkey);
            VoiceFileStatusResult result = vfstatus.get(fid);
            // result????????????????????????????????????status, {0: ?????????, 1: ??????, 2: ??????, 3: ?????????????????????}
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP???????????????
            e.printStackTrace();
        } catch (JSONException e) {
            // json????????????
            e.printStackTrace();
        } catch (IOException e) {
            // ??????IO??????
            e.printStackTrace();
        }
    }

    public void sendVoiceNoticeByFid(String phoneNumber) {
        try {
            // Note: ??????fid??????`??????????????????`????????????????????????????????????
            //       ??????fid??????????????????????????????????????????????????????fid
            String fid = "c799d10a43ec109f02f2288ca3c85b79e7700c98.mp3";
            FileVoiceSender fvsender = new FileVoiceSender(appid, appkey);
            FileVoiceSenderResult result = fvsender.send("86", phoneNumber, fid, 2, "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP???????????????
            e.printStackTrace();
        } catch (JSONException e) {
            // json????????????
            e.printStackTrace();
        } catch (IOException e) {
            // ??????IO??????
            e.printStackTrace();
        }
    }

    public void sendVoiceByTemplate(String phoneNumber) {
        try {
            int templateId = 45221;
            String[] params = {"5678"};
            TtsVoiceSender tvsender = new TtsVoiceSender(appid, appkey);
            TtsVoiceSenderResult result = tvsender.send("86", phoneNumber,
                    templateId, params, 2, "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP???????????????
            e.printStackTrace();
        } catch (JSONException e) {
            // json????????????
            e.printStackTrace();
        } catch (IOException e) {
            // ??????IO??????
            e.printStackTrace();
        }
    }

    public void sendVoiceByProxy(Integer templateId, String phoneNumber) {
        try {
            // ??????????????????httpclient
            ProxyHTTPClient httpclient = new ProxyHTTPClient("127.0.0.1", 8080, "http");

            String[] params = {"5678"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey, httpclient);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    templateId, params, smsSign, "", "");  // ????????????????????????????????????????????????????????????????????????
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP???????????????
            e.printStackTrace();
        } catch (JSONException e) {
            // json????????????
            e.printStackTrace();
        } catch (IOException e) {
            // ??????IO??????
            e.printStackTrace();
        }
    }
}
