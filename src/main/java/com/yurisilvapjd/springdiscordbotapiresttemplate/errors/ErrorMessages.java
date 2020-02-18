package com.yurisilvapjd.springdiscordbotapiresttemplate.errors;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ErrorMessages {


    public MessageEmbed getInternalError(){
        return new EmbedBuilder()
                .setTitle( "Internal Error" )
                .addField("Message","An internal error occured. Try again latter. ", false)
                .setColor(0xFF4000)
                .build();
    }

    public MessageEmbed getResourceNotFound(){
        return new EmbedBuilder()
                .setTitle( "Resource Not Found" )
                .addField("Message","No resource found.", false)
                .setColor(0xFF4000)
                .build();
    }
}
