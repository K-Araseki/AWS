package com.example.wsbp.page;

import com.example.wsbp.service.IChatService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import org.apache.wicket.model.Model;

import org.apache.wicket.spring.injection.annot.SpringBean;

@MountPath("Chat")

public class ChatPage extends WebPage{

    //IChatService を IoC/DI する
    @SpringBean
    private IChatService chatService;

    public ChatPage(IModel<String> userNameModel) {
        //var userNameModel = Model.of("");
        var userNameLabel = new Label("userName", userNameModel);
        add(userNameLabel);

        var msgBodyModel = Model.of("");

        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);

        /*
        var userInfoForm = new Form<>("userInfo");
        add(userInfoForm);
        */

        //配置したFormコンポーネントを匿名クラス化して処理を上書きする
        var chatInfoForm = new Form<>("chatInfo") {
            @Override
            protected void onSubmit() {
                var userName = userNameModel.getObject();
                var msgBody = msgBodyModel.getObject();
                var msg = "送信データ："
                        + userName
                        + ","
                        + msgBody;
                System.out.println(msg);
                // IoC/DI した chatService のメソッドを呼び出す
                chatService.registerChat(userName, msgBody);
                setResponsePage(new ChatCompPage(userNameModel));
            }
        };
        add(chatInfoForm);

        var msgBodyField = new TextField("msgBody", msgBodyModel);
        chatInfoForm.add(msgBodyField);

    }
}
