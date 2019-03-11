/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teknotera.testqrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Heru
 */
public class Main {

    public static void main(String[] args) throws WriterException, FileNotFoundException, IOException, NotFoundException, ChecksumException, FormatException {

        TestReadQR();
        TestWriteQR();
        
    }
    
    private static void TestReadQR() throws IOException, NotFoundException, ChecksumException, FormatException {
        File file = new File("3d721c7e-9e6d-4440-829b-cf80c6787bd6.jpg");
        BufferedImage image = ImageIO.read(file);
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		Binarizer binarizer = new HybridBinarizer(source); 
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);
        
        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(bitmap);
        System.out.println(result.getText());        
    }
    
    private static void TestWriteQR() throws WriterException, FileNotFoundException, IOException {
        
        int size = 200;
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode("Testing 1234567890", BarcodeFormat.QR_CODE, size, size);

        File file = new File("qrcode.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream stream = new FileOutputStream(file);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", stream);

        stream.flush();
        stream.close();
        
    }
}
