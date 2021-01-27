/**
 * 
 */
package com.mishra.shorturlapi.controller;

import java.net.URI;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mishra.shorturlapi.dto.URLLongRequest;
import com.mishra.shorturlapi.service.URLService;

import io.swagger.annotations.ApiOperation;

/**
 * @author vishal
 *
 */

@RestController
@RequestMapping("api/v1")
public class URLController {
	
    private final URLService urlService;

    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    /**
     * Method for creating short links
     * @param request : a long URL
     * @return : a short URL
     */
    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @PostMapping("create-short")
    public String convertToShortUrl(@RequestBody URLLongRequest request) {
        return urlService.convertToShortUrl(request);
    }

    /**
     * Method for redirecting to the original URL
     * @param request : a short URL
     * @return : original long URL
     */
    @ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
    @GetMapping(value = "{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        var url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

}
