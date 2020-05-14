package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<Matches> mMatches;
    FirebaseAuth fAuth;
    DatabaseReference reference;
    private List<String> userList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fAuth = FirebaseAuth.getInstance();


        userList=new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Chats").child(fAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Chat chat=snapshot.getValue(Chat.class);

                    if(chat.getSender().equals(fAuth.getCurrentUser().getUid())){
                        userList.add(chat.getReceiver());
                    }
                    if(chat.getReceiver().equals(fAuth.getCurrentUser().getUid())){
                        userList.add(chat.getSender());
                    }

                }
                readChat();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    //method for retrieving messages
    private void readChat(){
        mMatches=new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("Matches").child(fAuth.getCurrentUser().getUid());
        reference.child("list").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mMatches.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Matches match = snapshot.getValue(Matches.class);
                    for (String id:userList){
                        if(match.getFullname().equals(id)){
                            if(mMatches.size()!=0) {
                                for (Matches match1 : mMatches) {
                                    if(!match.getFullname().equals(match1.getFullname())){
                                        mMatches.add(match);
                                    }
                                }
                            }else{
                                mMatches.add(match);
                            }
                        }
                    }
                }
                userAdapter =new UserAdapter(getContext(),mMatches,true);
                recyclerView.setAdapter(userAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
