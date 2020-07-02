package graph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

/**
 * @author yangzifeng
 */
public class Cert {

    static void drawCert() throws IOException {

        String backgroundPath = "/Users/yangzifeng/Downloads/cert-background.png";
        String avatarUrl = "/Users/yangzifeng/Downloads/viewphoto.jpeg";
        String talentName = "杨子锋";
        String idNumber = "110101192006297236";
        String certificateNum = "UE20080929001";
        String certificateName = "云计算架构师";
        String certificateLevel = "T-8";
        String certificateEnglishName = "Cloud computing architect";
        String certificateEnglishLevel = "T-8";
        String text = String.format("根据《工业和信息化人才岗位能力评价通则》，经评定，该同志达到%s(%s)水平，并纳入工业和信息化人才数据库，特此发证。", certificateName, certificateLevel);
        String english = String.format("According to General Rules on Position Competency Assessment of Industry and Information Technology Talents, the candidate is evaluated and qualified as Junior (%s) %s. The candidate is listed in the database of Industrial and Information Technology Talents. This certificate is hereby awarded.", certificateEnglishLevel, certificateEnglishName);
        String certificateDate = LocalDate.now().toString();

        //创建图片对象
        BufferedImage image = ImageIO.read(new File(backgroundPath));
        BufferedImage avatar = ImageIO.read(new File(avatarUrl));
        //基于图片对象打开绘图
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.BLACK);

        graphics.drawImage(avatar, 248, 276, null);

        Font font = new Font("Adobe 黑体 Std", Font.PLAIN, 21);
        graphics.setFont(font);
        graphics.drawString(talentName, 260, 534);
        graphics.drawString(idNumber, 260, 617);
        graphics.drawString(certificateNum, 260, 701);

        int x = 644, y = 100, widthLength = 450, lineWidth = 10;
        font = new Font("Adobe 黑体 Std", Font.PLAIN, 24);
        graphics.setFont(font);
        int next = drawString(graphics, font, text, "zh", x, y, widthLength, lineWidth);

        widthLength = 400;
        font = new Font("Times New Roman", Font.ITALIC, 22);
        drawString(graphics, font, english, "en", x, next + 10, widthLength, lineWidth);

        font = new Font("Adobe 黑体 Std", Font.PLAIN, 18);
        graphics.setFont(font);
        graphics.drawString(certificateDate, 860, 714);

        graphics.dispose();
        //将绘制好的图片写入到图片
        ImageIO.write(image, "jpg", new File("/Users/yangzifeng/Downloads/cert.jpg"));
    }

    /**
     * 文字超出限定长度自动换行
     *
     * @param graphics    画布
     * @param font        字体样式
     * @param text        文字
     * @param x           文字位置坐标  x
     * @param y           文字位置坐标 Y
     * @param widthLength 最大长度  （多少长度后需要换行）
     * @param lineWidth   行间距，每次换行偏移多少pt
     * @return y
     */
    private static int drawString(Graphics2D graphics, Font font, String text, String language, int x, int y, int widthLength, int lineWidth) {
        FontMetrics fg = graphics.getFontMetrics(font);
        List<String> ls = new ArrayList<>(16);
        if (Locale.CHINESE.toString().equals(language)) {
            getChineseListText(fg, text, widthLength, ls);
        }
        if (Locale.ENGLISH.toString().equals(language)) {
            // TODO 根据字体宽度和widthLength计算每行的字符数
            widthLength = 37;
            drawEnglishListText(fg, text, widthLength, ls);
        }
        for (int i = 0; i < ls.size(); i++) {
            if (i == 0) {
                graphics.drawString(ls.get(i), x, y);
            } else {
                graphics.drawString(ls.get(i), x, y += (lineWidth + fg.getHeight()));
            }
        }
        return y + fg.getHeight();
    }

    /**
     * 递归 切割字符串
     *
     * @param fg          fg
     * @param text        test
     * @param widthLength 每行的长度限制
     * @param ls          string list
     */
    private static void getChineseListText(FontMetrics fg, String text, int widthLength, List<String> ls) {
        String ba = text;
        boolean b = true;
        int i = 1;
        while (b) {
            if (fg.stringWidth(text) > widthLength) {
                text = text.substring(0, text.length() - 1);
                i++;
            } else {
                b = false;
            }
        }
        if (i != 1) {
            ls.add(ba.substring(0, ba.length() - i));
            getChineseListText(fg, ba.substring(ba.length() - i), widthLength, ls);
        } else {
            ls.add(text);
        }
    }

    private static void drawEnglishListText(FontMetrics fg, String text, int widthLength, List<String> ls) {
        int i = 0;
        int j = widthLength / fg.getFont().getSize();
        j = widthLength;
        StringBuilder sb = new StringBuilder(text);
        while (i + j < sb.length() && (i = sb.lastIndexOf(" ", i + j)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        String[] strings = sb.toString().split("\n");
        ls.addAll(Arrays.asList(strings));
    }

    public static void main(String[] args) throws IOException {
        drawCert();
    }

}
