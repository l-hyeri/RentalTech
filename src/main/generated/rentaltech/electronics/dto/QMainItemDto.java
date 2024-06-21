package rentaltech.electronics.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * rentaltech.electronics.dto.QMainItemDto is a Querydsl Projection type for MainItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainItemDto extends ConstructorExpression<MainItemDto> {

    private static final long serialVersionUID = -1079003271L;

    public QMainItemDto(com.querydsl.core.types.Expression<Long> serialNum, com.querydsl.core.types.Expression<String> item_name, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<String> imgUrl, com.querydsl.core.types.Expression<rentaltech.electronics.constant.ItemStockStatus> stockStatus) {
        super(MainItemDto.class, new Class<?>[]{long.class, String.class, int.class, String.class, rentaltech.electronics.constant.ItemStockStatus.class}, serialNum, item_name, price, imgUrl, stockStatus);
    }

}

