/**
 * 
 */
package com.mishra.shorturlapi.service;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.mishra.shorturlapi.dto.URLLongRequest;
import com.mishra.shorturlapi.entity.Url;
import com.mishra.shorturlapi.repository.URLRepository;

/**
 * @author vishal
 *
 */

@Service
public class URLService {
	
    private final URLRepository urlRepository;
    private final BaseConversionService conversionService;

    public URLService(URLRepository urlRepository, BaseConversionService baseConversionService) {
        this.urlRepository = urlRepository;
		this.conversionService = baseConversionService;
    }

    /**
     * Used by the POST method from the controller. 
     * create a new record in the database and get an id
     * @param request : a long URL 
     * @return : base 62 short link
     */
    public String convertToShortUrl(URLLongRequest request) {
        var url = new Url();
        url.setLongURL(request.getLongURL());
        url.setExpiresDate(request.getExpiresDate());
        url.setCreatedDate(new Date());
        var entity = urlRepository.save(url);

        return conversionService.encode(entity.getId());
    }

    /** 
     * Used by the GET method from the controller.
     * converts a string to base 10 to an id.
     * gets a record from the database by id, 
     * throws an exception if it does not exist
     * @param shortUrl : original long URL
     * @return
     */
    public String getOriginalUrl(String shortUrl) {
        var id = conversionService.decode(shortUrl);
        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        if (entity.getExpiresDate() != null && entity.getExpiresDate().before(new Date())){
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        }

        return entity.getLongURL();
    }

}
