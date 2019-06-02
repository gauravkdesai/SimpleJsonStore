package com.gaurav.util;

/**
 * Created by gauravdesai on 02/06/19.
 */
public class Parameters {
    private static final String DATABASE_NAME = "GauravJSonStore";
    private static final String DB_COLLECTION = "documents";
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 100;
    private static final long KEEP_ALIVE_TIME = 50000L;
    private static final int CAPACITY = 100;
    private static final String CONTEXT_THREADPOOL_EXECUTOR = "context.threadpool.executor";

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static String getDbCollectionName() {
        return DB_COLLECTION;
    }

    public static int getCorePoolSize() {
        return CORE_POOL_SIZE;
    }

    public static int getMaximumPoolSize() {
        return MAXIMUM_POOL_SIZE;
    }

    public static long getKeepAliveTime() {
        return KEEP_ALIVE_TIME;
    }

    public static int getThreadpoolCapacity() {
        return CAPACITY;
    }

    public static String getContextThreadpoolExecutorName() {
        return CONTEXT_THREADPOOL_EXECUTOR;
    }
}
