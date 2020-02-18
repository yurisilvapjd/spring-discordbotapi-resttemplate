package com.yurisilvapjd.springdiscordbotapiresttemplate.components;

import com.yurisilvapjd.springdiscordbotapiresttemplate.listeners.CommandListener;
import com.yurisilvapjd.springdiscordbotapiresttemplate.utils.AuthBox;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;

@Component
public class DiscordBot {

    public static JDA jda;

    @Autowired
    CommandListener commandListener;

    @Autowired
    AuthBox authBox;

    public DiscordBot(CommandListener commandListener, AuthBox authBox) {
        this.commandListener = commandListener;
        this.authBox = authBox;
        this.start();
    }

    public void start() {

        try {
            jda = new JDABuilder(AccountType.BOT)
                    .setToken(authBox.getJDA_TOKEN())
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        jda.addEventListener(commandListener);
    }

}