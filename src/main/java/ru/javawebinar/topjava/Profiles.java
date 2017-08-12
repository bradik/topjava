package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Profiles {
    public static final String
            JDBC = "jdbc",
            JPA = "jpa",
            DATAJPA = "datajpa";

    public static final String REPOSITORY_IMPLEMENTATION = JDBC;

    public static final String
            POSTGRES_DB = "postgres",
            HSQL_DB = "hsqldb";

    //  Get DB profile depending of DB driver in classpath
    public static String[] getActiveProfiles() {
        try {
            Class.forName("org.postgresql.Driver");
            return new String[]{POSTGRES_DB , REPOSITORY_IMPLEMENTATION};
        } catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                return new String[]{Profiles.HSQL_DB, REPOSITORY_IMPLEMENTATION};
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Could not find DB driver");
            }
        }
    }

    public static ConfigurableApplicationContext getSpringContext(String... resourceLocations){

        //http://www.programcreek.com/java-api-examples/index.php?class=org.springframework.context.annotation.AnnotationConfigApplicationContext&method=scan

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles(getActiveProfiles());
        ctx.load(resourceLocations);
        ctx.refresh();

        return ctx;
    }
}
