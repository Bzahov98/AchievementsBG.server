package com.bg.bzahov.achievementsBG.constants;

public class PathConstants {
    public static final String API_VERSION_LATEST = "v1";

    public static final String BASE_URL = "/api/" + API_VERSION_LATEST + "/";

    // Authentication paths
    public static final String PATH_AUTH = "auth";
    public static final String PATH_AUTH_REQUEST_LOGIN = "login";
    public static final String PATH_AUTH_REQUEST_REGISTER = "register";
    public static final String PATH_AUTH_REQUEST_ROLES = "roles";

    // Rower paths
    public static final String PATH_ROWERS = "rowers";

    // Path for rowers' id cards
    public static final String PATH_ROWERS_ID_CARDS = "rowers/id_cards";

    // User paths
    public static final String PATH_USERS = "users";

    public static final String PATH_VAR_ID = "id";
    public static final String PATH_VARIABLE_ID = "{id}";
    public static final String PATH_VARIABLE_YEAR_OF_BIRTH = "{yearOfBirth}";

}
