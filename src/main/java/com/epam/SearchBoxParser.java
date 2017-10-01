package com.epam;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public final class SearchBoxParser {

    public static void main(String... aArguments) {
        SearchBoxParser parser = new SearchBoxParser("Notes on Oracle Coherence.\n" +
                "Coherense cache: fast & easy\n" +
                "Written by: Ricky Ho\n" +
                "Oracle Coherence is a distributed cache that functionally comparable to Memcached. On top of the basic cache API function, it has some additional capabilities that is attractive for building large scale enterprise applications. The API is based on a Java Map (Hashtable) Interface. It is based on a key/value store semantics where the value can be any Java Serializable object. Coherence allows multiple cache identified by a unique name (which they called a \"named cache\"). The common usage pattern is to locate a cache by its name, and then act on the cache. ");
        Set<String> tokens = parser.parseSearchText();
        System.out.println(tokens);
    }

    public SearchBoxParser(String aSearchText) {
        if (aSearchText == null) {
            throw new IllegalArgumentException("Search Text cannot be null.");
        }
        fSearchText = aSearchText;
    }

    public Set<String> parseSearchText() {
        Set<String> result = new LinkedHashSet<>();

        boolean returnTokens = true;
        String currentDelims = fWHITESPACE_AND_QUOTES;
        StringTokenizer parser = new StringTokenizer(
                fSearchText, currentDelims, returnTokens
        );

        String token = null;
        while (parser.hasMoreTokens()) {
            token = parser.nextToken(currentDelims);
            if (!isDoubleQuote(token)){
                addNonTrivialWordToResult(token, result);
            }
            else {
                currentDelims = flipDelimiters(currentDelims);
            }
        }
        return result;
    }

    private String fSearchText;
    private static final Set<String> fCOMMON_WORDS = new LinkedHashSet<>();
    private static final String fDOUBLE_QUOTE = "\"";

    private static final String fWHITESPACE_AND_QUOTES = " \t\r\n\"";
    private static final String fQUOTES_ONLY ="\"";

    static {
        fCOMMON_WORDS.add("a");
        fCOMMON_WORDS.add("and");
        fCOMMON_WORDS.add("be");
        fCOMMON_WORDS.add("for");
        fCOMMON_WORDS.add("from");
        fCOMMON_WORDS.add("has");
        fCOMMON_WORDS.add("i");
        fCOMMON_WORDS.add("in");
        fCOMMON_WORDS.add("is");
        fCOMMON_WORDS.add("it");
        fCOMMON_WORDS.add("of");
        fCOMMON_WORDS.add("on");
        fCOMMON_WORDS.add("mars");
        fCOMMON_WORDS.add("the");
    }

    private boolean isCommonWord(String aSearchTokenCandidate){
        return fCOMMON_WORDS.contains(aSearchTokenCandidate);
    }

    private boolean textHasContent(String aText){
        return (aText != null) && (!aText.trim().equals(""));
    }

    private void addNonTrivialWordToResult(String aToken, Set<String> aResult){
        if (textHasContent(aToken) && !isCommonWord(aToken.trim())) {
            aResult.add(aToken.trim());
        }
    }

    private boolean isDoubleQuote(String aToken){
        return aToken.equals(fDOUBLE_QUOTE);
    }

    private String flipDelimiters(String aCurrentDelims){
        String result = null;
        if (aCurrentDelims.equals(fWHITESPACE_AND_QUOTES)){
            result = fQUOTES_ONLY;
        }
        else {
            result = fWHITESPACE_AND_QUOTES;
        }
        return result;
    }
}
