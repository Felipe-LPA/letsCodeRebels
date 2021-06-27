package br.com.letscode.starwars.resistence.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResistenceConstants {

    public static final String API_PREFIX = "/api/v1";

    public static final String APP_BASE_PACKAGE = "br.com.letscode.starwars.resistence";

    public static final String REPOSITORY_BASE_PACKAGE = APP_BASE_PACKAGE + ".core.api.repository";
    
    public static final String ENTITY_BASE_PACKAGE = APP_BASE_PACKAGE + ".core.api.data";

}
