package com.xrigau.hnandroid.core.debug;

import java.io.IOException;
import java.util.Collections;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class MockClient implements Client {

    private static final String SUMMARY = "summary";

    private static final int CODE_SUCCESS = 200;
    private static final String REASON_SUCCESS = "Success";
    private static final String APPLICATION_JSON = "application/json";

    @Override
    public Response execute(Request request) throws IOException {
        final String mockResponse;
        if (request.getUrl().contains(SUMMARY)) {
            mockResponse = MOCK_SUMMARY_JSON;
        } else {
            mockResponse = MOCK_NEWS_LIST_JSON;
        }
        return new Response(CODE_SUCCESS, REASON_SUCCESS, Collections.EMPTY_LIST, new TypedByteArray(APPLICATION_JSON, mockResponse.getBytes()));
    }

    private static final String MOCK_NEWS_LIST_JSON =
            "{\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"domain\": \"wtsui.org\",\n" +
            "      \"title\": \"How to stop feeling lonely\",\n" +
            "      \"url\": \"http://wtsui.org/blog/how-to-stop-feeling-lonely/\",\n" +
            "      \"user\": \"wtsui\",\n" +
            "      \"userUrl\": \"user?id=wtsui\",\n" +
            "      \"comments\": 6,\n" +
            "      \"commentsUrl\": \"item?id=7044690\",\n" +
            "      \"points\": 33,\n" +
            "      \"timestamp\": \"1 hour ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"github.com\",\n" +
            "      \"title\": \"Xenia - An Xbox 360 emulator\",\n" +
            "      \"url\": \"https://github.com/benvanik/xenia\",\n" +
            "      \"user\": \"devbug\",\n" +
            "      \"userUrl\": \"user?id=devbug\",\n" +
            "      \"comments\": 27,\n" +
            "      \"commentsUrl\": \"item?id=7044533\",\n" +
            "      \"points\": 60,\n" +
            "      \"timestamp\": \"2 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"appwared.com\",\n" +
            "      \"title\": \"Awful app review trend among Turkish users\",\n" +
            "      \"url\": \"http://blog.appwared.com/awful-app-review-trend-among-turkish-users-this-is-why-your-app-gets-so-many-one-star-reviews/\",\n" +
            "      \"user\": \"mrtksn\",\n" +
            "      \"userUrl\": \"user?id=mrtksn\",\n" +
            "      \"comments\": 1,\n" +
            "      \"commentsUrl\": \"item?id=7044833\",\n" +
            "      \"points\": 12,\n" +
            "      \"timestamp\": \"24 minutes ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"ninjasandrobots.com\",\n" +
            "      \"title\": \"Not Wanted\",\n" +
            "      \"url\": \"http://ninjasandrobots.com/not-wanted/\",\n" +
            "      \"user\": \"nate\",\n" +
            "      \"userUrl\": \"user?id=nate\",\n" +
            "      \"comments\": 32,\n" +
            "      \"commentsUrl\": \"item?id=7043468\",\n" +
            "      \"points\": 165,\n" +
            "      \"timestamp\": \"7 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"brendaneich.com\",\n" +
            "      \"title\": \"Trust but Verify\",\n" +
            "      \"url\": \"https://brendaneich.com/2014/01/trust-but-verify/\",\n" +
            "      \"user\": \"khuey\",\n" +
            "      \"userUrl\": \"user?id=khuey\",\n" +
            "      \"comments\": 6,\n" +
            "      \"commentsUrl\": \"item?id=7044205\",\n" +
            "      \"points\": 49,\n" +
            "      \"timestamp\": \"3 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"cmu.edu\",\n" +
            "      \"title\": \"15-819 Homotopy Type Theory\",\n" +
            "      \"url\": \"http://www.cs.cmu.edu/~rwh/courses/hott/\",\n" +
            "      \"user\": \"mikevm\",\n" +
            "      \"userUrl\": \"user?id=mikevm\",\n" +
            "      \"comments\": 5,\n" +
            "      \"commentsUrl\": \"item?id=7044530\",\n" +
            "      \"points\": 26,\n" +
            "      \"timestamp\": \"2 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"tweaktown.com\",\n" +
            "      \"title\": \"Kingston displays 384GB of DDR4 in top-secret Intel server at CES\",\n" +
            "      \"url\": \"http://www.tweaktown.com/news/34788/kingston-displays-384gb-of-ddr4-in-top-secret-intel-server-at-ces/index.html\",\n" +
            "      \"user\": \"rbanffy\",\n" +
            "      \"userUrl\": \"user?id=rbanffy\",\n" +
            "      \"comments\": 17,\n" +
            "      \"commentsUrl\": \"item?id=7044123\",\n" +
            "      \"points\": 45,\n" +
            "      \"timestamp\": \"3 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"clipperhouse.github.io\",\n" +
            "      \"title\": \"Gen - a generics library for Go\",\n" +
            "      \"url\": \"http://clipperhouse.github.io/gen/\",\n" +
            "      \"user\": \"hebz0rl\",\n" +
            "      \"userUrl\": \"user?id=hebz0rl\",\n" +
            "      \"comments\": 17,\n" +
            "      \"commentsUrl\": \"item?id=7044070\",\n" +
            "      \"points\": 48,\n" +
            "      \"timestamp\": \"3 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"dadgum.com\",\n" +
            "      \"title\": \"A Worst Case for Functional Programming?\",\n" +
            "      \"url\": \"http://prog21.dadgum.com/189.html\",\n" +
            "      \"user\": \"platz\",\n" +
            "      \"userUrl\": \"user?id=platz\",\n" +
            "      \"comments\": 52,\n" +
            "      \"commentsUrl\": \"item?id=7043644\",\n" +
            "      \"points\": 68,\n" +
            "      \"timestamp\": \"6 hours ago\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"domain\": \"braveclojure.com\",\n" +
            "      \"title\": \"A Clojure Crash Course\",\n" +
            "      \"url\": \"http://www.braveclojure.com/do-things/\",\n" +
            "      \"user\": \"nonrecursive\",\n" +
            "      \"userUrl\": \"user?id=nonrecursive\",\n" +
            "      \"comments\": 49,\n" +
            "      \"commentsUrl\": \"item?id=7042720\",\n" +
            "      \"points\": 187,\n" +
            "      \"timestamp\": \"11 hours ago\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"nextPage\": \"news2\",\n" +
            "  \"currentPage\": \"/\"\n" +
            "}";

    private static final String MOCK_SUMMARY_JSON =
            "{\n" +
            "  \"keywords\": [\n" +
            "    \"Java\",\n" +
            "    \"Groovy\",\n" +
            "    \"Shell\"\n" +
            "  ],\n" +
            "  \"inLanguage\": \"en\",\n" +
            "  \"text\": \"Work in progress...\\n\\nThis project works with my other project that is an [unofficial json API for Hacker News](https://github.com/xrigau/news-ycombinator-json-api). In order to make it work you need to run it in your own server and in the Android app just modify API_URL in TaskExecutor to point to it:\\nprivate static final String API_URL = \\\"http://[YOUR_SERVER_IP]:32412\\\";\",\n" +
            "  \"schema\": \"http://schema.org/WebPage\",\n" +
            "  \"type\": \"WebPage\",\n" +
            "  \"url\": \"https://github.com/xrigau/hn-android-client\",\n" +
            "  \"name\": \"hn-android-client\",\n" +
            "  \"description\": \"hn-android-client - Unofficial Android client for the unofficial HN json API in https://github.com/xrigau/news-ycombinator-json-api\",\n" +
            "  \"image\": \"https://github.global.ssl.fastly.net/images/modules/logos_page/Octocat.png\",\n" +
            "  \"dateAccessed\": \"2014-01-12T02:50:40+00:00\"\n" +
            "}";
}
