package com.xrigau.hnandroid.core.debug;

import java.io.IOException;
import java.util.Collections;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class MockClient implements Client {

    private static final String APPLICATION_JSON = "application/json";
    private static final String SUCCESS = "Success";

    @Override
    public Response execute(Request request) throws IOException {
        return new Response(200, SUCCESS, Collections.EMPTY_LIST, new TypedByteArray(APPLICATION_JSON, MOCK_RESPONSE_JSON.getBytes()));
    }

    private static final String MOCK_RESPONSE_JSON =
            "{\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"domain\": \"ninjasandrobots.com\",\n" +
            "      \"title\": \"Not Wanted\",\n" +
            "      \"url\": \"http://ninjasandrobots.com/not-wanted/\",\n" +
            "      \"user\": \"nate\",\n" +
            "      \"userUrl\": \"http://hackednews-json-api.herokuapp.com/user?id=nate\",\n" +
            "      \"comments\": 25,\n" +
            "      \"commentsUrl\": \"http://hackednews-json-api.herokuapp.com/item?id=7043468\",\n" +
            "      \"points\": 99,\n" +
            "      \"timestamp\": \"3 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"coindesk.com\",\n" +
            "      \"title\": \"Bitcoin Price Touches $1,000 Again as Overstock Sales Hit $130,000\",\n" +
            "      \"url\": \"http://www.coindesk.com/bitcoin-price-1000-again-overstock-sales-130000/\",\n" +
            "      \"user\": \"dcawrey\",\n" +
            "      \"userUrl\": \"http://hackednews-json-api.herokuapp.com/user?id=dcawrey\",\n" +
            "      \"comments\": 21,\n" +
            "      \"commentsUrl\": \"http://hackednews-json-api.herokuapp.com/item?id=7043919\",\n" +
            "      \"points\": 41,\n" +
            "      \"timestamp\": \"1 hour ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"dadgum.com\",\n" +
            "      \"title\": \"A Worst Case for Functional Programming?\",\n" +
            "      \"url\": \"http://prog21.dadgum.com/189.html\",\n" +
            "      \"user\": \"platz\",\n" +
            "      \"userUrl\": \"http://hackednews-json-api.herokuapp.com/user?id=platz\",\n" +
            "      \"comments\": 27,\n" +
            "      \"commentsUrl\": \"http://hackednews-json-api.herokuapp.com/item?id=7043644\",\n" +
            "      \"points\": 38,\n" +
            "      \"timestamp\": \"2 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"books.google.com\",\n" +
            "      \"title\": \"50 solutions to the Fermi Paradox and the problem of extraterrestrial life\",\n" +
            "      \"url\": \"http://books.google.com/books?hl=en&lr=&id=-vZ0BVSHix4C&oi=fnd&pg=PR9&dq=fermi+paradox&ots=s4bVK3fA4E&sig=iimM4koFjlfKUQMgbmNbPjtiVd8#v=onepage&q&f=false\",\n" +
            "      \"user\": \"obblekk\",\n" +
            "      \"userUrl\": \"http://hackednews-json-api.herokuapp.com/user?id=obblekk\",\n" +
            "      \"comments\": 1,\n" +
            "      \"commentsUrl\": \"http://hackednews-json-api.herokuapp.com/item?id=7044010\",\n" +
            "      \"points\": 13,\n" +
            "      \"timestamp\": \"48 minutes ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"nytimes.com\",\n" +
            "      \"title\": \"Banks Say No to Marijuana Money, Legal or Not\",\n" +
            "      \"url\": \"http://www.nytimes.com/2014/01/12/us/banks-say-no-to-marijuana-money-legal-or-not.html?ref=business&pagewanted=all&_r=0\",\n" +
            "      \"user\": \"danso\",\n" +
            "      \"userUrl\": \"http://hackednews-json-api.herokuapp.com/user?id=danso\",\n" +
            "      \"comments\": 14,\n" +
            "      \"commentsUrl\": \"http://hackednews-json-api.herokuapp.com/item?id=7043751\",\n" +
            "      \"points\": 22,\n" +
            "      \"timestamp\": \"2 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"braveclojure.com\",\n" +
            "      \"title\": \"A Clojure Crash Course\",\n" +
            "      \"url\": \"http://www.braveclojure.com/do-things/\",\n" +
            "      \"user\": \"nonrecursive\",\n" +
            "      \"userUrl\": \"http://hackednews-json-api.herokuapp.com/user?id=nonrecursive\",\n" +
            "      \"comments\": 45,\n" +
            "      \"commentsUrl\": \"http://hackednews-json-api.herokuapp.com/item?id=7042720\",\n" +
            "      \"points\": 165,\n" +
            "      \"timestamp\": \"8 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"gamespot.com\",\n" +
            "      \"title\": \"Nintendo acquires \\\"troll's\\\" patent portfolio after legal victory\",\n" +
            "      \"url\": \"http://www.gamespot.com/articles/nintendo-acquires-troll-s-patent-portfolio-after-legal-victory/1100-6417060/\",\n" +
            "      \"user\": \"bane\",\n" +
            "      \"userUrl\": \"http://hackednews-json-api.herokuapp.com/user?id=bane\",\n" +
            "      \"comments\": 8,\n" +
            "      \"commentsUrl\": \"http://hackednews-json-api.herokuapp.com/item?id=7043463\",\n" +
            "      \"points\": 47,\n" +
            "      \"timestamp\": \"3 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"nedbatchelder.com\",\n" +
            "      \"title\": \"Comments should be sentences\",\n" +
            "      \"url\": \"http://nedbatchelder.com/blog/201401/comments_should_be_sentences.html\",\n" +
            "      \"user\": \"henrik_w\",\n" +
            "      \"userUrl\": \"http://hackednews-json-api.herokuapp.com/user?id=henrik_w\",\n" +
            "      \"comments\": 21,\n" +
            "      \"commentsUrl\": \"http://hackednews-json-api.herokuapp.com/item?id=7043598\",\n" +
            "      \"points\": 28,\n" +
            "      \"timestamp\": \"2 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"bing.com\",\n" +
            "      \"title\": \"Bing now supports https\",\n" +
            "      \"url\": \"https://www.bing.com\",\n" +
            "      \"user\": \"foolproof\",\n" +
            "      \"userUrl\": \"http://hackednews-json-api.herokuapp.com/user?id=foolproof\",\n" +
            "      \"comments\": 34,\n" +
            "      \"commentsUrl\": \"http://hackednews-json-api.herokuapp.com/item?id=7043536\",\n" +
            "      \"points\": 26,\n" +
            "      \"timestamp\": \"3 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"sophos.com\",\n" +
            "      \"title\": \"Target's data breach MUCH bigger than first thought\",\n" +
            "      \"url\": \"http://nakedsecurity.sophos.com/2014/01/11/target-data-breach-much-bigger-than-first-thought-now-more-than-100m-records/\",\n" +
            "      \"user\": \"kn9\",\n" +
            "      \"userUrl\": \"http://hackednews-json-api.herokuapp.com/user?id=kn9\",\n" +
            "      \"comments\": 3,\n" +
            "      \"commentsUrl\": \"http://hackednews-json-api.herokuapp.com/item?id=7043905\",\n" +
            "      \"points\": 8,\n" +
            "      \"timestamp\": \"1 hour ago\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"links\": [\n" +
            "    {\n" +
            "      \"rel\": \"nextPage\",\n" +
            "      \"href\": \"http://hackednews-json-api.herokuapp.com/news2\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"ycombinator\",\n" +
            "      \"href\": \"http://ycombinator.com\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"news\",\n" +
            "      \"href\": \"http://hackednews-json-api.herokuapp.com/news\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"newest\",\n" +
            "      \"href\": \"http://hackednews-json-api.herokuapp.com/newest\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"newcomments\",\n" +
            "      \"href\": \"http://hackednews-json-api.herokuapp.com/newcomments\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"ask\",\n" +
            "      \"href\": \"http://hackednews-json-api.herokuapp.com/ask\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"self\",\n" +
            "      \"href\": \"http://hackednews-json-api.herokuapp.com/\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}