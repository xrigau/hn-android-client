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
            "  \"news\": [\n" +
            "    {\n" +
            "      \"id\": \"6909172\",\n" +
            "      \"domain\": \"singlesexschools.org\",\n" +
            "      \"title\": \"Single-Sex vs. Coed: The Evidence \",\n" +
            "      \"link\": \"http://www.singlesexschools.org/evidence.html\",\n" +
            "      \"score\": 31,\n" +
            "      \"user\": \"dsego\",\n" +
            "      \"timestamp\": \"1 hour ago\",\n" +
            "      \"comments\": 36\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"6908648\",\n" +
            "      \"domain\": \"tydligapp.com\",\n" +
            "      \"title\": \"Calculator Reimagined For I-Phone/I-Pad\",\n" +
            "      \"link\": \"http://tydligapp.com\",\n" +
            "      \"score\": 198,\n" +
            "      \"user\": \"Istof\",\n" +
            "      \"timestamp\": \"6 hours ago\",\n" +
            "      \"comments\": 72\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"6909089\",\n" +
            "      \"domain\": \"aljazeera.com\",\n" +
            "      \"title\": \"Sex, lies and the Internet: The tale of Lena Chen\",\n" +
            "      \"link\": \"http://america.aljazeera.com/watch/shows/america-tonight/america-tonight-blog/2013/12/9/lena-chen-onlineharassment.html\",\n" +
            "      \"score\": 27,\n" +
            "      \"user\": \"ValentineC\",\n" +
            "      \"timestamp\": \"2 hours ago\",\n" +
            "      \"comments\": 29\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"6909326\",\n" +
            "      \"domain\": \"dadgum.com\",\n" +
            "      \"title\": \"Don't Distract New Programmers with OOP\",\n" +
            "      \"link\": \"http://prog21.dadgum.com/93.html?HN2\",\n" +
            "      \"score\": 8,\n" +
            "      \"user\": \"ColinWright\",\n" +
            "      \"timestamp\": \"28 minutes ago\",\n" +
            "      \"comments\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"6909146\",\n" +
            "      \"domain\": \"sethrobertson.github.io\",\n" +
            "      \"title\": \"On undoing, fixing, or removing commits in git\",\n" +
            "      \"link\": \"http://sethrobertson.github.io/GitFixUm/fixup.html\",\n" +
            "      \"score\": 19,\n" +
            "      \"user\": \"DanielShir\",\n" +
            "      \"timestamp\": \"1 hour ago\",\n" +
            "      \"comments\": 2\n" +
            "    }\n" +
            "  ],\n" +
            "  \"nextPagePath\": \"news2\",\n" +
            "  \"currentPage\": 0,\n" +
            "  \"nextPage\": -1\n" +
        "}";
}