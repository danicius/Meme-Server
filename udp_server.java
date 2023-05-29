package Server;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class udp_server {

    public static void main(String[] args) throws IOException {
        List<String> imageJokesMemes = new ArrayList<String>();
        imageJokesMemes.add("image1.jpeg");
        imageJokesMemes.add("image2.jpeg");
        imageJokesMemes.add("image3.jpeg");
        imageJokesMemes.add("image4.jpeg");
        imageJokesMemes.add("image5.jpeg");
        imageJokesMemes.add("image6.jpeg");
        imageJokesMemes.add("image7.jpeg");
        imageJokesMemes.add("image8.jpeg");
        imageJokesMemes.add("image9.jpeg");
        imageJokesMemes.add("image10.jpeg");

        // Shuffle the list using a random-number generator
        Collections.shuffle(imageJokesMemes, new Random());
        List<String> randomImageJokesMemes = imageJokesMemes.subList(1, 10);

        DatagramSocket socket = null;
        DatagramPacket packet = null;
        byte[] buffer = null;
        //third measurement
        ArrayList<Long> time = new ArrayList<Long>();


        try {
            socket = new DatagramSocket(9127);
            System.out.println("Connected to server ");

            while (true) {
                // Receive packet from client
                buffer = new byte[65508]; // Maximum UDP packet size//
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.out.println("New client connected...");

                InetAddress clientAddress = packet.getAddress(); //
                int clientPort = packet.getPort();

                for (int i = 0; i < imageJokesMemes.size(); i++) {
                    long m3start = System.currentTimeMillis();
                    BufferedImage image = ImageIO.read(new File(imageJokesMemes.get(i)));
                    ByteArrayOutputStream bytesToSend = new ByteArrayOutputStream() ;
                    long m3end = System.currentTimeMillis();
                    ImageIO.write(image, "jpg",bytesToSend);
                    byte[]  numOfBytesPerImage = bytesToSend.toByteArray();
                    time.add(m3end - m3start);
                    System.out.println("Time to read image: " + (m3end - m3start) + "ms"); L

                    // Send packet to client
                    packet = new DatagramPacket(numOfBytesPerImage, numOfBytesPerImage.length, clientAddress, clientPort);
                    socket.send(packet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
