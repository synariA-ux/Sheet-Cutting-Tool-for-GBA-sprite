package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import javax.imageio.ImageIO;

public class Z {
  public static final ArrayList I(String... paramVarArgs) {
    String str1 = "inputFR";
    String str2 = "outputFR";
    byte b1 = 64;
    byte b2 = 64;
    String[] arrayOfString1 = { "Front", "Shiny Front", "Back", "Shiny Back" };
    int[] arrayOfInt = { 0, 1, 0, 1 };
    String[] arrayOfString2 = { "Normal", "Shiny" };
    ArrayList<String> arrayList = new ArrayList();
    int i = arrayOfString1.length;
    File file1 = new File(str1);
    if (!file1.exists() || !file1.isDirectory()) {
      arrayList.add("[ERROR] the directory \"" + str1 + "\" does not exist!");
      return arrayList;
    } 
    File[] arrayOfFile1 = file1.listFiles();
    if (arrayOfFile1.length == 0) {
      arrayList.add("[ERROR] the directory \"" + str1 + "\" does not contain any files!");
      return arrayList;
    } 
    File file2 = new File(str2);
    if (!file2.exists()) {
      if (!file2.mkdir()) {
        arrayList.add("[ERROR] an error occurred trying to create the output folder \"" + str2 + "\".");
        return arrayList;
      } 
    } else {
      arrayList.add("[INFO] output folder exists, but contains files, deleting them now.");
      for (File file : file2.listFiles()) {
        if (file.isDirectory())
          for (File file3 : file.listFiles())
            file3.delete();  
        file.delete();
      } 
    } 
    file2 = new File(str2);
    File[] arrayOfFile2 = file2.listFiles();
    if (arrayOfFile2.length != 0) {
      arrayList.add("[ERROR] there are files in the output folder, even though there shouldn't be!");
      return arrayList;
    } 
    for (File file : arrayOfFile1) {
      String str = file.getName();
      if (!str.endsWith(".png")) {
        arrayList.add("[ERROR] unexpected file! (\"" + str + "\") continueing on...");
      } else {
        int j = Integer.parseInt(str.replace(".png", "").trim());
        boolean[] arrayOfBoolean = new boolean[arrayOfString2.length];
        Arrays.fill(arrayOfBoolean, false);
        ArrayList[] arrayOfArrayList = new ArrayList[arrayOfString2.length];
        BufferedImage bufferedImage = null;
        try {
          bufferedImage = ImageIO.read(file);
        } catch (Exception iOException) {
          iOException.printStackTrace();
        } 
        BufferedImage[] arrayOfBufferedImage = new BufferedImage[i];
        int[][][] arrayOfInt1 = new int[i][][];
        for (byte b3 = 0;; b3++) {
          BufferedImage bufferedImage1;
          ArrayList arrayList1;
          if (b3 < i) {
            int k = arrayOfInt[b3];
            bufferedImage1 = bufferedImage.getSubimage(b3 * b1, 0, b1, b2);
            if (!arrayOfBoolean[k]) {
              arrayOfBoolean[k] = true;
              BufferedImage bufferedImage2 = null;
              for (byte b = 0; b < i; b++) {
                if (b != b3 && arrayOfInt[b] == k)
                  bufferedImage2 = (bufferedImage2 == null) ? add(bufferedImage1, bufferedImage.getSubimage(b * b1, 0, b1, b2)) : add(bufferedImage2, bufferedImage.getSubimage(b * b1, 0, b1, b2)); 
              } 
              bufferedImage2 = (bufferedImage2 == null) ? bufferedImage1 : bufferedImage2;
              ArrayList<Integer> arrayList2 = delete(bufferedImage2);
              if (arrayList2.size() > 16) {
                arrayList.add("[ERROR] Palette is invalid for " + j + " (" + arrayOfString2[k] + ") Palette size: " + arrayList2.size() + " (Expected 16)");
              } else {
                while (arrayList2.size() < 16)
                  arrayList2.add(Integer.valueOf(0)); 
                arrayOfArrayList[k] = arrayList2;
                int[] arrayOfInt4 = dispose(arrayList2);
                int[][] arrayOfInt5 = createGraphics(bufferedImage1, arrayList2);
                arrayOfInt1[b3] = arrayOfInt5;
                byte[][] arrayOfByte1 = drawImage(arrayOfInt4);
                IndexColorModel indexColorModel1 = new IndexColorModel(4, arrayOfInt4.length, arrayOfByte1[0], arrayOfByte1[1], arrayOfByte1[2]);
                WritableRaster writableRaster1 = indexColorModel1.createCompatibleWritableRaster(bufferedImage1.getWidth(), bufferedImage1.getHeight());
                boolean bool2 = false;
              } 
              continue;
            } 
            if (arrayOfArrayList[k] == null)
              continue; 
            arrayList1 = arrayOfArrayList[k];
          } else {
            break;
          } 
          int[] arrayOfInt2 = dispose(arrayList1);
          int[][] arrayOfInt3 = createGraphics(bufferedImage1, arrayList1);
          arrayOfInt1[b3] = arrayOfInt3;
          byte[][] arrayOfByte = drawImage(arrayOfInt2);
          IndexColorModel indexColorModel = new IndexColorModel(4, arrayOfInt2.length, arrayOfByte[0], arrayOfByte[1], arrayOfByte[2]);
          WritableRaster writableRaster = indexColorModel.createCompatibleWritableRaster(bufferedImage1.getWidth(), bufferedImage1.getHeight());
          boolean bool1 = false;
        } 
        String str3 = String.format("%03d", new Object[] { Integer.valueOf(j) });
        boolean bool = false;
        for (byte b4 = 0; b4 < arrayOfString2.length; b4++) {
          if (arrayOfArrayList[b4] == null) {
            bool = true;
            break;
          } 
        } 
        if (!bool) {
          ArrayList arrayList1 = arrayOfArrayList[0];
          int[] arrayOfInt2 = dispose(arrayList1);
          int[][] arrayOfInt3 = arrayOfInt1[0];
          int[][] arrayOfInt4 = arrayOfInt1[0];
          int[][] arrayOfInt5 = new int[b2][2 * b1];
          byte b5;
          for (b5 = 0; b5 < b2; b5++) {
            for (byte b = 0; b < b1; b++)
              arrayOfInt5[b][b5] = arrayOfInt3[b][b5]; 
          } 
          for (b5 = 0; b5 < b2; b5++) {
            for (byte b = 0; b < b1; b++)
              arrayOfInt5[b][b5 + b2] = arrayOfInt4[b][b5]; 
          } 
          byte[][] arrayOfByte1 = drawImage(arrayOfInt2);
          IndexColorModel indexColorModel1 = new IndexColorModel(4, arrayOfInt2.length, arrayOfByte1[0], arrayOfByte1[1], arrayOfByte1[2]);
          WritableRaster writableRaster1 = indexColorModel1.createCompatibleWritableRaster(arrayOfInt5.length, (arrayOfInt5[0]).length);
          for (byte b6 = 0; b6 < arrayOfInt5.length; b6++) {
            for (byte b = 0; b < (arrayOfInt5[0]).length; b++) {
              writableRaster1.setDataElements(b6, b, new byte[] { (byte)arrayOfInt5[b6][b] });
            } 
          } 
          BufferedImage bufferedImage1 = new BufferedImage(indexColorModel1, writableRaster1, indexColorModel1.isAlphaPremultiplied(), null);
          ArrayList arrayList2 = arrayOfArrayList[1];
          int[] arrayOfInt6 = dispose(arrayList2);
          int[][] arrayOfInt7 = arrayOfInt1[1];
          int[][] arrayOfInt8 = arrayOfInt1[1];
          int[][] arrayOfInt9 = new int[b2][2 * b1];
          byte b7;
          for (b7 = 0; b7 < b2; b7++) {
            for (byte b = 0; b < b1; b++)
              arrayOfInt9[b][b7] = arrayOfInt7[b][b7]; 
          } 
          for (b7 = 0; b7 < b2; b7++) {
            for (byte b = 0; b < b1; b++)
              arrayOfInt9[b][b7 + b2] = arrayOfInt8[b][b7]; 
          } 
          byte[][] arrayOfByte2 = drawImage(arrayOfInt6);
          IndexColorModel indexColorModel2 = new IndexColorModel(4, arrayOfInt6.length, arrayOfByte2[0], arrayOfByte2[1], arrayOfByte2[2]);
          WritableRaster writableRaster2 = indexColorModel2.createCompatibleWritableRaster(arrayOfInt9.length, (arrayOfInt9[0]).length);
          for (byte b8 = 0; b8 < arrayOfInt9.length; b8++) {
            for (byte b = 0; b < (arrayOfInt9[0]).length; b++) {
              writableRaster2.setDataElements(b8, b, new byte[] { (byte)arrayOfInt9[b8][b] });
            } 
          } 
          BufferedImage bufferedImage2 = new BufferedImage(indexColorModel2, writableRaster2, indexColorModel2.isAlphaPremultiplied(), null);
          boolean bool1 = (ui.Z[0].isSelected() && ui.Z[1].isSelected()) ? true : false;
          boolean bool2 = (ui.Z[2].isSelected() && ui.Z[3].isSelected()) ? true : false;
          boolean bool3 = (ui.Z[4].isSelected() && ui.Z[5].isSelected()) ? true : false;
          if (ui.Z[0].isSelected())
            createCompatibleWritableRaster(str2 + "/" + str3, (bool1 ? "regular_" : "") + "front", arrayOfBufferedImage[0]); 
          if (ui.Z[1].isSelected())
            createCompatibleWritableRaster(str2 + "/" + str3, (bool1 ? "shiny_" : "") + "front", arrayOfBufferedImage[1]); 
          if (ui.Z[2].isSelected())
            createCompatibleWritableRaster(str2 + "/" + str3, (bool2 ? "regular_" : "") + "back", arrayOfBufferedImage[2]); 
          if (ui.Z[3].isSelected())
            createCompatibleWritableRaster(str2 + "/" + str3, (bool2 ? "shiny_" : "") + "back", arrayOfBufferedImage[3]); 
          if (ui.Z[4].isSelected())
            createCompatibleWritableRaster(str2 + "/" + str3, (bool3 ? "regular_" : "") + "anim_front", bufferedImage1); 
          if (ui.Z[5].isSelected())
            createCompatibleWritableRaster(str2 + "/" + str3, (bool3 ? "shiny_" : "") + "anim_front", bufferedImage2); 
          for (byte b9 = 0; b9 < arrayOfString2.length; b9++)
            append(str2 + "/" + str3, arrayOfString2[b9] + ".pal", arrayOfArrayList[b9]); 
        } 
      } 
    } 
    arrayList.add("Sprite splitting done.");
    return arrayList;
  }
  
