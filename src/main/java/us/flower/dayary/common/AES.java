package us.flower.dayary.common;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class AES {

    public Map<String,Object> getIvKeySpec(String key) throws Exception{
        String iv = key;

        Key keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

        return new HashMap<String,Object>(){
            {
                put("iv", iv);
                put("keySpec", keySpec);
            }
        };
    }

    public String aesEncode(String str, String key) throws Exception {

        Map<String,Object> data = getIvKeySpec(key);

        String iv = (String)data.get("iv");
        Key keySpec = (Key)data.get("keySpec");

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = Base64.getEncoder().encodeToString(encrypted);

        return enStr;
    }

    public String aesDecode(String str, String key) throws Exception {

        Map<String,Object> data = getIvKeySpec(key);

        String iv = (String)data.get("iv");
        Key keySpec = (Key)data.get("keySpec");

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));

        byte[] byteStr = Base64.getDecoder().decode(str);

        return new String(c.doFinal(byteStr),"UTF-8");
    }

}