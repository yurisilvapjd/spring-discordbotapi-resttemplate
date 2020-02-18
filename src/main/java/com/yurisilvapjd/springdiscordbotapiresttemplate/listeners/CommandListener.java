package com.yurisilvapjd.springdiscordbotapiresttemplate.listeners;


import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;
import com.yurisilvapjd.springdiscordbotapiresttemplate.entities.Track;
import com.yurisilvapjd.springdiscordbotapiresttemplate.errors.ErrorMessages;
import com.yurisilvapjd.springdiscordbotapiresttemplate.services.GoogleCloudTranslationService;
import com.yurisilvapjd.springdiscordbotapiresttemplate.services.HappiDevMusicService;
import com.yurisilvapjd.springdiscordbotapiresttemplate.services.WanakanaService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommandListener extends ListenerAdapter {

    @Autowired
    GoogleCloudTranslationService googleCloudTranslationService;

    @Autowired
    WanakanaService wanakanaService;

    @Autowired
    HappiDevMusicService happiDevMusicService;

    @Autowired
    ErrorMessages errorMessages;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String rawText = event.getMessage().getContentRaw();
        String command = rawText.split(" ")[0];
        String afterCommandText = rawText.replace(command + " ", "");

        if (command.equals("!commands")) {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Commands")
                    .setDescription("Open commands available")
                    .addField("!info", "Returns Gurren Larann's information.", false)
                    .addField("!translateJA [text]", "Returns the japanese translation of the given [text]", false)
                    .addField("!translateEN [text]", "Returns the english translation of the given [text]", false)
                    .addField("!findTracks [track name]", "Returns found track related to the given [track name]", false)
                    .addField("!getLyrics [artist_id] [album_id] [track_id]", "Return the selected track lyrics", false)
                    .setColor(0xFF4000)
                    .build()).queue();
        }

        if (command.equals("!info")) {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Greetings")
                    .setDescription("Kon'nichiwa, watashi wa Gurren Lagann desu.")
                    .setColor(0xFF4000)
                    .build()).queue();
        }

        if (command.equals("!translateJA")) {

            String hiragana = googleCloudTranslationService.toJapanese(afterCommandText);
            String romaji = wanakanaService._hiraganaToRomaji(hiragana);

            if(hiragana != null){
                event.getChannel().sendMessage(new EmbedBuilder()
                        .setTitle( "Japanese Translations" )
                        .addField("Hiragana", hiragana, false)
                        .addField("Romaji", romaji, false)
                        .setColor(0xFF4000)
                        .build()).queue();
            } else {
                event.getChannel().sendMessage(errorMessages.getInternalError()).queue();
            }

        }

        if (command.equals("!translateEN")) {

            String translatedText = googleCloudTranslationService.toEnglish(afterCommandText);

            if(translatedText != null){
                event.getChannel().sendMessage(new EmbedBuilder()
                        .setTitle( "English Translation" )
                        .addField("English", translatedText, false)
                        .setColor(0xFF4000)
                        .build()).queue();
            } else {
                event.getChannel().sendMessage(errorMessages.getInternalError()).queue();
            }
        }

        if (command.equals("!findTracks")) {

            List<Track> tracks = happiDevMusicService.findTracks(afterCommandText).getResult();

            if(tracks != null){
                if(tracks.size() > 0){
                    tracks.forEach(s -> event.getChannel().sendMessage(new EmbedBuilder()
                            .addField("Artist",s.getArtist(), true)
                            .addField("Album", s.getAlbum(), true)
                            .addField("Track", s.getTrack(), true)
                            .addField("Artist_id", String.valueOf(s.getId_artist()),true)
                            .addField("Album_id", String.valueOf(s.getId_album()), true)
                            .addField("Track_id", String.valueOf(s.getId_track()), true)
                            .addField("Has Lyrics", String.valueOf(s.getHaslyrics()), true)
                            .setColor(0xFF4000)
                            .build()).queue());
                } else {
                    event.getChannel().sendMessage(errorMessages.getResourceNotFound()).queue();
                }
            } else {
                event.getChannel().sendMessage(errorMessages.getInternalError()).queue();
            }
        }

        if (command.equals("!getLyrics")) {

            String artistId = afterCommandText.split(" ")[0];
            String albumId = afterCommandText.split(" ")[1];
            String trackId = afterCommandText.split(" ")[2];

            Track track = happiDevMusicService.getLyrics(artistId, albumId, trackId).getResult();

            if(track != null){
                event.getChannel().sendMessage(new EmbedBuilder()
                        .addField("Artist",track.getArtist(), true)
                        .addField("Album", track.getAlbum(), true)
                        .addField("Track", track.getTrack(), true)
                        .setColor(0xFF4000)
                        .build()).queue();
                event.getChannel().sendMessage(track.getLyrics()).queue();
            } else {
                event.getChannel().sendMessage(errorMessages.getInternalError()).queue();
            }
        }
    }
}
