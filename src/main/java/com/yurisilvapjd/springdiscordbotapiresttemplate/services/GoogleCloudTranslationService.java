package com.yurisilvapjd.springdiscordbotapiresttemplate.services;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class GoogleCloudTranslationService {

    private Translate translate;

    public GoogleCloudTranslationService() {
        this.translate = TranslateOptions.getDefaultInstance().getService();
    }

    public String toJapanese(String text){
        try{
            return this.translate.translate(text, Translate.TranslateOption.targetLanguage("ja")).getTranslatedText();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String toEnglish(String text){
        try{
            return this.translate.translate(text, Translate.TranslateOption.targetLanguage("en")).getTranslatedText();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
