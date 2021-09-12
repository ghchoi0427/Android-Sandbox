package com.example.firebase210724;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase210724.util.SocketClient;

import java.net.Socket;

public class SocketClientActivity extends AppCompatActivity {

    Button btnSocketSend;
    ImageView imgSocketClient;
    EditText editClientMessage;
    TextView textClientDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);

        btnSocketSend = findViewById(R.id.btn_socket_client_send);
        imgSocketClient = findViewById(R.id.image_socket_client);
        editClientMessage = findViewById(R.id.edit_client_message);
        textClientDialog = findViewById(R.id.txt_client_dialog);

        SocketClient client = new SocketClient();
        client.startConnection("192.168.0.47",9999);
        Socket clientSocket = client.socket;

        btnSocketSend.setOnClickListener(view -> {
            //new Thread(() -> client.getImage("192.168.0.47", 9999, imgSocketClient)).start();
            new Thread(() -> client.getImage("192.168.0.47", 9999, imgSocketClient)).start();
        });
    }
}
