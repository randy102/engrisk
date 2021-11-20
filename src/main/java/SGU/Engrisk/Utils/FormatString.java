package SGU.Engrisk.Utils;

import org.apache.commons.text.WordUtils;

public class FormatString {
    public static String TitleCase(String str) {
        if (str == null)
            throw new NullPointerException();
        return WordUtils.capitalizeFully(str.trim().replaceAll("(?U)\\s+", " "));
    }
}
