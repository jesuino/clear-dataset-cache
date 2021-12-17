package org.example;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.dashbuilder.dataprovider.StaticDataSetProvider;
import org.dashbuilder.dataset.def.DataSetDefRegistry;

@Singleton
public class ClearStaticDataSetsService {

    @Inject
    StaticDataSetProvider staticDataSetProvider;

    @Inject
    DataSetDefRegistry registry;

    // clear the static datasets each 10 seconds - be careful with thread safety
    @Schedule(second = "*/10", minute = "*", hour = "*")
    public void clearCache() {
        System.out.println("Clearing all static datasets");
        registry.getDataSetDefs(false)
                .forEach(def -> staticDataSetProvider.removeDataSet(def.getUUID()));
    }

}
