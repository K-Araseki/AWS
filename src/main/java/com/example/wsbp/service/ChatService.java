package com.example.wsbp.service;

import com.example.wsbp.data.Chat;
import com.example.wsbp.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements IChatService{

    private IChatRepository chatRepos;

    @Autowired
    public ChatService(IChatRepository chatRepos) {
        this.chatRepos = chatRepos;
    }

    @Override
    public void registerChat(String userName, String msgBody) {
        int n = chatRepos.insert(userName, msgBody);
        System.out.println("記録行数：" + n);
    }

    @Override
    public List<Chat> findChat() {
        var users = chatRepos.find();
        System.out.println("データ件数：" + users.size());
        return users;
    }

}
