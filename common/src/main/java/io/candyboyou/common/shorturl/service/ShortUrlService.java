package io.candyboyou.common.shorturl.service;

public interface ShortUrlService {

    String genShortUrl(String url);

    String expandShortUrl(String shortUrl);
}
