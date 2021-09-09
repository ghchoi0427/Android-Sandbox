package com.example.firebase210724.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClient {
    Socket socket;

    public SocketClient() {
        socket = new Socket();
    }

    public void startConnection(String address, int port) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(address, port));

            byte[] bytes;

            OutputStream output = socket.getOutputStream();
            String message = "test";
            bytes = message.getBytes("UTF-8");
            output.write(bytes);
            output.flush();

            InputStream input = socket.getInputStream();
            bytes = new byte[1024];
            int readByteCount = input.read(bytes);
            message = new String(bytes, 0, readByteCount, "UTF-8");

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


}
