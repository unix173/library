package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.security.auth.login.AppConfigurationEntry;
import javax.validation.Valid;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by ivsi on 1/25/2016.
 */
public class ZLibConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDatabase() {
        return database;
    }

    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }

}
