package Server;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class tcp_server {

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

        // initializing socket and input streams
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(9127);
            System.out.println("Connected to server ");

            while (true) {
                // accepting client in port
                socket = serverSocket.accept();
                System.out.println("New client connected...:");

                DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
                ArrayList<Long> time = new ArrayList<Long>();
                for (int i = 0; i < imageJokesMemes.size(); i++) {

                    long m3start = System.currentTimeMillis();
                    BufferedImage image = ImageIO.read(new File(imageJokesMemes.get(i)));
                    ByteArrayOutputStream bytesToSend = new ByteArrayOutputStream() ;
                    ImageIO.write(image, "jpg",bytesToSend);
                    byte[]  numOfBytesPerImage = bytesToSend.toByteArray();
                    long m3end = System.currentTimeMillis();
                    time.add(m3end - m3start);
                    System.out.println("Time to send image: " + (m3end - m3start) + "ms"); //measurement 3


                    outToClient.writeInt(numOfBytesPerImage.length);
                    outToClient.write(numOfBytesPerImage);
                    outToClient.flush();
                }

                // create input stream attached to socket
                // BufferedReader fromClient = new BufferedReader(
                // new InputStreamReader(socket.getInputStream());

                // String messageFromClient = " ";
                // messageFromClient = fromClient.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//            if (messageFromClient.equals("BYE")) {
//                System.out.println("BYEEEE");
//                break;
//            }
//
//            if (messageFromClient.equals("Joke 1")) {
//                String blahBlah = Files.readString(Path.of("joke1.txt"));
//                jokeMessage = blahBlah + '\n';
//                outToClient.writeBytes(jokeMessage);
//                //out.println(jokeMessage);
//            }
//
//            if (messageFromClient.equals("Joke 2")) {
//                String blahBlah = Files.readString(Path.of("joke2.txt"));
//                jokeMessage = blahBlah + '\n';
//                outToClient.writeBytes(jokeMessage);
//            }
//
//            if (messageFromClient.equals("Joke 3")) {
//                String blahBlah = Files.readString(Path.of("joke3.txt"));
//                jokeMessage = blahBlah + '\n';
//                outToClient.writeBytes(jokeMessage);
//            }
//            else {
//                //System.out.println("ERROR: Not a valid command " + messageFromClient);
//                outToClient.writeBytes("ERROR: Not a valid command " + messageFromClient + '\n');
//
//            }
            //System.out.println("Client " + messageFromClient);


//        }
//
//    }
//        }
//}

