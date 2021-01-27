/**
 * 
 */
package com.mishra.shorturlapi.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author vishal
 *
 */

@ApiModel(description = "Request object for POST method")
public class URLLongRequest {

    @ApiModelProperty(required = true, notes = "Url to convert to short")
    private String longURL;

    @ApiModelProperty(notes = "Expiration datetime of url")
    private Date expiresDate;

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

}
