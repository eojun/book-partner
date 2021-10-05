package com.bookpartner.domain.goods;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class GoodAuthorPK implements Serializable {

    @Column(name = "GAUT_GOODS_ID")
    private String gautGoodsId;
    @Column(name = "GAUT_TYPE")
    private String gautType;
    @Column(name = "GAUT_ID")
    private String gautId;
}
