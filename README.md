# Meme-Server
A server with great quality memes that are sent through the server as the client logs on, include with stats

Here are the steps to use this code for my project:
Using the Linux environment I downloaded my code using Google Drive and used the Terminal on two separate computers lin113-20 and lin113-23.
For TCP Server, after using javac *.java  I use java tcp_server.java to run my TCP server.
For TCP Client I used java tcp_client lin113-20.cise.ufl.edu to test 

For UDP Server after using javac *.java  I use java udp_server.java to run my UDP server.
For UDP Client I used java udp_client lin113-20.cise.ufl.edu to test 

I noticed the difference of times in milliseconds that TCP ran faster compared to UDP on the Linux testing. 
For localhost testing the numbers are different it shows that performing Experiment #1 running on “localhost” makes the file transfer faster compared to Experiment #2 on Linux







For TCP Client and Server Code:
I used the code to create a random number generator and stored my images in an array list to keep track of the pictures of my server side. 
The images are read and their bytes are sent over the socket using an output stream. 
The server listens on port 9127 for incoming client connections, and when a client connects, it sends the images one by one using a loop. 
The TCP server side also includes the time measurements for the time spent reading it through  using an array to collect the measurements and then adding it back to my array list—this way it keeps it organized. 
The client measures and prints the time taken to connect to the server and the time taken to receive each image. The program creates a socket to connect to the server and gets the 10 images that I have collected. 
For each image, the program creates an input stream attached to the socket, reads the image data from the stream, saves the image data to a file then writes out the image . I have included the measures and the time taken to receive the image and store it in the ArrayList. After all, images are received, the program calculates and prints the statistics of the round-trip time, including minimum, maximum, mean, and standard deviation. Finally, the program closes the connection to the server.

For UDP Client and Server Code:
I used a similar approach to how my TCP was created for the UDP yet the differences are using 
a DatagramSocket object and resolving the IP address of the server, it sends a 1-byte datagram to the server to initiate a connection. 
I do have ImageIO a class that provides methods for reading and writing images in various formats, including JPEG yet, I am unclear on how it is not being used therefore I think the image data is saved to a file using 
Files.write(Path. of(filename), imageData) is what helps make the images printed to the console. 
It has similar array lists for the statistics for the minimum, maximum, mean, and standard deviation
