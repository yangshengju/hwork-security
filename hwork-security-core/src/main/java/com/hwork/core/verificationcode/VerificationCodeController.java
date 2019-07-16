package com.hwork.core.verificationcode;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/verificationCode")
public class VerificationCodeController {
    static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/generateImageCode")
    public void createCode(HttpServletRequest request, HttpServletResponse response) {
        ImageCode imageCode = createImageCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        try {
            ImageIO.write(imageCode.getImage(),"JPG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImageCode createImageCode(HttpServletRequest request) {
        try {
            IdentifyCodeUtil idCode = new IdentifyCodeUtil();
            BufferedImage image = new BufferedImage(idCode.getWidth(), idCode.getHeight(),
                    BufferedImage.TYPE_INT_BGR);
            Graphics2D g = image.createGraphics();
            //定义字体样式
            Font myFont = new Font("黑体", Font.BOLD, 24);
            //设置字体
            g.setFont(myFont);

            g.setColor(idCode.getRandomColor(200, 250));
            g.fillRect(0, 0, idCode.getWidth(), idCode.getHeight());
            g.setColor(idCode.getRandomColor(180, 200));
            idCode.randomLine(g, 160);
            String checkCode = idCode.drawRandomString(4, g);
            return new ImageCode(image,checkCode,60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Color getRandColor(int fc, int bc) {
        // 取其随机颜色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
