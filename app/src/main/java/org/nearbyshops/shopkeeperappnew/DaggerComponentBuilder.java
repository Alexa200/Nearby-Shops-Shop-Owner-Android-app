package org.nearbyshops.shopkeeperappnew;

import org.nearbyshops.shopkeeperappnew.ApplicationState.ApplicationState;
import org.nearbyshops.shopkeeperappnew.DaggerComponents.DaggerNetComponent;
import org.nearbyshops.shopkeeperappnew.DaggerComponents.NetComponent;
import org.nearbyshops.shopkeeperappnew.DaggerModules.AppModule;
import org.nearbyshops.shopkeeperappnew.DaggerModules.NetModule;

/**
 * Created by sumeet on 14/5/16.
 */
public class DaggerComponentBuilder {


    private static DaggerComponentBuilder instance;

    private NetComponent mNetComponent;


    private DaggerComponentBuilder() {
    }

    public static DaggerComponentBuilder getInstance()
    {
        if(instance == null)
        {
            instance = new DaggerComponentBuilder();
        }

        return instance;
    }


    public NetComponent getNetComponent() {

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mAppComponent = com.codepath.dagger.components.DaggerNetComponent.create();


        if(mNetComponent == null)
        {
            // Dagger%COMPONENT_NAME%
            mNetComponent = DaggerNetComponent.builder()
                    // list of modules that are part of this component need to be created here too
                    .appModule(new AppModule(ApplicationState
                            .getInstance().getMyApplication())) // This also corresponds to the name of your module: %component_name%Module
                    .netModule(new NetModule())
                    .build();
        }

        return mNetComponent;
    }

}
