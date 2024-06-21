package rentaltech.electronics.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 819208243L;

    public static final QItem item = new QItem("item");

    public final StringPath details = createString("details");

    public final StringPath item_name = createString("item_name");

    public final NumberPath<Integer> period = createNumber("period", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> serialNum = createNumber("serialNum", Long.class);

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public final EnumPath<rentaltech.electronics.constant.ItemStockStatus> stockStatus = createEnum("stockStatus", rentaltech.electronics.constant.ItemStockStatus.class);

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

