package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import javax.imageio.ImageIO;

public class I {
  public static final ArrayList I(String... paramVarArgs) {
    String str1 = "inputEM";
    String str2 = "outputEM";
    byte b1 = 64;
    byte b2 = 64;
    String[] arrayOfString1 = { "Front", "Shiny Front", "Back", "Shiny Back", "Anim", "Shiny Anim" };
    int[] arrayOfInt = { 0, 1, 0, 1, 0, 1 };
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
        String str3 = str.replace(".png", "").trim();
        boolean[] arrayOfBoolean = new boolean[arrayOfString2.length];
        Arrays.fill(arrayOfBoolean, false);
        ArrayList[] arrayOfArrayList = new ArrayList[arrayOfString2.length];
        HashMap[] arrayOfHashMap1 = new HashMap[arrayOfString2.length];
        HashMap[] arrayOfHashMap2 = new HashMap[arrayOfString2.length];
        BufferedImage bufferedImage1 = null;
        try {
          bufferedImage1 = ImageIO.read(file);
        } catch (IOException iOException) {
          iOException.printStackTrace();
        } 
        BufferedImage bufferedImage2 = new BufferedImage(b1 * 6, b2, 1);
        Graphics2D graphics2D = (Graphics2D)bufferedImage2.getGraphics();
        for (byte b3 = 0; b3 < i; b3++) {
          switch (b3) {
            case 0:
            case 1:
            case 2:
            case 3:
              graphics2D.drawImage(bufferedImage1.getSubimage(b3 * b1, 0, b1, b2), b3 * b1, 0, (ImageObserver)null);
              break;
            case 4:
            case 5:
              graphics2D.drawImage(bufferedImage1.getSubimage((b3 - 4) * b1, b2, b1, b2), b3 * b1, 0, (ImageObserver)null);
              break;
          } 
        } 
        bufferedImage1 = bufferedImage2;
        BufferedImage[] arrayOfBufferedImage = new BufferedImage[i];
        int[][][] arrayOfInt1 = new int[i][][];
        byte b4;
        for (b4 = 0;; b4++) {
          int j;
          BufferedImage bufferedImage;
          ArrayList arrayList1;
          if (b4 < i) {
            j = arrayOfInt[b4];
            bufferedImage = bufferedImage1.getSubimage(b4 * b1, 0, b1, b2);
            if (!arrayOfBoolean[j]) {
              arrayOfBoolean[j] = true;
              BufferedImage bufferedImage3 = null;
              for (byte b = 0; b < i; b++) {
                if (b != b4 && arrayOfInt[b] == j)
                  bufferedImage3 = (bufferedImage3 == null) ? add(bufferedImage, bufferedImage1.getSubimage(b * b1, 0, b1, b2)) : add(bufferedImage3, bufferedImage1.getSubimage(b * b1, 0, b1, b2)); 
              } 
              bufferedImage3 = (bufferedImage3 == null) ? bufferedImage : bufferedImage3;
              ArrayList<Integer> arrayList2 = createGraphics(bufferedImage3);
              if (arrayList2.size() > 16) {
                arrayList.add("[ERROR] Palette is invalid for " + str3 + " (" + arrayOfString2[j] + ") Palette size: " + arrayList2.size() + " (Expected 16)");
              } else {
                while (arrayList2.size() < 16)
                  arrayList2.add(Integer.valueOf(0)); 
                arrayOfArrayList[j] = arrayList2;
              int[] arrayOfInt4 = delete(arrayList2);
              int[][] arrayOfInt5 = createCompatibleWritableRaster(bufferedImage, arrayList2, arrayOfHashMap1[j], arrayOfHashMap2[j]);
              arrayOfInt1[b4] = arrayOfInt5;

              byte[][] arrayOfByte1 = dispose(arrayOfInt4);
              IndexColorModel indexColorModel1 = new IndexColorModel(
                  4, arrayOfInt4.length, arrayOfByte1[0], arrayOfByte1[1], arrayOfByte1[2]);

              WritableRaster writableRaster1 =
                  indexColorModel1.createCompatibleWritableRaster(bufferedImage.getWidth(), bufferedImage.getHeight());

              for (int x = 0; x < arrayOfInt5.length; x++) {
                  for (int y = 0; y < arrayOfInt5[0].length; y++) {
                      writableRaster1.setDataElements(x, y, new byte[] { (byte) arrayOfInt5[x][y] });
                  }
              }

              arrayOfBufferedImage[b4] = new BufferedImage(
                  indexColorModel1,
                  writableRaster1,
                  indexColorModel1.isAlphaPremultiplied(),
                  null
              );
              } 
              continue;
            } 
            if (arrayOfArrayList[j] == null)
              continue; 
            arrayList1 = arrayOfArrayList[j];
          } else {
            break;
          } 
          int[] arrayOfInt2 = delete(arrayList1);
          int[][] arrayOfInt3 = createCompatibleWritableRaster(bufferedImage, arrayList1, arrayOfHashMap1[j], arrayOfHashMap2[j]);
          arrayOfInt1[b4] = arrayOfInt3;

          byte[][] arrayOfByte = dispose(arrayOfInt2);
          IndexColorModel indexColorModel = new IndexColorModel(
              4, arrayOfInt2.length, arrayOfByte[0], arrayOfByte[1], arrayOfByte[2]);

          WritableRaster writableRaster =
              indexColorModel.createCompatibleWritableRaster(bufferedImage.getWidth(), bufferedImage.getHeight());

          for (int x = 0; x < arrayOfInt3.length; x++) {
              for (int y = 0; y < arrayOfInt3[0].length; y++) {
                  writableRaster.setDataElements(x, y, new byte[] { (byte) arrayOfInt3[x][y] });
              }
          }

          arrayOfBufferedImage[b4] = new BufferedImage(
              indexColorModel,
              writableRaster,
              indexColorModel.isAlphaPremultiplied(),
              null
          );
        } 
        b4 = 0;
        for (byte b5 = 0; b5 < arrayOfString2.length; b5++) {
          if (arrayOfArrayList[b5] == null) {
            b4 = 1;
            break;
          } 
        } 
        if (b4 == 0) {
          ArrayList arrayList1 = arrayOfArrayList[0];
          int[] arrayOfInt2 = delete(arrayList1);
          int[][] arrayOfInt3 = arrayOfInt1[0];
          int[][] arrayOfInt4 = arrayOfInt1[4];
          int[][] arrayOfInt5 = new int[b2][2 * b1];
          byte b6;
          for (int y = 0; y < b2; y++) {
              for (int x = 0; x < b1; x++) {
                  arrayOfInt5[x][y] = arrayOfInt3[x][y];
              }
          }
          for (int y = 0; y < b2; y++) {
              for (int x = 0; x < b1; x++) {
                  arrayOfInt5[x][y + b2] = arrayOfInt4[x][y];
              }
          }
          byte[][] arrayOfByte1 = dispose(arrayOfInt2);
          IndexColorModel indexColorModel1 = new IndexColorModel(4, arrayOfInt2.length, arrayOfByte1[0], arrayOfByte1[1], arrayOfByte1[2]);
          WritableRaster writableRaster1 = indexColorModel1.createCompatibleWritableRaster(arrayOfInt5.length, (arrayOfInt5[0]).length);
          for (int x = 0; x < arrayOfInt5.length; x++) {
              for (int y = 0; y < arrayOfInt5[0].length; y++) {
                  writableRaster1.setDataElements(x, y, new byte[] { (byte) arrayOfInt5[x][y] });
              }
          }
          BufferedImage bufferedImage3 = new BufferedImage(indexColorModel1, writableRaster1, indexColorModel1.isAlphaPremultiplied(), null);
          ArrayList arrayList2 = arrayOfArrayList[1];
          int[] arrayOfInt6 = delete(arrayList2);
          int[][] arrayOfInt7 = arrayOfInt1[1];
          int[][] arrayOfInt8 = arrayOfInt1[5];
          int[][] arrayOfInt9 = new int[b2][2 * b1];
          byte b8;
          for (b8 = 0; b8 < b2; b8++) {
            for (byte b = 0; b < b1; b++)
              arrayOfInt9[b][b8] = arrayOfInt7[b][b8]; 
          } 
          for (b8 = 0; b8 < b2; b8++) {
            for (byte b = 0; b < b1; b++)
              arrayOfInt9[b][b8 + b2] = arrayOfInt8[b][b8]; 
          } 
          byte[][] arrayOfByte2 = dispose(arrayOfInt6);
          IndexColorModel indexColorModel2 = new IndexColorModel(4, arrayOfInt6.length, arrayOfByte2[0], arrayOfByte2[1], arrayOfByte2[2]);
          WritableRaster writableRaster2 = indexColorModel2.createCompatibleWritableRaster(arrayOfInt9.length, (arrayOfInt9[0]).length);
          for (int x = 0; x < arrayOfInt9.length; x++) {
              for (int y = 0; y < arrayOfInt9[0].length; y++) {
                  writableRaster2.setDataElements(x, y, new byte[] { (byte) arrayOfInt9[x][y] });
              }
          }
          BufferedImage bufferedImage4 = new BufferedImage(indexColorModel2, writableRaster2, indexColorModel2.isAlphaPremultiplied(), null);
          boolean bool1 = (ui.I[0].isSelected() && ui.I[1].isSelected()) ? true : false;
          boolean bool2 = (ui.I[2].isSelected() && ui.I[3].isSelected()) ? true : false;
          boolean bool3 = (ui.I[4].isSelected() && ui.I[5].isSelected()) ? true : false;
          if (ui.I[0].isSelected())
            containsKey(str2 + "/" + str3, (bool1 ? "regular_" : "") + "front", arrayOfBufferedImage[0]); 
          if (ui.I[1].isSelected())
            containsKey(str2 + "/" + str3, (bool1 ? "shiny_" : "") + "front", arrayOfBufferedImage[1]); 
          if (ui.I[2].isSelected())
            containsKey(str2 + "/" + str3, (bool2 ? "regular_" : "") + "back", arrayOfBufferedImage[2]); 
          if (ui.I[3].isSelected())
            containsKey(str2 + "/" + str3, (bool2 ? "shiny_" : "") + "back", arrayOfBufferedImage[3]); 
          if (ui.I[4].isSelected())
            containsKey(str2 + "/" + str3, (bool3 ? "regular_" : "") + "anim_front", bufferedImage3); 
          if (ui.I[5].isSelected())
            containsKey(str2 + "/" + str3, (bool3 ? "shiny_" : "") + "anim_front", bufferedImage4); 
          for (byte b10 = 0; b10 < arrayOfString2.length; b10++)
            append(str2 + "/" + str3, arrayOfString2[b10] + ".pal", arrayOfArrayList[b10]); 
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
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
  }
  
  public static final void containsKey(String paramString1, String paramString2, BufferedImage paramBufferedImage) {
    File file1 = new File(paramString1);
    if (!file1.exists())
      file1.mkdir(); 
    String str = paramString1 + "/" + paramString2 + ".png";
    File file2 = new File(str);
    try {
      ImageIO.write(paramBufferedImage, "png", file2);
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
  }
  
  public static final int[][] createCompatibleWritableRaster(BufferedImage paramBufferedImage, ArrayList paramArrayList, HashMap paramHashMap1, HashMap paramHashMap2) {
    int i = paramBufferedImage.getWidth();
    int j = paramBufferedImage.getHeight();
    int[][] arrayOfInt = new int[i][j];
    for (byte b = 0; b < i; b++) {
      for (byte b1 = 0; b1 < j; b1++) {
        int k = paramBufferedImage.getRGB(b, b1);
        if (paramHashMap1 != null && paramHashMap1.containsKey(Integer.valueOf(k))) {
          arrayOfInt[b][b1] = paramArrayList.indexOf(paramHashMap1.get(Integer.valueOf(k)));
        } else if (paramHashMap2 != null && paramHashMap2.containsKey(Integer.valueOf(k))) {
          arrayOfInt[b][b1] = paramArrayList.indexOf(paramHashMap2.get(Integer.valueOf(k)));
        } else {
          arrayOfInt[b][b1] = paramArrayList.indexOf(Integer.valueOf(k));
        } 
      } 
    } 
    return arrayOfInt;
  }
  
  public static final ArrayList<Integer> createGraphics(BufferedImage paramBufferedImage) {

    int i = paramBufferedImage.getWidth();
    int j = paramBufferedImage.getHeight();

    ArrayList<Integer> list = new ArrayList<>();

    for (int x = 0; x < i; x++) {
        for (int y = 0; y < j; y++) {
            list.add(paramBufferedImage.getRGB(x, y));
        }
    }

    return new ArrayList<>(new LinkedHashSet<>(list));
}
  
  public static final int[] delete(ArrayList paramArrayList) {
    int[] arrayOfInt = new int[paramArrayList.size()];
    for (byte b = 0; b < arrayOfInt.length; b++)
      arrayOfInt[b] = ((Integer)paramArrayList.get(b)).intValue(); 
    return arrayOfInt;
  }
  
  public static final byte[][] dispose(int[] paramArrayOfint) {
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