package com.example.demo.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImgurResponse {
//    "status":200,"success":true,"data":{"id":"oYM4NDD","deletehash":"mRb58uMA15sglVJ","account_id":null,"account_url":null,
//            "ad_type":null,"ad_url":null,"title":"testName","description":null,"name":"","type":"image/png","width":334,
//            "height":124,"size":42453,"views":0,"section":null,"vote":null,"bandwidth":0,"animated":false,"favorite":false,
//            "in_gallery":false,"in_most_viral":false,"has_sound":false,"is_ad":false,"nsfw":null,"link":"https://i.imgur.com/oYM4NDD.png",
//            "tags":[],"datetime":1670752883,"mp4":"","hls":""
    private Integer status;
    private boolean success;
    private ImgurResponseData data;
}
