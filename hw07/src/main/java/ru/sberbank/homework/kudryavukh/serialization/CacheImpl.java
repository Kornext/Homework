package ru.sberbank.homework.kudryavukh.serialization;

import ru.sberbank.homework.common.CachePathProvider;

public class CacheImpl implements CachePathProvider {

    private final String cacheDirectoryPath;

    public CacheImpl(String cacheDirectoryPath) {
        this.cacheDirectoryPath = cacheDirectoryPath;
    }

    @Override
    public String getCacheDirectoryPath() {
        return cacheDirectoryPath;
    }
}
