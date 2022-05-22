package service.crypt;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractVigenereCrypt {
    protected List<Character> list = Arrays.asList('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й',
            'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф',
            'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я');

    protected String getValidKey(String str) {
        return str.replaceAll("[^А-ЯЁ]", "");
    }

    protected String convertKey(String str) {
        StringBuilder m = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if(str.toCharArray()[i] == 'Я'){
                m.append('А');
            } else {
                m.append(list.get(list.indexOf(str.toCharArray()[i]) + 1));
            }
        }
        return m.toString();
    }
}
