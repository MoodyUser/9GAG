package me.storm.ninegag.api;

/**
 * Created by storm on 14-3-25.
 */
public class GithubApi {
    public static final String HOME = "http://api.github.com/";
    public static final String FORK = HOME + "gists/%s/forks?access_token=%s";
    public static final String OAUTH_URL = HOME + "login/oauth/authorize";
    public static final String OAUTH_ACCESS_TOKEN_URL = HOME + "login/complete";

    public static String fork(String key, String gistId) {
        return String.format(FORK, gistId, key);
    }


}

