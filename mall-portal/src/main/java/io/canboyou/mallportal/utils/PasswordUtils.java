package io.canboyou.mallportal.utils;

import io.canboyou.mallportal.enums.CharacterTypeEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 供应商密码工具类
 *
 * @author: jiangBo.Qin
 * @create: 2021-09-26 18:37
 **/
public class PasswordUtils {

    private static final String PWD_SALT = "1GT5XDBZs"; // 默认加密盐

    public static final char[] allowedSpecialCharacters = {'`', '~', '@', '#', '$', '%', '^', '&'};//密码能包含的特殊字符
    private static final int letterRange = 26;
    private static final int numberRange = 10;
    private static final int spCharacterRange = allowedSpecialCharacters.length;
    private static final Random random = new Random();

    public PasswordUtils(int passwordLength, int minVariousType) {
        if (minVariousType > CharacterTypeEnum.values().length) minVariousType = CharacterTypeEnum.values().length;
        if (minVariousType > passwordLength) minVariousType = passwordLength;
        //密码的长度
        //密码包含字符的最少种类
        int minVariousType1 = minVariousType;
    }

    /**
     * @param passwordLength 密码的长度
     * @param minVariousType 密码包含字符的最少种类
     * @return
     */
    public static String generateRandomPassword(int passwordLength, int minVariousType) {
        char[] password = new char[passwordLength];
        List<Integer> pwCharsIndex = new ArrayList<>();
        for (int i = 0; i < password.length; i++) {
            pwCharsIndex.add(i);
        }
        List<CharacterTypeEnum> takeTypes = new ArrayList<>(Arrays.asList(CharacterTypeEnum.values()));
        List<CharacterTypeEnum> fixedTypes = Arrays.asList(CharacterTypeEnum.values());
        int typeCount = 0;
        while (pwCharsIndex.size() > 0) {
            int pwIndex = pwCharsIndex.remove(random.nextInt(pwCharsIndex.size()));//随机填充一位密码
            Character c;
            if (typeCount < minVariousType) {//生成不同种类字符
                c = generateCharacter(takeTypes.remove(random.nextInt(takeTypes.size())));
                typeCount++;
            } else {//随机生成所有种类密码
                c = generateCharacter(fixedTypes.get(random.nextInt(fixedTypes.size())));
            }
            password[pwIndex] = c;
        }
        return String.valueOf(password);
    }

    /**
     * 直接生成密文密码
     */
    public static String generateEncryptedRandomPassword() {
        String password = generateRandomPassword(10, 4);
        return getEncryptedRandomPassword(password);
    }

    /**
     * 获取客户端传入经过一次加密的密码的二次加工密码
     */
    public static String getClientEncryptedPassword(String encryptOnePwd) {
        String rawEncryptPwd = encryptOnePwd + PWD_SALT; // 加盐
        return Md5Util.md5(rawEncryptPwd);
    }

    /**
     * 根据明文密码生成密文密码
     */
    public static String getEncryptedRandomPassword(String rawPassword) {
        String encryptOnePwd = Md5Util.md5(rawPassword); // md5加密一次的密码
        String rawEncryptPwd = encryptOnePwd + PWD_SALT; // 加盐
        return Md5Util.md5(rawEncryptPwd);
    }

    private static Character generateCharacter(CharacterTypeEnum type) {
        Character c = null;
        int rand;
        switch (type) {
            case LOWERCASE -> {//随机小写字母
                rand = random.nextInt(letterRange);
                rand += 97;
                c = (char) rand;
            }
            case UPPERCASE -> {//随机大写字母
                rand = random.nextInt(letterRange);
                rand += 65;
                c = (char) rand;
            }
            case NUMBER -> {//随机数字
                rand = random.nextInt(numberRange);
                rand += 48;
                c = (char) rand;
            }
            case SPECIAL_CHARACTER -> {//随机特殊字符
                rand = random.nextInt(spCharacterRange);
                c = allowedSpecialCharacters[rand];
            }
        }
        return c;
    }


}
