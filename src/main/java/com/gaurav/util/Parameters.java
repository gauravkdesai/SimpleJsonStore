package com.gaurav.util;

import java.util.ResourceBundle;

/**
 * Created by gauravdesai on 02/06/19.
 */
public class Parameters {


    enum PropertyName {
        DATABASE_CONNECTION, DATABASE_NAME, DB_COLLECTION, CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, CAPACITY, CONTEXT_THREADPOOL_EXECUTOR;
    }

    private static final ResourceBundle rb = ResourceBundle.getBundle("dev");

    public static String getDatabaseConnectionString() {
        return getStringProperty(PropertyName.DATABASE_CONNECTION);
    }

    public static String getDatabaseName() {
        return getStringProperty(PropertyName.DATABASE_NAME);
    }

    public static String getDbCollectionName() {
        return getStringProperty(PropertyName.DB_COLLECTION);
    }

    public static int getCorePoolSize() {
        return getIntProperty(PropertyName.CORE_POOL_SIZE);
    }

    public static int getMaximumPoolSize() {
        return getIntProperty(PropertyName.MAXIMUM_POOL_SIZE);
    }

    public static long getKeepAliveTime() {
        return getLongProperty(PropertyName.KEEP_ALIVE_TIME);
    }

    public static int getThreadpoolCapacity() {
        return getIntProperty(PropertyName.CAPACITY);
    }

    public static String getContextThreadpoolExecutorName() {
        return getStringProperty(PropertyName.CONTEXT_THREADPOOL_EXECUTOR);
    }

    public static String getStringProperty(PropertyName propertyName){
        return rb.getString(propertyName.name());
    }

    public static Integer getIntProperty(PropertyName propertyName){
        return Integer.parseInt(rb.getString(propertyName.name()));
    }

    public static Long getLongProperty(PropertyName propertyName){
        return Long.parseLong(rb.getString(propertyName.name()));
    }
}
