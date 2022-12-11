package com.example.demo.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImgurResponseData {
    
    private String id;
    private String title;
    private String description;
    private Long datetime;
    private String type;
    private boolean animated;
    private Integer width;
    private Integer height;
    private Integer size;
    private Integer views;
    private Integer bandwidth;
    private String vote;
    private boolean favorite;
    private String nsfw;
    private String section;
    @JsonProperty(value = "account_url")
    private String accountUrl;
    @JsonProperty(value = "account_id")
    private Long accountId;
    @JsonProperty(value = "is_ad")
    private boolean isAd;
    @JsonProperty(value = "in_most_viral")
    private boolean inMostViral;
    private String[] tags;
    @JsonProperty(value = "ad_type")
    private Integer adType;
    @JsonProperty(value = "ad_url")
    private String adUrl;
    @JsonProperty(value = "in_gallery")
    private boolean inGallery;
    @JsonProperty(value = "deletehash")
    private String deleteHash;
    private String name;
    private String link;
    @JsonProperty(value = "has_sound")
    private String hasSound;
    private String mp4;
    private String hls;
}
