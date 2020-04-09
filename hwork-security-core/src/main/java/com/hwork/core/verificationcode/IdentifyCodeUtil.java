package com.hwork.core.verificationcode;

import lombok.Data;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

@Data
public class IdentifyCodeUtil {

    /**
     * 验证码图片宽度
     */
    private int width = 120;
    /**
     * 验证码图片高度
     */
    private int height = 40;

    /**
     * 随机数
     */
    private Random random = new Random();

    /**
     * 生成随机颜色
     * @param fc
     * @param bc
     * @return
     */
    public Color getRandomColor(int fc,int bc){
        if(fc > 255){
            fc = 200;
        }
        if(bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }

    /**
     * 添加干扰线
     * @param graphics2D
     * @param nums
     */
    public void randomLine(Graphics2D graphics2D,int nums){
        graphics2D.setColor(this.getRandomColor(160,200));
        for(int i=0;i<nums;i++){
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(12);
            int y2 = random.nextInt(12);
            graphics2D.drawLine(x1,y1,x2,y2);
        }
    }

    /**
     *  输出验证码
     * @param length
     * @param g
     * @return
     */
    public String drawRandomString(int length,Graphics2D g){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = "";
        int itmp = 0;
        for(int i=0;i<length;i++){
            switch (random.nextInt(length)){
                case 1:
                    itmp = random.nextInt(26) + 65;//A--Z
                    temp = String.valueOf((char)itmp);
                    break;
                case 2:
                    itmp = random.nextInt(26) + 97;
                    temp = String.valueOf((char)itmp);
                    break;
                default:
                    itmp  = random.nextInt(10) + 48;
                    temp = String.valueOf((char)itmp);
                    break;
            }
            Color color = new Color(20+random.nextInt(20),20+random.nextInt(20),20+random.nextInt(20));
            g.setColor(color);
            AffineTransform trans = new AffineTransform();
            trans.rotate(random.nextInt(45)*3.14/180,15*i+8,7);

            float scaleSize = random.nextFloat() + 0.8f;
            if(scaleSize > 1f){
                scaleSize = 1f;
            }
            trans.scale(scaleSize,scaleSize);
            g.setTransform(trans);
            g.drawString(temp,15*i+18,18);

            stringBuffer.append(temp);
        }
        g.dispose();
        return stringBuffer.toString();
    }


    public static void main(String[] a){
        IdentifyCodeUtil idCode = new IdentifyCodeUtil();
        BufferedImage image =new BufferedImage(idCode.getWidth() , idCode.getHeight() , BufferedImage.TYPE_INT_BGR) ;
        Graphics2D g = image.createGraphics() ;
        idCode.randomLine(g,160);
        System.out.print(idCode.drawRandomString(4,g));
    }
}
