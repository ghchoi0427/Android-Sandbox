package com.example.firebase210724.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClient {
    public Socket socket;
    final int BUFFER_SIZE = 1024;

    public SocketClient() {
        socket = new Socket();
    }

    public void startConnection(String address, int port, TextView textView) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(address, port));

            ExecutorService reader = Executors.newCachedThreadPool();

            reader.execute(() -> {
                try {
                    readMessage(socket, textView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (socket.isClosed()) {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getImage(String address, int port, ImageView imageView) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(address, port));

            byte[] bytes;

            OutputStream output = socket.getOutputStream();
            String message = "get image";
            bytes = message.getBytes("UTF-8");
            output.write(bytes);
            output.flush();

            InputStream input = socket.getInputStream();
            bytes = new byte[57641];
            input.read(bytes, 0, bytes.length);

            Log.d("tester", new String(bytes, StandardCharsets.UTF_8));
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            imageView.setImageBitmap(bmp);

            output.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (socket.isClosed()) {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(Socket socket, String message) {
        OutputStream outputStream;
        try {
            outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage(Socket socket, TextView textView) throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        while (socket.getInputStream().read(bytes, 0, bytes.length) != -1) {
            textView.append(new String(bytes));
            bytes = new byte[BUFFER_SIZE];
        }
    }

    public void closeSocket() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
