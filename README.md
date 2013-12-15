Another unofficial Hacker News client for Android
===

Work in progress...

Usage
---

This project works with my other project that is an [unofficial json API for Hacker News](https://github.com/xrigau/news-ycombinator-json-api).
In order to make it work you need to run it in your own server and in the Android app just modify `API_URL` in `TaskExecutor` to point to it:
`java
    private static final String API_URL = "http://[YOUR_SERVER_IP]:32412";
`
