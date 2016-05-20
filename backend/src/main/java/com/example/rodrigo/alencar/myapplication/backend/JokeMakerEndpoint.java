/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.rodrigo.alencar.myapplication.backend;

import com.example.JokeMaker;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
  name = "jokeMakerApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.myapplication.alencar.rodrigo.example.com",
    ownerName = "backend.myapplication.alencar.rodrigo.example.com",
    packagePath=""
  )
)
public class JokeMakerEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "makeJoke")
    public JokeBean makeJoke() {

        JokeBean response = new JokeBean();
        response.setData(new JokeMaker().make());

        return response;
    }

}
