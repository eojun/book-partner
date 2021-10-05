package com.bookpartner.domain.goods;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "GOOD_IMAGE", schema = "", catalog = "")
public class GoodImage {


    @EmbeddedId // 복합키
    private GoodImagePK goodImagePK;
    @Column(name = "IMG_IMAGE_PATH")
    private String imgImagePath;
    @Column(name = "IMG_IMAGE_ID")
    private String imgImageId;
    @Column(name = "IMG_FILE_NAME")
    private String imgFileName;

}
