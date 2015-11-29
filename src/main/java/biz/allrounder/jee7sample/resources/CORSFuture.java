package biz.allrounder.jee7sample.resources;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

@Provider
public class CORSFuture implements Feature {

	@Override
	public boolean configure(FeatureContext context) {
		CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("http://localhost:9001");
        context.register(corsFilter);
        return true;
	}

}
