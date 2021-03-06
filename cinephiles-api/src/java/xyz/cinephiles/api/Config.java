/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package xyz.cinephiles.api;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author charliearlie
 */
@javax.ws.rs.ApplicationPath("/api")
public class Config extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(xyz.cinephiles.api.MediaResource.class);
        resources.add(xyz.cinephiles.api.UsersResource.class);
    }
    
}
