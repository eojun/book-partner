package com.bookpartner.domain.goods;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class GoodImagePK implements Serializable {

    @Column(name = "IMG_GOODS_ID")
    private String imgGoodsId;
    @Column(name = "IMG_SIZE")
    private String imgSize;
    @Column(name = "IMG_KIND")
    private String imgKind;
}
