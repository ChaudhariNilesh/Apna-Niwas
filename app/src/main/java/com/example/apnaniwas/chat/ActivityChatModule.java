package com.example.apnaniwas.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.apnaniwas.R;
import com.example.apnaniwas.login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityChatModule extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference dbReff;
    //private String mChatUser;
    private String mCurrentUserId;

    EditText msg;
    ImageButton btnSend;
    int increId = 101;

    private  final List<Message> ChatList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_module);

        //Firebase authentication
        mAuth = FirebaseAuth.getInstance();
        dbReff = FirebaseDatabase.getInstance().getReference();
        //mCurrentUserId = mAuth.getCurrentUser().getUid();

        btnSend = findViewById(R.id.btn_send_msg);
        msg = findViewById(R.id.edit_Text);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        loadMessages();

        //Recycler Integration
        chatAdapter = new ChatAdapter(ChatList);

        recyclerView = findViewById(R.id.rv_messages);
        //linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        //recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chatAdapter);

    }

    private void loadMessages() {

        dbReff.child("Chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Message newMessage = snapshot.getValue(Message.class);
                ChatList.add(newMessage);
                chatAdapter.notifyDataSetChanged();

                recyclerView.scrollToPosition(ChatList.size() -1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void sendMessage() {

        String curMsg = msg.getText().toString();

        if (!TextUtils.isEmpty(curMsg)) {

            Map data = new HashMap();
            data.put("id",increId);
            data.put("name","Nikul");
            data.put("message", curMsg);
            data.put("time", ServerValue.TIMESTAMP);

            String key  = dbReff.child("Chat").push().getKey();
            Toast.makeText(this, "Suceess " + key, Toast.LENGTH_SHORT).show();

            Map childUpdate = new HashMap();
            childUpdate.put("Chat"+ "/" + key, data);

            msg.setText("");

            dbReff.updateChildren(childUpdate, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if(error != null) {
                        Log.d("CHAT_LOG", error.getMessage().toString());
                    }
                }
            });

            increId++;
        }

    }
}
