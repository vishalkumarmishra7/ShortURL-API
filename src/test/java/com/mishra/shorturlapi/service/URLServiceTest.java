/**
 * 
 */
package com.mishra.shorturlapi.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mishra.shorturlapi.dto.URLLongRequest;
import com.mishra.shorturlapi.entity.Url;
import com.mishra.shorturlapi.repository.URLRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author vishal
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class URLServiceTest {
	
	@Mock
	URLRepository mockURLRepo;
	
	@Mock
	BaseConversionService mockBaseConversionService;
	
	@InjectMocks
	URLService urlService;
	
    @Test
    public void convertToShortUrlTest() {
        var url = new Url();
        url.setLongURL("https://github.com/vishalkumarmishra7/ShortURL-API");
        url.setCreatedDate(new Date());
        url.setId(5);

        when(mockURLRepo.save( any(Url.class))).thenReturn(url);
        when(mockBaseConversionService.encode(url.getId())).thenReturn("f");

        var urlRequest = new URLLongRequest();
        urlRequest.setLongURL("https://github.com/vishalkumarmishra7/ShortURL-API");

        assertEquals("f", urlService.convertToShortUrl(urlRequest));
    }

    @Test
    public void getOriginalUrlTest() {
        when(mockBaseConversionService.decode("h")).thenReturn((long) 7);

        var url = new Url();
        url.setLongURL("https://github.com/vishalkumarmishra7/ShortURL-API");
        url.setCreatedDate(new Date());
        url.setId(7);

        when(mockURLRepo.findById((long) 7)).thenReturn(java.util.Optional.of(url));
        assertEquals("https://github.com/vishalkumarmishra7/ShortURL-API", urlService.getOriginalUrl("h"));

    }
	

}
