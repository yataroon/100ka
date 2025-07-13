package dev.yataroon.hyakka.common;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JSONConverter {

    /**
     * * Object Reader
     * * ※スレッドセーフ
     */
    private ObjectReader objectReader;

    /**
     * * Object Writer
     * * ※スレッドセーフ
     */
    private ObjectWriter objectWriter;

    /**
     * * 初期化処理
     */
    @PostConstruct
    private void init() {
        ObjectMapper mapper = new ObjectMapper();
        // 設定
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // immutableなReader/Writer作成
        this.objectReader = mapper.reader();
        this.objectWriter = mapper.writer();
    }

    /**
     * * オブジェクト→JSONへの変換
     * 
     * @param object
     * @return
     * @throws IOException
     */
    public String toJSON(Object object) throws IOException {
        return objectWriter.writeValueAsString(object);
    }

    /**
     * * JSON→オブジェクトへの変換
     * 
     * @param <T>
     * @param json
     * @param clazz
     * @return
     * @throws IOException
     */
    public <T> T fromJSON(String json, Class<T> clazz) throws IOException {
        return objectReader.readValue(json, clazz);
    }

}
