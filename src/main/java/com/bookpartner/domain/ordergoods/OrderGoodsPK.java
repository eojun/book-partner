package com.bookpartner.domain.ordergoods;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderGoodsPK implements Serializable {

    @Column(name = "ORG_ORDER_ID")
    private String orgOrderId;

    @Column(name = "ORG_GOODS_ID")
    private String orgGoodsId;
}