  public static final BufferedImage add(BufferedImage paramBufferedImage1, BufferedImage paramBufferedImage2) {
    BufferedImage bufferedImage = new BufferedImage(paramBufferedImage1.getWidth(), paramBufferedImage2.getHeight() + paramBufferedImage1.getHeight(), paramBufferedImage1.getType());
    Graphics2D graphics2D = bufferedImage.createGraphics();
    graphics2D.drawImage(paramBufferedImage1, 0, 0, (ImageObserver)null);
    graphics2D.drawImage(paramBufferedImage2, 0, paramBufferedImage1.getHeight(), (ImageObserver)null);
    graphics2D.dispose();
    return bufferedImage;
  }
  
  public static final void append(String paramString1, String paramString2, ArrayList<Integer> paramArrayList) {
    File file = new File(paramString1);
    if (!file.exists())
      file.mkdir(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("JASC-PAL\r\n0100\r\n").append(paramArrayList.size()).append("\r\n");
    for (Integer integer : paramArrayList) {
      Color color = new Color(integer.intValue());
      stringBuilder.append(color.getRed() - color.getRed() % 4).append(" ").append(color.getGreen() - color.getGreen() % 4).append(" ").append(color.getBlue() - color.getBlue() % 4).append("\r\n");
    } 
    try {
      Files.write(Paths.get(paramString1 + "/" + paramString2, new String[0]), stringBuilder.toString().getBytes(), new java.nio.file.OpenOption[0]);
    } catch (Exception iOException) {
      iOException.printStackTrace();
    } 
  }
  
  public static final void createCompatibleWritableRaster(String paramString1, String paramString2, BufferedImage paramBufferedImage) {
    File file1 = new File(paramString1);
    if (!file1.exists())
      file1.mkdir(); 
    String str = paramString1 + "/" + paramString2 + ".png";
    File file2 = new File(str);
    try {
      ImageIO.write(paramBufferedImage, "png", file2);
    } catch (Exception iOException) {
      iOException.printStackTrace();
    } 
  }
  
  public static final int[][] createGraphics(BufferedImage paramBufferedImage, ArrayList paramArrayList) {
    int i = paramBufferedImage.getWidth();
    int j = paramBufferedImage.getHeight();
    int[][] arrayOfInt = new int[i][j];
    for (byte b = 0; b < i; b++) {
      for (byte b1 = 0; b1 < j; b1++)
        arrayOfInt[b][b1] = paramArrayList.indexOf(Integer.valueOf(paramBufferedImage.getRGB(b, b1))); 
    } 
    return arrayOfInt;
  }
  
  public static final ArrayList<Integer> delete(BufferedImage paramBufferedImage) {

    int i = paramBufferedImage.getWidth();
    int j = paramBufferedImage.getHeight();

    ArrayList<Integer> list = new ArrayList<>();

    for (byte b = 0; b < i; b++) {
        for (byte b1 = 0; b1 < j; b1++) {
            list.add(paramBufferedImage.getRGB(b, b1));
        }
    }

    return new ArrayList<>(new LinkedHashSet<>(list));
}
  
  public static final int[] dispose(ArrayList paramArrayList) {
    int[] arrayOfInt = new int[paramArrayList.size()];
    for (byte b = 0; b < arrayOfInt.length; b++)
      arrayOfInt[b] = ((Integer)paramArrayList.get(b)).intValue(); 
    return arrayOfInt;
  }
  
  public static final byte[][] drawImage(int[] paramArrayOfint) {
    byte[] arrayOfByte1 = new byte[paramArrayOfint.length];
    byte[] arrayOfByte2 = new byte[paramArrayOfint.length];
    byte[] arrayOfByte3 = new byte[paramArrayOfint.length];
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      Color color = new Color(paramArrayOfint[b]);
      arrayOfByte1[b] = (byte)(color.getRed() - color.getRed() % 4);
      arrayOfByte2[b] = (byte)(color.getGreen() - color.getGreen() % 4);
      arrayOfByte3[b] = (byte)(color.getBlue() - color.getBlue() % 4);
    } 
    return new byte[][] { arrayOfByte1, arrayOfByte2, arrayOfByte3 };
  }
}
