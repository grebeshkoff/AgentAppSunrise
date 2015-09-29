package ua.dn.effect.ilg.agentappsunrise.util;

import java.util.HashMap;
import java.util.Map;

/**
 * User: igrebeshkov
 * Date: 19.11.13
 * Time: 11:00
 */
public class Transliterator {

    private static final Map<Character, String> charMap = new HashMap<Character, String>();

    static {
        charMap.put('?', "A");
        charMap.put('?', "B");
        charMap.put('?', "V");
        charMap.put('?', "G");
        charMap.put('?', "D");
        charMap.put('?', "E");
        charMap.put('?', "E");
        charMap.put('?', "Zh");
        charMap.put('?', "Z");
        charMap.put('?', "I");
        charMap.put('?', "I");
        charMap.put('?', "K");
        charMap.put('?', "L");
        charMap.put('?', "M");
        charMap.put('?', "N");
        charMap.put('?', "O");
        charMap.put('?', "P");
        charMap.put('?', "R");
        charMap.put('?', "S");
        charMap.put('?', "T");
        charMap.put('?', "U");
        charMap.put('?', "F");
        charMap.put('?', "H");
        charMap.put('?', "C");
        charMap.put('?', "Ch");
        charMap.put('?', "Sh");
        charMap.put('?', "Sh");
        charMap.put('?', "");
        charMap.put('?', "Y");
        charMap.put('?', "");
        charMap.put('?', "E");
        charMap.put('?', "U");
        charMap.put('?', "Ya");
        charMap.put('?', "a");
        charMap.put('?', "b");
        charMap.put('?', "v");
        charMap.put('?', "g");
        charMap.put('?', "d");
        charMap.put('?', "e");
        charMap.put('?', "e");
        charMap.put('?', "zh");
        charMap.put('?', "z");
        charMap.put('?', "i");
        charMap.put('?', "i");
        charMap.put('?', "k");
        charMap.put('?', "l");
        charMap.put('?', "m");
        charMap.put('?', "n");
        charMap.put('?', "o");
        charMap.put('?', "p");
        charMap.put('?', "r");
        charMap.put('?', "s");
        charMap.put('?', "t");
        charMap.put('?', "u");
        charMap.put('?', "f");
        charMap.put('?', "h");
        charMap.put('?', "c");
        charMap.put('?', "ch");
        charMap.put('?', "sh");
        charMap.put('?', "sh");
        charMap.put('?', "");
        charMap.put('?', "y");
        charMap.put('?', "");
        charMap.put('?', "e");
        charMap.put('?', "u");
        charMap.put('?', "ya");

        charMap.put('?', "g");
        charMap.put('?', "g");

        charMap.put('?', "e");
        charMap.put('?', "e");

        charMap.put('?', "I");
        charMap.put('?', "i");

        charMap.put('?', "I");
        charMap.put('?', "i");

        charMap.put('\'', "");

        charMap.put('/', "");
        charMap.put(' ', "-");
        charMap.put('#', "");
        charMap.put('%', "");
        charMap.put('&', "");
        charMap.put('{', "");
        charMap.put('}', "");
        charMap.put('\\', "");
        charMap.put('<', "");
        charMap.put('>', "");
        charMap.put('*', "");
        charMap.put('?', "");
        charMap.put('$', "");
        charMap.put(':', "");
        charMap.put('+', "");
        charMap.put('|', "");
        charMap.put('=', "");
        charMap.put('-', "-");
        charMap.put('-', "-");
        charMap.put('?', "ya");
    }

    public String transliterate(String string) {
        StringBuilder transliteratedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            Character ch = string.charAt(i);
            String charFromMap = charMap.get(ch);

            //if (!charFromMap.equals('/')){
                if (charFromMap == null) {
                    if(Character.isLetterOrDigit(ch)){
                        transliteratedString.append(ch);
                    }
                } else {
                    transliteratedString.append(charFromMap);
                }
            //}
        }


        return transliteratedString.toString();
    }
}