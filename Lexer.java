import exceptions.ParseException;

class Lexer {
    private final String s;
    private int i, last;

    Lexer(String s) {
        this.s = s;
        i = 0;
    }

    int getLast() {
        return last;
    }

    String next() throws ParseException {
        while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
            i++;
        }
        last = i;
        if (i == s.length()) {
            return "";
        }
        if (Character.isDigit(s.charAt(i))) {
            int j = i;
            while (j + 1 < s.length() && (Character.isDigit(s.charAt(j + 1)) || s.charAt(j + 1) == 'E' ||
                s.charAt(j + 1) == 'e' || s.charAt(j + 1) == '-' || s.charAt(j + 1) == '.')) {
                j++;
            }
            int oi = i;
            i = j + 1;
            return s.substring(oi, j + 1);
        }
        if (Character.isLetter(s.charAt(i))) {
            int j = i;
            while (j + 1 < s.length() && Character.isLetter(s.charAt(j + 1))) {
                j++;
            }
            int oi = i;
            i = j + 1;
            String res = s.substring(oi, j + 1);
            if (!res.equals("x") && !res.equals("y") && !res.equals("z") && !res.equals("abs") && !res.equals("lb")) {
                throw new ParseException("parse fail");
            }
            return res;
        }
        if (s.charAt(i) != '-' && s.charAt(i) != '*' && s.charAt(i) != '/' && s.charAt(i) != '+' && s.charAt(i) != '~'
                && s.charAt(i) != '(' && s.charAt(i) != ')' && s.charAt(i) != '^') {
            throw new ParseException("parse fail");
        }
        i++;
        return s.substring(i - 1, i);
    }
}
