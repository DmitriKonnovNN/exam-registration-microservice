package io.dmitrikonnov.customer.external.config;

import feign.Param;

import java.time.OffsetDateTime;

public class OffsetDateTimeToMillisExapnder implements Param.Expander{
    @Override
    public String expand(Object value) {
        if(!OffsetDateTime.class.isAssignableFrom(value.getClass())){
            throw new IllegalArgumentException("Expander doesn't support this class");
        }
        long millis = ((OffsetDateTime)value).toInstant().toEpochMilli();

        return Long.toString(millis);
    }
}
