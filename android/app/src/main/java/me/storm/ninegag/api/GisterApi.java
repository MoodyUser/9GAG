package me.storm.ninegag.api;

/**
 * Created by storm on 14-3-25.
 */
public class GisterApi {
    public static final String HOME = "http://104.196.147.66/";
    public static final String HOST = HOME + "api/gists/";
    public static final String OAUTH_URL = HOME + "accounts/github/login";
    public static final String OAUTH_ACCESS_TOKEN_URL = HOME + "login/complete";

    public static String buildRequest(String category, String page) {
        String queryString = "?";
        category = category.isEmpty() ? "" : "category=" + category;
        page = page.isEmpty() ? "" : "page=" + page;
        queryString += !category.isEmpty() && !page.isEmpty() ? category + "&" + page : category + page;
        return HOST + (("?".equals(queryString)) ? "" : queryString);
    }

    public static String getStageUrl(OauthStage oauthStage) {
        switch (oauthStage) {
            case FIRST:
                return OAUTH_URL;
            case SECOND:
                return HOME;
            case THIRD:
                return OAUTH_ACCESS_TOKEN_URL;
            default:
                return HOME;
        }
    }

    public enum OauthStage {
        FIRST,
        SECOND,
        THIRD
    }
}

