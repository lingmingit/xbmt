package com.xbmt.framework.web.json;

import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import com.xbmt.framework.web.jackson.helper.ThreadJacksonMixInHolder;


/****
 * 重写 json 转换处理类  Jackson版本为1.xx <p>
 * @author LingMin 
 * @date 2015-8-18<br>
 * @version 1.0<br>
 */
public class JacksonHttpMessageConverter extends MappingJacksonHttpMessageConverter {

	/******/
	private ObjectMapper objectMapper = new ObjectMapper();
    private boolean prefixJson = false;


    /**
     * <br>
     * 2013-9-27 下午4:10:28
     *
     * @see org.springframework.http.converter.json.MappingJacksonHttpMessageConverter#writeInternal(Object,
     * org.springframework.http.HttpOutputMessage)
     */
    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        // super.writeInternal(object, outputMessage);

        // 判断是否需要重写objectMapper
        ObjectMapper objectMapper = this.objectMapper;// 本地化ObjectMapper，防止方法级别的ObjectMapper改变全局ObjectMapper
        if (ThreadJacksonMixInHolder.isContainsMixIn()) {
        	objectMapper = ThreadJacksonMixInHolder.builderCodehausMapper();
        }
        //this.getJsonEncoding(arg0)
        JsonEncoding encoding = this.getJsonEncoding(outputMessage.getHeaders().getContentType());
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(outputMessage.getBody() , encoding);

        // A workaround for JsonGenerators not applying serialization features
        // https://github.com/FasterXML/jackson-databind/issues/12
        if (this.objectMapper.isEnabled(SerializationConfig.Feature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }

        try {
            if (this.prefixJson) {
                jsonGenerator.writeRaw("{} && ");
            }
            objectMapper.writeValue(jsonGenerator, object);
        } catch (JsonProcessingException ex) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(),ex);
        }
    }

    public boolean isPrefixJson() {
        return prefixJson;
    }

    public void setPrefixJson(boolean prefixJson) {
        this.prefixJson = prefixJson;
    }

}
